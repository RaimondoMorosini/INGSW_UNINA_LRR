<template>
    <CaricamentoAste v-if="caricamentoAste" />

    <div v-else class="sezione-centrale">
        <CaratteristicheProdotto :propCategoria="props.propCategoriaCercata" />

        <div class="card mx-16">
            <DataView :value="products">
                <template #list="slotProps">
                    <div class="grid-nogutter grid">
                        <div v-for="(item, index) in slotProps.items" :key="index" class="col-12">
                            <div
                                class="flex-column sm:align-items-center flex gap-3 bg-zinc-100 p-4 sm:flex-row"
                                :class="{ 'border-top-1 surface-border': index !== 0 }"
                            >
                                <div class="md:w-10rem relative">
                                    <img
                                        class="border-round mx-auto block w-full xl:block"
                                        :src="`${item.immagini[0]}`"
                                        :alt="item.nome"
                                    />
                                    <Tag
                                        :value="item.tipoAsta"
                                        :severity="danger"
                                        class="absolute"
                                        style="left: 4px; top: 4px"
                                    ></Tag>
                                </div>
                                <div
                                    class="flex-column justify-content-between md:align-items-center flex flex-1 gap-4 md:flex-row"
                                >
                                    <div
                                        class="md:flex-column justify-content-between align-items-start flex flex-row gap-2"
                                    >
                                        <div>
                                            <span class="text-secondary text-sm font-medium">{{
                                                item.categoria
                                            }}</span>
                                            <div class="text-900 mt-2 text-lg font-medium">
                                                {{ item.nome }}
                                            </div>
                                            <div>Venditore: {{ item.emailUtenteCreatore }}</div>
                                            <span class="text-900 text-xl font-semibold"
                                                >BASE ASTA: EURO{{ item.baseAsta }}</span
                                            >
                                        </div>
                                    </div>
                                    <div class="flex-column md:align-items-end flex gap-5">
                                        <div
                                            class="contenitore-bottone flex flex-row-reverse gap-2 md:flex-row"
                                        >
                                            <Button
                                                @click="apriUnNuovoTab(item.idAsta)"
                                                icon="pi pi-shopping-cart"
                                                label="Partecipa all'asta"
                                                :disabled="item.inventoryStatus === 'OUTOFSTOCK'"
                                                class="white-space-nowrap flex-auto md:flex-initial"
                                            >
                                            </Button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </template>
            </DataView>
        </div>
    </div>

    <Paginator :rows="5" :totalRecords="props.propNumeroAste" @page="onPage($event)"></Paginator>
</template>

<script setup>
import DataView from 'primevue/dataview';
import Tag from 'primevue/tag';
import Button from 'primevue/button';
import Paginator from 'primevue/paginator';
import 'primeflex/primeflex.css';
import 'primevue/resources/themes/aura-light-green/theme.css';
import 'primeicons/primeicons.css';

import { ref, watch } from 'vue';
import CaricamentoAste from './CaricamentoAste.vue';
import CaratteristicheProdotto from '../caratteristicaProdotti/CaratteristicheProdotto.vue';

import axios from 'axios';

const props = defineProps([
    'propAste',
    'propLoading',
    'propNumeroAste',
    'propCategoriaCercata',
    'propProdottoCercato',
    'propTipoAsteCercate',
]);

const products = ref();
products.value = props.propAste;

const caricamentoAste = ref(true);

watch(
    () => props.propAste,
    (newValue) => {
        // Aggiorna products.values quando props.propAste cambia
        products.value = newValue;
    }
);

watch(
    () => props.propLoading,
    (newValue) => {
        // Aggiorna caricamentoAste quando props.props.propLaoding cambia
        caricamentoAste.value = newValue;
    }
);

const onPage = (event) => {
    setAstePerPagina(event.page + 1);
};

const setAstePerPagina = async (paginaSelezionata) => {
    caricamentoAste.value = true; // Imposta lo stato di caricamento su true prima di effettuare la richiesta

    //impostazione dei parametri del body da amndare nella richiesta axios
    const parametriBody = {
        pagina: paginaSelezionata,
        elementiPerPagina: 5,
        categoria: props.propCategoriaCercata,
        nomeProdotto: props.propProdottoCercato,
        tipoAsta: props.propTipoAsteCercate,
    };

    //tentativo di richesta axsios
    try {
        const response = await axios.post(
            'http://localhost:8081/public/asta/getAllAste',
            parametriBody
        );

        products.value = response.data; //Aggiorna i valori dei prodotti in asta
    } catch (error) {
        console.error('Si è verificato un errore:', error);
    } finally {
        caricamentoAste.value = false; // Imposta lo stato di caricamento su false dopo che la richiesta è completata (in modo che carica la componente delle aste cercate)
    }
};

const apriUnNuovoTab = (idAsta) => {
    const url = `http://localhost:8080/Asta/${idAsta}`;
    window.open(url, '_blank');
};
</script>

<style scoped>
.sezione-centrale {
    display: flex;
    flex-direction: row;
}

.sezione-caratteristiche {
    display: flex;
    flex-direction: column;
}

.contenitore-bottone {
    background-color: green;
    border: 2px solid #c5c5c58a;
    border-radius: 1rem;
    padding: 1rem;
    color: white;
}
</style>

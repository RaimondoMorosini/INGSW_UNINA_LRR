<template>
    <div class="card p-fluid flex flex-wrap gap-3">
        <div class="sezione-superiore w-full">
            <BarraRicercaPerOrdine
                @ordineSelezionato="setCampiOrdinamento($event)"
                @prezzoMinSelezionato="setPrezzoMin($event)"
                @prezzoMaxSelezionato="setPrezzoMax($event)"
            />
        </div>


    <div class="sezione-centrale">

        <!--SEZIONE FILTRI CARETTERISTICHE DEL PRODTTO-->
        <CaratteristicheProdotto v-if="props.propRicaricaComponenteCaratteristiche" :propCategoria="props.propCategoriaCercata"
            @caratteristicheSelezionate="setCaratteristiche($event)" />
        <!--SE CAMBIA CATEGORIA CARICA UN ATTIMO UN TEMPLATE VUOTO PER I FILTRI CARATTERISTICHE IN MODO DA RICARICARE LA COMPONENTE DELLE CARATTERISTICHE QUANDO DIVENTA DI NUOVO TRUE props.propRicaricaComponenteCaratteristiche-->
        <div v-else></div>
            
        <!--TAMPLATE CARICAMENTO ASTE-->
        <CaricamentoAste v-if="props.propLoading" />

        <!--CONTENITORE LISTA ASTE-->
        <div v-else class="card mx-16">
            <DataView :value="products">
                <template #list="slotProps">
                    <div class="grid-nogutter grid">
                        <div v-for="(item, index) in slotProps.items" :key="index" class="col-12 mt-4">
                            <div class="flex-column sm:align-items-center flex gap-3 bg-zinc-100 p-4 sm:flex-row"
                                :class="{ 'border-top-1 surface-border': index !== 0 }">
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
                                                class="contenitore-bottone flex flex-row-reverse gap-2 bg-primario-100 md:flex-row"
                                            >
                                                <Button
                                                    @click="apriUnNuovoTab(item.idAsta)"
                                                    icon="pi pi-shopping-cart"
                                                    label="Partecipa all'asta"
                                                    :disabled="
                                                        item.inventoryStatus === 'OUTOFSTOCK'
                                                    "
                                                    class="white-space-nowrap flex-auto md:flex-initial"
                                                >
                                                </Button>
                                            </div>
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
    </div>

    <!--COMPONENTE DELLE PAGINE-->
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

import { ref, watch, defineEmits } from 'vue';
import CaricamentoAste from './CaricamentoAste.vue';
import CaratteristicheProdotto from '../caratteristicaProdotti/CaratteristicheProdotto.vue';
import BarraRicercaPerOrdine from '../vetrinaAndHomepage/barraRicercaPerOrdine.vue';

const emit = defineEmits([
    'caratteristicheSelezionate',
    'numeroDiPaginaSelezionato',
    'ordineSelezionato',
    'prezzoMinSelezionato',
    'prezzoMaxSelezionato',
]);

const props = defineProps([
    'propAste',
    'propLoading',
    'propNumeroAste',
    'propCategoriaCercata',
    'propRicaricaComponenteCaratteristiche',
]);

const products = ref();
products.value = props.propAste;

watch(
    () => props.propAste,
    (newValue) => {
        // Aggiorna products.values quando props.propAste cambia
        products.value = newValue;
    }
);

const onPage = (event) => {
    emit('numeroDiPaginaSelezionato', event.page + 1);
};

const setCaratteristiche = (filtro) => {
    emit('caratteristicheSelezionate', filtro);
};

const setCampiOrdinamento = (ordinamentoSelezionato) => {
    emit('ordineSelezionato', ordinamentoSelezionato);
};

const setPrezzoMin = (prezzoMin) => {
    emit('prezzoMinSelezionato', prezzoMin);
};

const setPrezzoMax = (prezzoMax) => {
    emit('prezzoMaxSelezionato', prezzoMax);
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
    border: 2px solid #c5c5c58a;
    border-radius: 1rem;
    padding: 1rem;
    color: white;
}
</style>

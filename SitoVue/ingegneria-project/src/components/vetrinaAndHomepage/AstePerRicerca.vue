<template>
    <div class="card justify-between gap-3">
        <div class="sezione-superiore w-full">
            <barraDeiFIltriPrincipale
                @ordineSelezionato="setCampiOrdinamento"
                @prezzoMinSelezionato="setPrezzoMin"
                @prezzoMaxSelezionato="setPrezzoMax"
            />
        </div>

        <div class="sezione-centrale">
            <CaratteristicheProdotto
                v-if="props.propRicaricaComponenteCaratteristiche"
                :propCategoria="props.propCategoriaCercata"
                :propCaratteristicheselezionate="props.propCaratteristicheselezionate"
                @caratteristicheSelezionate="setCaratteristiche"
            />
            <div v-else></div>

            <CaricamentoAste v-if="props.propLoading" />

            <div v-else class="card mx-16 w-auto">
                <DataView :value="products">
                    <template #list="slotProps">
                        <div class="grid-nogutter grid">
                            <ProductItem
                                v-for="(item, index) in slotProps.items"
                                :key="index"
                                :item="item"
                                :index="index"
                                class="col-12 contenitore-articolo mt-4"
                            ></ProductItem>
                        </div>
                    </template>
                </DataView>
            </div>
        </div>
    </div>

    <Paginator :rows="5" :totalRecords="props.propNumeroAste" @page="onPage"></Paginator>
</template>

<script setup>
import DataView from 'primevue/dataview';
import Paginator from 'primevue/paginator';
import 'primeflex/primeflex.css';
import 'primeicons/primeicons.css';

import { ref, watch, defineEmits } from 'vue';
import CaricamentoAste from './CaricamentoAste.vue';
import CaratteristicheProdotto from './caratteristicaProdotti/CaratteristicheProdotto.vue';
import barraDeiFIltriPrincipale from './barraDeiFIltriPrincipale.vue';
import ProductItem from './singolo-prodotto/ProductItem.vue';
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
    'propCaratteristicheselezionate',
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

.contenitore-articolo {
    cursor: pointer;
    background-color: rgb(226 232 240 / 0.2);
}

.titolo-articolo:hover {
    font-size: 20px !important;
    text-decoration: underline;
}

.contenitore-bottone {
    border: 2px solid rgb(226 232 240 / 0.2);
    border-radius: 1rem;
    padding: 1rem;
    color: white;
}
</style>

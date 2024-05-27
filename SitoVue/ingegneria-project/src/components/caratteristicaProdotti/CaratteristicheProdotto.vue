<template>
    <div class="contenitore-caratteristiche">
        <h1 class="text-2xl font-bold">Filtra:</h1>
        <div
            class="contenitore-caratteristica"
            v-for="(caratteristica, index) in caratteristicheRelativeAllaCategoria"
        >
            <div class="linea-separatoria"></div>
            <opzioniSelezionabili :propOpzioni="caratteristica.opzioniSelezionabili"
                :propNomeCaratteristica="caratteristica.nomeCaratteristica" :key="index"
                @recuperoValoriSelezionati="aggiornaOpzioniSelezionate(caratteristica.id, $event)" />
            <div class="linea-separatoria"></div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted, defineEmits } from 'vue';

import { getRest } from '../../scripts/RestUtils';

import opzioniSelezionabili from '../caratteristicaProdotti/opzioniSelezionbili.vue';

const props = defineProps(['propCategoria']);
const emit = defineEmits(['caratteristicheSelezionate']);

const caratteristicheRelativeAllaCategoria = ref([]);

const getCaratteristiche = async (categoria) => {
    try {
        const response = await getRest(
            'public/getCaratteristicheDaCategoria?categoria=' + categoria
        ); //Get delle caratteristihce relative alla categoria cercata

        caratteristicheRelativeAllaCategoria.value = response.data;
    } catch (error) {
        console.log('errore richiesta  caratteristiche');
    }
};

const caratteristicheSelezionateDTO = ref(new Array(0));


const aggiornaOpzioniSelezionate = (idCaratteristica, valoriSelezionati) => {

    if (valoriSelezionati.length === 0) {

        const index = caratteristicheSelezionateDTO.value.findIndex(obj => obj.idCaratteristica === idCaratteristica);

        caratteristicheSelezionateDTO.value.splice(index, 1);

    } else {

        const caratteristica = {

            idCaratteristica: idCaratteristica,

            valoriSelezionati: valoriSelezionati
        }

        const index = caratteristicheSelezionateDTO.value.findIndex(obj => obj.idCaratteristica === idCaratteristica);

        if (index >= 0) {

            caratteristicheSelezionateDTO.value[index] = caratteristica;

        } else {

            caratteristicheSelezionateDTO.value.push(caratteristica);
        }
        
    }

    emit('caratteristicheSelezionate', caratteristicheSelezionateDTO.value);

}


onMounted(() => {
    getCaratteristiche(props.propCategoria);
});
</script>

<style scoped>

.contenitore-caratteristiche {
    width: 25%;
    display: flex;
    flex-direction: column;
    padding: 1rem;
}

.contenitore-caratteristica {
    width: 100%;
    display: flex;
    flex-direction: column;
}

.linea-separatoria {
    width: 98%;
    height: 0.5px;
    background-color: rgb(214, 206, 206);
    margin-top: 0.5rem;
    margin-bottom: 0.5rem;
}
</style>

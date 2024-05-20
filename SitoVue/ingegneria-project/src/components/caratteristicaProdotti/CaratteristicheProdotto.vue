<template>
    <div class="contenitore-caratteristiche">
        <h1 class="font-bold text-2xl">Filtra:</h1>
        <div class="contenitore-caratteristica" v-for="(caratteristica,index) in caratteristicheRelativeAllaCategoria">
            <div class="linea-separatoria"></div>
            <opzioniSelezionabili :propOpzioni="caratteristica.opzioniSelezionabili"
                :propNomeCaratteristica="caratteristica.nomeCaratteristica" 
                :key="index" @recuperoValoriSelezionati="aggiornaOpzioniSelezionate(index, caratteristica.nomeCaratteristica, $event)" />
            <div class="linea-separatoria"></div>
        </div>

        <button @click="filtra">Filtra ricerca</button>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';

import { getRest } from '../../scripts/RestUtils';

import opzioniSelezionabili from '../caratteristicaProdotti/opzioniSelezionbili.vue'

const props = defineProps(['propCategoria']);

const caratteristicheRelativeAllaCategoria = ref([]);

const getCaratteristiche = async (categoria) => {

    try {

        const response = await getRest('public/getCaratteristicheDaCategoria?categoria=' + categoria); //Get delle caratteristihce relative alla categoria cercata

        caratteristicheRelativeAllaCategoria.value = response.data;

    } catch (error) {

        console.log("errore richiesta  caratteristiche");
    }
}

const childValues = ref(new Array(caratteristicheRelativeAllaCategoria.length).fill(''));


const aggiornaOpzioniSelezionate = (index, nomeCaratteristica, valoriSelezionati) => {

    childValues.value[index] = {

        caratteristica : nomeCaratteristica,

        opzioni : valoriSelezionati
    }
}

const filtra = () => {

    childValues.value.forEach(element => {

        console.log(element);
        
    });

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

.contenitore-caratteristica{

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

<template>
    <div class="contenitore-caratteristiche">
        <div class="contenitore-caratteristica" v-for="caratteristica in caratteristicheRelativeAllaCategoria">
            <div class="linea-separatoria"></div>
            <opzioniSelezionabili :propOpzioni="caratteristica.opzioniSelezionabili"
                :propNomeCaratteristica="caratteristica.nomeCaratteristica" />
            <div class="linea-separatoria"></div>
        </div>
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

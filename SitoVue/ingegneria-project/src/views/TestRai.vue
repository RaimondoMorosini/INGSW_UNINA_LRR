<template>
    <div
        class="contenitore-caratteristiche"
        v-for="caratteristica in caratteristicheRelativeAllaCategoria"
    >
        <div class="linea-separatoria"></div>
        <opzioniSelezionabili
            :propOpzioni="caratteristica.opzioniSelezionabili"
            :propNomeCaratteristica="caratteristica.nomeCaratteristica"
        />
        <div class="linea-separatoria"></div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';

import { getRest } from '../scripts/RestUtils';

import opzioniSelezionabili from '../components/caratteristicaProdotti/opzioniSelezionbili.vue';

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

onMounted(() => {
    getCaratteristiche('Telefonia');
});
</script>

<style scoped>
.contenitore-caratteristiche {
    width: 20%;
    display: flex;
    flex-direction: column;
    padding: 0.5rem;
}

.linea-separatoria {
    width: 98%;
    height: 1px;
    background-color: black;
    margin-top: 0.5rem;
    margin-bottom: 0.5rem;
}
</style>

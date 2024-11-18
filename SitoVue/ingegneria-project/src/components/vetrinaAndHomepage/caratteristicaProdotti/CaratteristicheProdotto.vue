<template>
    <div class="contenitore-caratteristiche">
        <h1 v-if="caratteristicheRelativeAllaCategoria.length > 0" class="text-2xl font-bold">
            Filtra:
        </h1>
        <h1 v-else class="text-base font-normal">
            Non sono disponibili filtri per questa categoria
        </h1>
        <div
            class="contenitore-caratteristica"
            v-for="(caratteristica, index) in caratteristicheRelativeAllaCategoria"
        >
            <div class="linea-separatoria"></div>
            <opzioniSelezionabili
                :propOpzioni="caratteristica.opzioniSelezionabili"
                :propNomeCaratteristica="caratteristica.nomeCaratteristica"
                :propCaratteristicheselezionate="propCaratteristicheselezionate"
                :key="index"
                @recuperoValoriSelezionati="aggiornaOpzioniSelezionate(caratteristica.id, $event)"
            />
            <div class="linea-separatoria"></div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted, defineEmits } from 'vue';
import {
    getCaratteristiche,
    getArrayOpzionePerChiamataAxsios,
    getCaratteristicheSelezionateDTO,
} from '../../../service/carateristicheService.js';

import opzioniSelezionabili from './opzioniSelezionbili.vue';

const props = defineProps(['propCategoria', 'propCaratteristicheselezionate']);
const emit = defineEmits(['caratteristicheSelezionate']);

const caratteristicheRelativeAllaCategoria = ref([]);

const caratteristicheSelezionateDTO = ref(new Array(0));

const aggiornaOpzioniSelezionate = (idCaratteristica, valoriSelezionati) => {
    const valoriSelezionatiConvertito = getArrayOpzionePerChiamataAxsios(valoriSelezionati);

    caratteristicheSelezionateDTO.value = getCaratteristicheSelezionateDTO(
        caratteristicheSelezionateDTO.value,
        idCaratteristica,
        valoriSelezionatiConvertito
    );

    emit('caratteristicheSelezionate', caratteristicheSelezionateDTO.value);
};

onMounted(() => {
    getCaratteristiche(props.propCategoria)
        .then((response) => {
            caratteristicheRelativeAllaCategoria.value = response;
        })
        .catch((error) => {
            console.log(error);
        });
});
</script>

<style scoped>
.contenitore-caratteristiche {
    width: auto;
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

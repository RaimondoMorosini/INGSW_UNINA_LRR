<template>
    <div>
        <form @submit.prevent="gestioneInvio">
            <div class="mx-2 my-2 px-2 py-2 ring-2 ring-black">
                <InputField
                    v-for="caratteristica in caratteristicheRelativeAllaCategoria"
                    :key="caratteristica.id"
                    :label="caratteristica.nomeCaratteristica"
                    :options="caratteristica.opzioniSelezionabili"
                    v-model="arrayValoriSelezionati[caratteristica.id]"
                >
                </InputField>
                arrayValoriSelezionati {{ arrayValoriSelezionati }} <br />
                <hr class="my-4" />
            </div>

            <div class="areaBottoni flex justify-around gap-5">
                <button
                    class="previous bottone w-[30%] px-5"
                    @click="goToPreviousForm"
                    type="button"
                >
                    Precedente
                </button>
                <button class="bottone w-[30%] px-5" type="submit">Successivo</button>
            </div>
        </form>
    </div>
</template>

<script setup>
import { defineEmits, onBeforeMount, onUnmounted, ref } from 'vue';
import { getCaratteristiche } from '../../service/carateristicheService.js';
import { useAstaStore } from '../../stores/astaStore.js';
import InputField from './InputField.vue';

const emit = defineEmits('increase-page','decrease-page');

const gestioneInvio = () => {
    emit('increase-page');
};

const goToPreviousForm = () => {
    emit('decrease-page');
};

const arrayValoriSelezionati = ref({});

const caratteristicheRelativeAllaCategoria = ref([]);
//IMPORTANTE: se in futuro le categorie diverranno multiple, sostituire questo con Object.keys(storeInstance.asta.categoria)
const categoriaSelezionata = function (obj) {
    var keys = '';
    for (var key in obj) {
        keys = key;
    }
    return keys;
};

onBeforeMount(() => {
    const categorieSelezionata = categoriaSelezionata(useAstaStore().asta.categoria);
    console.log('categoria selezionata:', categorieSelezionata);
    getCaratteristiche(categorieSelezionata)
        .then((response) => {
            caratteristicheRelativeAllaCategoria.value = response;
        })
        .catch((error) => {
            console.log(error);
        });
    arrayValoriSelezionati.value = useAstaStore().asta.caratteristiche;
});

onUnmounted(() => {
    useAstaStore().asta.caratteristiche = arrayValoriSelezionati.value;
});
</script>

<style scoped>
.bottone {
    background-color: #cc85f5;
    margin: 10px;
    padding: 10px 20px;
    color: white;
    border-radius: 5px;
    font-size: 1.1rem;
    font-weight: bold;
    width: 50%;
}
.bottone:hover {
    background-color: #7c3aed;
}
</style>

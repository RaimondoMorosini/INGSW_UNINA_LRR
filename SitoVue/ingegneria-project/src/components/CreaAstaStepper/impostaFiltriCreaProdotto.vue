<template>
    <div>
        <form @submit.prevent="gestioneInvio">
            <div class="mx-2 my-2 bg-slate-200/20 px-2 py-2">
                
                <InputField
                    v-for="caratteristica in caratteristicheRelativeAllaCategoria"
                    :key="caratteristica.id"
                    :label="caratteristica.nomeCaratteristica"
                    :options="caratteristica.opzioniSelezionabili"
                    v-model="arrayValoriSelezionati[caratteristica.id]"
                >
                </InputField>

                <div v-if=production>
                    arrayValoriSelezionati {{ arrayValoriSelezionati }} 
                </div>
                <hr class="my-4" />
            </div>

            <div class="areaBottoni flex justify-around gap-5">
                <Button class="w-[45%]" size="large" @click="goToPreviousForm"
                    ><span class="font-bold"
                        ><i class="pi pi-arrow-left"></i> Precedente</span
                    ></Button
                >
                <Button class="w-[45%]" size="large" @click="gestioneInvio"
                    ><span class="font-bold">Successivo <i class="pi pi-arrow-right"></i></span
                ></Button>
            </div>
        </form>
    </div>
</template>

<script setup>
import Button from 'primevue/button';
import { defineEmits, onBeforeMount, onUnmounted, ref } from 'vue';
import { getCaratteristiche } from '../../service/carateristicheService.js';
import { useAstaStore } from '../../stores/astaStore.js';
import InputField from './InputField.vue';

const emit = defineEmits('increase-page', 'decrease-page');
const production = true;

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

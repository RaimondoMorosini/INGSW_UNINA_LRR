<template>

    <div class="flex flex-row justify-end w-full h-auto p-2 bg-zinc-100 border-solid rounded-2xl">

        <div class="flex flex-col mx-9">
            <label for="currency-germany" class="font-bold block mb-2"> Ordina per </label>
            <Dropdown @change="setCampiOrdinamento" v-model="opzioneSelezionata" :options="opzioniDiOrdinazione" optionLabel="name" placeholder="Ordina per" class="font-bold dimensione mx-9" />
        </div>



        <div class="flex flex-col mx-9">
            <label for="currency-germany" class="font-bold block mb-2"> Prezzo minimo </label>
            <InputNumber @input="setPrezzoMin($event)" v-model="prezzoMin" inputId="horizontal-buttons" showButtons buttonLayout="horizontal" :step="0.25" mode="currency" currency="EUR" :min="0">
                <template #incrementbuttonicon>
                    <span class="pi pi-plus" />
                </template>
                <template #decrementbuttonicon>
                    <span class="pi pi-minus" />
                </template>
            </InputNumber>
        </div>



        <div class="flex flex-col mx-9">
            <label for="currency-germany" class="font-bold block mb-2"> Prezzo massimo </label>
            <InputNumber @input="setPrezzoMax($event)"v-model="prezzoMax" inputId="horizontal-buttons" showButtons buttonLayout="horizontal" :step="0.25" mode="currency" currency="EUR" :min="0">
                <template #incrementbuttonicon>
                    <span class="pi pi-plus" />
                </template>
                <template #decrementbuttonicon>
                    <span class="pi pi-minus" />
                </template>
            </InputNumber>
        </div>
    </div>

</template>

<script setup>
import { ref, defineEmits } from "vue";
import Dropdown from 'primevue/dropdown';
import InputNumber from 'primevue/inputnumber';

const emit = defineEmits(['ordineSelezionato', 'prezzoMinSelezionato', 'prezzoMaxSelezionato']);

const opzioneSelezionata = ref(null);
const opzioniDiOrdinazione = ref([
    { name: 'Prezzo più basso' },
    { name: 'Prezzo più alto' },
    { name: 'Nome discedente' },
    { name: 'Nome crescente' },
    { name: 'Più recenti' }
]);

const prezzoMin = ref(0);
const prezzoMax = ref(null);

const setCampiOrdinamento = () => {

    emit('ordineSelezionato', opzioneSelezionata.value)
}

const setPrezzoMin = (valore) => {

    prezzoMin.value = valore.value
    emit('prezzoMinSelezionato',prezzoMin.value);
}

const setPrezzoMax = (valore) => {

    prezzoMax.value = valore.value;
    emit('prezzoMaxSelezionato', prezzoMax.value)
}

</script>

<style scoped>
.dimensione {

    width: 10 rem;
    height: 4 rem;
}
</style>

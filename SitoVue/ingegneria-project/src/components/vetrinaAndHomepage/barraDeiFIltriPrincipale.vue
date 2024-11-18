<template>
    <div class="flex h-auto flex-row justify-center rounded bg-slate-200/20 p-2">
        <div class="flex flex-col">
            <label for="currency-germany" class="mb-2 block font-bold"> Ordina per </label>
            <Dropdown
                @change="setCampiOrdinamento"
                v-model="opzioneSelezionata"
                :options="opzioniDiOrdinazione"
                optionLabel="name"
                placeholder="Ordina per"
                class="dimensione font-bold"
            />
        </div>

        <div class="flex flex-col">
            <label for="currency-germany" class="mb-2 block font-bold"> Prezzo minimo </label>
            <InputNumber
                @input="setPrezzoMin($event)"
                v-model="prezzoMin"
                inputId="horizontal-buttons"
                showButtons
                buttonLayout="horizontal"
                :step="0.25"
                mode="currency"
                currency="EUR"
                :min="0"
            >
                <template #incrementbuttonicon>
                    <span class="pi pi-plus" />
                </template>
                <template #decrementbuttonicon>
                    <span class="pi pi-minus" />
                </template>
            </InputNumber>
        </div>

        <div class="flex flex-col">
            <label for="currency-germany" class="mb-2 block font-bold"> Prezzo massimo </label>
            <InputNumber
                @input="setPrezzoMax($event)"
                v-model="prezzoMax"
                inputId="horizontal-buttons"
                showButtons
                buttonLayout="horizontal"
                :step="0.25"
                mode="currency"
                currency="EUR"
                :min="0"
            >
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
import { ref, defineEmits } from 'vue';
import Dropdown from 'primevue/dropdown';
import InputNumber from 'primevue/inputnumber';

const emit = defineEmits(['ordineSelezionato', 'prezzoMinSelezionato', 'prezzoMaxSelezionato']);

const opzioneSelezionata = ref(null);
const opzioniDiOrdinazione = ref([
    { name: 'Prezzo più basso' },
    { name: 'Prezzo più alto' },
    { name: 'Nome discedente' },
    { name: 'Nome crescente' },
    { name: 'Più recenti' },
]);

const prezzoMin = ref(0);
const prezzoMax = ref(null);

const setCampiOrdinamento = () => {
    emit('ordineSelezionato', opzioneSelezionata.value);
};

const setPrezzoMin = (valore) => {
    prezzoMin.value = valore.value;
    emit('prezzoMinSelezionato', prezzoMin.value);
};

const setPrezzoMax = (valore) => {
    prezzoMax.value = valore.value;
    emit('prezzoMaxSelezionato', prezzoMax.value);
};
</script>

<style scoped>
.dimensione {
    width: 10 rem;
    height: 4 rem;
}
</style>

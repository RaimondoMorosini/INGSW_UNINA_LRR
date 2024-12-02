<template>
    <form @submit.prevent="gestioneInvio">
        <div class="flex flex-col items-center justify-center">
            <h2>Seleziona tipo asta</h2>
            <div class="flex flex-col">
                <div class="flex items-center gap-2">
                    <RadioButton v-model="tipoAsta" inputId="Inglese" value="asta_inglese"/>
                    <label for="Inglese" class="ml-2">Asta Inglese</label>
                </div>
                <div class="flex items-center gap-2">
                    <RadioButton v-model="tipoAsta" inputId="Inversa" value="asta_inversa"/>
                    <label for="Inversa" class="ml-2">Asta Inversa</label>
                </div>
                <div class="flex items-center gap-2">
                    <RadioButton v-model="tipoAsta" inputId="Silenziosa" value="asta_silenziosa"/>
                    <label for="Silenziosa" class="ml-2">Asta Silenziosa</label>
                </div>
            </div>
        </div>

        <!--v-if="tipoAsta === 'asta_inglese'"-->
        <div
            v-if="checkInglese"
            class="mx-2 my-2 flex flex-col gap-2 rounded px-2 py-2 ring-2 ring-[#cc85f5]"
        >
            <h2>ASTA INGLESE</h2>
            <div class="w-[100%] pt-5 lg:pr-9">
                <FloatLabel variant="on">
                    <InputNumber
                        v-model="incrementoMinimo"
                        fluid
                        inputId="integeronly"
                        id="incrementoMinimo"
                        class="rounded"
                    />
                    <label
                        for="incrementoMinimo"
                        :class="{
                            'p-filled': incrementoMinimo !== null && incrementoMinimo !== '',
                        }"
                    >
                        Incremento minimo
                    </label>
                </FloatLabel>
            </div>
            <div class="pt-5 lg:pr-9">
                <FloatLabel variant="on">
                    <InputNumber
                        :min="0"
                        fluid
                        class="rounded"
                        id="durataEstensione"
                        v-model="durataEstensione"
                    />
                    <label for="durataEstensione">Lunghezza estensione asta</label>
                </FloatLabel>
            </div>
            <div class="bg-inherit pt-5 lg:pr-9">
                <FloatLabel variant="on">
                    <DatePicker
                        fluid
                        dateFormat="dd/mm/yy"
                        :minDate="minDate"
                        showIcon="true"
                        v-model="scadenzaAsta"
                        id="scadenzaAsta"
                        inputId="birth_date"
                        class="rounded bg-inherit"
                    />
                    <label for="scadenzaAsta">Data Scadenza</label>
                </FloatLabel>
            </div>
        </div>

        <!--v-if="tipoAsta === 'asta_silenziosa'"-->
        <div
            v-if="checkSilenziosa"
            class="mx-2 my-2 flex flex-col gap-2 rounded bg-slate-200/20 px-2 py-2 ring-2 ring-[#cc85f5]"
        >
            <h2>ASTA SILENZIOSA</h2>

            <div class="px-2 lg:pr-9">
                <FloatLabel variant="on">
                    <DatePicker
                        dateFormat="dd/mm/yy"
                        :minDate="minDate"
                        showIcon="true"
                        fluid
                        v-model="scadenzaAsta"
                        id="scadenzaAsta"
                        inputId="birth_date"
                        class="rounded"
                    />
                    <label for="scadenzaAsta">Data Scadenza</label>
                </FloatLabel>
            </div>
        </div>

        <!--ASTA INVERSA-->
        <div
            v-if="checkInversa"
            class="mx-2 my-2 flex flex-col gap-2 rounded bg-slate-200/20 px-2 py-2 ring-2 ring-[#cc85f5]"
        >
            <h2>ASTA INVERSA</h2>

            <div class="px-2 lg:pr-9">
                <FloatLabel variant="on">
                    <DatePicker
                        dateFormat="dd/mm/yy"
                        :minDate="minDate"
                        showIcon="true"
                        fluid
                        v-model="scadenzaAsta"
                        id="scadenzaAsta"
                        inputId="birth_date"
                        class="rounded"
                    />
                    <label for="scadenzaAsta">Data Scadenza</label>
                </FloatLabel>
            </div>
        </div>

        <div class="areaBottoni mx-4 flex justify-around gap-5 px-4">
            <Button class="w-[45%]" size="large" @click="goToPreviousForm"
                ><span class="font-bold"><i class="pi pi-arrow-left"></i> Precedente</span></Button
            >
            <Button class="w-[45%]" size="large" @click="gestioneInvio"
                ><span class="font-bold">Successivo <i class="pi pi-arrow-right"></i></span
            ></Button>
        </div>
    </form>
</template>

<script setup>
import RadioButton from 'primevue/radiobutton';
import InputNumber from 'primevue/inputnumber';
import FloatLabel from 'primevue/floatlabel';
import Button from 'primevue/button';
import DatePicker from 'primevue/datepicker';

import { defineEmits, ref, onMounted, onUnmounted, computed } from 'vue';
import { useAstaStore } from '../../stores/astaStore.js';

let checkInglese = computed(() => {
    if (tipoAsta.value === 'asta_inglese') return true; else return false;
});

let checkInversa = computed(() => {
    if (tipoAsta.value === 'asta_inversa') return true; else return false;
});

let checkSilenziosa = computed(() => {
    if (tipoAsta.value === 'asta_silenziosa') return true; else return false;
});

let today = new Date();
let nowMonth = today.getMonth();
let nowYear = today.getFullYear();

const minDate = ref(new Date());

minDate.value.setMonth(nowMonth);
minDate.value.setFullYear(nowYear);

const storeInstance = useAstaStore();

const tipoAsta = ref(storeInstance.asta.tipoAsta);

const incrementoMinimo = ref('');
const durataEstensione = ref('');
const scadenzaAsta = ref('');

onMounted(() => {
    storeInstance.updateAsta({ step: 2 });
    incrementoMinimo.value = storeInstance.asta.incrementoMinimo;
    durataEstensione.value = storeInstance.asta.durataEstensione;
    scadenzaAsta.value = storeInstance.asta.scadenzaAsta;
    scadenzaAsta.value = scadenzaAsta.value.split('T')[0];
    tipoAsta.value = storeInstance.asta.tipoAsta;
});

onUnmounted(() => {
    console.log('Unmounted');
});

const emit = defineEmits('increase-page', 'decrease-page');

const gestioneInvio = () => {
    if (tipoAsta == false) {
        if (!incrementoMinimo.value || !durataEstensione.value || !scadenzaAsta.value) {
            alert('Asta Inglese: Inserire tutti i campi');
            return;
        }
    } else if (!scadenzaAsta.value) {
        alert('Asta Silenziosa: Inserire tutti i campi');
        return;
    }

    // Emit event to notify parent component to move to the next form section
    if (tipoAsta.value) {
        storeInstance.updateAsta({
            tipoAsta: 'asta_silenziosa',
            scadenzaAsta: scadenzaAsta.value,
            step: 2,
        });
    } else {
        storeInstance.updateAsta({
            tipoAsta: 'asta_inglese',
            scadenzaAsta: scadenzaAsta.value,
            incrementoMinimo: incrementoMinimo.value,
            durataEstensione: durataEstensione.value,
            step: 2,
        });
    }
    emit('increase-page');
};
const goToPreviousForm = () => {
    // Emit event to notify parent component to move to   the previous form section
    emit('decrease-page');
};
</script>

<style scoped>
h2 {
    font-size: 1.2rem;
    font-weight: bold;
    color: #cc85f5;
}
.bottone {
    background-color: #cc85f5;
    padding: 10px 20px;
    color: white;
    border-radius: 5px;
    font-size: 1.1rem;
    font-weight: bold;
    width: 50%;
    margin: 10px;
}
.bottone:hover {
    background-color: #7c3aed;
}
</style>

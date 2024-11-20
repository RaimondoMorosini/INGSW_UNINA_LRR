<template>
    <form @submit.prevent="gestioneInvio">
        <div class="flex flex-col items-center justify-center">
            <h2>Seleziona tipo asta</h2>
            <div class="flex justify-center gap-2 py-2">
                <span
                    v-if="!checked"
                    class="text-bold rounded bg-slate-100 px-2 py-2 text-xl ring-1 ring-[#cc85f5]"
                    >ASTA INGLESE</span
                >

                <span v-if="checked" class="text-bold rounded px-2 py-2 text-xl">ASTA INGLESE</span>
                <ToggleSwitch id="switch" v-model="checked" class="my-2" />
                <span
                    v-if="checked"
                    class="text-bold rounded bg-slate-100 px-2 py-2 text-xl ring-1 ring-[#cc85f5]"
                    >ASTA SILENZIOSA</span
                >

                <span v-if="!checked" class="text-bold rounded px-2 py-2 text-xl"
                    >ASTA SILENZIOSA</span
                >
            </div>
        </div>

        <!--v-if="tipoAsta === 'asta_inglese'"-->
        <div
            v-if="!checked"
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
                        fluid
                        inputId="integeronly"
                        id="durataEstensione"
                        class="rounded"
                        v-model="durataEstensione"
                        prefix="Ore estensione: "
                    />
                    <label for="durataEstensione">Durata estensione</label>
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
            v-if="checked"
            class="mx-2 my-2 flex flex-col gap-2 rounded bg-slate-200/20 px-2 py-2 ring-2 ring-[#cc85f5]"
        >
            ASTA SILENZIOSA

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
import ToggleSwitch from 'primevue/toggleswitch';
import InputNumber from 'primevue/inputnumber';
import FloatLabel from 'primevue/floatlabel';
import Button from 'primevue/button';
import DatePicker from 'primevue/datepicker';

import { defineEmits, ref, onMounted } from 'vue';
import { useAstaStore } from '../../stores/astaStore.js';

const checked = ref(null);
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
    //incrementoMinimo.value = storeInstance.asta.incrementoMinimo;
    //durataEstensione.value = storeInstance.asta.durataEstensione;
    //scadenzaAsta.value = storeInstance.asta.scadenzaAsta;
    if (tipoAsta === 'asta_silenziosa') {
        checked = true;
    }
});

const emit = defineEmits('increase-page', 'decrease-page');

const gestioneInvio = () => {
    if (checked == false) {
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

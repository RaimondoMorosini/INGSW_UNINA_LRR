<template>
    <form @submit.prevent="gestioneInvio">
        <label for="asta">Seleziona tipo Asta</label>

        <div class="flex justify-center gap-2 py-2">
            <span
                v-if="!checked"
                class="text-bold rounded  bg-slate-200/20 px-2 py-2 text-xl ring-1 ring-[#cc85f5]"
                >ASTA INGLESE</span
            >

            <span v-if="checked" class="text-bold rounded px-2 py-2 text-xl">ASTA INGLESE</span>
            <ToggleSwitch v-model="checked" class="my-2" />
            <span
                v-if="checked"
                class="text-bold rounded  bg-slate-200/20 px-2 py-2 text-xl ring-1 ring-[#cc85f5]"
                >ASTA SILENZIOSA</span
            >

            <span v-if="!checked" class="text-bold rounded px-2 py-2 text-xl">ASTA SILENZIOSA</span>
        </div>

        <!--v-if="tipoAsta === 'asta_inglese'"-->
        <div v-if="!checked" class="mx-2 my-2 flex flex-col gap-2 px-2 py-2 ring-2 ring-[#cc85f5]">
            ASTA INGLESE
            <div class="formSpace w-[100%] pt-5 lg:pr-9">
                <InputNumber
                    id="incrementoMinimo"
                    class="w-[100%] rounded"
                    v-model="incrementoMinimo"
                    prefix="€ "
                />
                <label for="incrementoMinimo">Incremento minimo</label>
            </div>
            <div class="formSpace pt-5 lg:pr-9">
                <InputNumber
                    inputId="integeronly"
                    fluid
                    id="durataEstensione"
                    class="w-[100%] rounded"
                    v-model="durataEstensione"
                    prefix="Ore estensione: "
                />
                <label for="durataEstensione">Durata estensione</label>
            </div>
            <div class="formSpace bg-inherit pt-5 lg:pr-9">
                <DatePicker
                    dateFormat="dd/mm/yy"
                    :minDate="minDate"
                    showIcon="true"
                    fluid
                    v-model="scadenzaAsta"
                    id="scadenzaAsta"
                    inputId="birth_date"
                    class="w-[60%] rounded bg-inherit"
                />
                <label for="scadenzaAsta">Data Scadenza</label>
            </div>
        </div>

        <!--v-if="tipoAsta === 'asta_silenziosa'"-->
        <div
            v-if="checked"
            class="mx-2 my-2 flex flex-col gap-2  bg-slate-200/20 px-2 py-2 ring-2 ring-[#cc85f5]"
        >
            ASTA Silenziosa

            <div class="formSpace px-2 lg:pr-9">
                <DatePicker
                    dateFormat="dd/mm/yy"
                    :minDate="minDate"
                    showIcon="true"
                    fluid
                    v-model="scadenzaAsta"
                    id="scadenzaAsta"
                    inputId="birth_date"
                    class="w-[60%] rounded"
                />
                <label for="scadenzaAsta">Data Scadenza</label>
            </div>
        </div>

        <div class="areaBottoni mx-4 flex justify-around gap-5 px-4">
            <Button class="w-[45%]" size="large" @click="goToPreviousForm"
                ><span class="font-bold  "
                    ><i class="pi pi-arrow-left"></i> Precedente</span
                ></Button
            >
            <Button class="w-[45%]" size="large" @click="gestioneInvio"
                ><span class="font-bold  "
                    >Successivo <i class="pi pi-arrow-right"></i></span
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

const incrementoMinimo = ref(storeInstance.asta.incrementoMinimo);
const durataEstensione = ref(storeInstance.asta.durataEstensione);
const scadenzaAsta = ref(storeInstance.asta.scadenzaAsta);

onMounted(() => {
    storeInstance.updateAsta({ step: 2 });
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
form {
    text-align: center;
}
label {
    text-align: center;
    display: block;
    margin: 25px 0 15px;
    font-size: 1rem;
    text-transform: uppercase;
    letter-spacing: 1px;
    font-weight: bold;
}
.formSpace {
    justify-content: space-between;
    display: flex;
    direction: column;
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

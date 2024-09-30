<template>
    <form @submit.prevent="gestioneInvio">
        <label for="asta">Seleziona tipo Asta</label>
        <select name="tipoAsta" id="asta" v-model="tipoAsta" class="rounded bg-inherit">
            <option value="asta_inglese">Asta Inglese</option>
            <option value="asta_silenziosa">Asta Silenziosa</option>
        </select>

        <div
            v-if="tipoAsta === 'asta_inglese'"
            class="mx-2 my-2 flex flex-col gap-2 px-2 py-2 ring-2 ring-[#cc85f5]"
        >
            ASTA INGLESE
            <div class="formSpace  pt-5 w-[100%] lg:pr-9">

                 
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
                            inputId="integeronly" fluid
                            id="durataEstensione"
                            class="w-[100%] rounded"
                            v-model="durataEstensione"
                            prefix="Ore estensione: " 
                        />
                        <label for="durataEstensione">Durata estensione</label>
                     
                
              
            </div>
            <div class="formSpace pt-5 lg:pr-9">
                 
                    <DatePicker :minDate="minDate" showIcon="true" fluid v-model="scadenzaAsta" id="scadenzaAsta" inputId="birth_date" 
                    class="w-[60%] rounded bg-inherit"/>
                    <label for="scadenzaAsta">Data Scadenza</label>
                 
                
                
            </div>
        </div>

        <div
            v-if="tipoAsta === 'asta_silenziosa'"
            class="mx-2 my-2 flex flex-col gap-2 px-2 py-2 ring-2 ring-black"
        >
            ASTA Silenziosa

            <div class="formSpace px-2 lg:pr-9">
                <label for="prezzoBase">Data Scadenza</label>
                <input class="w-[60%] rounded" type="date" id="prezzoBase" v-model="scadenzaAsta" />
            </div>
        </div>

        <div class="areaBottoni mx-4 flex justify-around gap-5 px-4">
            <button class="previous bottone px-5" @click="goToPreviousForm" type="button">
                Precedente
            </button>
            <button class="bottone px-5" type="submit">Successivo</button>
        </div>
    </form>
</template>

<script setup>
import InputNumber from 'primevue/inputnumber';
import FloatLabel from 'primevue/floatlabel';
import DatePicker from 'primevue/datepicker';

import { defineEmits, ref, onMounted } from 'vue';
import { useAstaStore } from '../../stores/astaStore.js';

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
});

const emit = defineEmits(['update:active']);

const gestioneInvio = () => {
    if (tipoAsta === 'asta_inglese') {
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
    emit('update:active', 3);
};
const goToPreviousForm = () => {
    // Emit event to notify parent component to move to   the previous form section
    emit('update:active', 1);
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

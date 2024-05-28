<template>
    <form @submit.prevent="gestioneInvio">
        <label for="asta">Seleziona tipo Asta</label>
        <select name="tipoAsta" id="asta" v-model="tipoAsta" class="rounded">
            <option value="asta_inglese">Asta Inglese</option>
            <option value="asta_silenziosa">Asta Silenziosa</option>
        </select>

        <div
            v-if="tipoAsta === 'asta_inglese'"
            class="mx-2 my-2 flex flex-col gap-2 px-2 py-2 ring-2 ring-black"
        >
            ASTA INGLESE
            <div class="formSpace px-2 lg:pr-9">
                <label for="incrementoMinimo">Incremento minimo</label>
                <input
                    class="w-[60%] rounded"
                    type="number"
                    id="incrementoMinimo"
                    v-model="incrementoMinimo"
                />
            </div>
            <div class="formSpace px-2 lg:pr-9">
                <label for="durataEstensione">Durata estensione</label>
                <input
                    type="number"
                    class="w-[60%] rounded"
                    id="durataEstensione"
                    v-model="durataEstensione"
                />
            </div>
            <div class="formSpace px-2 lg:pr-9">
                <label for="prezzoBase">Data Scadenza</label>
                <input class="w-[60%] rounded" type="date" id="prezzoBase" v-model="scadenzaAsta" />
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
import { defineEmits, ref, onMounted } from 'vue';
import { useAstaStore } from '../../stores/astaStore.js';

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

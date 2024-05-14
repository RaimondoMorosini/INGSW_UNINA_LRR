<template>
    <div class="text-xl font-semibold">Revisione Dati Inseriti</div>

    <div class="grid grid-cols-2 px-3 py-2" @submit.prevent="gestioneInvio">
        <div class="label">Categoria:</div>
        <span>{{ storeInstance.asta.selectedCategory }}</span>
        <div class="label">nomeProdotto:</div>
        <span> {{ storeInstance.asta.nomeProdotto }}</span>
        <div class="label">descrizione</div>
        <span>{{ storeInstance.asta.descrizione }}</span>
        <div class="label">prezzo Base:</div>
        <span>€ {{ storeInstance.asta.prezzoBase }}</span>
        <div class="label">tipo Asta:</div>
        <span>{{ storeInstance.asta.tipoAsta }}</span>
        <div v-if="tipoAsta === 'Inglese'">
            <div class="label">incremento minimo:</div>
            <span>€ {{ storeInstance.asta.incrementoMinimo }}</span>
            <div class="label">durata estensione:</div>
            <span>{{ storeInstance.asta.durataEstensione }}</span>
        </div>

        <div class="label">scadenza asta:</div>
        <span>{{ storeInstance.asta.scadenzaAsta }}</span>
    </div>
    <div class="buttonArea">
        <button class="previous bottone mx-3 my-3 px-5" @click="goToPreviousForm" type="button">
            Precedente
        </button>
        <button class="bottone px-5" type="submit">Successivo</button>
    </div>

    {{ astaCompleta }}
</template>

<script setup>
import axios from 'axios';
import { useAstaStore } from '../../stores/astaStore.js';
import { ref, onMounted } from 'vue';

const storeInstance = useAstaStore();
const emit = defineEmits(['update:active']);
let success = false;
let error = '';
const astaCompleta = ref(storeInstance.asta);

const goToPreviousForm = () => {
    // Emit event to notify parent component to move to   the previous form section
    emit('update:active', 2);
};

onMounted(() => {
    storeInstance.updateAsta({ step: 3 });
});

const gestioneInvio = () => {
    axios
        .post('http://localhost:8081/asta', astaCompleta)
        .then((res) => {
            console.log(res.data);
            success = true;
            storeInstance.$reset();
        })
        .catch((err) => {
            error = error.data.message;
        });
};
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

div.label {
    text-align: left;
    font-size: 1rem;
    text-transform: uppercase;
    letter-spacing: 1px;
    font-weight: bold;
}
</style>

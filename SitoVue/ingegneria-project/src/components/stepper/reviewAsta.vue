<template>
    <div class="font-semibold text-xl">
        Revisione Dati Inseriti
    </div>
    
    <p>Categoria: {{ storeInstance.asta.selectedCategory }}</p>
    <p>nomeProdotto: {{ storeInstance.asta.nomeProdotto}}</p>
    <p>descrizione {{ storeInstance.asta.descrizione }}</p>

    <p>prezzo Base: € {{ storeInstance.asta.prezzoBase }}</p>
    <p>tipo Asta: {{ storeInstance.asta.tipoAsta }}</p>
    <div v-if="tipoAsta==='Inglese'">    
        <p>incremento minimo: € {{ storeInstance.asta.incrementoMinimo }}</p>
        <p>durata estensione:   {{ storeInstance.asta.durataEstensione }}</p>
    </div>

    <p> scadenza asta: {{ storeInstance.asta.scadenzaAsta}}</p>

    <button class="previous bottone my-3 mx-3 px-5" @click="goToPreviousForm" type="button">
        Precedente
    </button>
</template>

<script setup>
import { useAstaStore } from '../../stores/astaStore.js';
import {ref,onMounted} from 'vue';

const storeInstance = useAstaStore();
const emit = defineEmits(['update:active']);

const goToPreviousForm = () => {
  // Emit event to notify parent component to move to   the previous form section
  emit('update:active', 2);
};



onMounted(() =>
{
    storeInstance.updateAsta  ({ step : 3 });
    
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

label {
  text-align: center;
  display: block;
  margin: 25px 0 15px;
  font-size: 1rem;
  text-transform: uppercase;
  letter-spacing: 1px;
  font-weight: bold;
}

</style>
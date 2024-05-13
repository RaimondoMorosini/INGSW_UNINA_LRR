<template>
    <div class="font-semibold text-xl">
        REVIEW
    </div>
    
    <p>Categoria: {{ selectedCategory }}</p>
    <p>nomeProdotto: {{ nomeProdotto }}</p>
    <p>descrizione {{ descrizione }}</p>

    <p>prezzo Base: € {{ prezzoBase }}</p>
    <p>tipo Asta: {{ tipoAsta }}</p>
    <div v-if="tipoAsta==='Inglese'">    
        <p>incremento minimo: € {{ incrementoMinimo }}</p>
        <p>durata estensione:   {{ durataEstensione }}</p>
    </div>

    <p> scadenza asta: {{ scadenzaAsta}}</p>

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

const astaReview = ref({
    selectedCategory: '',
    nomeProdotto: '',
    descrizione : '',
    prezzoBase: '',
    tipoAsta: '',
    incrementoMinimo: '',
    durataEstensione:'',
    scadenzaAsta:''
});

onMounted(() =>
{
    storeInstance.updateAsta  ({ step : 3 });
    this.astaReview = storeInstance.getAsta;
    console.log(toString(this.astaReview));
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
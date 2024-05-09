<template>
    <div>
        REVIEW
    </div>
    <p>Categoria: {{ selectedCategory }}</p>
    <p>nomeProdotto: {{ nomeProdotto }}</p>
    <p>descrizione {{ descrizione }}</p>

    <p>prezzo Base: € {{ prezzoBase }}</p>
    <p>tipo Asta: {{ tipoAsta }}</p>
    <p>incremento minimo: € {{ incrementoMinimo }}</p>
    <p>durata estensione:   {{ durataEstensione }}</p>
    <p> scadenza asta: {{ scadenzaAsta }}</p>

    <button class="previous bottone my-3 mx-3 px-5" @click="goToPreviousForm" type="button">
        Precedente
      </button>
</template>

<script setup>
import { useAstaStore } from '../../stores/astaStore.js';
import {ref,onMounted} from 'vue';

const emit = defineEmits(['update:active']);

const goToPreviousForm = () => {
  // Emit event to notify parent component to move to   the previous form section
  emit('update:active', 2);
};
const storeInstance = useAstaStore();

const selectedCategory = ref('');
const nomeProdotto = ref('');
const descrizione = ref('');
const prezzoBase = ref('');
const tipoAsta = ref('');
const incrementoMinimo = ref('');
const durataEstensione = ref('');
const scadenzaAsta = ref('');

onMounted(() =>
{
    step = 3;
    nomeProdotto.value = storeInstance.asta.nomeProdotto;
    descrizione.value = storeInstance.asta.descrizione;
    prezzoBase.value = storeInstance.asta.prezzoBase;
    selectedCategory.value = storeInstance.asta.categorie;
    tipoAsta.value = storeInstance.asta.tipo;
    incrementoMinimo.value = storeInstance.asta.incrementoMinimo;
    durataEstensione.value = storeInstance.asta.estenzione;
    scadenzaAsta.value = storeInstance.asta.scadenza;
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

</style>
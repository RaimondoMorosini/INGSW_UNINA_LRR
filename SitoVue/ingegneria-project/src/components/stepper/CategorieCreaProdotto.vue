<template>
  <form @submit.prevent="gestioneInvio">
    <Chip class="chip" label="Eletronica" />
    <!--TODO decommentare quando tutte le pagine form sono complete-->
    <!--<button type="submit">Successivo</button> -->
    <button class="previous bottone" @click="goToPreviousForm" type="button">Precedente</button>
  </form>
</template>

<script setup>
import Chip from 'primevue/chip';
const emit = defineEmits(['update:active']);
const gestioneInvio = () => {
  emit('update:active', 2);
};
const goToPreviousForm = () => {
  // Emit event to notify parent component to move to   the previous form section
  emit('update:active', 0);
};
</script>

<style scoped>
.chip {
  text-transform: uppercase;
  font-weight: bold;
}
.bottone {
  background-color: #cc85f5;
  padding: 10px 20px;
  color: white;
  border-radius: 5px;
  font-size: 1.1rem;
  font-weight: bold;
  width: 100%;
}
.bottone:hover {
  background-color: #7c3aed;
}
</style>

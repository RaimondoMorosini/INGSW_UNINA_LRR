<template>
  <form @submit.prevent="gestioneInvio">
    <InputSwitch v-model="tipoAsta" />
    <div v-if="tipoAsta">ASTA INGLESE</div>
    <div v-else>ASTA Silenziosa</div>
    <div class="areaBottoni flex justify-around gap-5">
      <button class="previous bottone w-[30%] px-5" @click="goToPreviousForm" type="button">
        Precedente
      </button>
      <button class="bottone w-[30%] px-5" type="submit">Successivo</button>
    </div>
  </form>
</template>

<script setup>
import InputSwitch from 'primevue/inputswitch';
import { defineEmits, ref } from 'vue';

const tipoAsta = ref(false);

const emit = defineEmits(['update:active']);

const gestioneInvio = () => {
  console.log('\ncategorie selezionate:', categorieSelezionate.value[1]);
  emit('update:active', 3);
};
const goToPreviousForm = () => {
  // Emit event to notify parent component to move to   the previous form section
  emit('update:active', 1);
};
</script>

<style scoped>
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

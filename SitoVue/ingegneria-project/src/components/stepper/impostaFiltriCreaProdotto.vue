<template>
    <div>
      <form @submit.prevent="gestioneInvio">
      
        <div class="mx-2 my-2 px-2 py-2 ring-2 ring-black">
  <InputField v-for="caratteristica in caratteristicheRelativeAllaCategoria"
              :key="caratteristica.id"
              :label="caratteristica.label"
              :options="caratteristica.opzioniSelezionabili"
                v-model="arrayValoriSelezionati[caratteristica.id]"
              >
  </InputField>    
  arrayValoriSelezionati {{ arrayValoriSelezionati }} <br>
  <hr class="my-4">
</div>
  
        <div class="areaBottoni flex justify-around gap-5">
          <button class="previous bottone w-[30%] px-5" @click="goToPreviousForm" type="button">
            Precedente
          </button>
          <button class="bottone w-[30%] px-5" type="submit">Successivo</button>
        </div>
      </form>
    </div>
  </template>
  
  <script setup>
  import { defineEmits, onMounted, ref } from 'vue';
  import { getCaratteristiche } from '../../service/carateristicheService.js';
  import InputField from './InputField.vue';
  const emit = defineEmits(['update:active']);
  
  const gestioneInvio = () => {
    console.log('\ncategorie selezionate:', categorieSelezionate.value[1]);
    emit('update:active', 2);
  };
  
  const goToPreviousForm = () => {
    emit('update:active', 0);
  };
  
  const arrayValoriSelezionati = ref({});
  
  const caratteristicheRelativeAllaCategoria = ref([]);
  
  onMounted(() => {
    getCaratteristiche('Telefonia').then((response) => {
      caratteristicheRelativeAllaCategoria.value = response;
    }).catch((error) => {
      console.log(error);
    });
  });
  
  </script>
  
  <style scoped>
  /* Stili rimossi per brevità */
  </style>
  
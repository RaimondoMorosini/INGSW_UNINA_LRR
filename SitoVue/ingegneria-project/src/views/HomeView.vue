
<template>
  
  <SwipeCarosello />
  <div class="card flex justify-content-center justify-between py-3 px-6col md:px-2col sm:px-1col">
        <Dropdown v-model="selectedCity" :options="cities" optionLabel="name" placeholder="Select a City" class="bg-slate-200 text-black md:w-14rem min-w-2col"/>
        <Dropdown v-model="selectedCity1" :options="cities" optionLabel="name" placeholder="Select a City" class="bg-slate-200 text-black md:w-14rem min-w-2col"/>
        <Dropdown v-model="selectedCity2" :options="cities" optionLabel="name" placeholder="Select a City" class="bg-slate-200 text-black md:w-14rem min-w-2col"/>
  </div>
  
</template>

<script setup>
import SwipeCarosello from '../components/SwipeCarosello.vue'
import Dropdown from 'primevue/dropdown';
import { ref } from 'vue';

const selectedCity = ref();
const selectedCity1 = ref();
const selectedCity2 = ref();


const cities = ref([
    { name: 'New York', code: 'NY' },
    { name: 'Rome', code: 'RM' },
    { name: 'London', code: 'LDN' },
    { name: 'Istanbul', code: 'IST' },
    { name: 'Paris', code: 'PRS' }
]);
</script>

<template>
  
  <SwipeCarosello />

  <AreaRicerca/>

  <div class="flex flex-row items-center bg-slate-100">
    <Vetrina />
  </div>

</template>

<script setup>
import SwipeCarosello from '../components/SwipeCarosello.vue';

import Vetrina from '@/components/Vetrina.vue';
import AreaRicerca from '../components/AreaRicerca.vue';

import { onMounted, ref } from 'vue';

import {getCategorieRest} from "../scripts/categorie.js";

const value = ref(null);
const selectedCategory = ref();

const nodes = ref([]);

const getCategorie = async () => {
      try {
        nodes.value =await getCategorieRest(); // Assuming the response contains an array
      } catch (error) {
        console.error('Error categorie non trovate:', error);
      }
    };

onMounted(() => {
  
  getCategorie();

});

</script>

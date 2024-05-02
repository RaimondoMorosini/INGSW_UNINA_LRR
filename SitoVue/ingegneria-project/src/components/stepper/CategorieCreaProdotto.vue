<template>
  <form @submit.prevent="gestioneInvio">
    <InputGroup class="h-14 min-w-2col md:w-[14rem]">
      <InputGroupAddon class="bg-primario-100">
        <i class="pi pi-th-large" style="color: #424242"></i>
      </InputGroupAddon>
      <TreeSelect
        id="categoria"
        v-model="selectedCategory"
        :options="nodes"
        option-label="name"
        placeholder="Seleziona Categoria"
        class="rounded-r bg-primario-100/50 text-black"
      />
    </InputGroup>
    <button class="bottone" @click="selezioneCategoria">Seleziona</button>"

    <Chip class="chip" label="Eletronica" />
    <Chip v-for="categoria in categorieSelezionate" v-bind:label="categoria" />
    <!--TODO decommentare quando tutte le pagine form sono complete-->
    <!--<button type="submit">Successivo</button> -->
    <div class="areaBottoni flex justify-around gap-5">
      <button class="bottone w-[30%] px-5" type="submit">Successivo</button>
      <button class="previous bottone w-[30%] px-5" @click="goToPreviousForm" type="button">
        Precedente
      </button>
    </div>
  </form>
</template>

<script setup>
import Chip from 'primevue/chip';
import InputGroup from 'primevue/inputgroup';
import InputGroupAddon from 'primevue/inputgroupaddon';
import TreeSelect from 'primevue/treeselect';
import { defineEmits, onMounted, ref } from 'vue';

import { getCategorieRest } from '../../scripts/categorie.js';

const selectedCategory = ref(null);

const categorieSelezionate = ref([]);

const selezioneCategoria = () => {
  categorieSelezionate.value.push(selectedCategory.value.name);
  //console.log(categorieSelezionate.value);
};
const emit = defineEmits(['update:active']);
const gestioneInvio = () => {
  emit('update:active', 2);
};
const goToPreviousForm = () => {
  // Emit event to notify parent component to move to   the previous form section
  emit('update:active', 0);
};
const nodes = ref([]);

const getCategorie = async () => {
  try {
    nodes.value = await getCategorieRest(); // Assuming the response contains an array
  } catch (error) {
    console.error('Error categorie non trovate:', error);
  }
};

onMounted(() => {
  getCategorie();
});
</script>

<style scoped>
#categoria {
  width: 100%;
}
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

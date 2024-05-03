<template>
  <form @submit.prevent="gestioneInvio">
    <div class="mx-2 my-2 px-2 py-2 ring-2 ring-black">
      TODO: trovare un modo per riempire questa sezione, passare i filtri come oggetto a pinia
    </div>

    <div class="areaBottoni flex justify-around gap-5">
      <button class="previous bottone w-[30%] px-5" @click="goToPreviousForm" type="button">
        Precedente
      </button>
      <button class="bottone w-[30%] px-5" type="submit">Successivo</button>
    </div>
  </form>
</template>

<script setup>
import Button from 'primevue/button';

import { defineEmits, onMounted, ref } from 'vue';

import { getCategorieRest } from '../../scripts/categorie.js';

const selectedCategory = ref(null);

const categorieSelezionate = ref([]);

const selezioneCategoria = () => {
  if (selectedCategory.value) {
    console.log('percorso giusto');
    categorieSelezionate.value.push(selectedCategory.value.name);
  }
};
const emit = defineEmits(['update:active']);

const gestioneInvio = () => {
  console.log('\ncategorie selezionate:', categorieSelezionate.value[1]);
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

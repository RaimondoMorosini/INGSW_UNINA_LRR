<template>
  <form @submit.prevent="gestioneInvio">
    <main class="px-5 gap-3 min-w-3col h-auto md:w-auto w-[100%] flex flex-col lg:flex-row justify-around ">
      <div
        class="flex w-[100%] h-4col px-2 auto-rows-max flex-col justify-around gap-3  ">
        <div class="formSpace  ">
          <label for="nomeProdotto">Nome Prodotto</label>
          <input class="w-[60%] md:w-[70%] rounded" type="text" id="nomeProdotto" v-model="nomeProdotto" />
        </div>
        <div class="formSpace ">
          <label for="descrizione">Descrizione Prodotto</label>
          <textarea rows="5" class="w-[60%] md:w-[70%] rounded" id="descrizione" v-model="descrizione" />
        </div>
        <div class="formSpace  ">
          <label for="prezzoBase">Prezzo Base</label>
          <input class="w-[60%] md:w-[70%] rounded" type="number" id="prezzoBase" v-model="prezzoBase" />
        </div>

        <InputGroup class="categoriaSelector w-[100%] ">
          <InputGroupAddon class="bg-slate-100 ring-1 ring-black">
            <i class="pi pi-th-large" style="color: #000"></i>
          </InputGroupAddon>
          <TreeSelect
            id="categoria"
            v-model="selectedCategory"
            :options="nodes"
            option-label="name"
            placeholder="Seleziona Categoria"
            class=" rounded-r bg-slate-100 ring-1 ring-black text-black"
          />
        </InputGroup>
      </div>

      <div class="h-[100%] w-[100%] px-2  flex ">
        <ImageUploader class="h-[100%] flex" />
      </div>
    </main>
    <div class="areaBottoni my-4 px-10">
      <button class="bottone" type="submit">Successivo</button>
    </div>
  </form>
</template>

<script setup>
import InputGroup from 'primevue/inputgroup';
import InputGroupAddon from 'primevue/inputgroupaddon';
import TreeSelect from 'primevue/treeselect';
import { defineEmits } from 'vue';

import { onMounted, ref } from 'vue';
import { useAstaStore } from '../../stores/astaStore.js';
import ImageUploader from '../ImageUploader.vue';

import { getCategorieRest } from '../../scripts/categorie.js';

const nodes = ref([]);

const getCategorie = async () => {
  try {
    nodes.value = await getCategorieRest(); // Assuming the response contains an array
  } catch (error) {
    console.error('Error categorie non trovate:', error);
  }
};

const storeInstance = useAstaStore();

const emit = defineEmits(['update:active']);
const selectedCategory = ref('');
const nomeProdotto = ref('');
const descrizione = ref('');
const prezzoBase = ref('');



onMounted(() => {
  storeInstance.updateAsta({
    step : 0,
  });
  selectedCategory = storeInstance.asta?.categoria;
  nomeProdotto = storeInstance.asta?.nomeProdotto;
  descrizione = storeInstance.asta?.descrizione;
  prezzoBase = storeInstance.asta?.prezzoBase;
  getCategorie();
});

const gestioneInvio = () => {
  /** TODO da togliere quando il server è funzionante
  if (
    !nomeProdotto.value.trim ||
    !descrizione.value.trim ||
    !prezzoBase.value.trim ||!selectedCategory.value.trim
  ) {
    alert('Compila tutti i campi.');
    return;
  }
  */
  storeInstance.updateAsta({
    
    nomeProdotto: nomeProdotto.value,
    descrizione: descrizione.value,
    prezzoBase: prezzoBase.value,
    categoria: selectedCategory.value,
  });
  emit('update:active', 1);
};

const onAdvancedUpload = () => {
  toast.add({ severity: 'info', summary: 'Success', detail: 'File Uploaded', life: 3000 });
};
</script>

<style scoped>
form {
  text-align: center;
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
.formSpace {
  justify-content: space-between;
  display: flex;
  direction: column;
}

button.bottone {
  background-color: #cc85f5;
  padding: 10px 20px;
  color: white;
  border-radius: 5px;
  font-size: 1.1rem;
  font-weight: bold;
  width: 50%;
  margin: 10px;
  
}

button.bottone:hover {
  background-color: #7c3aed;
}

textarea {
  resize: none;
}

/* Chrome, Safari, Edge, Opera */
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}

/* Firefox */
input[type=number] {
  -moz-appearance: textfield;
}
</style>

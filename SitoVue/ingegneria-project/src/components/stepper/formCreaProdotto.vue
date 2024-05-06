<template>
  <!--TODO: rimuovere i ring di supporto a pagina finita-->

  <form @submit.prevent="gestioneInvio">
    <main class="flex h-4col flex-col justify-around gap-2 py-4 lg:flex-row">
      <div
        class="flex w-[100%] min-w-4col flex-col justify-around gap-3 px-2 py-2 ring-2 ring-black lg:max-w-[40%]"
      >
        <div class="formSpace px-2 lg:pr-9">
          <label for="nomeProdotto">Nome Prodotto</label>
          <input class="w-[60%] lg:w-max" type="text" id="nomeProdotto" v-model="nomeProdotto" />
        </div>
        <div class="formSpace px-2 lg:pr-9">
          <label for="descrizione">Descrizione Prodotto</label>
          <textarea class="w-[60%] lg:w-max" id="descrizione" v-model="descrizione" />
        </div>
        <div class="formSpace px-2 lg:pr-9">
          <label for="prezzoBase">Prezzo Base</label>
          <input class="w-[60%] lg:w-max" type="number" id="prezzoBase" v-model="prezzoBase" />
        </div>

        <InputGroup class="categoriaSelector w-[100%] px-2">
          <InputGroupAddon class="bg-primario-100">
            <i class="pi pi-th-large" style="color: #424242"></i>
          </InputGroupAddon>
          <TreeSelect
            id="categoria"
            v-model="selectedCategory"
            :options="nodes"
            option-label="name"
            placeholder="Seleziona Categoria"
            class="w-[100px] rounded-r bg-primario-100/50 text-black"
          />
        </InputGroup>
      </div>

      <div class="h-[100%] w-[100%] px-2 py-2 ring-2 ring-black lg:w-[60%]">
        <ImageUploader class="h-[100%]" />
      </div>
    </main>
    <div class="absolute">
      <button class="bottone relative top-24 lg:top-0" type="submit">Successivo</button>
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

onMounted(() => {
  getCategorie();
});
const storeInstance = useAstaStore();

const emit = defineEmits(['update:active']);
const selectedCategory = ref('');
const nomeProdotto = ref('');
const descrizione = ref('');
const prezzoBase = ref('');

const gestioneInvio = () => {
  if (
    !nomeProdotto.value.trim ||
    !descrizione.value.trim ||
    !prezzoBase.value.trim ||
    !selectedCategory.value.trim
  ) {
    alert('Compila tutti i campi.');
    return;
  }
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
  width: 25%;
  margin: 0 auto;
  width: 100%;
}

button.bottone:hover {
  background-color: #7c3aed;
}
</style>

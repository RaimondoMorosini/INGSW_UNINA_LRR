<template>
  <!--TODO: rimuovere i ring di supporto a pagina finita-->

  <form @submit.prevent="gestioneInvio" class="relative">
    <main class="flex h-4col flex-col justify-around gap-2 py-2 lg:flex-row">
      <div
        class="flex w-[100%] min-w-4col flex-col justify-around gap-3 px-3 py-2 ring-2 ring-black lg:max-w-[40%]"
      >
        <div class="formSpace px-5 lg:pr-9">
          <label for="nomeProdotto">Nome Prodotto</label>
          <input
            class="w-[60%] lg:w-max"
            required
            type="text"
            id="nomeProdotto"
            v-model="nomeProdotto"
          />
        </div>
        <div class="formSpace px-5 lg:pr-9">
          <label for="descrizione">Descrizione Prodotto</label>
          <textarea class="w-[60%] lg:w-max" required id="descrizione" v-model="descrizione" />
        </div>
        <div class="formSpace px-5 lg:pr-9">
          <label for="prezzoBase">Prezzo Base</label>
          <input
            class="w-[60%] lg:w-max"
            required
            type="number"
            id="prezzoBase"
            v-model="prezzoBase"
          />
        </div>
      </div>

      <div class="relative h-[100%] w-[100%] px-2 py-2 ring-2 ring-black lg:w-[60%]">
        <ImageUploader class="absolute h-[100%]" />
      </div>
    </main>
    <button class="bottone" type="submit">Successivo</button>
  </form>
</template>

<script setup>
import { ref } from 'vue';
import { useAstaStore } from '../../stores/astaStore.js';
import ImageUploader from '../ImageUploader.vue';

const storeInstance = useAstaStore();

const emit = defineEmits(['update:active']);

const nomeProdotto = ref('');
const descrizione = ref('');
const prezzoBase = ref('');

const gestioneInvio = () => {
  // console.log(nomeProdotto.value);
  // console.log(descrizione.value);
  // console.log(prezzoBase.value);
  storeInstance.updateAsta({
    nomeProdotto: nomeProdotto.value,
    descrizione: descrizione.value,
    prezzoBase: prezzoBase.value,
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
}

button.bottone:hover {
  background-color: #7c3aed;
}
</style>

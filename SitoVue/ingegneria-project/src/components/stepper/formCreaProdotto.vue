<template>
  <!--TODO: rimuovere i ring di supporto a pagina finita-->

  <form @submit.prevent="gestioneInvio" class="px-3 py-2">
    <main class="flex justify-between">
      <div
        class="flex w-[100%] min-w-4col justify-between gap-3 px-3 py-2 ring-2 ring-black md:flex md:flex-col lg:max-w-[40%]"
      >
        <div class="formSpace px-5 lg:pr-9">
          <label for="nomeProdotto">Nome Prodotto</label>
          <InputText id="nomeProdotto" v-model="nomeProdotto" />
        </div>
        <div class="formSpace px-5 lg:pr-9">
          <label for="descrizione">Descrizione Prodotto</label>
          <Textarea id="descrizione" v-model="descrizione" autoResize variant="filled" rows="5" />
        </div>
        <div class="formSpace px-5 lg:pr-9">
          <label for="prezzoBase">Prezzo Base</label>
          <InputNumber id="prezzoBase" v-model="prezzoBase" />
        </div>
      </div>

      <div class="px-2 py-2 ring-2 ring-black">
        <ImageUploader class="w-max" />
      </div>
    </main>
    <button class="bottone" type="submit">Successivo</button>
  </form>
</template>

<script setup>
import InputNumber from 'primevue/inputnumber';
import InputText from 'primevue/inputtext';
import { ref } from 'vue';
import { useAstaStore } from '../../stores/astaStore.js';
import ImageUploader from '../ImageUploader.vue';

const storeInstance = useAstaStore();

const emit = defineEmits(['update:active']);
const descrizione = ref('');
const nomeProdotto = ref('');
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

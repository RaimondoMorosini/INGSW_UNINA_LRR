<template>
  <div>
    <h1>Form Crea Prodotto</h1>
  </div>
  <div class="flex flex-col justify-between gap-4 lg:flex-row">
    <!--TODO: rimuovere i ring di supporto a pagina finita-->
    <div
      class="contenitore-form jusify-center flex w-[100%] flex-col justify-between gap-4 ring-2 ring-black lg:w-[55%]"
    >
      <div class="nome-prodotto">
        <label for="nomeProdotto">Nome Prodotto</label>
        <InputText class="w-[100%] md:w-[60%]" id="nomeProdotto" v-model="nomeProdotto" />
      </div>

      <div class="descrizione-prodotto flex w-auto">
        <label for="descrizione">Descrizione Prodotto</label>
        <Textarea
          id="descrizione"
          class="w-[100%] md:w-[60%]"
          v-model="descrizione"
          autoResize
          variant="filled"
          rows="5"
        />
      </div>

      <div class="prezzo-prodotto flex">
        <label for="prezzoBase">Prezzo Base</label>
        <InputNumber class="w-[100%] md:w-[60%]" id="prezzoBase" v-model="prezzoBase" />
      </div>
    </div>

    <div class="w-[100%] lg:w-[43%]">
      <ImageUpdater class="w-[100%] ring-2 ring-black md:w-[80%]" />
    </div>
  </div>
</template>

<script setup>
import InputNumber from 'primevue/inputnumber';
import InputText from 'primevue/inputtext';
import { ref } from 'vue';
import ImageUpdater from '../ImageUpdater.vue';

const descrizione = ref(null);
const nomeProdotto = ref(null);
const prezzoBase = ref(null);

import { useToast } from 'primevue/usetoast';
const toast = useToast();

const onAdvancedUpload = () => {
  toast.add({ severity: 'info', summary: 'Success', detail: 'File Uploaded', life: 3000 });
};
</script>

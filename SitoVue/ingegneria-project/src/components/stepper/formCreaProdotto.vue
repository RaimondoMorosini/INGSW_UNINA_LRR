<template>
  <div class="flex flex-col justify-between gap-3 md:flex-row">
    <!--TODO: rimuovere i ring di supporto a pagina finita-->

    <form
      @submit="gestioneInvio()"
      class="block w-[100%] min-w-4col justify-between gap-3 ring-2 ring-black md:flex md:flex-col lg:max-w-[40%]"
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
    </form>

    <div class="w-[100%] md:w-[65%]">
      <ImageUpdater class="ring-2 ring-black" />
    </div>
  </div>
</template>

<script setup>
import InputNumber from 'primevue/inputnumber';
import InputText from 'primevue/inputtext';
import { ref } from 'vue';
import useAstaStore from '../../stores/astaStore';
import ImageUpdater from '../ImageUpdater.vue';

const store = useAstaStore();
const descrizione = ref(null);
const nomeProdotto = ref(null);
const prezzoBase = ref(null);

function gestioneInvio() {
  console.log(nomeProdotto.value);
  console.log(descrizione.value);
  console.log(prezzoBase.value);
}

const onAdvancedUpload = () => {
  toast.add({ severity: 'info', summary: 'Success', detail: 'File Uploaded', life: 3000 });
};
</script>

<style scoped>
form {
  text-align: center;
}
label {
  display: inline-block;
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
</style>

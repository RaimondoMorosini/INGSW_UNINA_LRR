<template>
  <Stepper linear v-model:activeStep="active">
    <StepperPanel header="Descrizione Prodotto">
      <template #content="{ nextCallback }">
        <div><CreaProdotto /></div>
        <div class="flex justify-between py-4">
          <Button label="Successivo" @click="nextCallback" class="bottone" />
        </div>
      </template>
    </StepperPanel>
    <StepperPanel header="Selezione Categoria">
      <template #content="{ prevCallback, nextCallback }">
        <div>
          <SelezionaCategorie />
        </div>
        <div class="flex justify-between gap-2 py-4">
          <Button class="bottone" label="Precedente" severity="secondary" @click="prevCallback" />
          <Button class="bottone" label="Successivo" @click="nextCallback" />
        </div>
      </template>
    </StepperPanel>
    <StepperPanel header="Dettagli Asta">
      <template #content="{ prevCallback, nextCallback }">
        <div>
          <h2>Review Content</h2>
        </div>
        <div class="flex justify-between gap-2 py-4">
          <Button class="bottone" label="Precedente" severity="secondary" @click="prevCallback" />
          <Button class="bottone" label="Successivo" @click="nextCallback" />
        </div>
      </template>
    </StepperPanel>

    <StepperPanel header="Revisione Dati Inseriti">
      <template #content="{ prevCallback }">
        <div>
          <h2>Final Content</h2>
        </div>
        <div class="flex justify-between gap-2 py-4">
          <Button class="bottone" label="Precedente" severity="secondary" @click="prevCallback" />
        </div>
      </template>
    </StepperPanel>
  </Stepper>
</template>

<script setup>
import Button from 'primevue/button';
import Stepper from 'primevue/stepper';
import StepperPanel from 'primevue/stepperpanel';
import { ref } from 'vue';
import SelezionaCategorie from '../components/stepper/CategorieCreaProdotto.vue';
import CreaProdotto from '../components/stepper/formCreaProdotto.vue';

const step = ref(1);
const loading = ref(false);

const asta = ref({
  nomeProdotto: '',
  descrizione: '',
  prezzoBase: 0,
  categorie: '[]',
  immagini: '[]',
  tipo: '',
  scadenza: '',
  estenzione: '',
  incrementoMinimo: 0,
});

const ricevutoProdotto = (contenuto) => {};
const finalize = () => {
  loading.value = true;
};
</script>

<style scoped>
.bottone {
  background-color: #cc85f5;
  padding: 10px 20px;
  color: white;
  border-radius: 5px;
  font-size: 1.1rem;
  font-weight: bold;
}
.bottone:hover {
  background-color: #7c3aed;
}
</style>

<template>
  <Steps class="py-1" :model="items" v-model:activeStep="active" />
  <CreaProdotto @update:active="updateCurrentForm($event)" v-if="active === 0" />
  <SelezionaCategorie @update:active="updateCurrentForm($event)" v-if="active === 1" />
</template>

<script setup>
import Steps from 'primevue/steps';
import { ref } from 'vue';
import SelezionaCategorie from '../components/stepper/CategorieCreaProdotto.vue';
import CreaProdotto from '../components/stepper/formCreaProdotto.vue';

const active = ref(0);
const loading = ref(false);

const updateCurrentForm = (value) => {
  active.value = value;
};

const items = ref([
  {
    label: 'Descrizione Prodotto',
  },
  {
    label: 'Selezione Categorie',
  },
  {
    label: 'Dettagli Asta',
  },
  {
    label: 'Revisione Dati Inseriti',
  },
]);

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

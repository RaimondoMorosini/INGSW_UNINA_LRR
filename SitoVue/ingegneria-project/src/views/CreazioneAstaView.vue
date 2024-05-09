<template>
  <Steps class="py-2 min-w-4col" :model="items" v-model:activeStep="active" />
  <CreaProdotto
    @update:active="updateCurrentForm($event)"
    v-if="active === 0"
  />
  <SelezionaFiltri @update:active="updateCurrentForm($event)" v-if="active === 1" />
  <SelezioneTipoAsta @update:active="updateCurrentForm($event)" v-if="active === 2" />
  <Review @update:active="updateCurrentForm($event)" v-if="active === 3"/>

</template>

<script setup>
import Steps from 'primevue/steps';
import { ref } from 'vue';
import SelezioneTipoAsta from '../components/stepper/dettagliCreaAsta.vue';
import CreaProdotto from '../components/stepper/formCreaProdotto.vue';
import SelezionaFiltri from '../components/stepper/impostaFiltriCreaProdotto.vue';
import Review from '../components/stepper/reviewAsta.vue';

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
    label: 'Selezione Filtri',
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

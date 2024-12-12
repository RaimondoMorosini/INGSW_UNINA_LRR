<template>
  <div class="p-4 max-w-lg mx-auto bg-white shadow-md rounded-md">
    <h2 class="text-lg font-bold mb-4">Modifica Profilo</h2>
    <form @submit.prevent="salvaProfilo">
      <!-- Modifica Immagine Profilo -->

      <ModificaImmagineProfilo :immagineOriginale="dati.immagine" @immagineModificata="aggiornaImmagine" />


      <!-- Nome -->
      <div class="mb-4">
        <label class="block text-sm font-medium text-gray-700">Nome</label>
        <InputText v-model="dati.nome" class="w-full" :class="{ 'p-invalid': validato && errori.nome }" />
        <p v-if="validato && errori.nome" class="text-red-500 text-sm mt-1">{{ errori.nome }}</p>
      </div>

      <!-- Cognome -->
      <div class="mb-4">
        <label class="block text-sm font-medium text-gray-700">Cognome</label>
        <InputText v-model="dati.cognome" class="w-full" :class="{ 'p-invalid': validato && errori.cognome }" />
        <p v-if="validato && errori.cognome" class="text-red-500 text-sm mt-1">{{ errori.cognome }}</p>
      </div>

      <!-- Bio -->
      <div class="mb-4">
        <label class="block text-sm font-medium text-gray-700">Bio</label>
        <Textarea v-model="dati.bio" rows="4" class="w-full" :class="{ 'p-invalid': validato && errori.bio }" />
        <p v-if="validato && errori.bio" class="text-red-500 text-sm mt-1">{{ errori.bio }}</p>
      </div>

      <!-- Città -->
      <div class="mb-4">
        <label class="block text-sm font-medium text-gray-700">Città</label>
        <Select v-model="dati.citta" :options="ComuniItalia" filter class="w-full"
          :class="{ 'p-invalid': validato && errori.citta }" />
      </div>


      <!-- Link ai Social -->
      <div class="mb-4">
        <div class="flex flex-row items-center justify-between mb-2">
          <label class="block text-sm font-medium text-gray-700">Link ai Social</label>
          <Button label="Aggiungi Link" icon="pi pi-plus" @click="aggiungiLink" class="p-button-sm p-button-text" />
        </div>
        <div v-for="(link, indice) in dati.linksSocial" :key="indice" class="mt-2 flex flex-col">
          <div class="flex items-center space-x-2">
            <InputText type="url" v-model="dati.linksSocial[indice]" class="w-full"
              :class="{ 'p-invalid': validato && errori.linksSocial[indice] }" />
            <Button @click="rimuoviLink(indice)">
              <i class="pi pi-times"></i>
              <span class="hidden  sm:inline">Rimuovi</span>
            </Button>
          </div>
          <p v-if="validato && errori.linksSocial[indice]" class="text-red-500 text-sm mt-1">{{
            errori.linksSocial[indice] }}</p>
        </div>
      </div>

      <!-- Pulsante Salva -->
      <Button label="Salva" icon="pi pi-save" type="submit" class="w-full p-button-primary" />
    </form>
  </div>
  <img :src="immagineAnteprima || immagineOriginale" alt="Anteprima" class="w-24 h-24 rounded-full object-cover" />

</template>

<script setup>
import { ref, reactive, computed } from 'vue';
import { Textarea, InputText, Button, Select } from 'primevue';
import ComuniItalia from '../assets/json/ComuniItalia.json'
import ModificaImmagineProfilo from './ModificaImmagineProfilo.vue';

// Dati iniziali
const datiOriginali = reactive({
  immagine: 'https://via.placeholder.com/150',
  nome: 'Mario',
  cognome: 'Rossi',
  bio: 'Questa è la mia bio.',
  citta: 'Italia IT',
  linksSocial: ['https://twitter.com/mario', 'https://linkedin.com/in/mario'],
});

// Stato reattivo per il form
const dati = reactive({ ...datiOriginali });
const immagineAnteprima = ref(null);
const validato = ref(false);


// Aggiungere un nuovo link social
function aggiungiLink() {
  dati.linksSocial.unshift('');
}

// Rimuovere un link social
function rimuoviLink(indice) {
  dati.linksSocial.splice(indice, 1);
}

// Validazione tramite computed
const errori = computed(() => {
  const erroriLocali = {
    nome: dati.nome.trim() ? '' : 'Il nome è obbligatorio.',
    cognome: dati.cognome.trim() ? '' : 'Il cognome è obbligatorio.',
    bio: dati.bio.trim() ? '' : 'La bio è obbligatoria.',
    linksSocial: dati.linksSocial.map((link) => {
      if (!link.trim()) {
        return 'Il link non può essere vuoto.';
      }
      if (!/^https?:\/\//.test(link)) {
        return 'Il link deve essere un URL valido.';
      }
      return '';
    }),
  };
  return erroriLocali;
});
function aggiornaImmagine(nuovaImmagine) {
  immagineAnteprima.value = nuovaImmagine;
}

// Validazione finale e salvataggio
function salvaProfilo() {
  validato.value = true;
  const valido = !errori.value.nome && !errori.value.cognome && !errori.value.bio && errori.value.linksSocial.every((e) => !e);
  if (valido) {
    console.log('Profilo salvato:', dati);
    alert('Profilo aggiornato con successo!');
    // Copiare i dati aggiornati come originali
    Object.assign(datiOriginali, dati);
  } else {
    alert('Si prega di correggere gli errori prima di salvare.');
  }
}
</script>


<style scoped>
.p-invalid {
  border-color: #f56565 !important;
}
</style>

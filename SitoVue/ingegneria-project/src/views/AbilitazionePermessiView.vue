<template>
  <div class="max-w-md mx-auto p-4">
    <h2 class="text-xl font-bold mb-4">Modulo Aziendale</h2>
    <form @submit.prevent="submitForm" class="space-y-4">
      <div>
        <label for="partitaIva" class="block font-semibold">Partita IVA</label>
        <InputText 
          id="partitaIva" 
          v-model="formData.partitaIva" 
          class="w-full" 
          @blur="markAsTouched('partitaIva')" 
        />
        <small v-if="isFieldInvalid('partitaIva')" class="text-red-500">{{ computedErrors.partitaIva }}</small>
      </div>

      <div>
        <label for="codiceFiscale" class="block font-semibold">Codice Fiscale</label>
        <InputText 
          id="codiceFiscale" 
          v-model="formData.codiceFiscale" 
          class="w-full" 
          @blur="markAsTouched('codiceFiscale')" 
        />
        <small v-if="isFieldInvalid('codiceFiscale')" class="text-red-500">{{ computedErrors.codiceFiscale }}</small>
      </div>

      <div>
        <label for="telefono" class="block font-semibold">Numero di Telefono</label>
        <InputText 
          id="telefono" 
          v-model="formData.telefono" 
          class="w-full" 
          @blur="markAsTouched('telefono')" 
        />
        <small v-if="isFieldInvalid('telefono')" class="text-red-500">{{ computedErrors.telefono }}</small>
      </div>

      <div>
        <label for="nomeAzienda" class="block font-semibold">Nome Azienda</label>
        <InputText 
          id="nomeAzienda" 
          v-model="formData.nomeAzienda" 
          class="w-full" 
          @blur="markAsTouched('nomeAzienda')" 
        />
        <small v-if="isFieldInvalid('nomeAzienda')" class="text-red-500">{{ computedErrors.nomeAzienda }}</small>
      </div>

      <Button label="Invia" type="submit" class="w-full" />
    </form>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import InputText from 'primevue/inputtext';
import Button from 'primevue/button';
import { DiventaVenditore } from '../service/profiloService';

// Dati del modulo
const formData = ref({
  partitaIva: '',
  codiceFiscale: '',
  telefono: '',
  nomeAzienda: ''
});

// Stato dei campi "toccati"
const touchedFields = ref({
  partitaIva: false,
  codiceFiscale: false,
  telefono: false,
  nomeAzienda: false
});

// Funzione per contrassegnare un campo come "toccato"
const markAsTouched = (field) => {
  touchedFields.value[field] = true;
};

// Proprietà calcolata per gli errori
const computedErrors = computed(() => {
  const errors = {};
  if (!formData.value.partitaIva) {
    errors.partitaIva = 'La partita IVA è obbligatoria.';
  }else if (isNaN(formData.value.partitaIva)) {
    errors.partitaIva = 'La partita IVA deve contenere solo cifre.';
  }else if (formData.value.partitaIva.length !== 11) {
    errors.partitaIva = 'La partita IVA deve essere composta da 11 cifre.';
  }
  if (!formData.value.codiceFiscale) {
    errors.codiceFiscale = 'Il codice fiscale è obbligatorio.';
  }else if (formData.value.codiceFiscale.length < 16) {
    errors.codiceFiscale = 'Il codice fiscale deve essere di almeno 16 caratteri.';
  }
  if (!formData.value.telefono) {
    errors.telefono = 'Il numero di telefono è obbligatorio.';
  }else if (isNaN(formData.value.telefono)) {
    errors.telefono = 'Il numero di telefono deve essere numerico.';
  }else if (formData.value.telefono.length < 10) {
    errors.telefono = 'Il numero di telefono deve essere di almeno 10 cifre.';
  }
  if (!formData.value.nomeAzienda) {
    errors.nomeAzienda = 'Il nome dell\'azienda è obbligatorio.';
  }else if (formData.value.nomeAzienda.length < 3) {
    errors.nomeAzienda = 'Il nome dell\'azienda deve essere di almeno 3 caratteri.';
  }
  return errors;
});

// Funzione per determinare se un campo è invalido e deve mostrare l'errore
const isFieldInvalid = (field) => {
  return touchedFields.value[field] && computedErrors.value[field];
};

// Gestione invio modulo
const submitForm = () => {
  Object.keys(touchedFields.value).forEach((field) => {
    touchedFields.value[field] = true; // Contrassegna tutti i campi come "toccati"
  });

  if (Object.keys(computedErrors.value).length === 0) {
    DiventaVenditore(formData.value.partitaIva, formData.value.codiceFiscale, formData.value.telefono, formData.value.nomeAzienda);
    console.log('Dati inviati:', formData.value);
  }
};
</script>

<style>
.text-red-500 {
  color: red;
}
</style>

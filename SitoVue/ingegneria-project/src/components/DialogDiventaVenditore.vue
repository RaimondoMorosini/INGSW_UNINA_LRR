<template>
  <Dialog v-model:visible="dialogVisible" header="Compila il Modulo" @hide="resetForm">
    <AbilitazionePermessiView/>
  </Dialog>
</template>

<script setup>
import  {ref, onMounted } from 'vue';
import  Dialog  from 'primevue/dialog';
import  Button  from 'primevue/button';
import InputText from 'primevue/inputtext';
import eventBus from '../scripts/eventBus';
import AbilitazionePermessiView from '../views/AbilitazionePermessiView.vue';

const dialogVisible = ref(false);
const formData = ref({ name: '', email: '' });

// Funzione per aprire il dialog
const openDialog = () => {
  console.log('Dialog aperto tramite evento callback');
  dialogVisible.value = true;
};

// Ascolta l'evento 'open-dialog' all'inizio
onMounted(() => {
  eventBus.on('open-dialog', openDialog);
});
</script>
<template>
  <Dialog v-model:visible="dialogVisible" header="Compila il Modulo" @hide="resetForm">
    <AbilitazionePermessiView/>
  </Dialog>
</template>

<script setup>
import  {ref, onMounted } from 'vue';
import  Dialog  from 'primevue/dialog';
import eventBus from '../scripts/eventBus';
import AbilitazionePermessiView from '../views/AbilitazionePermessiView.vue';

const dialogVisible = ref(false);

// Funzione per aprire il dialog
const openDialog = () => {
  console.log('Dialog aperto tramite evento callback');
  dialogVisible.value = true;
};
const ChiudiDialog = () => {
  dialogVisible.value = false;
  alert('Form inviato con successo');
  
};

// Ascolta l'evento 'open-dialog' all'inizio
onMounted(() => {
  eventBus.on('open-FormDiventaVenditore', openDialog);
  eventBus.on('close-open-FormDiventaVenditore', ChiudiDialog);
});
</script>
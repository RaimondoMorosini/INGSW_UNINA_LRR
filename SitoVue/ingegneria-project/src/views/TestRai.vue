<template>
  <div>
    <SessionExpiredDialog
      :visible="isPopupVisible"
      @update:visible="isPopupVisible = $event"
    />
    <button @click="checkSession">Check Session</button>
    ispopupvisible: {{ isPopupVisible }}
    usestore visible: {{ dialogStore.isDialogVisible }}
  </div>
</template>

<script setup>
import { ref, onMounted,watch } from 'vue';
import SessionExpiredDialog from '../components/SessionExpiredDialog.vue';
import { useDialogStore } from '../stores/dialogStore';

const dialogStore = useDialogStore();
const isPopupVisible = ref(dialogStore.isDialogVisible);


const checkSession = () => {
  const token = localStorage.getItem('token'); // Sostituisci con la tua logica
  console.log(token) // Aggiungi logica per controllare la scadenza del token

  
    isPopupVisible.value = true; // Mostra il popup se la sessione è scaduta
  
};

watch(
  () => dialogStore.isDialogVisible,
  (newVal) => {
    isPopupVisible.value = newVal;
  }
);
watch(
  () => isPopupVisible.value,
  (newVal) => {
    dialogStore.isDialogVisible=newVal;
  }
);

onMounted(() => {
  checkSession();
});
</script>

<style>
/* Aggiungi eventuali stili personalizzati qui */
</style>
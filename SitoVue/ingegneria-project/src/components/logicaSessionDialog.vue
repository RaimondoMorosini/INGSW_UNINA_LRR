<template>
    <div>
        <SessionExpiredDialog :visible="isPopupVisible" @update:visible="isPopupVisible = $event" />
    </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue';
import SessionExpiredDialog from '../components/SessionExpiredDialog.vue';
import { useDialogStore } from '../stores/dialogStore';
import { isTokenScaduto } from '../service/authService';
import { useProfiloStore } from '../stores/profiloStore';
const dialogStore = useDialogStore();
const isPopupVisible = ref(dialogStore.isDialogVisible);

const checkSession = () => {
    if (isTokenScaduto() && useProfiloStore().isProfiloPresente()) {
        console.log('Sessione scaduta');
        isPopupVisible.value = true; // Mostra il popup se la sessione è scaduta
    }
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
        dialogStore.isDialogVisible = newVal;
    }
);

onMounted(() => {
    checkSession();
});
</script>

<style>
/* Aggiungi eventuali stili personalizzati qui */
</style>

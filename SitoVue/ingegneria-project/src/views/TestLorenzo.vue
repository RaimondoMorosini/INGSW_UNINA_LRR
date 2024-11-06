<template>
    <div>
        <ImageUploader
            :storeInstance="profiloStoreInstance.profilo.immagineSalvata"
            :multi="false"
        />
    </div>
</template>

<script setup>
import { useToast } from 'primevue/usetoast'; // Import the useToast hook
import Button from 'primevue/button';
import ImageUploader from '../components/CreaAstaStepper/ImageUploader.vue';
import { useProfiloStore } from '../stores/profiloStore.js';

const profiloStoreInstance = useProfiloStore();

const toast = useToast();

const showTopLeft = () => {
    toast.add({
        severity: 'info',
        summary: 'Info Message',
        detail: 'Message Content',
        group: 'tr',
        life: 3000,
    });
};
</script>

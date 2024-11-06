<template>
    <Button severity="secondary" @click="handleLogout" class="button__logout"
        ><span class="font-bold">Log Out</span></Button
    >
</template>

<script setup>
import Button from 'primevue/button';
import { useProfiloStore } from '../../stores/profiloStore.js';
import { eliminaDato } from '@/scripts/DatiUtils.js';
import { useRouter } from 'vue-router';

const router = useRouter();
const storeinstance = useProfiloStore();
function handleLogout() {
    eliminaDato('token');
    storeinstance.logout();
    console.log('storeinstance.profilo.isAutenticato', storeinstance.profilo.isAutenticato);
    if (router.currentRoute.value.name == 'profilo') {
        router.push({ name: 'home' });
    }
}
</script>

<style scoped>
.my-custom-button {
    background-color: var(--secondary-color);
    color: var(--secondary-color-text);
}

.my-custom-button:hover {
    --secondary-color: #3f3f46;
    --secondary-color-text: #d4d4d8; /* Optional: Text color for better contrast */
}
</style>

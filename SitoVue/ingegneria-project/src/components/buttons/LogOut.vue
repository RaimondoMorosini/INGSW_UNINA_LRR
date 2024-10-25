<template>
    <Button severity="secondary"  @click="handleLogout" class="button__logout "
        ><span class="font-bold ">Log Out</span></Button
    >
</template>

<script setup>
import Button from 'primevue/button';
import { useProfiloStore } from '../../stores/profiloStore.js';
import { logoutAndClean } from '../../service/authService.js';

function handleLogout() {
    console.log('logout');
}
    
</script>

<style scoped>
.my-custom-button {
  background-color: var(--secondary-color);
  color: var(--secondary-color-text);
}

.my-custom-button:hover {
    --secondary-color: #3F3F46; 
    --secondary-color-text: #D4D4D8;     /* Optional: Text color for better contrast */
}
</style>
<template>
    <Button severity="secondary"  @click="handleLogout" class="button__logout "
        ><span class="font-bold ">Log Out</span></Button
    >
</template>

<script setup>
import Button from 'primevue/button';
import { useAuth0 } from '@auth0/auth0-vue';

const { logout } = useAuth0();

const handleLogout = () =>
    console.log('Logging out...')
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
<template>
    <Button severity="secondary" @click="handleLogin" class="button__login"
        ><span class="font-bold ">Log In</span></Button
    >
</template>

<script setup>
import Button from 'primevue/button';
import { useAuth0 } from '@auth0/auth0-vue';
import { eliminaDato } from '../../scripts/DatiUtils';
import { useAstaStore } from '../../stores/astaStore';
const { loginWithRedirect } = useAuth0();
const storeinstance = useAstaStore();

const handleLogin = () => {
    eliminaDato('token');
    storeinstance.logout();

    loginWithRedirect({
        appState: {
            target: '/profilo',
        },
    });
};
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

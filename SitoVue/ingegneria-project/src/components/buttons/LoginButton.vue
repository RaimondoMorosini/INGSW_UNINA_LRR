<template>
    <Button @click="handleLogin" class="button__login ring-2 ring-slate-300/80"
        ><span class="font-bold text-[#1C1B22]">Log In </span></Button
    >
</template>

<script setup>
import Button from 'primevue/button';
import { useAuth0 } from '@auth0/auth0-vue';

const { loginWithRedirect } = useAuth0();

const handleLogin = () => {
    loginWithRedirect({
        appState: {
            target: '/profilo',
        },
    });
};
</script>

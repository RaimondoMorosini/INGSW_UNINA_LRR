<template>
    <Button @click="handleSignUp" class="button__sign-up ring-2 ring-slate-300/80"><span class="text-[#1C1B22] font-bold">Registati </span></Button>
</template>

<script setup>
import { useAuth0 } from '@auth0/auth0-vue';
import Button from 'primevue/button';

const { loginWithRedirect } = useAuth0();

const handleSignUp = () => {
    loginWithRedirect({
        appState: {
            target: '/profilo',
        },
        authorizationParams: {
            screen_hint: 'signup',
        },
    });
};
</script>

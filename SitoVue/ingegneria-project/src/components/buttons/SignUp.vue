<template>
    <Button severity="secondary" @click="handleSignUp" class="button__sign-up"
        ><span class="font-bold">Registati </span></Button
    >
</template>

<script setup>
import { useAuth0 } from '@auth0/auth0-vue';
import Button from 'primevue/button';

const { loginWithRedirect } = useAuth0();

const handleSignUp = () => {
    loginWithRedirect({
        authorizationParams: {
            screen_hint: 'signup',
        },
    });
};
</script>

<style scoped>
.my-custom-button {
    background-color: var(--secondary-color);
    color: var(--secondary-color-text);
}
</style>

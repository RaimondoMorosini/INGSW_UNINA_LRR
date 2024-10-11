<template>
    <Button @click="handleLogout" class="button__logout ring-2 ring-slate-300/80"><span class="  font-bold">Log Out </span></Button>
</template>

<script setup>
import Button from 'primevue/button';
import { useAuth0 } from '@auth0/auth0-vue';

const { logout } = useAuth0();

const handleLogout = () =>
    logout({
        logoutParams: {
            returnTo: window.location.origin,
        },
    });
</script>

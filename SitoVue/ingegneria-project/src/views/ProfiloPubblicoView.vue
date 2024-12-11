<template>
        <InfoProfiloPubblico :email="utente" />
</template>

<script setup>
import InfoProfiloPubblico from '../components/Profilo/InformazioneProfiloPubblico.vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const utente = route.params.utente;

</script>

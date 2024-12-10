<template>
        <InfoProfiloPubblico :email="utente" />
</template>

<script setup>
import { ref, onMounted } from 'vue';
import InfoProfiloPubblico from '../components/Profilo/InformazioneProfiloPubblico.vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const utente = route.params.utente;

</script>

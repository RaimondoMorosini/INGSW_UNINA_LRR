<template>
    <div v-for="carosello in caroselli" class="contenitore-carosello">
        <h1 class="text-4xl font-bold">{{ carosello.titoloCarosello }}</h1>
        <CaroselloProdotti :propProdotti="carosello.prodotti" />
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import CaroselloProdotti from '../vetrinaAndHomepage/CaroselloProdotti.vue';
import { fetchCaroselli } from '../../scripts/prodottiVetrina';

const caroselli = ref([]);

onMounted(async () => {
    caroselli.value = await fetchCaroselli();
});
</script>

<style scoped>
.contenitore-carosello {
    margin-left: 2rem;
    margin-right: 2rem;
    margin-top: 2rem;
    padding: 1rem;
}
</style>

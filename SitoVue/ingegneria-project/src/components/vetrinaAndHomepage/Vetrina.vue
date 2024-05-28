<template>
    <div class="contenitore-carosello">
        <h1 class="font-bold text-4xl">Titolo carosello</h1>
        <CaroselloProdotti />
    </div>

    <div class="contenitore-carosello">
        <h1>Titolo carosello</h1>
    </div>
</template>

<script setup>
import CaroselloProdotti from '../vetrinaAndHomepage/CaroselloProdotti.vue';
</script>

<style scoped>
.contenitore-carosello {
    margin-left: 2rem;
    margin-right: 2rem;
    margin-top: 2rem;
    padding: 1rem;
}
</style>

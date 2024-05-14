<template>
    <div class="not-found">
        <h1>Errore 404</h1>
        <p>La pagina che stai cercando non esiste.</p>
        <div class="pt-4">
            <router-link
                :to="{ name: 'home' }"
                class="max-w-1col rounded bg-primario-300 px-2 py-1 hover:bg-primario-400"
                >Back to home</router-link
            >
        </div>
    </div>
</template>

<script setup>
import { RouterLink } from 'vue-router';
</script>

<style scoped>
.not-found {
    text-align: center;
    margin-top: 100px;
}

h1 {
    font-size: 36px;
    color: #ff0000;
}

p {
    font-size: 18px;
    color: #333333;
}
</style>

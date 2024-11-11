<template>
    <div class="not-found">
        <h1>Errore 404</h1>
        <p>La pagina che stai cercando non esiste.</p>
        <div class="pt-4">
            <Button as="router-link" to="/"
                ><span class="font-bold">Torna alla pagina principale</span></Button
            >
        </div>
    </div>
</template>

<script setup>
import Button from 'primevue/button';
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
}
</style>

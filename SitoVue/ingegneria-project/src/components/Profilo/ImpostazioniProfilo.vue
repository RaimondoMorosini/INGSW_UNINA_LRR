<template>
    <div>
        <h1>Impostazioni Profilo</h1>
        <form @submit="saveSettings" class="gap-53 flex flex-col px-5 py-3">
            <label for="name">Nome:</label>
            <input type="text" id="name" v-model="name" required />

            <label for="email">Email:</label>
            <input type="email" id="email" v-model="email" required />

            <label for="password">Password:</label>
            <input type="password" id="password" v-model="password" required />

            <button type="submit" classe="bottone">Salva</button>
        </form>
    </div>
</template>

<script setup>
import { ref } from 'vue';
const name = ref('');
const email = ref('');
const password = ref('');

import { useProfiloStore } from '../../stores/profiloStore';
const storeInstance = useProfiloStore();

function saveSettings() {}
</script>

<style scoped>
.bottone {
    background-color: #cc85f5;
    padding: 10px 20px;
    color: white;
    border-radius: 5px;
    font-size: 1.1rem;
    font-weight: bold;
    width: 50%;
    margin: 10px;
}
.bottone:hover {
    background-color: #7c3aed;
}
</style>

<template>
    <div>
        <h1>Questo è la pagine per modoficare la tua immagine profilo</h1>
        <p>Dati Personali</p>

        <ul>
            <li><strong>Nome:</strong> {{ nome }}</li>
            <li><strong>Cognome:</strong> {{ cognome }}</li>
            <li><strong>Indirizzo:</strong> {{ indirizzo }}</li>
            <li><strong>Bio:</strong> {{ bio }}</li>
            <li>
                <strong>Siti Social:</strong>
                <a :href="sitiSocial" target="_blank">{{ sitiSocial }}</a>
            </li>
        </ul>

        <button @click="ModificaProfilo">Modifica Profilo !!!</button>
        <input type="file" @change="onFileChange" />
        <div v-if="image">
            <h2>Anteprima Immagine</h2>
            <img :src="image" alt="Immagine Profilo" />
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { modificaProfiloPublico } from '../service/profiloService';

const image = ref(null);
const nomeImage = ref('');
const nome = 'Raimondosss';
const cognome = 'EL Morosss';
const indirizzo = 'Italiasss';
const bio = 'La Biossss';
const sitiSocial = 'WWW.sitoss.comss';

function ModificaProfilo() {
    console.log('Modifica Profilo in corso');
    console.log('IMG VALUE ' + image.value);
    modificaProfiloPublico(nome, cognome, indirizzo, bio, sitiSocial, image.value, nomeImage)
        .then((response) => {
            console.log('Modifica avvenuta con successo!!!: ' + response);
        })
        .catch((error) => {
            console.error('Errore durante la modifica del profilo: ', error);
        });
}

function onFileChange(event) {
    const file = event.target.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = (e) => {
            image.value = e.target.result;
            nomeImage.value = file.name;
            console.log('Nome del file: ' + nomeImage.value);
        };
        reader.readAsDataURL(file);
    }
}
</script>

<style scoped>
h1 {
    color: #42b983;
}

button {
    background-color: #425eb9;
    color: white;
    border: none;
    padding: 10px 20px;
    cursor: pointer;
}

button:hover {
    background-color: #358a6b;
}
</style>

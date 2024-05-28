<template>
    <div id="app">
        <div class="container">
            <input type="file" multiple @change="onFileChange" /><br /><br />

            <div class="drop-area" @dragover.prevent @dragenter.prevent @drop.prevent="onDrop">
                Trascina i file qui
            </div>

            <div class="jumbotron">
                <div class="immagini-container">
                    <div v-for="(immagine, indice) in immagini" :key="indice" class="immagine">
                        <button type="button" @click="rimuoviImmagine(indice)" class="btn-close">
                            &times;
                        </button>
                        <img class="preview img-thumbnail" :src="immagine.src" :alt="immagine.name"/>
                        <div class="img-name">{{ immagine.name }}</div>
                    </div>
                </div>
            </div>
        </div>

        <button @click="eseguiChiamataAxios">Esegui chiamata Axios</button>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { uploadImages } from '../scripts/ImageUploadService.js';
const immagini = ref([]);

function eseguiChiamataAxios() {
    console.log('sto eseguendo la chiamata Axios');
    // In questo caso, potresti voler implementare il codice per inviare l'immagine al server
    // e gestire la risposta qui
    console.log('Immagine caricata:', immagini.value);

    if (immagini.value === null) {
        alert("Seleziona un'immagine prima di caricare");
        return;
    }
    uploadImages(immagini.value)
        .then((response) => {
            console.log(response);
        })
        .catch((error) => {
            console.log(error);
        });
}
function onFileChange(e) {
    aggiungiFile(e.target.files);
}

function onDrop(e) {
    aggiungiFile(e.dataTransfer.files);
}

function aggiungiFile(files) {
    console.log('hai selezionato ', files.length, ' file');
    for (const element of files) {
        immagini.value.push({ file: element, src: null, name: element.name });
    }
    console.log('immagini: ', immagini.value);
    immagini.value.forEach((immagine, indice) => {
        if (!immagine.src) {
            const lettore = new FileReader();
            lettore.addEventListener(
                'load',
                () => {
                    immagini.value[indice].src = lettore.result;
                    console.log('lettore.result di indice ', indice, ' ', lettore.result);
                },
                false
            );
            lettore.readAsDataURL(immagine.file);
        }
    });
}

function rimuoviImmagine(indice) {
    console.log("sto rimuovendo l'immagine con indice ", indice);
    immagini.value.splice(indice, 1);
}
</script>

<style scoped>
.container {
    margin: 20px;
}

.drop-area {
    width: 100%;
    height: 150px;
    display: flex;
    align-items: center;
    justify-content: center;
    border: 2px dashed #ccc;
    margin-bottom: 20px;
    cursor: pointer;
}

.drop-area:hover {
    background-color: #f8f9fa;
}

.jumbotron {
    padding: 20px;
    background-color: #f8f9fa;
}

.immagini-container {
    display: flex;
    flex-wrap: wrap;
    gap: 10px;
}

.immagine {
    position: relative;
    flex: 1 1 calc(33.333% - 10px); /* Tre immagini per riga con uno spazio di 10px */
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-bottom: 10px;
}

.btn-close {
    position: absolute;
    top: 5px;
    right: 5px;
    background: none;
    border: none;
    font-size: 1.5rem;
    cursor: pointer;
    color: #dc3545;
}

.preview {
    max-width: 100%;
    height: auto;
    margin-bottom: 5px;
}

.img-name {
    word-break: break-all;
    text-align: center;
}
</style>

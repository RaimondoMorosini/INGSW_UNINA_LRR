<template>
    <div id="app">
        <div
            class="container flex w-auto flex-col content-center items-center justify-center px-5 py-2"
        >
            <div class="drop-area" @dragover.prevent @dragenter.prevent @drop.prevent="onDrop">
                <label class="custom-file-upload flex flex-col items-center gap-0 text-4xl">
                    <input type="file" accept="image/*" multiple @change="onFileChange" />
                    <i class="bi bi-camera"></i>
                    <i class="bi bi-box-arrow-in-down"></i>
                </label>
            </div>

            <div class="jumbotron w-fit shadow">
                <div class="immagini-container grid grid-cols-4 justify-between gap-2">
                    <div v-if="isEmpty">
                        <img
                            src="../../assets/img/placeholder/placeholder.png"
                            alt="Immagine temporanea placeholder"
                            class="text-center shadow"
                        />
                    </div>
                    <div
                        v-for="(immagine, indice) in store.asta.immaginiSalvate"
                        :key="indice"
                        class="immagine shadow"
                    >
                        <button type="button" @click="rimuoviImmagine(indice)" class="btn-close">
                            &times;
                        </button>
                        <img
                            class="preview h-28 shadow lg:h-32"
                            :src="immagine.src"
                            alt="Catalogo immagini prodotto"
                        />
                        <div class="img-name">{{ immagine.name }}</div>
                        <div>file {{ immagine.file }}</div>
                    </div>
                </div>
            </div>
        </div>

        <button
            @click="eseguiChiamataAxios"
            class="my-5 h-fit w-[100%] rounded bg-primario-100 px-5 py-5 text-xl font-semibold text-white"
        >
            Esegui chiamata Axios
        </button>
    </div>
</template>

<script setup>
import { ref, onUnmounted, onMounted, computed } from 'vue';
import { uploadImages } from '../../scripts/ImageUploadService.js';
import { useAstaStore } from '../../stores/astaStore';
import { inserisciDato, getDato } from '../../scripts/DatiUtils.js';
const store = useAstaStore();
const immagini = ref([]);
const isEmpty = computed(() => {
    return store.asta.immaginiSalvate.length === 0;
});

function eseguiChiamataAxios() {
    console.log('sto eseguendo la chiamata Axios');
    // In questo caso, potresti voler implementare il codice per inviare l'immagine al server
    // e gestire la risposta qui
    console.log('Immagine caricata:', store.asta.immaginiSalvate);

    if (store.asta.immaginiSalvate === null) {
        alert("Seleziona un'immagine prima di caricare");
        return;
    }
    uploadImages()
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
        console.log('file: ', element);
        store.asta.immaginiSalvate.push({ file: element, src: null, name: element.name });
    }
    console.log('immagini: ', store.asta.immaginiSalvate);
    store.asta.immaginiSalvate.forEach((immagine, indice) => {
        if (!immagine.src) {
            const lettore = new FileReader();
            lettore.addEventListener(
                'load',
                () => {
                    store.asta.immaginiSalvate[indice].src = lettore.result;
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
    store.asta.immaginiSalvate.splice(indice, 1);
}

onMounted(() => {
    //store.asta.immaginiSalvate = getDato('immaginiSalvate');
});
onUnmounted(() => {
    inserisciDato('immaginiSalvate', store.asta.immaginiSalvate);
});
</script>

<style scoped>
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
    top: 2px;
    right: 5px;
    background: none;
    border: none;
    font-size: 1.8rem;
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

input[type='file'] {
    display: none;
}

.custom-file-upload {
    background-color: #cc85f5;
    padding: 10px 5px;
    color: white;
    border-radius: 5px;
    font-size: 2.5rem;
    font-weight: bold;
    width: 50%;
    cursor: pointer;
}
.custom-file-upload:hover {
    background-color: #7c3aed;
}

.icon-size {
    font-size: 50px;
}
</style>

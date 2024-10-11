<template>
    <div id="app">
        <div class="container flex w-full flex-col content-center items-center justify-center py-2">
            <div class="drop-area ring-1 ring-primario-400" @dragover.prevent @dragenter.prevent @drop.prevent="onDrop">
                <label class="custom-file-upload bg-primario-400 hover:bg-primario-300 flex flex-col items-center gap-0 text-4xl">
                    <input type="file" accept="image/*" multiple @change="onFileChange" />
                    <i class="pi pi-camera py-2" style="font-size: 2.5rem"></i>
                    <i class="pi pi-file-plus py-2" style="font-size: 2.5rem"></i>
                </label>
            </div>

            <div
                v-if="isEmpty"
                class="flex w-[100%] items-center justify-center ring-2 ring-[#cd87f6]"
            >
                <img
                    src="../../assets/img/placeholder/placeholder.png"
                    alt="Immagine temporanea placeholder"
                    class="immagine px-2 py-2 shadow lg:px-64"
                />
            </div>
            <div v-else class="jumbotron ring-1 ring-primario-400 w-[100%] shadow">
                <div class="immagini-container grid grid-cols-4 justify-between gap-2">
                    <div
                        v-for="(immagine, indice) in store.asta.immaginiSalvate"
                        :key="indice"
                        class="immagine shadow"
                    >
                        
                        <Button size="small" severity="danger" class="absolute left-0" outlined icon="pi pi-times" @click="rimuoviImmagine(indice)"></Button>
                        <div class="p-2 flex rounded text-center align-middle justify-center bg-slate-50/10">
                            <img
                            class="preview shadow lg:max-h-64 max-h-64 "
                            :src="immagine.src"
                            alt="Catalogo immagini prodotto"
                        />
                        </div>
                        <div class="img-name">{{ immagine.name }}</div>
                        <div>file {{ immagine.file }}</div>
                    </div>
                </div>
            </div>
        </div>

        <!--
        TODO: Da togliere quanto non più necessari test
        <button
            @click="eseguiChiamataAxios"
            class="my-5 h-fit w-[100%] rounded bg-primario-400 px-5 py-5 text-xl font-semibold text-white"
        >
            Esegui chiamata Axios
        </button>
        -->
    </div>
</template>

<script setup>
import Button from 'primevue/button'
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
    margin-bottom: 20px;
    cursor: pointer;
}

.jumbotron {
    padding: 20px;
}

.immagini-container {
    gap: 10px;
}

.immagine {
    position: relative;
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-bottom: 10px;
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
    padding: 10px 5px;
    color: white;
    border-radius: 5px;
    font-size: 2.5rem;
    font-weight: bold;
    width: 50%;
    cursor: pointer;
}

.icon-size {
    font-size: 50px;
}
</style>

<template>
    <div id="app">
        <div class="container flex w-full flex-col content-center items-center justify-center py-2">
            <div
                class="drop-area rounded ring-1 ring-primario-400"
                @dragover.prevent
                @dragenter.prevent
                @drop.prevent="onDrop"
            >
                <label
                    class="custom-file-upload flex flex-col items-center gap-0 bg-primario-400 text-4xl hover:bg-primario-300"
                >
                    <input type="file" accept="image/*" multiple @change="onFileChange" />
                    <i class="pi pi-camera py-2" style="font-size: 2.5rem"></i>
                    <i class="pi pi-file-plus py-2" style="font-size: 2.5rem"></i>
                </label>
            </div>

            <div
                v-if="isEmpty"
                class="flex w-[100%] items-center justify-center rounded shadow ring-2 ring-primario-400"
            >
                <img
                    src="../../assets/img/placeholder/placeholder.png"
                    alt="Immagine temporanea placeholder"
                    class="immagine px-2 py-2 shadow lg:px-64"
                />
            </div>
            <div v-else class="jumbotron w-[100%] rounded shadow ring-1 ring-primario-400">
                <div class="immagini-container grid grid-cols-4 gap-2">
                    <div
                        v-for="(immagine, indice) in store.asta.immaginiSalvate"
                        :key="indice"
                        class="immagine shadow"
                    >
                        <Button
                            size="small"
                            severity="danger"
                            class="absolute right-0 top-0"
                            outlined
                            icon="pi pi-times"
                            @click="rimuoviImmagine(indice)"
                        ></Button>
                        <div
                            class="flex justify-center rounded  bg-slate-200/20 p-2 text-center align-middle"
                        >
                            <img
                                class="preview max-h-64 shadow lg:max-h-64"
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

        
    </div>
</template>

<script setup>
import Button from 'primevue/button';
import { ref, onUnmounted, onMounted, computed } from 'vue';
import { useAstaStore } from '../../stores/astaStore';
import { inserisciDato, getDato } from '../../scripts/DatiUtils.js';
const store = useAstaStore();
const isEmpty = computed(() => {
    return store.asta.immaginiSalvate.length === 0;
});

function onFileChange(e) {
    aggiungiFile(e.target.files);
    e.target.value = '';
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
    store.asta.immaginiSalvate.forEach((immagine, indice) => {
        if (!immagine.src) {
            const lettore = new FileReader();
            lettore.addEventListener(
                'load',
                () => {
                    store.asta.immaginiSalvate[indice].src = lettore.result;
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

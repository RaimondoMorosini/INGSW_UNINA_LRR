
<script setup>
import Card from 'primevue/card';
import Button from 'primevue/button';
import { onUnmounted, onMounted, computed } from 'vue';
import { inserisciDato } from '../../scripts/DatiUtils.js';

//Ogni Store che contiene immagini
const props = defineProps({
    storeInstance: Object,
});

const isEmpty = computed(() => {
    return props.storeInstance.length === 0;
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
        props.storeInstance.push({ file: element, src: null, name: element.name });
    }

    props.storeInstance.forEach((immagine, indice) => {
        if (!immagine.src) {
            const lettore = new FileReader();
            lettore.addEventListener(
                'load',
                () => {
                    props.storeInstance[indice].src = lettore.result;
                },
                false
            );
            lettore.readAsDataURL(immagine.file);
        }
    });
}

function rimuoviImmagine(indice) {
    console.log("sto rimuovendo l'immagine con indice ", indice);
    props.storeInstance.splice(indice, 1);
}

onMounted(() => {
    //props.storeInstance = getDato('immaginiSalvate');
});
onUnmounted(() => {
    inserisciDato('immaginiSalvate', props.storeInstance);
});
</script>

<template>
    <Card fluid class="flex flex-col items-center justify-center">
        <template #header>
            <h2 class="text-xl font-bold">Carica Immagini</h2>
        </template>

        <template #content>
            <!-- Area di caricamento immagini -->
            <div
                class="drop-area w-full h-36 flex items-center justify-center rounded ring-1 ring-primario-500 mb-5"
                @dragover.prevent
                @dragenter.prevent
                @drop.prevent="onDrop"
            >
                <label class="custom-file-upload flex flex-col items-center gap-2 bg-primario-500 text-4xl hover:bg-primario-600 cursor-pointer p-4 rounded">
                    <input type="file" accept="image/*" @change="onFileChange" class="hidden" />
                    <i class="pi pi-camera text-white" style="font-size: 2.5rem"></i>
                    <i class="pi pi-file-plus text-white" style="font-size: 2.5rem"></i>
                </label>
            </div>

            <!-- Placeholder quando non ci sono immagini -->
            <div v-if="isEmpty" class="flex flex-col items-center justify-center rounded shadow ring-2 ring-primario-500 mb-5 p-4">
                <p class="text-center mt-2">Nessuna immagine caricata. Usa l'area sopra per caricare le immagini.</p>
                <img
                    src="../../assets/img/placeholder/placeholder.png"
                    alt="Immagine temporanea placeholder"
                    class="px-2 py-2 shadow max-h-96 w-full object-cover"
                />
            </div>

            <!-- Visualizzazione delle immagini caricate -->
            <div v-else class="jumbotron rounded shadow ring-1 ring-primario-500 p-5">
                <div class="immagini-container grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 gap-4">
                    <div
                        v-for="(immagine, indice) in props.storeInstance"
                        :key="indice"
                        class="immagine relative shadow"
                    >
                        <Button
                            size="small"
                            severity="danger"
                            class="absolute right-2 top-2 z-10"
                            outlined
                            icon="pi pi-times"
                            @click="rimuoviImmagine(indice)"
                        />
                        <div class="flex justify-center rounded bg-slate-200/20 p-2 text-center">
                            <img
                                class="preview max-h-64 w-full object-cover shadow"
                                :src="immagine.src"
                                alt="Catalogo immagini prodotto"
                            />
                        </div>
                    </div>
                </div>
            </div>
        </template>
    </Card>
</template>

<style scoped>
.drop-area {
    transition: background-color 0.3s ease;
}
.drop-area:hover {
    background-color: rgba(255, 255, 255, 0.1); /* Effetto hover per l'area di caricamento */
}
</style>
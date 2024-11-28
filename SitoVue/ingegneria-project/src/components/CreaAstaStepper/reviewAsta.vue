<template>
    <div class="flex flex-col items-start justify-center gap-3 px-5 py-3 lg:flex-row">
        <Card style="width: 100%">
            <template #title>
                <h2>Revisione Dati Inseriti</h2>
            </template>
            <template #content>
                <div class="flex flex-col gap-2">
                    <div class="flex flex-row gap-2">
                        <span class="label">Nome Prodotto</span>
                        <span class="campo">{{ storeInstance.asta.nomeProdotto }}</span>
                    </div>
                    <div class="flex flex-row gap-2">
                        <span class="label">Descrizione Prodotto</span>
                        <span class="campo">{{ storeInstance.asta.descrizione }}</span>
                    </div>
                    <div class="flex flex-row gap-2">
                        <span class="label">Prezzo Base</span>
                        <span class="campo">{{ storeInstance.asta.prezzoBase }} €</span>
                    </div>
                    <div class="flex flex-row gap-2">
                        <span class="label">Categoria</span>
                        <span class="campo">{{ categoriaInviata }}</span>
                    </div>
                    <div class="flex flex-row gap-2">
                        <span class="label">Tipo Asta</span>
                        <span class="campo">{{ tipoAstaNew }}</span>
                    </div>
                    <div class="flex flex-row gap-2">
                        <span class="label">Scadenza Asta</span>
                        <span class="campo">{{ dateScadenza.toLocaleString().split(',')[0] }}</span>
                    </div>
                    <div v-if="tipoAsta == asta_inglese" class="flex flex-row gap-2">
                        <span class="label">Durata Estensione</span>
                        <span class="campo">{{ storeInstance.asta.durataEstensione }} ore</span>
                    </div>
                    <div v-if="tipoAsta == asta_inglese" class="flex flex-row gap-2">
                        <span class="label">Incremento Minimo</span>
                        <span class="campo">{{ storeInstance.asta.incrementoMinimo }} €</span>
                    </div>
                </div>
            </template>
        </Card>
        <Card style="width: 100%">
            <template #title>
                <h2>Immagini</h2>
            </template>
            <template #content>
                <div class="w-[100%] items-center gap-2 rounded bg-slate-200 px-5 py-3">
                    <div class="flex flex-col">
                        <label for="cover"><h1>Immagine di Copertina</h1></label>
                        <br />
                        <img
                            id="cover"
                            v-if="storeInstance.asta.immaginiSalvate?.length > 0"
                            :src="storeInstance.asta?.immaginiSalvate[0].src"
                            alt="Immagine Copertina"
                            class="w-[20rem] rounded shadow ring-2 ring-primario-400 lg:w-[24rem]"
                        />
                    </div>

                    <div class="grid grid-cols-4 gap-2">
                        <div
                            v-for="image in storeInstance.asta.immaginiSalvate"
                            class="mr-5 rounded ring-0 ring-primario-400"
                        >
                            <br />
                            <div>
                                <div class="flex flex-row rounded">
                                    <Button
                                        outlined
                                        severity="contrast"
                                        icon="pi pi-expand"
                                        size="small"
                                        @click="toFront(image)"
                                    />
                                    <img
                                        :src="image.src"
                                        alt="Catalogo immagini prodotto"
                                        class="h-[10rem] rounded-r shadow lg:h-[15rem]"
                                    />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </template>
        </Card>

        <div class="buttonArea flex w-[100%] flex-row justify-around">
            <Button class="w-[45%]" size="large" @click="goToPreviousForm"
                ><span class="font-bold"><i class="pi pi-arrow-left"></i> Precedente</span></Button
            >
            <Button class="sp-button w-[45%]" size="large" @click="gestioneInvio"
                ><span class="font-bold">Finalizza <i class="pi pi-check"></i></span
            ></Button>
        </div>
    </div>
</template>

<script setup>
import Card from 'primevue/card';
import Button from 'primevue/button';
import { defineEmits, ref } from 'vue';
import { creaAsta } from '../../service/astaService.js';
import { useAstaStore } from '../../stores/astaStore.js';
import { onMounted } from 'vue';

function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

const storeInstance = useAstaStore();

//IMPORTANTE: se in futuro le categorie diverranno multiple, sostituire questo con Object.keys(storeInstance.asta.categoria)
const categoriaSelezionata = function (obj) {
    let keys = '';
    for (let key in obj) {
        keys = key;
    }
    return keys;
};

const tipoAsta = storeInstance.asta.tipoAsta;
let tipoAstaSplit = tipoAsta.split('_');
let tipoAstaNew =
    capitalizeFirstLetter(tipoAstaSplit[0]) + ' ' + capitalizeFirstLetter(tipoAstaSplit[1]);

const scadenzaAsta = ref(storeInstance.asta.scadenzaAsta);
let dateScadenza = new Date(scadenzaAsta.value);

const categoriaInviata = categoriaSelezionata(storeInstance.asta.categoria);

const datiExtra = JSON.stringify({
    tempoEstensione: storeInstance.asta.durataEstensione,
    quotaFissaPerLaPuntata: storeInstance.asta.incrementoMinimo,
    astaId: 0,
});

const emit = defineEmits('decrease-page', 'finalize');
let success = false;
let error = '';

const goToPreviousForm = () => {
    // Emit event to notify parent component to move to   the previous form section
    emit('decrease-page');
};

onMounted(() => {
    storeInstance.updateAsta({ step: 3 });
});

const gestioneInvio = () => {
    //path = asta/creaAsta
    creaAsta()
        .then((response) => {
            console.log('response: ', response);
            success = true;
        })
        .catch((error) => {
            console.log('error: ', error);
            error = error;
        });
};

function toFront(newCover) {
    let images = storeInstance.asta.immaginiSalvate;
    let index = images.indexOf(newCover);
    images.unshift(images.splice(index, 1)[0]);
    storeInstance.updateAsta({ step: 3 });
    console.log('images: ', images);
}
</script>

<style scoped>
h1 {
    font-size: 1.2rem;
    font-weight: bold;
    color: #cc85f5;
}

span.label {
    text-align: left;
    font-size: 1rem;
    text-transform: uppercase;
    letter-spacing: 1px;
    font-weight: bold;
}

.campo::before {
    content: ' > ';
    font-size: 1.1rem;
    font-weight: bold;
    color: #cc85f5;
}

.preview {
    max-width: 100%;
    height: auto;
    margin-bottom: 5px;
}

.sp-button {
    background-image: linear-gradient(to top left, var(--tw-gradient-stops));
    --tw-gradient-from: rgb(244 117 117 / 0.9) var(--tw-gradient-from-position);
    --tw-gradient-to: rgb(244 117 117 / 0) var(--tw-gradient-from-position);
    --tw-gradient-stops: var(var(--tw-gradient-from), var(--tw-gradient-to));
    --tw-gradient-to: rgb(232 121 249 / 0) var(--tw-gradient-to-position);
    --tw-gradient-stops: var(--tw-gradient-from), #e879f9 var(--tw-gradient-via-position),
        var(--tw-gradient-to);
    --tw-gradient-to: #4d91ff var(--tw-gradient-to-position);
}
</style>

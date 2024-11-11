<template>
    <div class="px-5 text-xl font-semibold" font-semibold>Revisione Dati Inseriti</div>
    <div class="flex flex-col gap-3 px-5 py-3 lg:flex-row">
        <div class="grid" grid-cols-2 px-3 py-2>
            <span class="label">Categoria:</span>
            <span class="campo w-[100%] lg:w-[45rem]">
                {{ categoriaSelezionata(storeInstance.asta.categoria) }}
            </span>
            <span> {{ testCategoria }} </span>
            <span class="label">nomeProdotto:</span>
            <span class="campo w-[100%] lg:w-[45rem]"> {{ storeInstance.asta.nomeProdotto }}</span>
            <span class="label">descrizione</span>
            <span class="campo w-[100%] lg:w-[45rem]">{{ storeInstance.asta.descrizione }}</span>
            <span class="label">prezzo Base:</span>
            <span class="campo w-[100%] lg:w-[45rem]">€ {{ storeInstance.asta.prezzoBase }}</span>
            <span class="label">tipo Asta:</span>
            <span class="campo w-[100%] lg:w-[45rem]">{{ tipoAstaNew }}</span>

            <span v-if="tipoAsta === 'asta_inglese'">
                <span class="label">incremento minimo:</span>
                <span class="campo w-[100%] lg:w-[45rem]"
                    >€ {{ storeInstance.asta.incrementoMinimo }}</span
                >
                <span class="label">durata estensione:</span>
                <span class="campo w-[100%] lg:w-[45rem]">{{
                    storeInstance.asta.durataEstensione
                }}</span>
            </span>

            <span class="label">scadenza asta:</span>
            <span class="campo w-[100%] lg:w-[45rem]">{{ dateScadenza.toLocaleDateString() }}</span>
        </div>
        <div class="grid w-[100%] grid-cols-4 gap-2 px-5 py-3">
            <img
                v-if="storeInstance.asta.immaginiSalvate?.length > 0"
                :src="storeInstance.asta?.immaginiSalvate[0].src"
                alt="Immagine Copertina"
                class="col-span-4 h-[9rem] shadow ring-2 ring-primario-400 lg:h-[12rem]"
            />
            <div
                v-for="image in storeInstance.asta.immaginiSalvate"
                class="mr-5 rounded ring-0 ring-primario-400"
            >
                <div class="flex rounded">
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
                        class="h-[7rem] shadow lg:h-[10rem]"
                    />
                </div>
            </div>
        </div>
    </div>
    <div class="buttonArea flex justify-around">
        <Button class="w-[45%]" size="large" @click="goToPreviousForm"
            ><span class="font-bold"><i class="pi pi-arrow-left"></i> Precedente</span></Button
        >
        <Button class="sp-button w-[45%]" size="large" @click="gestioneInvio"
            ><span class="font-bold">Finalizza <i class="pi pi-check"></i></span
        ></Button>

        <!--success: {{ success }}-->
    </div>
</template>

<script setup>
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
    console.log('test gettere asta: ', storeInstance.getFormattedData());
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

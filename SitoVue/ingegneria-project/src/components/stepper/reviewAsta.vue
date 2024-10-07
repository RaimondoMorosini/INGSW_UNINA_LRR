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
            <span class="campo w-[100%] lg:w-[45rem]">{{tipoAstaNew }}</span>

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
            
            <span class="campo w-[100%] lg:w-[45rem]">{{scadenzaAsta}}</span>
        </div>
        <div class="grid w-[100%] grid-cols-4 gap-2 px-5 py-3">
            <img
                :src="storeInstance.asta.immaginiSalvate[0].src"
                alt="Immagine Copertina"
                class="col-span-4 h-[9rem] shadow ring-2 ring-[#cc85f5] lg:h-[12rem]"
            />
            <div v-for="image in storeInstance.asta.immaginiSalvate">
                
                <img :src="image.src" alt="immagine caricata" class="h-[7rem] shadow lg:h-[10rem]" />
            </div>
        </div>
    </div>
    <div class="buttonArea flex">
        <button class="bottone mx-3 my-3 px-5" @click="goToPreviousForm" type="button">
            <i class="pi pi-arrow-left"></i>
            Precedente
        </button>
        <button
            class="bottone bg-gradient-to-tl from-danger-300/90 via-primario-100 to-secondario-300 px-5 hover:from-danger-400 hover:via-[#7c3aed] hover:to-secondario-400"
            @click="gestioneInvio"
        >
            Finalizza
            <i class="pi pi-check"></i>
        </button>
    </div>
    <div class="test">
        success: {{ success }}
    </div>
</template>

<script setup>
import { creaAsta } from '../../service/astaService.js';
import { useAstaStore } from '../../stores/astaStore.js';
import { ref, onMounted } from 'vue';

const storeInstance = useAstaStore();

//IMPORTANTE: se in futuro le categorie diverranno multiple, sostituire questo con Object.keys(storeInstance.asta.categoria)
const categoriaSelezionata = function (obj) {
    let keys = '';
    for (let key in obj) {
        keys = key;
    }
    return keys;
};

const categoriaInviata = categoriaSelezionata(storeInstance.asta.categoria);

const datiExtra = JSON.stringify({
    tempoEstensione: storeInstance.asta.durataEstensione,
    quotaFissaPerLaPuntata: storeInstance.asta.incrementoMinimo,
    astaId: 0,
});

function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

const scadenzaAsta = ref(storeInstance.asta.scadenzaAsta);


const date = new Date();
const timestamp = date.getTime() / 1000;

const tipoAsta = storeInstance.asta.tipoAsta 
let tipoAstaSplit = tipoAsta.split('_')
let tipoAstaNew = capitalizeFirstLetter(tipoAstaSplit[0])+' '+capitalizeFirstLetter(tipoAstaSplit[1]);


const emit = defineEmits(['decrease-page','finalize']);
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
        emit('finalize');
};
</script>

<style scoped>
.bottone {
    background-color: #cc85f5;
    margin: 10px;
    padding: 10px 20px;
    color: white;
    border-radius: 5px;
    font-size: 1.1rem;
    font-weight: bold;
    width: 50%;
}
.bottone:hover {
    background-color: #7c3aed;
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
</style>

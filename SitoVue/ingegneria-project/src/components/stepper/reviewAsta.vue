<template>
    <div class="px-5 text-xl font-semibold" font-semibold>Revisione Dati Inseriti</div>
    <div class="flex-cols flex justify-around">
        <div class="grid" grid-cols-2 px-3 py-2>
            <span class="label">Categoria:</span>
            <span class="campo w-96 shadow ring-1 ring-slate-100">
                {{ categoriaSelezionata(storeInstance.asta.categoria) }}
            </span>
            <span> {{ testCategoria }} </span>
            <span class="label">nomeProdotto:</span>
            <span class="campo w-96 shadow ring-1 ring-slate-100">
                {{ storeInstance.asta.nomeProdotto }}</span
            >
            <span class="label">descrizione</span>
            <span class="campo w-96 shadow ring-1 ring-slate-100">{{
                storeInstance.asta.descrizione
            }}</span>
            <span class="label">prezzo Base:</span>
            <span class="campo w-96 shadow ring-1 ring-slate-100"
                >€ {{ storeInstance.asta.prezzoBase }}</span
            >
            <span class="label">tipo Asta:</span>
            <span class="campo w-96 shadow ring-1 ring-slate-100">{{
                storeInstance.asta.tipoAsta
            }}</span>

            <span v-if="tipoAsta === 'asta_inglese'">
                <span class="label">incremento minimo:</span>
                <span class="campo w-96 shadow ring-1 ring-slate-100"
                    >€ {{ storeInstance.asta.incrementoMinimo }}</span
                >
                <span class="label">durata estensione:</span>
                <span class="campo w-96 shadow ring-1 ring-slate-100">{{
                    storeInstance.asta.durataEstensione
                }}</span>
            </span>

            <span class="label">scadenza asta:</span>
            <span class="campo w-96 shadow ring-1 ring-slate-100">{{
                storeInstance.asta.scadenzaAsta
            }}</span>
        </div>
        <div class="grid grid-cols-4 gap-2 px-5 shadow ring-1 ring-slate-100">
            <img
                :src="storeInstance.asta.immaginiSalvate[0]"
                alt="Immagine Copertina"
                class="col-span-4 h-[8rem] ring-2 ring-[#cc85f5]"
            />
            <div v-for="image in storeInstance.asta.immaginiSalvate">
                <img :src="image" alt="immagine caricata" class="h-[8rem]" />
            </div>
        </div>
    </div>
    <div class="buttonArea">
        <button class="bottone mx-3 my-3 px-5" @click="goToPreviousForm" type="button">
            Precedente
        </button>
        <button class="bottone px-5" @click="gestioneInvio">Finalizza</button>
    </div>
</template>
<!--
    <div class="block px-5 py-5">
            <img
                :src="storeInstance.asta.immaginiSalvate[0]"
                alt="Immagine Copertina"
                class=" ring-2 ring-[#cc85f5] w-[10%]"
            />
            <div v-for="image in storeInstance.asta.immaginiSalvate">
                <img :src="image" alt="immagine caricata" class="w-[10%] px-5" />
            </div>
        </div>
    -->

<script setup>
import axios from 'axios';
import { postRestWithtoken } from '../../scripts/RestUtils.js';
import { useAstaStore } from '../../stores/astaStore.js';
import { ref, onMounted } from 'vue';

const storeInstance = useAstaStore();

//IMPORTANTE: se in futuro le cateorie diverranno multiple, sostituire questo con Object.keys(storeInstance.asta.categoria)
const categoriaSelezionata = function (obj) {
    var keys = '';
    for (var key in obj) {
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

const date = new Date();
const timestamp = date.getTime() / 1000;

const emit = defineEmits(['update:active']);
let success = false;
let error = '';

function generaDTO() {
    if (storeInstance.asta.tipoAsta === 'asta_inglese') {
        return {
            datiProdotto: {
                nomeProdotto: storeInstance.asta.nomeProdotto,
                descrizioneProdotto: storeInstance.asta.descrizione,
                immagini: storeInstance.asta.immaginiSalvate,
                categoriaProdotto: categoriaInviata,
                caratteristicheProdotto: [
                    {
                        idCaratteristica: 0,
                        valore: 'android',
                    },
                    {
                        idCaratteristica: 2,
                        valore: '64GB',
                    },
                ],
            },
            datiAsta: {
                baseAsta: storeInstance.asta.prezzoBase,
                dataScadenza: storeInstance.asta.scadenzaAsta.valueOf() / 1000,
                dataInizio: timestamp,
                tipoAsta: storeInstance.asta.tipoAsta,
                datiExtraJson: datiExtra,
            },
        };
    } else {
        return {
            datiProdotto: {
                nomeProdotto: storeInstance.asta.nomeProdotto,
                descrizioneProdotto: storeInstance.asta.descrizione,
                immagini: ['TODO togliere quando funziona'],
                categoriaProdotto: categoriaInviata,
                caratteristicheProdotto: [
                    {
                        idCaratteristica: 0,
                        valore: 'android',
                    },
                    {
                        idCaratteristica: 2,
                        valore: '64GB',
                    },
                ],
            },
            datiAsta: {
                baseAsta: storeInstance.asta.prezzoBase,
                dataScadenza: storeInstance.asta.scadenzaAsta.valueOf() / 1000,
                dataInizio: timestamp,
                tipoAsta: storeInstance.asta.tipoAsta,
                datiExtraJson: '',
            },
        };
    }
}

const astaCompleta = generaDTO();

const goToPreviousForm = () => {
    // Emit event to notify parent component to move to   the previous form section
    emit('update:active', 2);
};

onMounted(() => {
    storeInstance.updateAsta({ step: 3 });
});

const gestioneInvio = () => {
    //path = asta/creaAsta
    postRestWithtoken('asta/creaAsta', astaCompleta)
        .then((response) => {
            success = true;
            console.log(response);
        })
        .catch((error) => {
            console.log(error);
            success = false;
        });
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
</style>

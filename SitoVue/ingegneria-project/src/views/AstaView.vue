<template>

    <div class="contenitore_colonne">

        <div class="colonna">
            <h1 class="text-lg font-bold m-0.5" >INFORAZIONI PRODOTTO</h1>
            <ImmaginiProdotto v-if="item" :prodotto="item" />
        </div>

        <div class="colonna">
            <h1 class="text-lg font-bold m-0.5" >INFORMAZIONI ASTA</h1>
            <InfoAstaProdotto
                v-if="item"
                :prodotto="item"
                :utenteUltimaOfferta="utenteUltimaOfferta"
            />
        </div>

        <div v-if="partecipantiIsVisible" class="colonna">

            <h1 class="text-lg font-bold m-0.5" >OFFERTE ASTE</h1>
            <PartecipantiAsta v-if="offerte" :offerte="offerte" />
        </div>

    </div>

</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue';
import { useRoute } from 'vue-router';
import { getInfoAstaProdotto, getDatiastaInglese } from '../service/PaginaProdottoAstaService';
import { getOfferteAstaIinglese } from '../service/offertaService';
import { mantieniAggiornamenti, disconnettiti } from '../scripts/websocket/websocket.js';
import ImmaginiProdotto from '../components/PaginaAsta/ImmaginiProdotto.vue';
import InfoAstaProdotto from '../components/PaginaAsta/InfoAstaProdotto.vue';
import PartecipantiAsta from '../components/PaginaAsta/PartecipantiAsta.vue';

const route = useRoute();
const astaId = route.params.id;
const item = ref(null);
const offerte = ref(null);
const utenteUltimaOfferta = ref(null);
const disconnesioneFunction  = ref(null);
const datiExtra = ref(null);
const partecipantiIsVisible = ref(true);

onMounted(async () => {
    try {
        console.log('Caricamento asta in corso...');
        item.value = await getInfoAstaProdotto(astaId);
        if(item.value.tipoAsta === 'asta_silenziosa') {
            partecipantiIsVisible.value = false;
        }
    } catch (e) {
        console.error("Errore durante il caricamento dell'asta:", e);
    }
    try {
        datiExtra.value = await getDatiastaInglese(astaId);
    } catch (e) {
        console.error('Errore richiesta datiExtra:', e);
    }
    try {
        offerte.value = await getOfferteAstaIinglese(astaId);
    } catch (e) {
        console.log('Errore durante il carimento delle offerte:', e);
    }
    console.log('datiExtra:', datiExtra.value);
    disconnesioneFunction .value = mantieniAggiornamenti('/asta/' + astaId, handleMessage);
});

onUnmounted(() => {
    console.log('mounted');
    disconnesioneFunction .value();
});

function handleMessage(message) {

    console.log('Messaggio ricevuto dalla websocket:', message);
    alert('Messaggio ricevuto dalla websocket: ' + message);

    const data = JSON.parse(message);
    const offerta = {
        id: data.offerta.id,
        tempoOfferta: data.offerta.tempoOfferta,
        prezzoProposto: data.offerta.prezzoProposto,
        emailUtente: data.offerta.emailUtente,
        astaId: data.offerta.astaId,
        offertaVincente: data.offerta.offertaVincente,
    };
    offerte.value.unshift(offerta);
    alert('Offerta ricevuta!');
    utenteUltimaOfferta.value = offerta.emailUtente;

    switch (item.value.tipoAsta) {
        case 'asta_inglese':
            if (offerta.prezzoProposto > item.value.prezzoAttuale) {
                item.value.prezzoAttuale = offerta.prezzoProposto;
                item.value.dataScadenza = offerta.tempoOfferta + datiExtra.value.tempoEstensione;
                console.log(
                    'tempoEstensione:',
                    offerta.tempoOfferta + datiExtra.value.tempoEstensione
                );
                console.log('Prezzo attuale aggiornato:', item.value.prezzoAttuale);
                console.log('Tempo rimanente:', offerta.tempoOfferta);
            }
            break;
        case 'asta_inversa':
            if (offerta.prezzoProposto < item.value.prezzoAttuale) {
                item.value.prezzoAttuale = offerta.prezzoProposto;
                console.log('Prezzo attuale aggiornato:', item.value.prezzoAttuale);
            }
            break;
        case 'asta_silenziosa':
            alert("Qualcuno ha fatto un'offerta!");
            break;
        default:
            console.warn("Tipo d'asta non riconosciuto:", item.value.tipoAsta);
    }
}

</script>

<style scoped>
.contenitore_colonne {
    /* Abilita Flexbox */
    display: flex;
    /* Spazia le colonne uniformemente */
    justify-content: space-between;
}

.colonna {
    flex: 1; /* Ogni colonna occupa spazio uguale */
    margin: 10px 10px; /* Margine tra le colonne, se necessario */
    padding: 20px; /* Padding interno per le colonne */
}

@media (max-width: 768px) {
    .contenitore_colonne {
        flex-direction: column; /* Disposizione in colonna per schermi piccoli */
    }
}
</style>

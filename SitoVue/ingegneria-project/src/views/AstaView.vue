<template>
    <div class="contenitore_colonne">
        <div class="colonna">
            <h1 class="m-0.5 text-lg font-bold">INFORAZIONI PRODOTTO</h1>
            <ImmaginiProdotto v-if="item" :prodotto="item" />
        </div>

        <div class="colonna" >
            <h1 class="m-0.5 text-lg font-bold">INFORMAZIONI ASTA</h1>
            <InfoAstaProdotto
                v-if="item"
                :prodotto="item"
                :utenteUltimaOfferta="utenteUltimaOfferta"
            />
        </div>

        <div v-if="partecipantiIsVisible" class="colonna">
            <h1 class="m-0.5 text-lg font-bold">OFFERTE ASTE</h1>
            <PartecipantiAsta v-if="offerte" :offerte="offerte"
            :astaId="astaId" />
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue';
import { useRoute } from 'vue-router';
import {  getDatiastaInglese } from '../service/PaginaProdottoAstaService';
import { useAstaChacheStore } from '../stores/astaStore';
import { getOfferteAstaIinglese } from '../service/offertaService';
import { mantieniAggiornamenti, disconnettiti } from '../scripts/websocket/websocket.js';
import ImmaginiProdotto from '../components/PaginaAsta/ImmaginiProdotto.vue';
import InfoAstaProdotto from '../components/PaginaAsta/InfoAstaProdotto.vue';
import PartecipantiAsta from '../components/PaginaAsta/PartecipantiAsta.vue';
import { useProfiloStore } from '../stores/profiloStore';
const route = useRoute();
const astaId = route.params.id;
const item = ref(null);
const offerte = ref(null);
const utenteUltimaOfferta = ref(null);
const disconnesioneFunction = ref(null);
const datiExtra = ref(null);
const partecipantiIsVisible = ref(true);
const isOwner = ref(false);
const isSilentAuction = ref(false);


onMounted(async () => {
    
    

    
    
    try {
        console.log('Caricamento asta in corso...');
        item.value = await useAstaChacheStore().getAstaById(astaId);
        if (item.value.tipoAsta === 'asta_silenziosa') {
            partecipantiIsVisible.value = true;
            isSilentAuction.value = true;
        }
        
        if (useProfiloStore().profilo.email === item.value.emailUtenteCreatore) {
            isOwner.value = true;
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
        if (offerte.value.length > 0)
        utenteUltimaOfferta.value = offerte.value[0].emailUtente;
    } catch (e) {
        console.log('Errore durante il carimento delle offerte:', e);
    }
    console.log('datiExtra:', datiExtra.value);
    disconnesioneFunction.value = mantieniAggiornamenti('/asta/' + astaId, handleMessage);

    
    
});

onUnmounted(() => {
    console.log('mounted');
    disconnesioneFunction.value();
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
    utenteUltimaOfferta.value = offerta.emailUtente;
    alert('Offerta ricevuta! ');
    switch (item.value.tipoAsta) {
        case 'asta_inglese':
            if (offerta.prezzoProposto > item.value.prezzoAttuale) {
                item.value.prezzoAttuale = offerta.prezzoProposto;
                item.value.dataScadenza = offerta.tempoOfferta + datiExtra.value.tempoEstensione;
            }
            break;
        case 'asta_inversa':
            if (offerta.prezzoProposto < item.value.prezzoAttuale) {
                item.value.prezzoAttuale = offerta.prezzoProposto;
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

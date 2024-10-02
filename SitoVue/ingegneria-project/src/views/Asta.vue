<template>
    <div class="contenitore_colonne">
        <div class="colonna">
            <ImmaginiProdotto v-if="item" :prodotto="item" />
        </div>
        <div class="colonna">
            <InfoAstaProdotto v-if="item" :prodotto="item" />
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue';
import { useRoute } from 'vue-router';
import { getInfoAstaProdotto ,getDatiastaInglese} from '../service/PaginaProdottoAstaService';
import { mantieniAggiornamenti, disconnettiti } from '../scripts/websocket/websocket.js';
import ImmaginiProdotto from '../components/PaginaAsta/ImmaginiProdotto.vue';
import InfoAstaProdotto from '../components/PaginaAsta/InfoAstaProdotto.vue';

const route = useRoute();
const astaId = route.params.id;
const item = ref(null);
const stomp1 = ref(null);
const datiExtra = ref(null);
onMounted(async () => {
    try {
        console.log('Caricamento asta in corso...');
        item.value = await getInfoAstaProdotto(astaId);
        
    } catch (e) {
        console.error("Errore durante il caricamento dell'asta:", e);
    }
    try {
        datiExtra.value = await getDatiastaInglese(astaId);
    } catch (e) {
        console.error("Errore richiesta datiExtra:", e);
    }
    console.log('datiExtra:', datiExtra.value);
    stomp1.value = mantieniAggiornamenti('/asta/' + astaId, handleMessage);
});

onUnmounted(() => {
    console.log('mounted');
    disconnettiti(stomp1.value);
});

function handleMessage(message) {
    console.log('Messaggio ricevuto dalla websocket:', message);
    const data = JSON.parse(message);
    const offerta = {
        id: data.offerta.id,
        tempoOfferta: data.offerta.tempoOfferta,
        prezzoProposto: data.offerta.prezzoProposto,
        emailUtente: data.offerta.emailUtente,
        astaId: data.offerta.astaId,
        offertaVincente: data.offerta.offertaVincente,
    };
    
    switch (item.value.tipoAsta) {
        case 'asta_inglese':
            if (offerta.prezzoProposto > item.value.prezzoAttuale) {
                item.value.prezzoAttuale = offerta.prezzoProposto;
                item.value.dataScadenza = offerta.tempoOfferta + datiExtra.value.tempoEstensione;
                console.log('tempoEstensione:', 
                offerta.tempoOfferta + datiExtra.value.tempoEstensione);
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

function faiOfferta() {
    console.log('Offerta fatta!');
    //TODO chiamre il servizio per fare l'offerta
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
    background: linear-gradient(
        to bottom,
        #f0f0f0,
        #ffffff
    ); /* Sfondo grigio chiaro sfumato nel bianco */
    border-radius: 8px; /* Angoli arrotondati per un aspetto più morbido */
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* Ombra leggera per profondità */
}

@media (max-width: 768px) {
    .contenitore_colonne {
        flex-direction: column; /* Disposizione in colonna per schermi piccoli */
    }
}
</style>

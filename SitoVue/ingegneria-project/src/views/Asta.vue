<template>
  <br>
  <div class="asta" v-if="item">
    <h1>{{ item.nome }} (Categoria: {{ item.categoria }})</h1>
    <img v-if="item.immagini && item.immagini.length" :src="item.immagini[0]" alt="Immagine Prodotto" class="immagine-prodotto" />
    <div class="dettagli">
      <p><strong>Base d'asta:</strong> {{ item.baseAsta }} €</p>
      <p><strong>Prezzo attuale:</strong> {{ item.prezzoAttuale }} €</p>
      <p><strong>Tipo d'asta:</strong> {{ item.tipoAsta }}</p>
      <p><strong>Data di scadenza:</strong> {{ new Date(item.dataScadenza).toLocaleString('it-IT', { timeZone: 'GMT' }) }} GMT</p>
      <p><strong>Creato da:</strong> {{ item.emailUtenteCreatore }}</p>
      <button @click="faiOfferta" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
        Fai un'offerta
      </button>
      <Countdown :unixTimestamp="item.dataScadenza" @update:remainingTime="tempoInSecondi = $event"></Countdown>
      secondi rimanenti: {{ tempoInSecondi }}
    </div>
    <div class="descrizione">
      <h2>Descrizione</h2>
      <p>{{ item.descrizione }}</p>
    </div>
  </div>
  <div v-else>
    <p>Caricamento in corso...</p>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue';
import { useRoute } from 'vue-router';
import { getInfoAstaProdotto } from '../service/PaginaProdottoAstaService';
import { mantieniAggiornamenti, disconnettiti } from '../scripts/websocket/websocket.js';
import Countdown from '../components/PaginaAsta/countdown.vue';

const route = useRoute();
const astaId = route.params.id;
const item = ref(null);
const stomp1 = ref(null);
const tempoInSecondi = ref(0);

onMounted(async () => {
  try {
    console.log('Caricamento asta in corso...');
    item.value = await getInfoAstaProdotto(astaId);
  } catch (e) {
    console.error("Errore durante il caricamento dell'asta:", e);
  }
  stomp1.value = mantieniAggiornamenti('/asta/' + astaId, handleMessage);
});

onUnmounted(() => {
  console.log('mounted');
  disconnettiti(stomp1.value);
});

function handleMessage(message) {
  console.log('Messaggio ricevuto dalla websocket:', message);
  const data = JSON.parse(message);
  const offerta = data.offerta;
  switch (item.value.tipoAsta) {
    case 'asta_inglese':
      if (offerta.prezzoProposto > item.value.prezzoAttuale) {
        item.value.prezzoAttuale = offerta.prezzoProposto;
        item.value.dataScadenza = offerta.tempoOfferta + 60000;
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
      alert('Qualcuno ha fatto un\'offerta!');
      break;
    default:
      console.warn('Tipo d\'asta non riconosciuto:', item.value.tipoAsta);
  }
  
}


function faiOfferta() {
  console.log('Offerta fatta!');
  //TODO chiamre il servizio per fare l'offerta 

}
</script>

<style scoped>
.asta {
  max-width: 600px;
  margin: 0 auto;
}

.immagine-prodotto {
  max-width: 100%;
  height: auto;
  display: block;
  margin: 10px 0;
}

.dettagli, .descrizione {
  margin: 20px 0;
}
</style>
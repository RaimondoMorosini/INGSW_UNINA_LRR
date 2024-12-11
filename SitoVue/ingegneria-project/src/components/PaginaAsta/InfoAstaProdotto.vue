<template>
    <Card  class="relative">
      <template #content>
        <div>

          <div v-if="tempoInSecondi<=0" class="content">
    <h3 class="text-lg font-bold">ASTA CONCLUSA </h3>
    <p>Il vincitore è: <span class="font-bold"> vincitore </span></p>
    <p>Prezzo finale: <span class="font-bold"> prezzoFinale €</span></p>
</div>
       
          <div v-else >
            <h1 class="text-xl font-bold">Scadenza asta</h1>
            <Countdown
            :unixTimestamp="props.prodotto.dataScadenza"
            @update:remainingTime="tempoInSecondi = $event"
            />
          </div>
    
          <BottoneOfferta
            v-if="tipoAsta === 'asta_inglese'"
            :tipoAsta="tipoAsta"
            :prezzoAttuale="props.prodotto.prezzoAttuale"
            :incrementoOfferta="incrementoOfferta"
            :faiOfferta="faiOffertaParziale"
            :utenteUltimaOfferta="props.utenteUltimaOfferta"
            :idAsta="'provaInglese'"
            :tempoRimanente="tempoInSecondi"
          />
  
          <BottoneOfferta
            v-else-if="tipoAsta === 'asta_silenziosa'"
            :tipoAsta="props.prodotto.tipoAsta"
            :baseAsta="props.prodotto.baseAsta"
            :faiOfferta="faiOffertaParziale"
            :utenteUltimaOfferta="props.utenteUltimaOfferta"
            :idAsta="props.prodotto.idAsta"
            :tempoRimanente="tempoInSecondi"
          />
  
          <BottoneOfferta
            v-else-if="tipoAsta === 'asta_inversa'"
            :tipoAsta="props.prodotto.tipoAsta"
            :prezzoAttuale="props.prodotto.prezzoAttuale"
            :faiOfferta="faiOffertaParziale"
            :utenteUltimaOfferta="props.utenteUltimaOfferta"
            :idAsta="'astaInversa'"
            :tempoRimanente="tempoInSecondi"
          />
        </div>
    
      </template>
    </Card>
    
  </template>
  

<script setup>
import { ref, onMounted } from 'vue';
import Card from 'primevue/card';
import Countdown from '../PaginaAsta/countdown.vue';
import BottoneOfferta from '../PaginaAsta/BottoneOfferta.vue';
import { faiOfferta } from '../../service/offertaService.js';
import { getDatiastaInglese } from '../../service/PaginaProdottoAstaService';
import { useProfiloStore } from '../../stores/profiloStore';
const props = defineProps(['prodotto', 'utenteUltimaOfferta']);

const tipoAsta = ref(null);
const datiExtra = ref(null);
const incrementoOfferta = ref();
const isOwner = ref(false);
const isSilentAuction = ref(false);
const currentTime = Math.floor(Date.now()); // Ottieni il tempo corrente in secondi
let secondirimaneti = Math.floor((props.prodotto.dataScadenza - currentTime) / 1000);
if (secondirimaneti <= 0) {
    secondirimaneti = 0;
}
const tempoInSecondi = ref(secondirimaneti);

onMounted(async () => {
    tipoAsta.value = props.prodotto.tipoAsta;

    if (tipoAsta.value == 'asta_inglese') {
        try {
            datiExtra.value = await getDatiastaInglese(props.prodotto.idAsta);
        } catch (e) {
            console.error('Errore richiesta datiExtra:', e);
        }

        incrementoOfferta.value = datiExtra.value.quotaFissaPerLaPuntata;
    }else if (tipoAsta.value == 'asta_silenziosa') {
        isSilentAuction.value = true;

    }
    if(props.prodotto.emailUtenteCreatore == useProfiloStore().profilo.email){
        isOwner.value = true;
    }
});

const faiOffertaParziale = (prezzoProposto) => {
    return faiOfferta(prezzoProposto, props.prodotto.idAsta);
};
</script>

<style>
.titolo_prodotto {
    font-size: xx-large;
}
</style>

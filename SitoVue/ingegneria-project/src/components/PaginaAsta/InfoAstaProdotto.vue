<template>
    <Card style="width: 25rem; overflow: hidden" class="relative">
      <template #content>
        <div>
          <h1 class="text-xl font-bold">Scadenza asta</h1>
          <Countdown
            :unixTimestamp="props.prodotto.dataScadenza"
            @update:remainingTime="tempoInSecondi = $event"
          />
  
          <BottoneOfferta
            v-if="tipoAsta === 'asta_inglese'"
            :tipoAsta="tipoAsta"
            :prezzoAttuale="props.prodotto.prezzoAttuale"
            :incrementoOfferta="incrementoOfferta"
            :faiOfferta="faiOffertaParziale"
            :utenteUltimaOfferta="props.utenteUltimaOfferta"
            :idAsta="'provaInglese'"
          />
  
          <BottoneOfferta
            v-else-if="tipoAsta === 'asta_silenziosa'"
            :tipoAsta="props.prodotto.tipoAsta"
            :baseAsta="props.prodotto.baseAsta"
            :faiOfferta="faiOffertaParziale"
            :utenteUltimaOfferta="props.utenteUltimaOfferta"
            :idAsta="props.prodotto.idAsta"
          />
  
          <BottoneOfferta
            v-else-if="tipoAsta === 'asta_inversa'"
            :tipoAsta="props.prodotto.tipoAsta"
            :prezzoAttuale="props.prodotto.prezzoAttuale"
            :faiOfferta="faiOffertaParziale"
            :utenteUltimaOfferta="props.utenteUltimaOfferta"
            :idAsta="'astaInversa'"
          />
        </div>
  
        <!-- Overlay -->
<div
  class="absolute top-1/2 left-[-25%] w-[150%] h-10 bg-red-500/70 flex items-center justify-center rotate-[-45deg]">
  <span class="text-white text-xl font-bold tracking-widest uppercase rotate-[45deg]">
    Asta Scaduta
  </span>
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

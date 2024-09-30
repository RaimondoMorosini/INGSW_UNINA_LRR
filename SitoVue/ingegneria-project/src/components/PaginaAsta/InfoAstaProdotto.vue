<template>
    <Card style="width: 25rem; overflow: hidden">
        <template #header>
            
        </template>
        
        <template #content>
            <h1 class="font-bold text-xl">Scadenza asta</h1>
            <Countdown :unixTimestamp="dataScadenza" @update:remainingTime="tempoInSecondi = $event" />

            <BottoneOfferta v-if="tipoAsta==='asta_inglese'" 
            :tipoAsta="tipoAsta" 
            :prezzoAttuale="props.prodotto.prezzoAttuale" 
            :incrementoOfferta="1" 
            :faiOfferta="faiOffertaParziale" />

            <BottoneOfferta v-else-if="tipoAsta==='asta_silenziosa'" 
            :tipoAsta="tipoAsta" 
            :baseAsta="baseAsta" 
            :faiOfferta="faiOffertaParziale" />

            <BottoneOfferta v-else
            :tipoAsta="tipoAsta" 
            :prezzoAttuale="prezzoAttuale" 
            :faiOfferta="faiOffertaParziale"/>

        </template>
        <template #footer>
            <div class="flex gap-4 mt-1">
            </div>
        </template>
    </Card>
</template>

<script setup>
import { ref, onMounted } from "vue";
import Card from 'primevue/card';
import Countdown from '../PaginaAsta/countdown.vue';
import BottoneOfferta from "../PaginaAsta/BottoneOfferta.vue";
import { faiOfferta } from '../../service/offertaService.js';

const tempoInSecondi = ref(0);
const dataScadenza = ref(null);
const tipoAsta = ref(null);
const baseAsta = ref(null);
const idAsta = ref(null);
const prezzoAttuale = ref(null);


const props = defineProps([
    'prodotto'
]);

onMounted(() => {

    dataScadenza.value = props.prodotto.dataScadenza;
    tipoAsta.value = props.prodotto.tipoAsta;
    baseAsta.value = props.prodotto.baseAsta;
    idAsta.value = props.idAsta;
    prezzoAttuale.value = props.prodotto.prezzoAttuale;
    console.log(props.prodotto.value)
});

const faiOffertaParziale = (prezzoProposto) => {
  return faiOfferta(prezzoProposto, props.prodotto.idAsta);
};

</script>

<style>

.titolo_prodotto{

    font-size: xx-large;
}

</style>
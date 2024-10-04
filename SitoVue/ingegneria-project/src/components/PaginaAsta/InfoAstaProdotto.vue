<template>
    <Card style="width: 25rem; overflow: hidden">
        <template #header>

        </template>

        <template #content>
            <h1 class="font-bold text-xl">Scadenza asta</h1>
            <Countdown :unixTimestamp="props.prodotto.dataScadenza" @update:remainingTime="tempoInSecondi = $event" />

            <BottoneOfferta v-if="tipoAsta === 'asta_inglese'" :tipoAsta="tipoAsta"
                :prezzoAttuale="props.prodotto.prezzoAttuale" :incrementoOfferta="incrementoOfferta" :faiOfferta="faiOffertaParziale" />

            <BottoneOfferta v-else-if="tipoAsta === 'asta_silenziosa'" :tipoAsta="props.prodotto.tipoAsta" :baseAsta="props.prodotto.baseAsta"
                :faiOfferta="faiOffertaParziale" />

            <BottoneOfferta v-else :tipoAsta="props.prodotto.tipoAsta" :prezzoAttuale="props.prodotto.prezzoAttuale"
                :faiOfferta="faiOffertaParziale" />

                    {{ utenteUltimaOfferta }}
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
import { getDatiastaInglese } from '../../service/PaginaProdottoAstaService';


const props = defineProps([
    'prodotto',
    'utenteUltimaOfferta'
]);


const tipoAsta = ref(null);
const datiExtra = ref(null);
const incrementoOfferta = ref();

onMounted(async () => {

    tipoAsta.value = props.prodotto.tipoAsta;

    if (tipoAsta.value == 'asta_inglese') {

        try {
            datiExtra.value = await getDatiastaInglese(props.prodotto.idAsta);
                } catch (e) {
            console.error("Errore richiesta datiExtra:", e);
        }

        incrementoOfferta.value = datiExtra.value.quotaFissaPerLaPuntata;

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
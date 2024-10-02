<template>
    <div>
        <h1>Test Bottone Offerta</h1>
        <button @click="clicktest" class="button purple">Invia Offerta</button>
        <!-- Asta Inglese -->
        <BottoneOfferta
            tipoAsta="asta_inglese"
            :prezzoAttuale="245"
            :incrementoOfferta="1"
            :faiOfferta="faiOffertaParziale"
        />

        <!-- Asta Inversa -->
        <BottoneOfferta
            tipoAsta="asta_inversa"
            :prezzoAttuale="100"
            :faiOfferta="faiOffertaParziale"
        />

        <!-- Asta Silenziosa -->
        <BottoneOfferta
            tipoAsta="asta_silenziosa"
            :baseAsta="50"
            :faiOfferta="faiOffertaParziale"
        />
    </div>
</template>

<script setup>
import BottoneOfferta from '../components/PaginaAsta/BottoneOfferta.vue';
import { faiOfferta } from '../service/offertaService.js';

const idAsta = 58; // ID dell'asta
function clicktest() {
    console.log('click');
    faiOffertaParziale(245).then((res) => {
        console.log(res);
    });
}

const faiOffertaParziale = (prezzoProposto) => {
    return faiOfferta(prezzoProposto, idAsta);
};
</script>

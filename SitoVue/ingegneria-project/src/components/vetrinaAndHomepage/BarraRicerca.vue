<template>
    <div class="contenitoreEtichetta">
        <p :style="{ color: coloreEtichetta }">COSA CERCHI?</p>
    </div>
    <div
        class="barraRicerca"
        :style="{ border: bordoBarra, 'border-bottom': bordoBarraBottom }"
        @mouseover="hoverBarra(true)"
        @mouseleave="hoverBarra(false)"
    >
        <div class="outputBarra">
            <img src="../assets/Icon/lente.svg" class="iconaRicerca" alt="Icona Ricerca" />
            <input
                style="color: black"
                type="text"
                placeholder="Iphone,Dyson.."
                class="inputRicerca"
                @click="focusBarra"
                @keyup.enter="focusBarra"
            />
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';

const cliccato = ref(false);
const bordoBarra = ref('2px solid black');
const bordoBarraBottom = ref('2px solid black');
const coloreEtichetta = ref('black');

function hoverBarra(stato) {
    if (stato) {
        if (!this.cliccato) {
            this.bordoBarra = '2px solid #0071fe';
            this.bordoBarraBottom = '2px solid #0071fe';
            this.coloreEtichetta = '#0071fe';
        }
    } else if (!this.cliccato) {
        this.bordoBarra = '2px solid black';
        this.bordoBarraBottom = '2px solid black';
        this.coloreEtichetta = 'black';
    }
}

function focusBarra() {
    this.cliccato = !this.cliccato;

    if (this.cliccato) {
        this.bordoBarra = '2px solid #0071fe';
        this.bordoBarraBottom = '4px solid #0071fe';
        this.coloreEtichetta = '#0071fe';
    } else {
        this.bordoBarra = '2Px solid black';
        this.bordoBarraBottom = '2px solid black';
        this.coloreEtichetta = 'black';
    }
}
</script>

<style scoped>
.barraRicerca {
    width: 16rem;
    height: 3rem;
    margin-top: 0.1rem;
    margin-left: auto;
    margin-right: auto;
    border-radius: 1rem;
    cursor: pointer;
}

.outputBarra {
    display: flex;
    padding: 1rem;
    width: 100%;
    height: 100%;
}

.iconaRicerca {
    width: 1rem;
    height: 1rem;
    margin-right: 0.5rem;
}

.inputRicerca {
    width: 100%;
    height: 100%;
    border: none;
    outline: none !important;
    box-shadow: none !important;
}

.contenitoreEtichetta {
    width: 16rem;
    height: 3rem;
    margin-top: 1rem;
    margin-left: auto;
    margin-right: auto;
    display: flex;
}

p {
    margin-top: auto;
}
</style>

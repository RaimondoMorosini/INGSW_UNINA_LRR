<template>
    <div class="contenitoreEtichetta">
        <p :style="{ color: colorEtichetta }">IN QUALE CATEGORIA?</p>
    </div>
    <div :style="{ border: coloreBordo }" class="barraTipoAsta" @click="gestioneTendina" @mouseover="hoverBarra(true)"
        @mouseleave="hoverBarra(false)">
        <div class="outputBarra">
            <img src="../assets/Icon/astaInglese.svg" class="iconaAsta" />
            <label class="labelTesto">{{ testoBarra }}</label>
            <img src="../assets/Icon/frecciaGiu.svg" class="iconaFrecciaGiu" />
        </div>
        <div v-if="tendina" class="tendina">
            <div class="opzioneAsta" @click="opzioneSelected('Asta inglese')">
                <p>Asta inglese</p>
            </div>
            <div class="opzioneAsta" @click="opzioneSelected('Asta silenziosa')">
                <p>Asta silenziosa</p>
            </div>
        </div>
    </div>
</template>

<script setup>
    import { ref } from 'vue';

    const testoBarra = ref('Seleziona l\'asta');
    const tendina = ref(false);
    const colorEtichetta = ref('');
    

    function gestioneTendina() {
        this.tendina = !this.tendina;
    };

    function opzioneSelected(opzione) {
        this.testoBarra = opzione;
        this.colorEtichetta = 'black';
        this.coloreBordo = '2px solid black';
    };

    function hoverBarra(stato) {
        if (stato) {
            this.colorEtichetta = '#0071fe';
            this.coloreBordo = '2px solid #0071fe';

        } else {
            this.colorEtichetta = 'black';
            this.coloreBordo = '2px solid black';
        }
    };


</script>

<style>
.barraTipoAsta {

    color: rgb(215, 14, 14);
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
    color: #9E9E9E
}

.iconaAsta {

    width: 1rem;
    height: 1rem;
    margin-right: 0.5rem;
}

.labelTesto {

    font-size: 70%;
    font: Inter;
}

.iconaFrecciaGiu {

    width: 1rem;
    height: 1rem;
    margin-left: auto;
}

.tendina {

    width: 70%;
    height: auto;
    margin-top: 0.1rem;
    margin-left: 4.6rem;
    border: 2px solid black;
    cursor: pointer;

}

.opzioneAsta {

    width: 100%;
    height: 2rem;
    cursor: pointer;
    color: #9E9E9E;
    font: Inter;
    cursor: pointer;
    text-align: left;
    display: flex;
    cursor: pointer;
}

.opzioneAsta:hover {

    background-color: #9E9E9E;
    color: black;
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

    margin-top: auto !important;
}
</style>
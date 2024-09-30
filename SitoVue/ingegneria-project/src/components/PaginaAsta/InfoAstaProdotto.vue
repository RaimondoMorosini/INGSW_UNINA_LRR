<template>
    <Card style="width: 25rem; overflow: hidden">
        <template #header>
            
        </template>
        
        <template #content>
            <h1 class="font-bold text-2xl">Scadenza asta</h1>
            <Countdown :unixTimestamp="dataScadenza" @update:remainingTime="tempoInSecondi = $event" />
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
import Button from 'primevue/button';
import Countdown from '../PaginaAsta/countdown.vue';

const tempoInSecondi = ref(0);
const dataScadenza = ref(null);


const props = defineProps([
    'prodotto'
]);

onMounted(() => {

    dataScadenza.value = props.prodotto.dataScadenza;
});

</script>

<style>

.titolo_prodotto{

    font-size: xx-large;
}

</style>
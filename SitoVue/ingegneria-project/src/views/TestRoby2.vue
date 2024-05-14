<template>
    ciao
    <div class="">
        <p v-if="mesg1">asta 1 = {{ mesg1 }}</p>
    </div>
</template>

<script setup>
import { mantieniAggiornamenti, disconnettiti } from '../scripts/websocket/websocket.js';

import { ref, onMounted, onUnmounted } from 'vue';
const mesg1 = ref(null);
const stomp1 = ref(null);
function handleMessage(message) {
    console.log('Messaggio ricevuto test roby 2:', message);
    mesg1.value = message;
}

onMounted(() => {
    stomp1.value = mantieniAggiornamenti('/asta/1', handleMessage);
    const stomp2 = mantieniAggiornamenti('/asta/2', handleMessage);
});

onUnmounted(() => {
    console.log('mounted');
    disconnettiti(stomp1.value);
});
</script>

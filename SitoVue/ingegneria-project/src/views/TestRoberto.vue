<template>
    <div>
      <h1>Test WebSocket con STOMP</h1>
      <input v-model="topic" placeholder="Inserisci il topic" />
      <button @click="connect">Connetti</button>
      <h2>Messaggi ricevuti: {{ messageCount }}</h2>
      <ul>
        <li v-for="(message, index) in messages" :key="index">{{ message }}</li>
      </ul>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import { mantieniAggiornamenti } from '../scripts/websocket/websocket';
  const topic = ref('');
  const messages = ref([]);
  const messageCount = ref(0);
function connect() {
    if (topic.value === '') {
      alert('Inserisci un topic');
      return;
    }
    mantieniAggiornamenti(topic.value, callback
    );
  }
  
  function callback(message) {
    console.log('Messaggio ricevuto:', message);
    alert('Messaggio ricevuto: ' + message);
  messages.value.push(message.body);
          messageCount.value++;
 }
         </script>
  
  <style scoped>
  /* Aggiungi qui il tuo stile */
  </style>
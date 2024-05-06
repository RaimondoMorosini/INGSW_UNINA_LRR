<template>
    <div>
      <h1>WebSocket Example</h1>
      <ul>
        <li v-for="message in messages" :key="message.id">{{ message }}</li>
      </ul>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import VueNativeSock from 'vue-native-websocket';
  
  // URL del server WebSocket
  const socketUrl = 'ws://localhost:8080websocket';
  
  // Array reattivo per memorizzare i messaggi ricevuti
  const messages = ref([]);
  
  // Configura il WebSocket all'avvio del componente
  onMounted(() => {
    VueNativeSock.install(Vue, socketUrl, {
      // Opzioni del WebSocket
      format: 'json', // Tipo di formato dei messaggi (json, text, binary)
      reconnection: true, // Riconnessione automatica
      reconnectionAttempts: 5, // Numero di tentativi di riconnessione
      reconnectionDelay: 3000, // Ritardo tra i tentativi di riconnessione (in millisecondi)
      store: false, // Memorizza i messaggi ricevuti nello store Vuex
      protocols: ['websocket'], // Protocolli supportati (websocket, xhr-streaming, xhr-polling, etc.)
    });
  
    // Gestisce l'evento di ricezione dei messaggi
    Vue.$socket.addEventListener('message', (event) => {
      const message = JSON.parse(event.data);
      messages.value.push(message);
    });
  });
  </script>
  
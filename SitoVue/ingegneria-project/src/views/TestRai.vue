<script setup>
import { ref, onMounted } from 'vue';
import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';

const stompClient = ref(null);

onMounted(() => {
  connect();
});

function connect() {
  const socket = new SockJS('http://localhost:8081/websocket');
  stompClient.value = Stomp.over(socket);

  const onConnect = (frame) => {
    console.log('Connected: ' + frame);
    stompClient.value.subscribe('/asta', (message) => {
      console.log('Received: ' + message.body);
    });
  };

  const onError = (error) => {
    console.log('Error: ' + error);
  };

  stompClient.value.connect({}, onConnect, onError);
}
</script>
<template>
  <div class="mx-auto w-3col">
    CIAO  SONO IL TEST DI RAI
    <br>
    <p v-if="message">{{ message }}</p>
</div>
</template>

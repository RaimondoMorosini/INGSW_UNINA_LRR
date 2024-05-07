<script setup>
import Stomp from "webstomp-client"

console.log("CIAO, SONO IL TEST DI RAI");
var socket = new WebSocket('ws://localhost:8081/websocket');

socket.onopen = function(event) {
  console.log('Connected to WebSocket');
};

socket.onmessage = function(event) {
  console.log('Received message: ' + event.data);
};

socket.onerror = function(error) {
  console.log('WebSocket Error: ' + error);
};

socket.onclose = function(event) {
  console.log('WebSocket connection closed');
};

</script>
<template>
  <div class="mx-auto w-3col">
    CIAO  SONO IL TEST DI RAI
    <br>
    <p v-if="message">{{ message }}</p>
</div>
</template>

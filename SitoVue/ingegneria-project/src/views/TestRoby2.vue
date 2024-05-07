<template>
  ciao
</template>

<script>
import SockJS from 'sockjs-client'
import { Stomp }from '@stomp/stompjs';

export default {

  data() {
    return {

    }
  },

  methods: {

    connect() {

      const socket = new SockJS('http://localhost:8081/websocket'); // Il percorso deve corrispondere a quello definito nel server Spring
      const stompClient = Stomp.over(socket);
      stompClient.connect({}, () => {
        console.log('Connesso al server WebSocket');
        // Aggiungi qui la logica per gestire gli eventi WebSocket
        stompClient.subscribe('/asta', (message) => {
          console.log('Messaggio ricevuto:', message.body);
        });
      }, error => {
        console.error('Errore durante la connessione WebSocket:', error);
      });
    }
  },

  mounted() {
   this.connect();
  },


}

</script>

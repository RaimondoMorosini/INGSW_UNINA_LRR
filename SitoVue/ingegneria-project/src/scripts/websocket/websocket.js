import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';

function mantieniAggiornamenti(topic, callback) {
    console.log('Connetti al server WebSocket');
    const socket = new SockJS('http://localhost:8081/websocket'); // Il percorso deve corrispondere a quello definito nel server Spring
    const stompClient = Stomp.over(socket);
    stompClient.connect(
        {},
        () => {
            console.log('Connesso al server WebSocket e al topic:', topic); 
            // Aggiungi qui la logica per gestire gli eventi WebSocket
            setInterval(() => {
                if (stompClient.connected) {
                    stompClient.send('/app/heartbeat', {}, JSON.stringify({}));
                    console.log('Heartbeat inviato per mantenere la connessione attiva');
                }
            }, 10000); // Invia un heartbeat ogni 10 secondi
            stompClient.subscribe(topic, (message) => {
                console.log('Messaggio ricevuto dal topic: ', topic);
                callback(message.body);
            });
        },
        (error) => {
            console.error('Errore durante la connessione WebSocket', ':', error);
        }
    );

    return stompClient;
}
function disconnettiti(stompClient) {
    stompClient.disconnect();
    console.log('Disconnesso');
}

export { mantieniAggiornamenti, disconnettiti };

import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';

function mantieniAggiornamenti(topic, callback) {
    console.log('Connetti al server WebSocket');
    const socket = new SockJS('http://localhost:8081/websocket'); // Il percorso deve corrispondere a quello definito nel server Spring
    const stompClient = Stomp.over(socket);

    // Configura gli heartbeat in entrata e in uscita per mantenere viva la connessione
    stompClient.heartbeat.outgoing = 10000; // Invia heartbeat ogni 10 secondi
    stompClient.heartbeat.incoming = 10000; // Attendi heartbeat dal server ogni 10 secondi

    stompClient.connect(
        {},
        () => {
            console.log('Connesso al server WebSocket e al topic:', topic);

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
    stompClient.disconnect(() => {
        console.log('Disconnesso');
    });
}

export { mantieniAggiornamenti, disconnettiti };

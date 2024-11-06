import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';

function mantieniAggiornamenti(topic, callback, jwtToken = null) {
    console.log('Connetti al server WebSocket');
    const socket = new SockJS('http://localhost:8081/websocket'); // Il percorso deve corrispondere a quello definito nel server Spring
    const stompClient = Stomp.over(socket);

    // Configura il logging
    stompClient.debug = (message) => {
        // Controlla se il messaggio è un PING o PONG e non loggarlo
        if (!message.includes('PING') && !message.includes('PONG')) {
            console.log("messaggio:",message);
        }
    };

    // Configura gli heartbeat in entrata e in uscita per mantenere viva la connessione
    stompClient.heartbeat.outgoing = 10000; // Invia heartbeat ogni 10 secondi
    stompClient.heartbeat.incoming = 10000; // Attendi heartbeat dal server ogni 10 secondi

    // Configura l'oggetto di intestazione
    const headers = {};
    if (jwtToken) {
        headers['Authorization'] = `Bearer ${jwtToken}`;
    }
    console.log(headers);

    stompClient.connect(
        headers,
        () => {
            console.log('Connesso al server WebSocket e al topic:', topic);

            stompClient.subscribe(
                topic,
                (message) => {
                    console.log('Messaggio ricevuto dal topic: ', topic);
                    callback(message.body);
                    headers;
                },
                headers
            );
        },
        (error) => {
            console.error('Errore durante la connessione WebSocket', ':', error);
        }
    );

    stompClient.onclose = () => {
        console.log('Connessione WebSocket chiusa. Tentativo di riconnessione...');
        // Riconnessione automatica
        setTimeout(() => {
            mantieniAggiornamenti(topic, callback, jwtToken); // Passa il token anche durante la riconnessione
        }, 5000); // 5 secondi di attesa prima di riconnettersi
    };

    return stompClient;
}

function disconnettiti(stompClient) {
    stompClient.disconnect(() => {
        console.log('Disconnesso');
    });
}

export { mantieniAggiornamenti, disconnettiti };

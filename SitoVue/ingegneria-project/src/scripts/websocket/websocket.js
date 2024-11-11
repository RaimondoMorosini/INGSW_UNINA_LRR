import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';

function mantieniAggiornamenti(topic, callback, jwtToken = null) {
    console.log('Connetti al server WebSocket');

    let stompClient;

    const connect = () => {
        const socket = new SockJS('http://localhost:8081/websocket');
        stompClient = Stomp.over(socket);

        const headers = {};
        if (jwtToken) {
            headers['Authorization'] = `Bearer ${jwtToken}`;
        }

        stompClient.connect(
            headers,
            () => {
                console.log('Connesso al server WebSocket e al topic:', topic);

                stompClient.subscribe(
                    topic,
                    (message) => {
                        console.log('Messaggio ricevuto dal topic:', topic);
                        callback(message.body);
                    },
                    headers
                );
            },
            (error) => {
                console.log('Errore durante la connessione WebSocket:', error);
                setTimeout(connect, 1000); // Riprova la connessione dopo 5 secondi
            }
        );
    };

    connect();

    return () => {
        if (stompClient) {
            stompClient.disconnect();
        }
    };
}

function disconnettiti(stompClient) {
    stompClient.disconnect(() => {
        console.log('Disconnesso');
    });
}

export { mantieniAggiornamenti, disconnettiti };

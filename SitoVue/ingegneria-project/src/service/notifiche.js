import { mantieniAggiornamenti } from '../scripts/websocket/websocket.js';
import { getRestWithtoken, postRestWithtoken } from '../scripts/RestUtils.js';

function apriWebSocketNotifichePersonali(username, callback) {
    return mantieniAggiornamenti(
        'notifichePersonali/' + username,
        callback,
        'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtb3Jvc2luaS5yYWd1c2FAZ21haWwuY29tIiwicGVybWVzc2kiOlsiY3JlYV9hc3RhX2luZ2xlc2UiLCJjcmVhX2FzdGFfc2lsZW56aW9zYSIsImZhaV9vZmZlcnRhX2FzdGFfaW52ZXJzYSJdLCJleHAiOjE3Mjk2MTE0NjAsImlhdCI6MTcyOTUyNTA2MH0.hV_DCDMUbNXU2PtTkIHtYarspwoBz3HP0QBeowXP1r0'
    );
}

async function getNumeroDiNotificheNonLette() {
    try {
        const response = await getRestWithtoken('/numeroNotificheNonVisualizzate', {});
        return response;
    } catch (error) {
        console.error('Errore:', error);
        throw error; // Propaga l'errore per gestirlo nel componente chiamante
    }
}

async function getNotificheNonLette(parametri) {
    const numeroElementi = parametri.numeroElementi;
    const numeroPagina = parametri.numeroPagina;
    // Componi l'URL con i query parameters
    const url = `${'/notificheNonVisualizzate'}?numeroElementi=${numeroElementi}&numeroPagina=${numeroPagina}`;
    console.log('URL:', url);

    try {
        //const response = await postRestWithtoken(url,{});
        const response = await getRestWithtoken(url, {});
        console.log('Response:', response);
        return response;
    } catch (error) {
        console.error('Errore:', error);
        throw error; // Propaga l'errore per gestirlo nel componente chiamante
    }
}

export { apriWebSocketNotifichePersonali, getNumeroDiNotificheNonLette, getNotificheNonLette };

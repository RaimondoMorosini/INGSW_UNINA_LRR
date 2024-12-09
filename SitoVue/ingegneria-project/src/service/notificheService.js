import { mantieniAggiornamenti } from '../scripts/websocket/websocket.js';
import { getRestWithtoken, postRestWithtoken } from '../scripts/RestUtils.js';

function apriWebSocketNotifichePersonali(username, callback) {
    return mantieniAggiornamenti(
        'notifichePersonali/' + username,
        callback,
        'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtb3Jvc2luaS5yYWd1c2FAZ21haWwuY29tIiwicGVybWVzc2kiOlsiY3JlYV9hc3RhX2luZ2xlc2UiLCJjcmVhX2FzdGFfc2lsZW56aW9zYSIsImZhaV9vZmZlcnRhX2FzdGFfaW52ZXJzYSJdLCJleHAiOjE3MzA4MTI1MjcsImlhdCI6MTczMDcyNjEyN30.ewJM_ILJQ2DJLL_aZf-XGNRmdddw5krC0h6kSbwJdAg'
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

async function setNotificaVisualizzata(ListaIdNotifiche) {
    try {
        const formdata = new FormData();
        for (const idNotifica of ListaIdNotifiche) {
            formdata.append('idNotifiche', idNotifica);
        }

        const response = await postRestWithtoken('/segnaNotificheVisualizzate', formdata);
        return response;
    } catch (error) {
        console.error('Errore:', error);
        console.log('Errore: le notifiche non sono state segnate come visulizzate', error);
        throw error; // Propaga l'errore per gestirlo nel componente chiamante
    }
}
async function getNotifiche(pagine, quantita) {
    try {
        //numeroElementi

        //numeroPagina
        const url = '/notificheTotali?numeroElementi=' + quantita + '&numeroPagina=' + (pagine - 1);
        console.log('URL:', url);
        const response = await getRestWithtoken(url, {});
        console.log('Response:', response);
        return response;
    } catch (error) {
        console.log('Errore: le notifiche non sono state recuperate', error);
        throw error; // Propaga l'errore per gestirlo nel componente chiamante
    }
}
async function getNumeroNotifiche() {
    try {
        const response = await getRestWithtoken('/numeroNotificheTotali', {});
        return response;
    } catch (error) {
        console.log('Errore nel getNumeroNotificheeeeeeee: ', error);
        console.log('Errore: il numero delle notifiche non è stato recuperato', error);
        throw error; // Propaga l'errore per gestirlo nel componente chiamante
    }
}
export {
    apriWebSocketNotifichePersonali,
    getNumeroDiNotificheNonLette,
    getNotificheNonLette,
    getNotifiche,
    setNotificaVisualizzata,
    getNumeroNotifiche,
};

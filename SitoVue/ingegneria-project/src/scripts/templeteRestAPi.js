// Importa Axios
const axios = require('axios');

// Funzione per effettuare una richiesta GET alle API REST utilizzando Axios
async function getDatoFromAPI() {
    try {
        const response = await axios.get('URL_DELLE_TUE_API_REST');
        return response.data;
    } catch (error) {
        console.error('Errore durante la richiesta GET alle API:', error);
        throw error;
    }
}

// Funzione per controllare se il dato è presente nei cookie
function checkCookie(nomeDato) {
    const cookies = document.cookie.split(';');
    for (const element of cookies) {
        const cookie = element.trim();
        if (cookie.startsWith(nomeDato + '=')) {
            // Il dato è presente nei cookie
            return true;
        }
    }
    // Il dato non è presente nei cookie
    return false;
}

// Funzione principale per gestire il flusso
async function gestisciFlusso() {
    const nomeDato = 'IL_TUO_NOME_DATO';

    // Controlla se il dato è presente nei cookie
    if (checkCookie(nomeDato)) {
        console.log('Il dato è già presente nei cookie.');
        // Esegui qui il codice per gestire il caso in cui il dato è già presente nei cookie
    } else {
        console.log('Il dato non è presente nei cookie. Chiamando le API REST...');
        try {
            // Chiama le API REST per ottenere il dato
            const dato = await getDatoFromAPI();
            console.log('Dato ottenuto dalle API REST:', dato);
            // Esegui qui il codice per gestire il caso in cui il dato viene ottenuto dalle API REST
            // Aggiungi il dato ai cookie
            document.cookie = `${nomeDato}=${dato}`;
        } catch (error) {
            console.error('Errore durante il recupero del dato dalle API REST:', error);
            // Esegui qui il codice per gestire eventuali errori durante la chiamata alle API REST
        }
    }
}

// Chiama la funzione principale per avviare il flusso
gestisciFlusso();

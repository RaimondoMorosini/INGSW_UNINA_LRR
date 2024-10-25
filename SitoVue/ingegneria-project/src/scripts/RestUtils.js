import axios from 'axios';
import { getDato } from './DatiUtils';

axios.defaults.baseURL = 'http://localhost:8081/';
axios.defaults.headers.common['Authorization'] = `Bearer }`;

// Funzione per ottenere un elemento dal server
async function getRest(path) {
    try {
        const response = await axios.get(`${path}`);
        console.log('Get path:', path, ' response.data:', response.data);
        return response.data;
    } catch (error) {
        console.error(error);
        throw new Error("Impossibile ottenere l'elemento dal server" + error);
    }
}

async function postRest(path, data) {
    try {
        console.log('Post path:', path, ' dati body: ', JSON.stringify(data));
        delete axios.defaults.headers.common['Authorization'];
        const response = await axios.post(`${path}`, data);
        console.log(' response.data:', response.data);
        return response.data;
    } catch (error) {
        console.error(error);
        throw new Error("Impossibile inviare l'elemento al server" + error);
    }
}

//funzioni per ottenere e inviare dati al server con il token

async function getRestWithtoken(path) {
    try {
        const response = await axios.get(`${path}`);
        console.log('Get path:', path, ' response.data:', response.data);
        return response;
    } catch (error) {
        console.error(error);
        throw new Error("Impossibile ottenere l'elemento dal server" + error);
    }
}

async function postRestWithtoken(path, data) {
    try {
        const token = getDato('token');
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

        const headers = {
            Authorization: `Bearer ${token}`,
            'User-Agent': 'NomeDellApplicazione/versione',
        };
        console.log('Post path:', path, ' dati body: ', data, ' headers: ', headers);
        const response = await axios.post(`${path}`, data, headers);
        console.log(' response.data:', response.data);
        delete axios.defaults.headers.common['Authorization'];

        return response.data;
    } catch (error) {
        delete axios.defaults.headers.common['Authorization'];
        console.error(
            'errore nella chiamata Post: ',
            error.response ? error.response.data : error.message
        );
        throw new Error("Impossibile inviare l'elemento al server" + error);
    }
}

// Aggiungi un interceptor per le richieste
axios.interceptors.request.use(
    function (config) {
        console.log('Richiesta in uscita:');
        console.log('URL:', config.url);
        console.log('Metodo:', config.method);
        console.log('Header:', config.headers);
        console.log('Dati:', config.data);
        return config;
    },
    function (error) {
        return Promise.reject(error);
    }
);
function getToken() {
    const token =getDato('token');
    if(token!=null){
        const tokenPayload = JSON.parse(atob(token.split('.')[1]));
        const expiryTime = tokenPayload.exp * 1000;
        if (Date.now() >= expiryTime) {
            console.log('Token scaduto');
        }
        return token;
    }
    return null;
}

export { getRest, postRest, postRestWithtoken, getToken };


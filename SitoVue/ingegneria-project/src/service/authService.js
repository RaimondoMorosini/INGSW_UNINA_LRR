import { postRest } from '../scripts/RestUtils.js';
import { getDato, inserisciDato, eliminaDato } from '../scripts/DatiUtils.js';
import { useAuth0 } from '@auth0/auth0-vue';
import { useProfiloStore } from '../stores/profiloStore.js';

export async function login(inputEmail, inputPassword, inputMetodoDiRegistrazione) {
    try {
        const bodyData = {
            email: inputEmail,
            password: inputPassword,
            metodoDiRegistrazione: inputMetodoDiRegistrazione,
        };

        const token = await postRest('prova/hello', bodyData);
        console.log('Token ricevuto:', token);
        if (token) {
            inserisciDato('token', token);
            const profiloStore = useProfiloStore();
            const auth0 = useAuth0();

            profiloStore.profilo.nome = auth0.user.name;
            profiloStore.profilo.isAutenticato = true;
            profiloStore.profilo.immagine= auth0.user.picture;
            console.log('Immagine:', auth0.user.picture);
            profiloStore.profilo.email = auth0.user.email;

            return true;
        } else {
            throw new Error('Login fallito: token non ricevuto');
        }
    } catch (error) {
        console.error('Login error:', error);
        throw error;
    }
}

export function logoutAndClean() {
    console.log('Logout and clean');
    // Aggiungi qui la logica per il logout e la pulizia dei dati
}
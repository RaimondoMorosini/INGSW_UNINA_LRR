import {postRest} from '../scripts/RestUtils.js';
import {getDato,inserisciDato,eliminaDato} from '../scripts/DatiUtils.js';
import { useAuth0 } from '@auth0/auth0-vue';
import { useProfiloStore } from '../stores/profiloStore.js';

async function login(inputEmail, inputPassword,inputMetodoDiRegistrazione) {
    try {
        const bodyData = {
            email:"inputEmail@prova.it",
            password: "inputPassword",
            metodoDiRegistrazione: "inputMetodoDiRegistrazione",
        };

        const token = await postRest('prova/hello', bodyData);
        console.log('Token ricevuto:', token);
        if (token) {
            inserisciDato('token', token);
            useProfiloStore().profilo.nome= useAuth0().user.name;
            useProfiloStore().profilo.isAutenticato=true;

            return true;
        } else {
            throw new Error('Login fallito: token non ricevuto');
        }
    } catch (error) {
        console.error('Login error:', error);
        throw error;
    }
}
function logoutAndClean() {
    console.log('Logout and clean');
}

export { login ,logoutAndClean};
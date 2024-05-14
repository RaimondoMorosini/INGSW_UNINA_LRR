import axios from 'axios';
import { inserisciDato } from '../DatiUtils';
// Funzione per effettuare il login
function login(emailInput, passwordInput, metodoDiRegistrazioneInPut) {
    try {
        axios
            .post('http://localhost:8081/auth/login', {
                email: emailInput,
                password: passwordInput,
                metodoDiRegistrazione: metodoDiRegistrazioneInPut,
            })
            .then((response) => {
                console.log('Token: ', response.data);
                // Salva il token JWT come cookie
                inserisciDato('token', response.data);
            });

        // Restituisce true se il login è andato a buon fine
        return true;
    } catch (error) {
        console.error('Errore durante il login:', error);
        return false;
    }
}

export { login };

import login from './Login.js';
import registrazione from './Registrazione.js';

// Esegui il login
const effettuatoLogin = await login('utente@example.com', 'password');
if (effettuatoLogin) {
    console.log('Login effettuato con successo!');
} else {
    console.error('Login fallito.');
}

// Esegui la registrazione
const effettuataRegistrazione = await registrazione(
    'Mario Rossi',
    'mario.rossi@example.com',
    'password123'
);
if (effettuataRegistrazione) {
    console.log('Registrazione effettuata con successo!');
} else {
    console.error('Registrazione fallita.');
}

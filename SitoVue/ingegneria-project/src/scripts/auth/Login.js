import axios from 'axios';
import {inserisciDato} from "../DatiUtils";
// Funzione per effettuare il login
async function login(email, password , metodoDiRegistrazione) {
  try {
    const risposta = await axios.post('http://localhost:8081/auth/login', {
      "email":"3roby3@gmail.com",
      "password":"123",
      "metodoDiRegistrazione":"dieti"
  });

    const { token } = risposta.data;
    console.log("risposta login axios:  ",token);

    // Salva il token JWT come cookie
    document.cookie = `token=${token};path=/`;

    //inserisciDato('token', token);


    // Restituisce true se il login è andato a buon fine
    return true;
  } catch (error) {
    console.error('Errore durante il login:', error);
    return false;
  }
}

export { login};

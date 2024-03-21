<script setup>
import { inserisciDato, checkEsisteDato ,eliminaDato, getDato} from '../scripts/DatiUtils';
import { ref } from "vue";
import { login } from "../scripts/auth/Login";

let datoEsiste = false;
let messaggio = ref ("Il dato non esiste!"); 
let nomeDato = 'dato2';
let valoreDato = 'val1';
const insDato = () => {
  console.log('insDato');
  inserisciDato(nomeDato, valoreDato);
};

const controllaDato = async () => {
  datoEsiste = await checkEsisteDato(nomeDato);
  console.log('datoEsiste', datoEsiste);
  
  if (datoEsiste) {
    messaggio.value = 'Il dato esiste!';
  } else {
    messaggio.value = 'Il dato non esiste!';
  }
};



const token = ref(null);
const loginTest = async (email, password, ruolo) => {
  const effettuatoLogin = await login(email, password, ruolo);
if (effettuatoLogin) {
  console.log('Login effettuato con successo!');
  token.value = getDato('token');
} else {
  console.error('Login fallito.');
}

}


</script>
<template>
  
    <button class="hover:bg-slate-400" @click="insDato">Inserisci Dato</button>
    <br>
    <button class="hover:bg-slate-400 "@click="controllaDato ">Controlla se il Dato Esiste</button>
    <br>
    <button class="hover:bg-slate-400 "@click="eliminaDato(nomeDato) ">elimina Dato</button>
    <p >{{ messaggio }}</p>
    <button class="hover:bg-slate-400" @click='loginTest("3roby3@gmail.com","123","dieti")'>Login</button>
    <p>
      token: {{ token }}
    </p>  

</template>


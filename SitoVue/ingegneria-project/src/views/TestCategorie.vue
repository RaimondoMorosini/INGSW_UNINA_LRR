<script setup>
import { inserisciDato, checkEsisteDato ,eliminaDato} from '../scripts/DatiUtils';
import { ref } from "vue";

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


</script>
<template>
  
    <button class="hover:bg-slate-400" @click="insDato">Inserisci Dato</button>
    <br>
    <button class="hover:bg-slate-400 "@click="controllaDato ">Controlla se il Dato Esiste</button>
    <br>
    <button class="hover:bg-slate-400 "@click="eliminaDato(nomeDato) ">elimina Dato</button>
    <p >{{ messaggio }}</p>
  
</template>


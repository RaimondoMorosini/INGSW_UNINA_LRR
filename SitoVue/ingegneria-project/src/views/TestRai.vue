<template>
  <div class="p-4 md:p-8 bg-gray-100 min-h-screen">
    <div class="bg-white shadow rounded-lg p-6">
      <!-- Sezione immagine profilo e nome -->
      <div class="flex flex-col md:flex-row items-center md:items-start">
        <img v-if="utente.immagine"
          :src="utente.immagine"
          alt="Foto profilo"
          class="w-32 h-32 md:w-48 md:h-48 rounded-full object-cover shadow-md"
        />
        <img v-else src="https://via.placeholder.com/150" alt="Foto profilo"
         class="w-32 h-32 md:w-48 md:h-48 rounded-full object-cover shadow-md" />
        <div class="mt-4 md:mt-0 md:ml-6 text-center md:text-left">
          <h1 class="text-2xl font-bold text-gray-800">{{ utente.nome }} {{ utente.cognome }}</h1>
          <p class="text-sm text-gray-600 mt-2">{{ utente.area_geografica }}</p>
          <p class="text-sm text-gray-600 mt-1">{{ utente.bio }}</p>
        </div>
      </div>

      <!-- Sezione dettagli -->
      <div class="mt-6 grid grid-cols-1 md:grid-cols-2 gap-4">
        <div>
          <h2 class="text-lg font-semibold text-gray-800">Informazioni di Contatto</h2>
          <ul class="mt-2 text-gray-600">
            <li><strong>Email:</strong> {{ utente.datiVenditore.nomeUtente }}</li>
            <li><strong>Telefono:</strong> {{ utente.datiVenditore.numeroTelefono }}</li>
          </ul>
        </div>
        <div>
          <h2 class="text-lg font-semibold text-gray-800">Dati Aziendali</h2>
          <ul class="mt-2 text-gray-600">
            <li><strong>Partita IVA:</strong> {{ utente.datiVenditore.partitaIva }}</li>
            <li><strong>Codice Fiscale:</strong> {{ utente.datiVenditore.codiceFiscale }}</li>
            <li><strong>Nome Azienda:</strong> {{ utente.datiVenditore.nomeAzienda }}</li>
          </ul>
        </div>
      </div>

      <!-- Sezione link -->
      <div class="mt-6">
        <h2 class="text-lg font-semibold text-gray-800">Siti Web</h2>
        <div v-if="utente.siti" class="mt-2">
          <ul class="list-disc pl-5 text-blue-600">
            <li v-for="sito in sitiWeb" :key="sito">
              <a :href="sito" target="_blank" rel="noopener noreferrer" class="hover:underline">
                {{ sito }}
              </a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { getDatiProfiloPublici } from "../service/profiloService";
// Dati statici per l'utente (puoi sostituirli con dati dinamici)
const utente = ref({
  nome: "Nome",
  cognome: "Cognome",
  siti: "[https://x.com/elonmusk]",
  area_geografica: "Citta, Provincia",
  bio: "Bio dell'utente",
  immagine:
    "https://via.placeholder.com/150",
  isVenditore: true,
  datiVenditore: {
    nomeUtente: "morosini.ragusa@gmail.com",
    partitaIva: "partita iva",
    codiceFiscale: "codice fiscale",
    nomeAzienda: "nome azienda",
    numeroTelefono: "numero telefono",
  },
});
const emailInput= ref("morosini.ragusa@gmail.com");
getDatiProfiloPublici(emailInput.value).then((response) => {
  utente.value = response;
  console.log("dati profilo...", response); // Mostra i dati ricevuti
});
//set siti per test
utente.value.siti="[https://x.com/elonmusk, https://www.facebook.com/elonmusk, https://www.instagram.com/elonmusk,https://www.linkedin.com/in/elonmusk, https://www.youtube.com/elonmusk, https://www.tiktok.com/@elonmusk, https://www.reddit.com/user/elonmusk, https://www.quora.com/profile/Elon-Musk, https://bsky.app/elonmusk, https://www.threads.net/@elonmusk, https://angel.co/elonmusk]";


// Funzione per gestire i siti web
const sitiWeb = utente.value.siti
  .replace(/\[|\]/g, "") // Rimuove parentesi quadre
  .split(",") // Divide i siti in un array
  .map((sito) => sito.trim()); // Rimuove spazi superflui
</script>

<style>
/* Lo stile viene gestito principalmente da Tailwind CSS */
</style>

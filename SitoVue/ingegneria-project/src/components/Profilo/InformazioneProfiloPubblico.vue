<template>
    <div class="p-4 md:p-8 bg-gray-100 min-h-screen">
      <div class="bg-white shadow rounded-lg p-6">
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
             <template v-if="utente.datiVenditore">
              <span class="text-sm bg-primario-200 text-primario-600 font-medium px-2 py-1 rounded mt-2">Account Venditore</span>
            </template>
            <p class="text-sm text-gray-600 mt-2">{{ emailInput}}</p>
            <p class="text-sm text-gray-600 mt-2">{{ utente.area_geografica }}</p>
            <p class="text-sm text-gray-600 mt-1">{{ utente.bio }}</p>
  
           
          </div>
        </div>
  
        <div class="mt-6 grid grid-cols-1 md:grid-cols-2 gap-4">
          <template v-if="utente.datiVenditore">
            <div>
              <h2 class="text-lg font-semibold text-gray-800">Informazioni di Contatto</h2>
              <ul class="mt-2 text-gray-600">
                <li><strong>Telefono:</strong> {{ utente.datiVenditore.numeroTelefono }}</li>
                <li><strong>Email:</strong> {{ utente.datiVenditore.nomeUtente }}</li>
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
          </template>
        </div>
  
        <div class="mt-6">
          <h2 class="text-lg font-semibold text-gray-800">Link Social</h2>
          <div v-if="utente.siti" class="mt-2">
            <ul class="flex flex-wrap gap-4">
              <li v-for="sito in sitiWeb" :key="sito.url"
                class="flex items-center space-x-2 bg-primario-50/50 p-2 rounded shadow hover:shadow-lg transition">
                <i :class="sito.icona" class="text-blue-600 text-xl"></i>
                <a :href="sito.url" target="_blank" rel="noopener noreferrer" class="text-blue-600 hover:underline">
                  {{ sito.nome }}
                </a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    utente immagine: {{utente.immagine}}
  </template>

  <script setup>
  import '@fortawesome/fontawesome-free/css/all.min.css';
  
  import { ref,computed ,defineProps,onMounted} from "vue";
  import { getDatiProfiloPublici } from "../../service/profiloService";
  // Dati statici per l'utente (puoi sostituirli con dati dinamici)
  const utente = ref({
    nome: "",
    cognome: "",
    siti: "[]",
    area_geografica: "",
    bio: "",
    immagine:
      "https://via.placeholder.com/150",
    isVenditore: true,
    datiVenditore: {
      nomeUtente: "",
      partitaIva: "",
      codiceFiscale: "",
      nomeAzienda: "",
      numeroTelefono: "",
    },
  });
  const utenteTimestamp= ref ({})
  const props = defineProps(['email']);
  const emailInput= ref(props.email);
  onMounted(() => {
    getDatiProfiloPublici(emailInput.value).then((response) => {
      
        utente.value = response;
        console.log("utente...", utente.value);
        utenteTimestamp.value = response;; 
      
      console.log("dati profilo...", response); // Mostra i dati ricevuti
    }).catch((error) => {
      console.log("Errore nel caricamento del profilo", error);
    });
  });
  // Associazioni sito -> icona
  const iconeSiti = {
    "x.com": "fab fa-twitter",
    "twitter.com": "fab fa-twitter",
    "facebook.com": "fab fa-facebook",
    "instagram.com": "fab fa-instagram",
    "linkedin.com": "fab fa-linkedin",
    "youtube.com": "fab fa-youtube",
    "tiktok.com": "fab fa-tiktok",
    "github.com": "fab fa-github",
    "gitlab.com": "fab fa-gitlab",
    "bitbucket.org": "fab fa-bitbucket",
    "stackoverflow.com": "fab fa-stack-overflow",
    "reddit.com": "fab fa-reddit",
    "pinterest.com": "fab fa-pinterest",
    "snapchat.com": "fab fa-snapchat",
    "spotify.com": "fab fa-spotify",
    "amazon.com": "fab fa-amazon",
    "google.com": "fab fa-google",
    "microsoft.com": "fab fa-microsoft",
    "apple.com": "fab fa-apple",
    "dropbox.com": "fab fa-dropbox",
    "slack.com": "fab fa-slack",
    "dribbble.com": "fab fa-dribbble",
    "behance.net": "fab fa-behance",
    "medium.com": "fab fa-medium",
    "vimeo.com": "fab fa-vimeo",
    "paypal.com": "fab fa-paypal",
  
    // Piattaforme di messaggistica
    "whatsapp.com": "fab fa-whatsapp",
    "telegram.com": "fab fa-telegram",
    "discord.com": "fab fa-discord",
    "signal.org": "fab fa-signal",
  
    // Piattaforme di streaming
    "twitch.tv": "fab fa-twitch",
    "mixer.com": "fab fa-mixer",
  
    // Piattaforme di blogging
    "tumblr.com": "fab fa-tumblr",
    "blogger.com": "fab fa-blogger",
  
    // Piattaforme di e-commerce
    "etsy.com": "fab fa-etsy",
    "shopify.com": "fab fa-shopify",
  
    // Piattaforme di recensioni
    "yelp.com": "fab fa-yelp",
    "tripadvisor.com": "fab fa-tripadvisor",
  
    // Piattaforme di cloud storage
    "onedrive.com": "fab fa-onedrive",
    "googledrive.com": "fab fa-google-drive",
  
    // Piattaforme di sviluppo
    "stack-overflow.com": "fab fa-stack-overflow",
    "github.com": "fab fa-github",
    "gitlab.com": "fab fa-gitlab",
    "bitbucket.org": "fab fa-bitbucket",
  
    // Piattaforme di design
    "figma.com": "fab fa-figma",
    "sketch.com": "fab fa-sketch",
  
    // Altre piattaforme popolari
    "reddit.com": "fab fa-reddit",
    "pinterest.com": "fab fa-pinterest",
    "snapchat.com": "fab fa-snapchat",
    "spotify.com": "fab fa-spotify",
    "amazon.com": "fab fa-amazon",
    "google.com": "fab fa-google",
    "microsoft.com": "fab fa-microsoft",
    "apple.com": "fab fa-apple",
    "dropbox.com": "fab fa-dropbox",
    "slack.com": "fab fa-slack",
    // ... (aggiungi altre icone a piacere)
  };
  
  

  const sitiWeb = computed(() => {
    if (!utente.value.siti) return [];
    try {
      return utente.value.siti
        .replace(/\[|\]/g, "") // Rimuove parentesi quadre
        .split(",") // Divide i siti in un array
        .map((sito) => {
          const url = sito.trim();
          const dominio = new URL(url).hostname.replace("www.", "");
          return {
            url,
            nome: dominio + new URL(url).pathname,
            icona: iconeSiti[dominio] || "fas fa-globe", // Icona predefinita
          };
        });
    } catch (error) {
      console.info("Errore nel parsing dei siti", error);
      return [];
    }
  });
  </script>
  
  <style>
  /* Lo stile viene gestito principalmente da Tailwind CSS */
  </style>
  
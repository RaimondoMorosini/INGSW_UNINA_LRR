<template>
    <div class="lista-aste">
      <h1>Aste Create</h1>
  
      <!-- Indicatore di caricamento -->
      <div v-if="caricamento" class="caricamento">
        <p>Caricamento in corso...</p>
      </div>
  
      <!-- Messaggio se nessuna asta è disponibile -->
      <div v-else-if="aste.length === 0" class="nessuna-asta">
        <p>Non hai ancora creato nessuna asta.</p>
      </div>
  
      <!-- Lista delle aste -->
      <div v-else class="griglia-aste">
        <div
          v-for="asta in aste"
          :key="asta.idAsta"
          class="card-asta"
          @click="vaiAllaPaginaAsta(asta.idAsta)"
        >
          <div class="overlay" v-if="isAstaScaduta(asta.dataScadenza)">
            <p>Asta Scaduta</p>
          </div>
          <img
            :src="asta.immagini[0] || 'https://via.placeholder.com/150'"
            alt="Immagine asta"
            class="immagine-asta"
          />
          <div class="contenuto-asta">
            <h2>{{ asta.nome }}</h2>
            <h3>{{ asta.tipoAsta }}</h3>
            <p>Scadenza: {{ formatDataScadenza(asta.dataScadenza) }}</p>
            <p>{{ mostraDescrizioneParziale(asta.descrizione) }}</p>
          </div>
        </div>
      </div>
  
      <!-- Paginazione -->
      <div class="paginazione">
        <button :disabled="pagina === 1" @click="pagina-- && caricaAste()">Precedente</button>
        <span>Pagina {{ pagina }} di {{ totalePagine }}</span>
        <button :disabled="pagina === totalePagine" @click="pagina++ && caricaAste()">Successiva</button>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import { getAsteCreate, getNumeroAsteCreate } from '../../service/astaService';
  
  const aste = ref([]);
  const caricamento = ref(true);
  const pagina = ref(1);
  const elementiPerPagina = 5; // Numero di aste per pagina
  const totalePagine = ref(0);
  
  // Recupero delle aste
  const caricaAste = async () => {
    caricamento.value = true;
    try {
      const numeroAste = await getNumeroAsteCreate();
      totalePagine.value = Math.ceil(numeroAste / elementiPerPagina);
  
      aste.value = await getAsteCreate(pagina.value, elementiPerPagina);
    } catch (error) {
      console.error("Errore nel recupero delle aste:", error);
    } finally {
      caricamento.value = false;
    }
  };
  
  // Navigazione alla pagina dell'asta
  const vaiAllaPaginaAsta = (idAsta) => {
    window.location.href = `/asta/${idAsta}`;
  };
  
  // Funzione per mostrare descrizione parziale
  const mostraDescrizioneParziale = (descrizione) => {
    const lunghezzaMassima = 50;
    return descrizione.length > lunghezzaMassima
      ? descrizione.slice(0, lunghezzaMassima) + "..."
      : descrizione;
  };
  
  // Funzione per controllare se l'asta è scaduta
  const isAstaScaduta = (dataScadenza) => {
    return Date.now() > dataScadenza;
  };
  
  // Formatta la data di scadenza
  const formatDataScadenza = (dataScadenza) => {
    const date = new Date(dataScadenza);
    return date.toLocaleString(); // Formato locale
  };
  
  // Montaggio del componente
  onMounted(caricaAste);
  </script>
  
  <style scoped>
  /* Stile generale */
  .lista-aste {
    max-width: 1000px;
    margin: 0 auto;
    padding: 20px;
    font-family: Arial, sans-serif;
  }
  
  .caricamento, .nessuna-asta {
    text-align: center;
    margin-top: 50px;
    font-size: 18px;
    color: #555;
  }
  
  /* Stile griglia e card */
  .griglia-aste {
    display : grid;
    grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
    gap: 20px;
  }
  
  .card-asta {
    position: relative;
    display: flex;
    flex-direction: column;
    border: 1px solid #ccc;
    border-radius: 8px;
    padding: 10px;
    cursor: pointer;
    background: #fff;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    transition: transform 0.2s ease, box-shadow 0.2s ease;
  }
  
  .card-asta:hover {
    transform: scale(1.02);
    box-shadow: 0 6px 10px rgba(0, 0, 0, 0.15);
  }
  
  /* Overlay per le aste scadute */
  .overlay {
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background-color: rgba(255, 0, 0, 0.5); /* Rosso traslucido */
    color: white;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 20px;
    border-radius: 8px;
  }
  
  /* Immagine dell'asta */
  .immagine-asta {
    width: 100%;
    height: 150px;
    object-fit: cover;
    border-radius: 8px;
    margin-bottom: 10px;
  }
  
  /* Contenuto della card */
  .contenuto-asta h2 {
    font-size: 18px;
    margin: 0 0 8px;
    color: #333;
  }
  
  .contenuto-asta p {
    font-size: 14px;
    margin: 0;
    color: #666;
  }
  
  /* Paginazione */
  .paginazione {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 20px;
  }
  
  .paginazione button {
    padding: 10px;
    margin: 0 10px;
    border: none;
    background: #007bff;
    color: #fff;
    border-radius: 5px;
    cursor: pointer;
  }
  
  .paginazione button:disabled {
    background: #ccc;
    cursor: not-allowed;
  }
  
  .paginazione span {
    font-size: 16px;
  }
  </style>
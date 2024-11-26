<template>
  <div class="bg-white shadow-md rounded-lg overflow-hidden mb-4">
    <div class="flex">
      <img :src="asta.immagini[0]" :alt="'Immagine del prodotto: ' + asta.nome" class="w-1/3 object-cover">
      <div class="p-4 w-2/3">
        <h2 class="text-xl font-bold mb-2">{{ asta.nome }}</h2>
        <p class="text-gray-700 mb-2">{{ asta.descrizione }}</p>
        <p class="text-gray-500 mb-2">Categoria: {{ asta.categoria }}</p>
        <p class="text-gray-500 mb-2">Base Asta: €{{ asta.baseAsta }}</p>
        <p class="text-gray-500 mb-2">Prezzo Attuale: €{{ asta.prezzoAttuale }}</p>
        <p class="text-gray-500 mb-2">Data Scadenza: {{ new Date(asta.dataScadenza).toLocaleString() }}</p>
        <p class="text-gray-500 mb-2">Tipo Asta: {{ asta.tipoAsta }}</p>
        <p class="text-gray-500 mb-2">Creato da: {{ asta.emailUtenteCreatore }}</p>

        <!-- Bottone per andare alla pagina dell'asta -->
        <button 
          @click="apriPaginaAsta(asta.idAsta)" 
          class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-700">
          Vai alla Pagina dell'Asta
        </button>

        <!-- Bottone per confermare il vincitore (solo per asta silenziosa) -->
        <button 
          v-if="asta.tipoAsta === 'asta_silenziosa'" 
          @click="confermaVincitore(asta.idAsta)" 
          class="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-700 ml-4">
          Conferma Vincitore
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { defineProps } from 'vue';
import { useRouter } from 'vue-router';

// Importa le prop
const props = defineProps({
  asta: {
    type: Object,
    required: true,
  },
});

const router = useRouter();

// Funzione per aprire la pagina dell'asta
const apriPaginaAsta = (idAsta) => {
  const link = `/asta/${idAsta}`;
  router.push(link);
};

// Funzione per gestire l'azione di selezionare il vincitore
const confermaVincitore = () => {
  alert(`Seleziona il vincitore per l'asta: ${props.asta.nome}`);
};
</script>

<style scoped>
.elemento-asta {
  background-color: #ffffff;
}
.elemento-asta:hover {
  background-color: #f8fafc;
}
</style>

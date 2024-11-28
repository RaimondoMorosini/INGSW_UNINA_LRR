<template>
    <div class="mb-4 overflow-hidden rounded-lg bg-white shadow-md">
        <div class="flex">
            <img
                :src="asta.immagini[0]"
                :alt="'Immagine del prodotto: ' + asta.nome"
                class="w-1/3 object-cover"
            />
            <div class="w-2/3 p-4">
                <h2 class="mb-2 text-xl font-bold">{{ asta.nome }}</h2>
                <p class="mb-2 text-gray-700">{{ asta.descrizione }}</p>
                <p class="mb-2 text-gray-500">Categoria: {{ asta.categoria }}</p>
                <p class="mb-2 text-gray-500">Base Asta: €{{ asta.baseAsta }}</p>
                <p class="mb-2 text-gray-500">Prezzo Attuale: €{{ asta.prezzoAttuale }}</p>
                <p class="mb-2 text-gray-500">
                    Data Scadenza: {{ new Date(asta.dataScadenza).toLocaleString() }}
                </p>
                <p class="mb-2 text-gray-500">Tipo Asta: {{ asta.tipoAsta }}</p>
                <p class="mb-2 text-gray-500">Creato da: {{ asta.emailUtenteCreatore }}</p>

                <!-- Bottone per andare alla pagina dell'asta -->
                <button
                    @click="apriPaginaAsta(asta.idAsta)"
                    class="rounded bg-blue-500 px-4 py-2 text-white hover:bg-blue-700"
                >
                    Vai alla Pagina dell'Asta
                </button>

                <!-- Bottone per confermare il vincitore (solo per asta silenziosa) -->
                <button
                    v-if="asta.tipoAsta === 'asta_silenziosa'"
                    @click="confermaVincitore(asta.idAsta)"
                    class="ml-4 rounded bg-green-500 px-4 py-2 text-white hover:bg-green-700"
                >
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

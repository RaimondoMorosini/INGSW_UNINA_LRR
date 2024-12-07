<template>
         <div class="flex flex-col items-center justify-center">
            <h2 class="text-xl font-bold text-primario-500">Aggiungi altre informazioni sul prodotto</h2>
            </div>
    <div v-if="caratteristicheRelativeAllaCategoria.length > 0" class="mx-2 my-2 gap-2 rounded px-2 py-2 ring-2 ring-primario-600">
        <form>
            <div class="mx-2 my-2 bg-slate-200/20 px-2 py-2 max-h-[300px] overflow-y-auto">
                <InputField
                    v-for="caratteristica in caratteristicheRelativeAllaCategoria"
                    :key="caratteristica.id"
                    :label="caratteristica.nomeCaratteristica"
                    :options="caratteristica.opzioniSelezionabili"
                    v-model="arrayValoriSelezionati[caratteristica.id]"
                />
                <hr class="my-4" />
            </div>

        </form>
    </div>
    <div v-else class="text-center py-4">
        <p class="text-gray-500">Non ci sono caratteristiche disponibili per questa categoria.</p>
    </div>
    <div class="areaBottoni flex justify-around gap-5">
                <Button class="w-[45%]" size="large" @click="goToPreviousForm">
                    <span class="font-bold">
                        <i class="pi pi-arrow-left"></i> Precedente
                    </span>
                </Button>
                <Button class="w-[45%]" size="large" @click="gestioneInvio">
                    <span class="font-bold">
                        Successivo <i class="pi pi-arrow-right"></i>
                    </span>
                </Button>
            </div>
</template>

<script setup>
import { ref, computed, watch } from 'vue';
import Button from 'primevue/button';
import { getCaratteristiche } from '../../service/carateristicheService.js';
import { useAstaStore } from '../../stores/astaStore.js';
import InputField from './InputField.vue';
import { defineEmits } from 'vue';

// Emissioni per navigazione
const emit = defineEmits(['increase-page', 'decrease-page']);

// Store
const astaStoreInstance = useAstaStore();

// Sincronizzazione di `arrayValoriSelezionati` con lo store
const arrayValoriSelezionati = computed({
    get: () => astaStoreInstance.asta.caratteristiche || {},
    set: (val) => {
        astaStoreInstance.updateAsta({ caratteristiche: val });
    },
});

// Caratteristiche relative alla categoria
const caratteristicheRelativeAllaCategoria = ref([]);

// Watch per caricare le caratteristiche solo quando cambia la categoria
watch(
    () => astaStoreInstance.asta.categoria,
    (categoria) => {
        if (categoria) {
            getCaratteristiche(categoria)
                .then((response) => {
                    caratteristicheRelativeAllaCategoria.value = response;
                })
                .catch((error) => {
                    console.error('Errore nel recupero delle caratteristiche:', error);
                    caratteristicheRelativeAllaCategoria.value = [];
                });
        } else {
            caratteristicheRelativeAllaCategoria.value = [];
        }
    },
    { immediate: true } // Carica immediatamente al montaggio
);

// Navigazione tra le pagine del form
const gestioneInvio = () => {
    emit('increase-page');
};

const goToPreviousForm = () => {
    emit('decrease-page');
};
</script>

<style scoped>
/* Stile per contenitore con scrollbar */
.max-h-[300px] {
    max-height: 300px; /* Altezza massima */
}
.overflow-y-auto {
    overflow-y: auto; /* Abilita la scrollbar verticale */
}

/* Pulsanti con effetti di hover e focus */
.areaBottoni .w-[45%] {
    transition: background-color 0.3s ease, transform 0.2s ease;
}
.areaBottoni .w-[45%]:hover {
    background-color: #6d28d9;
    transform: scale(1.05);
}
.areaBottoni .w-[45%]:focus {
    outline: 2px solid #9333ea;
}

/* Messaggio di default */
.text-gray-500 {
    color: #6b7280; /* Grigio chiaro per il testo */
}
</style>

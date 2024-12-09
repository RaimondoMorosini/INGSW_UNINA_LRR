<template>
    <form @submit.prevent="gestioneInvio">
        <!-- Selezione tipo di asta -->
        <div class="flex flex-col items-center justify-center">
            <h2 class="text-xl font-bold text-primario-500">Seleziona tipo asta</h2>
            <div class="flex flex-col">
                <div 
                    v-for="(label, id) in tipiAsta" 
                    :key="id" 
                    class="flex items-center gap-2"
                >
                    <RadioButton v-model="tipoAsta" :inputId="id" :value="id" />
                    <label :for="id" class="ml-2">{{ label }}</label>
                </div>
            </div>
            <!-- Descrizione tipo di asta -->
            <p class="mt-3 text-gray-600">{{ descrizioneTipoAsta }}</p>
        </div>

        <!-- Input dinamici -->
        <div class="sezione-asta flex flex-col gap-2 rounded px-2 pt-2 pb-4 ring-2 ring-primario-600 mt-5 mb-5 ">
            <h2 class="text-xl font-bold text-[#cc85f5]">{{ labelTipoAsta }}</h2>

            <!-- Prezzo di Partenza -->
            <div class="bg-inherit pt-5">
                <FloatLabel variant="on">
                    <InputNumber
                        mode="currency"
                        currency="EUR"        
                        v-model="prezzoBase"
                        :min="1"
                        fluid
                        id="prezzoBase"
                        class="rounded bg-inherit"
                    />
                    <label for="prezzoBase">Prezzo di Partenza (valore in €)</label>
                </FloatLabel>
                <p class="text-sm text-gray-500 mt-2">
                    Il prezzo di partenza è la base d'asta da cui iniziare.
                </p>
            </div>

            <!-- Incremento (solo per asta inglese) -->
            <div v-if="tipoAsta === 'asta_inglese'" class="w-full pt-5">
                <FloatLabel variant="on">
                    <InputNumber
                        v-model="incremento"
                        :min="10"
                        fluid
                        id="incremento"
                        class="rounded"
                    />
                    <label for="incremento">Incremento(valore in €)</label>
                </FloatLabel>
                <p class="text-sm text-gray-500 mt-2">
                    L'incremento è il valore fisso con cui aumentare un'offerta.
                </p>
            </div>

            <!-- Durata estensione (solo per asta inglese) -->
            <div v-if="tipoAsta === 'asta_inglese'" class="pt-5">
                <FloatLabel variant="on">
                    <InputNumber
                        v-model="durataEstensione"
                        :min="1"
                        fluid
                        class="rounded"
                        id="durataEstensione"
                    />
                    <label for="durataEstensione">Durata estensione (ore)</label>
                </FloatLabel>
                <p class="text-sm text-gray-500 mt-2">
                    La durata estensione è il tempo aggiuntivo se arriva un'offerta poco prima della scadenza.
                </p>
            </div>

            <!-- Scadenza (comune per tutte le aste) -->
<div class="bg-inherit pt-5">
    <FloatLabel variant="on">
        <DatePicker
            v-model="scadenzaAsta"
            :minDate="minDate"
            dateFormat="dd/mm/yy"
            showIcon
            id="scadenzaAsta"
            class="rounded bg-inherit"
        />
        <label for="scadenzaAsta">Data Scadenza</label>
    </FloatLabel>
    <p v-if="testoScadenza" class="mt-2 text-sm text-gray-600">
        {{ testoScadenza }}
    </p>
</div>

        </div>

        <!-- Bottoni -->
        <div class="areaBottoni mx-4 flex justify-around gap-5 px-4">
            <Button class="w-[45%]" size="large" @click="goToPreviousForm">
                <span class="font-bold"><i class="pi pi-arrow-left"></i> Precedente</span>
            </Button>
            <Button class="w-[45%]" size="large" @click="gestioneInvio">
                <span class="font-bold">Successivo <i class="pi pi-arrow-right"></i></span>
            </Button>
        </div>
    </form>
</template>

<script setup>
import RadioButton from 'primevue/radiobutton';
import InputNumber from 'primevue/inputnumber';
import FloatLabel from 'primevue/floatlabel';
import Button from 'primevue/button';
import DatePicker from 'primevue/datepicker';
import { computed, defineEmits} from 'vue';
import { useAstaStore } from '../../stores/astaStore.js';

const emit = defineEmits(['decrease-page', 'increase-page']);
const tipiAsta = {
    asta_inglese: 'Asta Inglese',
    asta_inversa: 'Asta Inversa',
    asta_silenziosa: 'Asta Silenziosa',
};

const storeInstance = useAstaStore();

const tipoAsta = computed({
    get: () => storeInstance.asta.tipoAsta,
    set: (valore) => storeInstance.updateAsta({ tipoAsta: valore }),
});

const prezzoBase = computed({
    get: () => storeInstance.asta.prezzoBase || 1,
    set: (valore) => storeInstance.updateAsta({ prezzoBase: valore }),
});

const incremento = computed({
    get: () => storeInstance.asta.incremento || 10,
    set: (valore) => storeInstance.updateAsta({ incremento: valore }),
});

const durataEstensione = computed({
    get: () => storeInstance.asta.durataEstensione || 1,
    set: (valore) => storeInstance.updateAsta({ durataEstensione: valore }),
});

const scadenzaAsta = computed({
    get: () => {
        const scadenza = storeInstance.asta.scadenzaAsta;
        return scadenza ? new Date(scadenza) : minDate.value;
    },
    set: (valore) => {
        if (valore) {
            valore.setHours(23, 59, 59); // Imposta l'orario alle 23:59:59
            storeInstance.updateAsta({ scadenzaAsta: valore.getTime() });
        }
    },
});

const minDate = computed(() => {
    const dataMinima = new Date();
    dataMinima.setDate(dataMinima.getDate() + 1); // Data minima: domani
    return dataMinima;
});

// Descrizione del tipo di asta
const descrizioneTipoAsta = computed(() => {
    switch (tipoAsta.value) {
        case 'asta_inglese':
            return 'L\'asta inglese consente di fare offerte incrementali. Ogni offerta deve aumentare il prezzo corrente di un valore fisso.';
        case 'asta_inversa':
            return 'L\'asta inversa premia il miglior prezzo più basso. Gli acquirenti propongono offerte decrescenti.';
        case 'asta_silenziosa':
            return 'Nell\'asta silenziosa, gli offerenti non vedono le offerte degli altri. Vince chi offre il prezzo più alto.';
        default:
            return 'Seleziona un tipo di asta per vedere i dettagli.';
    }
});

// Etichetta dinamica per il titolo della sezione
const labelTipoAsta = computed(() => tipiAsta[tipoAsta.value] || 'Tipo di Asta');

// Testo per la scadenza dell'asta
const testoScadenza = computed(() => {
    if (!scadenzaAsta.value) return null;
    const opzioni = {
        weekday: 'long',
        year: 'numeric',
        month: 'long',
        day: 'numeric',
        hour: '2-digit',
        minute: '2-digit',
    };
    return `L'asta scadrà il ${scadenzaAsta.value.toLocaleDateString('it-IT', opzioni)}.`;
});

const gestioneInvio = () => {
    if (!prezzoBase || !scadenzaAsta) {
       if(tipoAsta.value === 'asta_inglese' && !incremento && !durataEstensione) {
            alert('Compila tutti i campi obbligatori.');
            return;
        }
        alert('Compila tutti i campi obbligatori.');
        return;
    }
    emit('increase-page');
};

const goToPreviousForm = () => {
    emit('decrease-page');
};
</script>

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
    storevalue <br>
    tipo asta: {{ storeInstance.asta.tipoAsta }} <br>
    incremento inglese:{{ storeInstance.asta.incremento }} <br>
    estensione inglese:{{ storeInstance.asta.durataEstensione }} <br>
    data scadenza:{{ storeInstance.asta.scadenzaAsta }}
</template>

<script setup>
import RadioButton from 'primevue/radiobutton';
import InputNumber from 'primevue/inputnumber';
import FloatLabel from 'primevue/floatlabel';
import Button from 'primevue/button';
import DatePicker from 'primevue/datepicker';
import { ref, computed, watch, onMounted } from 'vue';
import { useAstaStore } from '../../stores/astaStore.js';

const tipiAsta = {
    asta_inglese: 'Asta Inglese',
    asta_inversa: 'Asta Inversa',
    asta_silenziosa: 'Asta Silenziosa',
};

const tipoAsta = ref('');
const incremento = ref(10); // Valore predefinito: 10€
const durataEstensione = ref(1); // Valore predefinito: 1 ora
const scadenzaAsta = ref(null); // Scadenza inizialmente nulla
const minDate = ref(new Date());
//add one day to the min date
minDate.value.setDate(minDate.value.getDate() + 1);

const storeInstance = useAstaStore();

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
        minute: '2-digit' 
    };
    return `L'asta scadrà il ${scadenzaAsta.value.toLocaleDateString('it-IT', opzioni)}.`;
});


onMounted(() => {
    const oggi = new Date();
    minDate.value.setFullYear(oggi.getFullYear());
    minDate.value.setMonth(oggi.getMonth());

    // Sincronizzazione iniziale dallo store con conversione da unix time
    tipoAsta.value = storeInstance.asta.tipoAsta || '';
    incremento.value = storeInstance.asta.incremento || 10;
    durataEstensione.value = storeInstance.asta.durataEstensione || 1;

    // Converte il valore unix time (in millisecondi) in un oggetto Date
    if (storeInstance.asta.scadenzaAsta) {
        scadenzaAsta.value = new Date(storeInstance.asta.scadenzaAsta);
    }
});

// Osserva i cambiamenti e aggiorna lo store
watch(tipoAsta, (nuovoValore) => {
    storeInstance.updateAsta({ tipoAsta: nuovoValore });
});

watch(incremento, (nuovoValore) => {
    if (tipoAsta.value === 'asta_inglese') {
        storeInstance.updateAsta({ incremento: nuovoValore });
    }
});

watch(durataEstensione, (nuovoValore) => {
    if (tipoAsta.value === 'asta_inglese') {
        storeInstance.updateAsta({ durataEstensione: nuovoValore });
    }
});

watch(scadenzaAsta, (nuovoValore) => {
    if (nuovoValore) {
        // Imposta l'ora alle 23:59:59
        nuovoValore.setHours(23, 59, 59);
        // Converte l'oggetto Date in unix time (millisecondi)
        storeInstance.updateAsta({ scadenzaAsta: nuovoValore.getTime() });
    }
});


const gestioneInvio = () => {
    if (!scadenzaAsta.value) {
        alert('Compila tutti i campi obbligatori.');
        return;
    }
    console.log('Dati sincronizzati con lo store');
};

const goToPreviousForm = () => {
    emit('decrease-page');
};
</script>



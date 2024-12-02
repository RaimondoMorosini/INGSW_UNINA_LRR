<template>
    <div class="filtri grid grid-cols-1 gap-6 rounded-lg bg-gray-50 p-4 shadow-md lg:grid-cols-4">
        <!-- Input di ricerca -->
        <div>
            <InputGroup>
                <Button>
                    <i class="pi pi-search"></i>
                </Button>
                <FloatLabel variant="on">
                    <label for="filtro-ricerca" class="block text-sm font-medium text-gray-700">
                        Cerca un'asta
                    </label>

                    <!-- placeholder="🔍 Inserisci parole chiave"-->
                    <InputText
                        id="filtro-ricerca"
                        v-model="filtroRicerca"
                        @input="aggiornaFiltro('filtroRicerca', filtroRicerca)"
                        aria-label="Campo di ricerca per le aste"
                        class="w-full rounded-lg border border-gray-300 p-3 focus:ring-2 focus:ring-fuchsia-500"
                    />
                </FloatLabel>
            </InputGroup>
        </div>

        <!-- Selezione tipo di asta -->
        <div>
            <InputGroup>
                <Button>
                    <i class="pi pi-hammer"></i>
                </Button>
                <FloatLabel variant="on">
                    <MultiSelect
                        id="filtro-tipo-asta"
                        v-model="filtroTipoAsta"
                        :options="tipoAstaOptions"
                        optionLabel="label"
                        optionValue="value"
                        @change="aggiornaFiltro('filtroTipoAsta', filtroTipoAsta)"
                        aria-label="Selezione del tipo di asta"
                        class="w-full rounded-lg border border-gray-300 p-3 focus:ring-2 focus:ring-fuchsia-500"
                    />
                    <label for="filtro-tipo-asta" class="block text-sm font-medium text-gray-700">
                        Tipo di asta
                    </label>
                </FloatLabel>
            </InputGroup>
        </div>

        <!-- Filtro per scadenza -->
        <div>
            <InputGroup>
                <Button>
                    <i class="pi pi-hourglass"></i>
                </Button>
                <FloatLabel variant="on">
                    <MultiSelect
                        id="filtro-scadenza"
                        v-model="filtroScadenza"
                        :options="scadenzaOptions"
                        optionLabel="label"
                        optionValue="value"
                        @change="aggiornaFiltro('filtroScadenza', filtroScadenza)"
                        aria-label="Filtro per scadenza dell'asta"
                        class="w-full rounded-lg border border-gray-300 p-3 focus:ring-2 focus:ring-fuchsia-500"
                    />
                    <label for="filtro-scadenza" class="block text-sm font-medium text-gray-700">
                        Scadenza
                    </label>
                </FloatLabel>
            </InputGroup>
        </div>

        <!-- Ordine -->
        <div>
            <label for="ordine" class="block text-sm font-medium text-gray-700"> Ordina per </label>
            <Dropdown
                id="ordine"
                v-model="ordine"
                :options="ordineOptions"
                optionLabel="label"
                optionValue="value"
                placeholder="↕️ Scegli ordinamento"
                @change="aggiornaFiltro('ordine', ordine)"
                aria-label="Selezione dell'ordine delle aste"
                class="w-full rounded-lg border border-gray-300 p-3 focus:ring-2 focus:ring-fuchsia-500"
            />
        </div>
    </div>
</template>

<script setup>
import Button from 'primevue/button';
import InputGroup from 'primevue/inputgroup';
import InputGroupAddon from 'primevue/inputgroupaddon';
import FloatLabel from 'primevue/floatlabel';
import { ref, watch } from 'vue';
import { usaAstaStore } from '../../../stores/astePersonaliStore';
import InputText from 'primevue/inputtext';
import MultiSelect from 'primevue/multiselect';
import Dropdown from 'primevue/dropdown';

const astaStore = usaAstaStore();

// Riferimenti ai filtri
const filtroRicerca = ref(astaStore.filtroRicerca);
const filtroTipoAsta = ref(astaStore.filtroTipoAsta);
const filtroScadenza = ref(astaStore.filtroScadenza);
const ordine = ref(astaStore.ordine);

// Opzioni per i filtri
const tipoAstaOptions = [
    { label: 'Asta Inglese', value: 'asta_inglese' },
    { label: 'Asta Inversa', value: 'asta_inversa' },
    { label: 'Asta Silenziosa', value: 'asta_silenziosa' },
];

const scadenzaOptions = [
    { label: 'Non scadute', value: 'non_scadute' },
    { label: 'Scadute', value: 'scadute' },
];

const ordineOptions = [
    { label: 'Data di scadenza', value: 'dataScadenza' },
    { label: 'Prezzo', value: 'prezzo' },
];

// Funzione generica per aggiornare i filtri
const aggiornaFiltro = (filtro, valore) => {
    astaStore[`aggiorna${filtro.charAt(0).toUpperCase() + filtro.slice(1)}`](valore);
};

// Sincronizzazione con lo store
watch(
    () => astaStore.filtroRicerca,
    (newValue) => {
        filtroRicerca.value = newValue;
    }
);
watch(
    () => astaStore.filtroTipoAsta,
    (newValue) => {
        filtroTipoAsta.value = newValue;
    }
);
watch(
    () => astaStore.filtroScadenza,
    (newValue) => {
        filtroScadenza.value = newValue;
    }
);
watch(
    () => astaStore.ordine,
    (newValue) => {
        ordine.value = newValue;
    }
);
</script>

<style scoped>
.filtri {
    background-color: #f9fafb;
}

.filtri input:focus,
.filtri .p-inputtext:focus,
.filtri .p-dropdown:focus,
.filtri .p-multiselect:focus {
    border-color: #6366f1;
    box-shadow: 0 0 0 2px rgba(99, 102, 241, 0.5);
}

.filtri input::placeholder {
    color: #9ca3af;
    font-style: italic;
}
</style>

<template>
    <div class="card">
        <DataTable
            :value="props.offerte"
            paginator
            showGridlines
            :rows="5"
            dataKey="id"
            :loading="loading"
        >
            <template #header> </template>
            <template #empty> Non ci sono partecipanti attualmente. </template>
            <template #loading> Caricamento asta. </template>
            <Column header="Utente" :filterMenuStyle="{ width: '10rem' }" style="min-width: 10rem">
                <template #body="{ data }">
                    <div class="flex items-center gap-2">
                        <!-- <img :alt="data.representative.name" :src="`https://primefaces.org/cdn/primevue/images/avatar/${data.representative.image}`" style="width: 32px" /> -->
                        <span>{{ data.emailUtente }}</span>
                    </div>
                </template>
            </Column>
            <Column header="Data offerta" dataType="date" style="min-width: 10rem">
                <template #body="{ data }">
                    {{ formatDate(data.tempoOfferta) }}
                </template>
            </Column>
            <Column header="Prezzo offerto" dataType="numeric" style="min-width: 10rem">
                <template #body="{ data }"> {{ data.prezzoProposto }}€ </template>
            </Column>
        </DataTable>
    </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue';
import DataTable from 'primevue/datatable';
import Column from 'primevue/column';

const customers = ref();
const loading = ref(false);

const props = defineProps(['offerte']);

onMounted(() => {});

const formatDate = (unixTime) => {
    // Converti il timestamp Unix in millisecondi
    const date = new Date(unixTime);
    const day = String(date.getDate()).padStart(2, '0'); // Ottieni il giorno e aggiungi uno zero iniziale se necessario
    const month = String(date.getMonth() + 1).padStart(2, '0'); // I mesi partono da 0, quindi aggiungi 1
    const year = String(date.getFullYear()).slice(-2); // Prendi solo le ultime due cifre dell'anno
    const hours = String(date.getHours()).padStart(2, '0'); // Ottieni l'ora
    const minutes = String(date.getMinutes()).padStart(2, '0'); // Ottieni i minuti
    const seconds = String(date.getSeconds()).padStart(2, '0'); // Ottieni i secondi

    // Restituisci la data formattata
    return `${day}/${month}/${year} ${hours}:${minutes}:${seconds}`;
};

const getCustomers = (data) => {
    return [...(data || [])].map((d) => {
        d.date = new Date(d.date);

        return d;
    });
};

watch(props.offerte, () => {});
</script>

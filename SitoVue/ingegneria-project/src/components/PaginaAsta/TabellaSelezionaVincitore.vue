<template>
    <div>
        <!-- Bottone per aprire il dialog -->
        <Button label="Show" @click="() => { visible= true; caricamentoOfferte(); }" />
        
        <!-- Dialog gestito internamente -->
        <Dialog v-model:visible="visible" modal >
            <div class="card">
                <DataTable
                    :value="offerte"
                    paginator
                    showGridlines
                    :rows="5"
                    dataKey="id"
                    :loading="loading"
                    sortField="prezzoProposto"
                    sortOrder="-1"
                >
                <template #header>
                <div class="flex items-center justify-between">
                    <h3>Seleziona Vincitore astaid {{ astaId }}</h3>
                    <Button label="Aggiorna" icon="pi pi-refresh"  @click="caricamentoOfferte" />
                </div>
                </template>
                    <template #empty> Non ci sono partecipanti attualmente. </template>
                    <template #loading> Caricamento asta. </template>
                    <Column 
                        header="Utente" 
                        :filterMenuStyle="{ width: '10rem' }" 
                        style="min-width: 10rem" 
                        field="emailUtente" 
                        sortable
                    >
                        <template #body="{ data }">
                            <div class="flex items-center gap-2">
                                <span>{{ data.emailUtente }}</span>
                            </div>
                        </template>
                    </Column>
                    <Column 
                        header="Data offerta" 
                        dataType="date" 
                        style="min-width: 10rem" 
                        field="tempoOfferta" 
                        sortable
                    >
                        <template #body="{ data }">
                            {{ formatDate(data.tempoOfferta) }}
                        </template>
                    </Column>
                    <Column 
                        header="Prezzo offerto" 
                        dataType="numeric" 
                        style="min-width: 10rem" 
                        field="prezzoProposto" 
                        sortable
                    >
                        <template #body="{ data }"> {{ data.prezzoProposto }}€ </template>
                    </Column>
                    <Column  
                        header="Seleziona vincitore" 
                        style="width: 10rem">
                        <template #body="{ data }">
                            <BottoneSelezionaVincitore 
                            :offerta="data"
                             />
                        </template>
                    </Column>
                </DataTable>
            </div>
        </Dialog>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import DataTable from 'primevue/datatable';
import Column from 'primevue/column';
import Dialog from 'primevue/dialog';
import BottoneSelezionaVincitore from '../BottoneSelezionaVincitore.vue';
import { getOfferteSilenziose } from '../../service/offertaService';
import { Button } from 'primevue';
// Prop per l'ID dell'asta
const props = defineProps({
    astaId: {
        type: Number,
        required: true
    }
});
// Variabili di stato locali
const isOwner = ref(false);
const isSilentAuction = ref(false);
const offerte = ref([]);
const loading = ref(true);
const dialog = ref(null);
const visible = ref(false);


// Funzione per formattare la data
const formatDate = (unixTime) => {
    const date = new Date(unixTime);
    const day = String(date.getDate()).padStart(2, '0');
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const year = String(date.getFullYear()).slice(-2);
    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');
    const seconds = String(date.getSeconds()).padStart(2, '0');
    return `${day}/${month}/${year} ${hours}:${minutes}:${seconds}`;
};

// Funzione per caricare i dati all'avvio
function caricamentoOfferte(){
    loading.value=true;
    getOfferteSilenziose(props.astaId).then((data) => {
        alert('Offerte caricate');
        offerte.value = data;
        loading.value = false;
    }).catch((error) => {
        alert('Errore nel recupero delle offerte' ,props.astaId);
        console.error('Errore nel recupero delle offerte:', error);
        loading.value = false;
    });
}


// Funzione per aprire la schermata di selezione vincitore
function ApriSchermataDialogVincitore(offerta) {
    alert("Apri schermata dialog per selezionare il vincitore\n" + JSON.stringify(offerta));
}
</script>


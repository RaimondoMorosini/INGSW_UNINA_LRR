<template>
    <div class="card">
        <DataTable
            :value="props.offerte"
            paginator
            showGridlines
            :rows="5"
            dataKey="id"
            :loading="loading"
            sortField="tempoOfferta"
            sortOrder="-1"
        >
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
            <Column v-if="!isSilentAuction" 
                header="Prezzo offerto" 
                dataType="numeric" 
                style="min-width: 10rem" 
                field="prezzoProposto" 
                sortable
            >
                <template #body="{ data }"> {{ data.prezzoProposto }}€ </template>
            </Column>
        </DataTable>
    </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue';
import DataTable from 'primevue/datatable';
import Column from 'primevue/column';
import { useAstaChacheStore } from '../../stores/astaStore.js';
import { useProfiloStore } from '../../stores/profiloStore';

const astaStore = useAstaChacheStore();
const asta = ref({ idAsta: "", nome: '', descrizione: '', immagini: [] });

const customers = ref();
const loading = ref(false);

const props = defineProps(['offerte', 'astaId']);
const isOwner = ref(false);
const isSilentAuction = ref(false);


function ApriSchermataDialogVincitore(data){
    alert("Apri schermata dialog per selezionare il vincitore\n" + JSON.stringify(data));
}

onMounted(() => {

useAstaChacheStore().getAstaById(props.astaId).then((data) => {
    asta.value = data;
    const email = useProfiloStore().profilo.email;
        if (email === data.emailUtenteCreatore) {
            isOwner.value = true;
            
        }
        if (data.tipoAsta === 'asta_silenziosa') {
            isSilentAuction.value = true;
        }
    });
});

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



watch(props.offerte, () => {});
</script>


<template>
    <div class="card flex justify-center">
        <Button label="Seleziona Vincitore" icon="pi pi-trophy" @click="visible = true" />

        <Dialog fluid v-model:visible="visible" modal header="Seleziona Vincitore" >
            <p>Vuoi confermare questa offerta come vincitore dell'asta?</p>
            <p>Prezzo offerto: <b>{{ offerta.prezzoProposto }}€</b></p>
        <p>Proposta da: <b>{{ offerta.emailUtente }}</b></p>
            <div class="flex justify-end   justify-between pt-4 ">

                <Button label="Conferma" icon="pi pi-check" @click="confermaOfferta" />
                <Button label="Chiudi" icon="pi pi-times" @click="visible = false" />
            </div>

        </Dialog>
    </div>
</template>



<script setup>
import { ref } from 'vue';
import Button from 'primevue/button';
import Dialog from 'primevue/dialog';
import { ConfermaOffertaVincente } from '../service/offertaService';
const visible = ref(false);
// Props definiti automaticamente disponibili
const props = defineProps({
    offerta: {
        type: Object,
        required: true,
    }
});

// Funzione per confermare l'offerta
function confermaOfferta() {
    console.log('Conferma offerta vincente');
    console.log('offerta:', props.offerta); // Qui accedi direttamente a `offerta`, senza `props`
    ConfermaOffertaVincente(props.offerta.astaId, props.offerta.id).then(() => {
        visible.value = false;
        alert('Offerta confermata come vincitore');
    }).catch((error) => {
        console.error('Errore nella conferma dell\'offerta:', error);
        alert('Errore nella conferma dell\'offerta');
    });
}
</script>

<style scoped>
/* Aggiungi eventuali stili personalizzati qui */
</style>

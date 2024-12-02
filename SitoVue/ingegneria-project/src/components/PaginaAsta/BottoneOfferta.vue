<template>
    <div class="card">
        <h2 class="title">{{ titoloAsta }}
            <Button icon="pi pi-question-circle"  severity="contrast"  v-tooltip.bottom="descrizioneAsta" variant="text" rounded aria-label="Filter" />   
        </h2>
        <p v-if="isOwner">Sei il creatore dell'asta</p>
        <div v-if="tipoAsta === TipoAsta.INGLESE" class="content">
            <p class="info text-base">
                {{ testoPrezzo }}
                <span :style="{ color: colorePrezzo, fontSize: sizePrezzo }" class="font-bold"
                    >{{ prezzoAttuale }}€</span
                >
           

                <br>ultima offerta di: 
                <span v-if="utenteUltimaOfferta" :style="{ color: coloreUltimaOfferta }">
                    {{ utenteUltimaOfferta }}</span>
                    <span v-else> nessuno ha fatto un'offerta </span>
                    
            </p>
            <br>
            
            <p class="info text-base">
                Incremento per ogni puntata:
                <span class="font-bold">{{ incrementoOfferta }}€</span>
            </p>
            <Button @click="aumentaOfferta" :disabled="isOwner" class="w-full" >
                <span class="text-2xl font-semibold font-sans">PUNTA</span>
            </Button>
        </div>

        <div v-else-if="tipoAsta === TipoAsta.INVERSA" class="content">
            <p class="info text-base">
                {{ testoPrezzo }}
                <span :style="{ color: colorePrezzo, fontSize: sizePrezzo }" class="font-bold"
                    >{{ prezzoAttuale }}€</span
                >
                
                    <br>ultima offerta di: 
                    <span v-if="utenteUltimaOfferta" :style="{ color: coloreUltimaOfferta }">
                    {{ utenteUltimaOfferta }}</span>
                    <span v-else> nessuno ha fatto un'offerta </span>
            

            </p>
            <p class="info"><br>Inserisci un'offerta inferiore al prezzo attuale:</p>
            <input
                v-model="nuovaOfferta"
                type="number"
                class="input"
                placeholder="Inserisci la tua offerta"
            />

            <Button @click="inviaOffertaInversa" :disabled="isOwner">Invia Offerta</Button>
        </div>

        <div v-else-if="tipoAsta === TipoAsta.SILENZIOSA" class="content">
            
                <p class="info text-left">
                    La base d'asta è: <span class="highlight">{{ baseAsta }}€</span>
                    <br>ultima offerta di: 
                    <span v-if="utenteUltimaOfferta" :style="{ color: coloreUltimaOfferta }">
                    {{ utenteUltimaOfferta }}</span>
                    <span v-else> nessuno ha fatto un'offerta </span>
                </p>
                <span v-if="!isOwner">

                    <p class="info text-left"><br>Offri quanto vuoi, purché sia superiore alla base d'asta</p>
                    <p v-if="maxOffertaEffettuata>0" class="info text-left">La tua ultima offerta è: {{maxOffertaEffettuata  }}€</p>
                    <input
                    v-model="nuovaOfferta"
                    type="number"
                    class="input"
                    placeholder="Inserisci la tua offerta"
                    />
                    <Button 
                    @click="inviaOffertaSilenziosa" 
                    :disabled="isOwner" 
                    v-tooltip.bottom="{ value: 'non puoi fare un offerta su una tua asta', disabled: !isOwner }">
                    Invia Offerta
                    </Button>   
                </span>
                <span v-else>
                    <p class="info text-left"><br>Visulizza le offerte e seleziona un vincitore</p>    
                    <Button label="Visualizza offerte" icon="pi pi-eye"  @click="showDialog = true" />
          
        <TabellaSelezionaVincitore
            :astaId="idAsta" 
        />
                </span>

        </div>

        <div v-if="errore" class="error">{{ errore }}</div>
    </div>
</template>

<script setup>
import Button from 'primevue/button';
import { ref, computed, watch, onMounted } from 'vue';
import { defineProps } from 'vue';
import { TipoAsta } from '../../service/astaService.js';
import { getOfferteEffettuate } from '../../service/offertaService.js';
import { useAstaChacheStore } from '../../stores/astaStore.js';
import{useProfiloStore} from '../../stores/profiloStore.js';
import TabellaSelezionaVincitore from './TabellaSelezionaVincitore.vue';
const props = defineProps({
    tipoAsta: {
        type: String,
        required: true,
    },
    prezzoAttuale: {
        type: Number,
        required: true,
    },
    baseAsta: {
        type: Number,
        required: false,
    },
    incrementoOfferta: {
        type: Number,
        default: 10,
    },
    faiOfferta: {
        type: Function,
        required: true,
    },

    utenteUltimaOfferta: {
        type: String,
        required: false,
    },
    idAsta:{
        type: Number,
        required: false,
    }
});

const showDialog = ref(false);

const nuovaOfferta = ref(0);
const errore = ref('');
const testoPrezzo = ref('Prezzo attuale: ');

// Variabili reattive per lo stile CSS
const colorePrezzo = ref('black');
const sizePrezzo = ref('medium');
const coloreUltimaOfferta = ref('black');

const titoliAsta = {
    [TipoAsta.INGLESE]: 'Asta Inglese',
    [TipoAsta.INVERSA]: 'Asta Inversa',
    [TipoAsta.SILENZIOSA]: 'Asta Silenziosa',
};
const titoloAsta = computed(() => {
    return titoliAsta[props.tipoAsta] || 'Asta';
});
const descrizioniAsta ={
    [TipoAsta.INGLESE]: 'Nell\'asta inglese, ogni partecipante può fare offerte più alte. L\'offerta più alta al termine dell\'asta vince.',
    [TipoAsta.INVERSA]: 'Nell\'asta inversa, l\'offerta più bassa vince. I partecipanti devono fare offerte inferiori al prezzo corrente.',
    [TipoAsta.SILENZIOSA]: 'Nell\'asta silenziosa, tutte le offerte sono nascoste. Il creatore dell\'asta seleziona il vincitore in base alle offerte ricevute.',
}
const descrizioneAsta = computed(() => {
    return descrizioniAsta[props.tipoAsta] || 'Descrizione asta';
});


const aumentaOfferta = () => {
    nuovaOfferta.value = props.prezzoAttuale + props.incrementoOfferta;
    props.faiOfferta(nuovaOfferta.value).catch((err) => {
        errore.value = "Errore durante l'invio dell'offerta." + err;
    });
};

const inviaOffertaInversa = () => {
    if (nuovaOfferta.value < props.prezzoAttuale) {
        errore.value = '';
        props.faiOfferta(nuovaOfferta.value).catch((err) => {
            errore.value = "Errore durante l'invio dell'offerta.";
        });
    } else {
        errore.value = "L'offerta deve essere inferiore al prezzo corrente.";
    }
};

const inviaOffertaSilenziosa = () => {
    if (nuovaOfferta.value > props.baseAsta) {
        if (nuovaOfferta.value <= maxOffertaEffettuata.value) {
            errore.value = "Non puoi offrire un valore inferiore alla tua offerta.";
            
            return;
        }
        errore.value = '';
        props.faiOfferta(nuovaOfferta.value).catch((err) => {
            errore.value = "Errore durante l'invio dell'offerta.";
        }).then(() => {
            maxOffertaEffettuata.value = nuovaOfferta.value;
        });
    } else {
        errore.value = "L'offerta deve essere superiore alla base d'asta.";
    }
};

const maxOffertaEffettuata= ref(0);
const isSilentAuction = props.tipoAsta === TipoAsta.SILENZIOSA;
const isOwner = ref(false);
onMounted(() => {
    if (props.tipoAsta === TipoAsta.SILENZIOSA) {
    
        if(props.idAsta)
        getOfferteEffettuate(props.idAsta).then((offerteEffettuate) => {
            maxOffertaEffettuata.value = Math.max(...offerteEffettuate.map((offerta) => offerta.prezzoProposto));
        });
    }
    useAstaChacheStore().getAstaById(props.idAsta).then((asta) => {
        if(asta.emailUtenteCreatore == useProfiloStore().profilo.email){
            isOwner.value = true;
        }
    }).catch((err) => {
        console.error("Errore durante il recupero dell'asta: ", err);
    });

});

// Animazione refresh puntata asta 
watch(
    () => props.prezzoAttuale,
    (newVal) => {
        changeStyle();
    }
);

// Funzione di animazione reflesh asta 
const changeStyle = () => {
    colorePrezzo.value = '#d946ef';
    sizePrezzo.value = 'xx-large';
    testoPrezzo.value = 'Nuova offerta:';
    coloreUltimaOfferta.value = '#d946ef';
    setTimeout(() => {
        colorePrezzo.value = 'black';
        sizePrezzo.value = 'large';
        testoPrezzo.value = 'Prezzo attuale:';
        coloreUltimaOfferta.value = 'black';
    }, 2000); // Pausa di 2000 millisecondi (2 secondi)
};
</script>

<style scoped>
.container {
    padding: 1.5rem;
    background-color: #f7fafc;
    min-height: 100vh;
}

.card {
    max-width: 28rem;
    margin: 0 auto;
    background-color: #fff;
    box-shadow:
        0 10px 15px -3px rgba(0, 0, 0, 0.1),
        0 4px 6px -2px rgba(0, 0, 0, 0.05);
    border-radius: 0.5rem;
    overflow: hidden;
}

.title {
    @apply bg-primario-500/95;

    font-size: 1.5rem;
    font-weight: bold;
    text-align: center;
    color: #fff;
    padding: 1rem;
}

.content {
    padding: 1rem;
    text-align: center;
}

.info {
    color: #4a5568;
}

.dynamic-text {
    transition: all 0.3s ease;
    /* Aggiungi transizioni per vedere meglio il cambiamento */
}

.input {
    border: 1px solid #e2e8f0;
    border-radius: 0.375rem;
    padding: 0.5rem;
    width: 100%;
    margin-top: 0.5rem;
    focus: ring;
    focus: ring-blue-500;
}

.button {
    margin-top: 1rem;
    padding: 0.5rem 1rem;
    border-radius: 9999px;
    color: #fff;
    width: 250px;
    /* Larghezza */
}

.green {
    background-color: #48bb78;
}

.green:hover {
    background-color: #38a169;
}

.blue {
    background-color: #4299e1;
}

.blue:hover {
    background-color: #3182ce;
}

.purple {
    background-color: #9f7aea;
}

.purple:hover {
    background-color: #805ad5;
}

.error {
    padding: 1rem;
    background-color: #fed7d7;
    color: #c53030;
    text-align: center;
    margin-top: 1rem;
}
</style>

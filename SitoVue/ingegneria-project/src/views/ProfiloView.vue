<template>
    <div v-if="!storeinstance.profilo.isAutenticato">
        <i class="pi pi-spin pi-spinner" style="font-size: 2rem"></i>
        <p>Caricamento in corso...</p>
        {{ isautenticatoRef }}
    </div>
    <ul class="flex flex-row justify-between border-b-2 border-slate-300 bg-slate-200/20 px-3 py-2">
        <li
            @click="pagina = 1"
            class="hover: flex rounded px-2 py-1 font-bold hover:bg-primario-400 hover:ring-2 hover:ring-slate-200/50"
        >
            <span>
                <i class="pi pi-user" style="font-size: 1.5rem; padding-right: 0.8rem"></i>
            </span>
            Informazioni Profilo
        </li>
        <li
            @click="pagina = 2"
            class="hover: flex cursor-pointer rounded px-2 py-1 font-bold hover:bg-primario-400 hover:ring-2 hover:ring-slate-200/50"
        >
            <span>
                <i class="pi pi-inbox" style="font-size: 1.5rem; padding-right: 0.8rem"></i>
            </span>
            Notifiche
            <Badge v-if="isCisonoNuoveNotifiche"></Badge>
        </li>
        <li
            @click="pagina = 3"
            class="hover: flex cursor-pointer rounded px-2 py-1 font-bold hover:bg-primario-400 hover:ring-2 hover:ring-slate-200/50"
        >
            <span>
                <i class="pi pi-shopping-bag" style="font-size: 1.5rem; padding-right: 0.8rem"></i>
            </span>
            Aste Personali
        </li>
    </ul>
    <div v-if="pagina === 1">
        <InfoProfilo />
    </div>
    <div v-else-if="pagina === 2">
        <<<<<<< Updated upstream
        <Notifiche @emitNotifica="clickNotifica($event)" />
        =======
        <Notifiche
            v-if="numeroNotificheDaLeggere"
            :numeroNotificheNonLette="numeroNotificheDaLeggere"
            @emitNotifica="clickNotifica($event)"
        />
        >>>>>>> Stashed changes
    </div>
    <div v-else-if="pagina === 3">
        <AstePersonali />
    </div>
</template>

<script setup>
import { useToast } from 'primevue/usetoast';
import { ref, watch, onMounted } from 'vue';
import InfoProfilo from '../components/Profilo/InformazioniProfilo.vue';
import Notifiche from '../components/Profilo/MessaggiProfilo.vue';
import AstePersonali from '../components/Profilo/AstePersonaliProfilo.vue';
import Badge from 'primevue/badge';
import { useRouter } from 'vue-router';
import { useProfiloStore } from '../stores/profiloStore.js';
import { isProfiloCompletato, getDatiProfiloPublichi } from '../service/profiloService';
const router = useRouter();
const isCisonoNuoveNotifiche = ref(false);
const storeinstance = useProfiloStore();

const pagina = ref(1);
const toast = useToast();

// Osserva i cambiamenti di storeinstance.profilo.isAutenticato
watch(
    () => storeinstance.profilo.isAutenticato,
    (newVal, oldVal) => {
        if (oldVal === false && newVal === true) {
            isProfiloCompletato().then((profiloCompletato) => {
                console.log('profiloCompletato', profiloCompletato);
                if (!profiloCompletato) {
                    toast.add({
                        severity: 'warn',
                        summary: 'Profilo incompleto',
                        detail: 'Completa il tuo profilo per accedere a tutte le funzionalità',
                        life: 5000,
                    });
                    router.push({ name: 'ediProfilo' });
                } else {
                    getDatiProfiloPublichi(storeinstance.profilo.email).then((response) => {
                        storeinstance.profilo.area_geografica = response.area_geografica;
                        storeinstance.profilo.bio = response.bio;
                        storeinstance.profilo.cognome = response.cognome;
                        storeinstance.profilo.nome = response.nome;
                        storeinstance.profilo.immagine = response.immagine;
                    });
                }
            });
        }
    }
<<<<<<< Updated upstream
});
import { getNumeroDiNotificheNonLette } from '../service/notificheService';
=======
);
import { getNumeroDiNotificheNonLette } from '../service/notifiche';
>>>>>>> Stashed changes

const numeroNotificheDaLeggere = ref();

onMounted(async () => {
    try {
        numeroNotificheDaLeggere.value = await getNumeroDiNotificheNonLette();
        if (numeroNotificheDaLeggere.value > 0) {
            isCisonoNuoveNotifiche.value = true;
        } else {
            isCisonoNuoveNotifiche.value = false;
        }
    } catch (e) {
        console.error('Errore richiesta numero notificheeeee:', e);
    }
});

const clickNotifica = (numeroNotificheNonLette) => {
    numeroNotificheDaLeggere.value = numeroNotificheNonLette;
};
</script>

<template>
    <ul class="flex flex-row justify-between border-b-2 border-slate-300 bg-slate-200/20 px-3 py-2 cursor-pointer">
        <li @click="pagina = 1"
            class="flex rounded px-2 py-1 font-bold hover:bg-primario-400 hover:  hover:ring-2 hover:ring-slate-200/50">
            <span>
                <i class="pi pi-user" style="font-size: 1.5rem; padding-right: 0.8rem"></i>
            </span>
            Informazioni Profilo
        </li>
        <li @click="pagina = 2"
            class="flex rounded px-2 py-1 font-bold hover:bg-primario-400 hover:  hover:ring-2 hover:ring-slate-200/50 cursor-pointer">
            <span>
                <i class="pi pi-inbox" style="font-size: 1.5rem; padding-right: 0.8rem"></i>
            </span>
            Notifiche
            <Badge v-if="checked">{{ numeroNotificheDaLeggere }}</Badge>
        </li>
        <li @click="pagina = 3"
            class="flex rounded px-2 py-1 font-bold hover:bg-primario-400 hover:  hover:ring-2 hover:ring-slate-200/50 cursor-pointer">
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
        <Notifiche v-if="numeroNotificheDaLeggere" :numeroNotificheNonLette="numeroNotificheDaLeggere" @emitNotifica="clickNotifica($event)"/>
    </div>
    <div v-else-if="pagina === 3">
        <AstePersonali />
    </div>
</template>

<script setup>
import { useToast } from 'primevue/usetoast';
import { ref, onMounted } from 'vue';
import InfoProfilo from '../components/Profilo/InformazioniProfilo.vue';
import Notifiche from '../components/Profilo/MessaggiProfilo.vue';
import AstePersonali from '../components/Profilo/AstePersonaliProfilo.vue';
import Badge from 'primevue/badge';
import { getNumeroDiNotificheNonLette } from '../service/notifiche';

const checked = ref(true);
const pagina = ref(1);
const toast = useToast();
const numeroNotificheDaLeggere = ref();

onMounted(async () => {

    try {

        numeroNotificheDaLeggere.value = await getNumeroDiNotificheNonLette();
        if (numeroNotificheDaLeggere.value > 0) {
            checked.value = true;
        } else {
            checked.value = false;
        }

    } catch (e) {
        console.error("Errore richiesta numero notificheeeee:", e);
    }
});

clickNotifica = (numeroNotificheNonLette) => {

    numeroNotificheDaLeggere.value = numeroNotificheNonLette;
};

</script>

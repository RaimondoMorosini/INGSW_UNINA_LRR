<template>
    <ul class="flex flex-row justify-between border-b-2 border-slate-300 px-3 py-2">
        <li
            @click="pagina = 1"
            class="flex rounded px-2 py-1 hover:bg-primario-100 hover:ring-2 hover:ring-slate-300/80"
        >
            <span>
                <i class="pi pi-user" style="font-size: 1.5rem; padding-right: 0.8rem"></i>
            </span>
            Informazioni Profilo
        </li>
        <li
            @click="pagina = 2"
            class="flex rounded px-2 py-1 hover:bg-primario-100 hover:ring-2 hover:ring-slate-300/80"
        >
            <span>
                <i class="pi pi-inbox" style="font-size: 1.5rem; padding-right: 0.8rem"></i>
            </span>
            Notifiche
        </li>
        <li
            @click="pagina = 3"
            class="flex rounded px-2 py-1 hover:bg-primario-100 hover:ring-2 hover:ring-slate-300/80"
        >
            <span>
                <i class="pi pi-shopping-bag" style="font-size: 1.5rem; padding-right: 0.8rem"></i>
            </span>
            Aste Personali
        </li>
        <li
            @click="pagina = 4"
            class="flex rounded px-2 py-1 hover:bg-primario-100 hover:ring-2 hover:ring-slate-300/80"
        >
            <span>
                <i class="pi pi-cog" style="font-size: 1.5rem; padding-right: 0.8rem"></i>
            </span>
            Impostzioni
        </li>
    </ul>
    <div v-if="pagina === 1"><InfoProfilo /></div>
    <div v-else-if="pagina === 2"><Notifiche /></div>
    <div v-else-if="pagina === 3"><AstePersonali /></div>
    <div v-else-if="pagina === 4"><Impostazioni /></div>
</template>

<script setup>
import { useToast } from 'primevue/usetoast';
import { ref } from 'vue';
import InfoProfilo from '../components/Profilo/InformazioniProfilo.vue';
import Notifiche from '../components/Profilo/MessaggiProfilo.vue';
import AstePersonali from '../components/Profilo/AstePersonaliProfilo.vue';
import Impostazioni from '../components/Profilo/ImpostazioniProfilo.vue';

const pagina = ref(1);
const toast = useToast();
</script>

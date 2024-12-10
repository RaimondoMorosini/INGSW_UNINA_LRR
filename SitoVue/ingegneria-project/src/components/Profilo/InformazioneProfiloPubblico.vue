<template>
    <div v-if="infoProfilo" class="flex flex-col gap-3 px-2 py-3">
        <div
            class="user-info-photo flex w-full flex-row items-center justify-between space-x-5 rounded bg-slate-200/20 px-2 py-3 ring-1 ring-[#cbd5e1]">
            <div class="flex flex-row space-x-1">
                <img :src="infoProfilo.foto_profilo" alt="Immagine Profilo impostata"
                    class="h-20 w-20 rounded-full border-primario-400" />
                <div class="User info">
                    <h2 class="mt-4 text-2xl font-semibold">
                        {{ infoProfilo.nome }}
                        {{ infoProfilo.cognome }}
                    </h2>
                    <h2 class="text-sm text-gray-500">{{ email }}</h2>
                </div>
            </div>
        </div>

        <div class="Bio rounded bg-slate-200/20 px-2 ring-1 ring-[#cbd5e1]">
            <h1 class="text-xl">Bio:</h1>
            <p>{{ infoProfilo.bio }}</p>
        </div>

        <div class="Indirizzo rounded bg-slate-200/20 px-2 ring-1 ring-[#cbd5e1]">
            <h1 class="text-xl">Indirizzo:</h1>
            {{ infoProfilo.areaGeografica }}
        </div>

        <ul class="LinkAssociati rounded bg-slate-200/20 px-2 ring-1 ring-[#cbd5e1]">
            <h1 class="text-xl">Collegamenti</h1>
            <li v-for="(link, index) in linksSocial" v-bind:key="index">
                <a :href="link.value" class="flex justify-between" target="_blank" rel="noopener noreferrer">
                    <h2>{{ linkNomi[index] }} </h2><i class="pi pi-external-link"></i>
                </a>

            </li>
        </ul>
    </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { getRest } from '../../scripts/RestUtils'

import { useProfiloStore } from '../../stores/profiloStore';

const props = defineProps(['email']);
const infoProfilo = ref(null);

//const linkUtili = ['GitHub', 'LinkedIn', 'Twitter', 'Facebook'];
const instanceStoreProfilo = useProfiloStore();
const linksSocial = ref([]);
const linkNomi = ref([]);

onMounted(async () => {

        infoProfilo.value = await getRest("public/utente/datiProfilo/" + props.email, {});

        let listaSiti = [];

        if(infoProfilo.siti != null){
            
            listaSiti = convertiDaStringaAlista(infoProfilo.value.siti);
        }

        for (let i = 0; i < listaSiti.length; i++) {
            linksSocial.value.push({ value: listaSiti[i] });
            let domain = new URL(listaSiti[i]).hostname;
            console.log('domain:', domain);
            linkNomi.value.push(domain);
        }
});

const convertiDaStringaAlista = (stringa) => {

    // Rimuovo le parentesi quadre
    let senzaParentesi = stringa.slice(1, -1);

    // Divido la stringa in base alla virgola
    let lista = senzaParentesi.split(',');

    // Pulisco eventuali spazi bianchi (opzionale)
    lista = lista.map(elemento => elemento.trim());

    return lista;
}

</script>

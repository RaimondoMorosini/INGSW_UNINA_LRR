<template>
    <div class="flex flex-col gap-3 px-2 py-3">
        <div
            class="user-info-photo flex w-full flex-row items-center justify-between space-x-5 rounded bg-slate-200/20 px-2 py-3 ring-1 ring-[#cbd5e1]"
        >
            <div class="flex flex-row space-x-1">
                <img
                    :src="instanceStoreProfilo.profilo.imageURL"
                    alt="Immagine Profilo impostata"
                    class="h-20 w-20 rounded-full border-primario-400"
                />
                <div class="User info">
                    <h2 class="mt-4 text-2xl font-semibold">
                        {{ instanceStoreProfilo.profilo.nome }}
                        {{ instanceStoreProfilo.profilo.cognome }}
                    </h2>
                    <h2 class="text-sm text-gray-500">{{ instanceStoreProfilo.profilo.email }}</h2>
                </div>
            </div>
            <RouterLink :to="{ name: 'datiPersonali' }">
                <Button><span class="font-bold">Modifica Profilo</span> </Button>
            </RouterLink>
        </div>

        <div class="Bio rounded bg-slate-200/20 px-2 ring-1 ring-[#cbd5e1]">
            <h1 class="text-xl">Bio:</h1>
            <p>{{ instanceStoreProfilo.profilo.bio }}</p>
        </div>

        <div class="Indirizzo rounded bg-slate-200/20 px-2 ring-1 ring-[#cbd5e1]">
            <h1 class="text-xl">Indirizzo:</h1>
            {{ instanceStoreProfilo.profilo.area_geografica }}
        </div>

        <ul class="LinkAssociati rounded bg-slate-200/20 px-2 ring-1 ring-[#cbd5e1]">
            <h1 class="text-xl">Collegamenti</h1>
            <li v-for="(link, index) in linksSocial" v-bind:key="index" >
                <a :href="link.value" class="flex justify-between" target="_blank" rel="noopener noreferrer"
                    ><h2>{{ linkNomi[index] }} </h2><i class="pi pi-external-link"></i></a
                >
                
            </li>
        </ul>
    </div>
</template>

<script setup>
import { onMounted, ref, computed } from 'vue';
import { useAuth0 } from '@auth0/auth0-vue';
import { useProfiloStore } from '../../stores/profiloStore';
import Button from 'primevue/button';


const { idTokenClaims, isAuthenticated, user } = useAuth0();

//const linkUtili = ['GitHub', 'LinkedIn', 'Twitter', 'Facebook'];

const instanceStoreProfilo = useProfiloStore();
const linksSocial = ref([]);
const linkNomi = ref([]);

onMounted(() => {
    console.log('Profilo caricato', linksSocial);
    console.info('isntanceStoreProfilo', instanceStoreProfilo.profilo.siti_social)
    for (let i = 0; i < instanceStoreProfilo.profilo.siti_social.length; i++) {
        linksSocial.value.push({ value: instanceStoreProfilo.profilo.siti_social[i] });
        let domain = new URL(instanceStoreProfilo.profilo.siti_social[i]).hostname;
        console.log('domain:', domain);
        linkNomi.value.push(domain);
    }
});
</script>

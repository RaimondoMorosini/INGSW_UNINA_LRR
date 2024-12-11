<template>
    <div class="p-4 md:p-8 bg-gray-100 min-h-screen">
        <div class="bg-white shadow rounded-lg p-6">
            <div class="flex flex-col md:flex-row items-center md:items-start">
                <img v-if="instanceStoreProfilo.profilo.imageURL" :src="instanceStoreProfilo.profilo.imageURL"
                    alt="Foto profilo" class="w-32 h-32 md:w-48 md:h-48 rounded-full object-cover shadow-md" />
                <img v-else src="https://via.placeholder.com/150" alt="Foto profilo"
                    class="w-32 h-32 md:w-48 md:h-48 rounded-full object-cover shadow-md" />

                <div class="mt-4 md:mt-0 md:ml-6 text-center md:text-left">
                    <h1 class="text-2xl font-bold text-gray-800">{{ instanceStoreProfilo.profilo.nome }} {{
                        instanceStoreProfilo.profilo.cognome }}</h1>
                    <template v-if="instanceStoreProfilo.profilo.dati_venditore">
                        <span
                            class="text-sm bg-primario-200 text-primario-600 font-medium px-2 py-1 rounded mt-2">Account
                            Venditore</span>
                    </template>
                    <p class="text-sm text-gray-600 mt-2">{{ emailInput }}</p>
                    <p class="text-sm text-gray-600 mt-2">{{ instanceStoreProfilo.profilo.area_geografica }}</p>
                    <p class="text-sm text-gray-600 mt-1">{{ instanceStoreProfilo.profilo.bio }}</p>

                    <div class="my-4">
                        <RouterLink :to="{ name: 'datiPersonali' }">
                            <Button><span class="font-bold">Modifica Profilo</span> </Button>
                        </RouterLink>
                    </div>

                </div>
            </div>

            <div class="mt-6 grid grid-cols-1 md:grid-cols-2 gap-4">
                <template v-if="instanceStoreProfilo.profilo.isVenditore">
                    <div>
                        <h2 class="text-lg font-semibold text-gray-800">Informazioni di Contatto</h2>
                        <ul class="mt-2 text-gray-600">
                            <li><strong>Telefono:</strong> {{ instanceStoreProfilo.profilo.dati_venditore.numeroTelefono
                                }}</li>
                            <li><strong>Email:</strong> {{ instanceStoreProfilo.profilo.dati_venditore.nomeUtente }}
                            </li>
                        </ul>
                    </div>
                    <div>
                        <h2 class="text-lg font-semibold text-gray-800">Dati Aziendali</h2>
                        <ul class="mt-2 text-gray-600">
                            <li><strong>Partita IVA:</strong> {{ instanceStoreProfilo.profilo.dati_venditore.partitaIva
                                }}</li>
                            <li><strong>Codice Fiscale:</strong> {{
                                instanceStoreProfilo.profilo.dati_venditore.codiceFiscale }}</li>
                            <li><strong>Nome Azienda:</strong> {{ instanceStoreProfilo.profilo.dati_venditore.nomeAzienda
                                }}</li>
                        </ul>
                    </div>
                </template>
            </div>

            <div class="mt-6">
                <h2 class="text-lg font-semibold text-gray-800">Link Social</h2>
                <div v-if="instanceStoreProfilo.profilo.siti_social" class="mt-2">
                    <ul class="flex flex-wrap gap-4">
                        <li v-for="sito in sitiWeb" :key="sito.url"
                            class="flex items-center space-x-2 bg-primario-50/50 p-2 rounded shadow hover:shadow-lg transition">
                            <i :class="sito.icona" class="text-blue-600 text-xl"></i>
                            <a :href="sito.url" target="_blank" rel="noopener noreferrer"
                                class="text-blue-600 hover:underline">
                                {{ sito.nome }}
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
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

const sitiWeb = computed(() => {
    if (!instanceStoreProfilo.profilo.siti) return [];
    try {
        return instanceStoreProfilo.profilo.siti
            .replace(/\[|\]/g, "") // Rimuove parentesi quadre
            .split(",") // Divide i siti in un array
            .map((sito) => {
                const url = sito.trim();
                const dominio = new URL(url).hostname.replace("www.", "");
                return {
                    url,
                    nome: dominio + new URL(url).pathname,
                    icona: iconeSiti[dominio] || "fas fa-globe", // Icona predefinita
                };
            });
    } catch (error) {
        console.info("Errore nel parsing dei siti", error);
        return [];
    }
});

</script>

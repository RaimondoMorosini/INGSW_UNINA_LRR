<template>
    <div
        class="flex min-w-full flex-col justify-around gap-3 px-5 py-3 duration-500 ease-in lg:flex lg:w-auto lg:flex-row lg:px-20"
    >
        <!-- INPUT TESTO PER CERCARE UNA SOTTOSTRINGA DI UN TITOLO DI UN ASTA -->
        <InputGroup class="h-14 w-[100%] lg:w-[33%]">
            <Button><i class="pi pi-search text-[#1C1B22]"></i></Button>
            <InputText
                v-model="nomeProdottoCercato"
                placeholder="Search"
                class="w-[100%] rounded-r"
            >
            </InputText>
        </InputGroup>

        <!-- CASSELLA CATEGORIE -->
        <InputGroup class="h-14 w-[100%] lg:w-[33%]">
            <Button><i class="pi pi-th-large text-[#1C1B22]"></i></Button>
            <TreeSelect
                @change="setCategoriaSelezionata"
                id="idCategoria"
                v-model="selectedCategory"
                :options="gerarchiaCategorie"
                option-label="name"
                placeholder="Seleziona Categoria"
                class="w-[100%] rounded-r"
            />
        </InputGroup>

        <!-- CASSELLA TIPI DI ASTA -->
        <InputGroup class="h-14 w-[100%] lg:w-[33%]">
            <Button>
                <i class="pi pi-hammer text-[#1C1B22]"></i>
            </Button>

            <MultiSelect
                append-to="body"
                @change="setTipoAsteSelezionate"
                v-model="selectedAuction"
                :options="auctions"
                optionLabel="name"
                placeholder="Seleziona aste"
                :maxSelectedLabels="3"
                class="z-30 w-[100%] rounded-r"
            />
        </InputGroup>

        <!-- BOTTONE CERCA-->
        <Button class="lg:w-[10%]] w-[100%]" @click="OnCLickCerca(false)"
            ><span class="font-bold">Cerca Aste</span></Button
        >
    </div>

</template>

<script setup>
import Button from 'primevue/button';
import InputText from 'primevue/inputtext';
import TreeSelect from 'primevue/treeselect';
import InputGroup from 'primevue/inputgroup';
import InputGroupAddon from 'primevue/inputgroupaddon';
import MultiSelect from 'primevue/multiselect';
import 'primeflex/primeflex.css';
import 'primeicons/primeicons.css';

import { onMounted, ref } from 'vue';

import { getCategorieRest } from '../../scripts/categorie.js';

import Vetrina from './Vetrina.vue';

import { useRouter } from 'vue-router';
const router = useRouter(); // Usa il router

//Lista dei tipi di aste
const selectedAuction = ref();
const auctions = ref([
    { name: 'Asta inglese', code: 'AI' },
    { name: 'Asta silenziosa', code: 'AS' },
    { name: 'Asta inversa', code: 'AIV' },
]);

const selectedCategory = ref(); //Variabile che contiene la categoria selezionata dall'utente
const gerarchiaCategorie = ref([]); //Variabile che contiene la lista gerarchica di tutte le categorie esistenti
let categoriaCercata = ref('tutte'); //Variabile passata al body della richiesta axsios che contiene o "tutte" oppure la categoria selezionata
let tipoAstaCercata = ref([]); //Contiene la lista dei tipi di asta cercata
let nomeProdottoCercato = ref(''); //contiene la sottostringa del titolo del prodotto cercato
const nomeOrdinamento = ref(null);
const direzioneOrdinamento = ref(null);
const prezzoMin = ref(null);
const prezzoMax = ref(null);

onMounted(async () => {
    try {
        gerarchiaCategorie.value = await getCategorieRest();
    } catch (error) {
        console.error('Error categorie non trovate:', error);
    }
});

const setCategoriaSelezionata = () => {
    try {
        //se la categoria è selezionata allora imposta come filtro di ricerca su categoria: la categoria selezionata
        categoriaCercata.value = Object.entries(selectedCategory.value)[0][0];
    } catch (error) {
        //se la categoria non è selezionata allora imposta come filtro di riceca su categoria: "tutte"
        categoriaCercata.value = 'tutte';
    }
};

const setTipoAsteSelezionate = () => {
    try {
        tipoAstaCercata = ref([]);

        selectedAuction.value.forEach((asta) => {
            switch (asta.name) {
                case 'Asta inglese':
                    tipoAstaCercata.value.push('asta_inglese');
                    break;

                case 'Asta silenziosa':
                    tipoAstaCercata.value.push('asta_silenziosa');
                    break;

                case 'Asta inversa':
                    tipoAstaCercata.value.push('asta_inversa');
                    break;
            }
        });
    } catch (error) {
        tipoAstaCercata = ref([]);
    }
};

const OnCLickCerca = () => {
    // Cambia l'URL e sposta l'utente
    router.push({
        path: '/aste/',
        query: {
            pagina: 1,
            elementiPerPagina: 5,
            categoria: categoriaCercata.value,
            nomeProdotto: nomeProdottoCercato.value,
            tipoAsta: JSON.stringify(tipoAstaCercata.value),
            prezzoMin: prezzoMin.value,
            prezzoMax: prezzoMax.value,
            campoOrdinamento: nomeOrdinamento.value,
            direzioneOrdinamento: direzioneOrdinamento.value,
            lista: JSON.stringify([]),
        },
    });
};
</script>

<style>
.p-checkbox {
    width: 20px;
    height: 20px;
    background-color: #f0f0f0;
    border: 1px solid #ccc;
}
</style>

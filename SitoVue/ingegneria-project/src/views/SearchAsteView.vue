<template>
    <div
        class="flex min-w-full flex-col justify-around gap-3 px-5 py-3 duration-500 ease-in lg:flex lg:w-auto lg:flex-row lg:px-20"
    >
        <!-- INPUT TESTO PER CERCARE UNA SOTTOSTRINGA DI UN TITOLO DI UN ASTA -->
        <InputGroup class="h-14 w-[100%] lg:w-[33%]">
            <InputGroupAddon class="bg-primario-400">
                <i class="pi pi-search" style="color: #424242"></i>
            </InputGroupAddon>
            <InputText
                v-model="nomeProdottoCercato"
                placeholder="Search"
                class="w-[100%] rounded-r bg-primario-400/50 text-greyButton-400"
            >
                <template #imputtexticon>
                    <i class="pi pi-search" style="color: #424242"></i>
                </template>
            </InputText>
        </InputGroup>

        <!-- CASSELLA CATEGORIE -->
        <InputGroup class="h-14 w-[100%] lg:w-[33%]">
            <InputGroupAddon class="bg-primario-400">
                <i class="pi pi-th-large" style="color: #424242"></i>
            </InputGroupAddon>
            <TreeSelect
                @change="setCategoriaSelezionata"
                id="idCategoria"
                v-model="selectedCategory"
                :options="gerarchiaCategorie"
                option-label="name"
                placeholder="Seleziona Categoria"
                class="w-[100%] rounded-r bg-primario-400/50 text-greyButton-400"
            />
        </InputGroup>

        <!-- CASSELLA TIPI DI ASTA -->
        <InputGroup class="h-14 w-[100%] lg:w-[33%]">
            <InputGroupAddon class="bg-primario-400">
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 20 20">
                    <g id="asta">
                        <path
                            d="M19.0096 9.61639L17.7596 8.36639C17.5476 8.15463 17.2665 8.02607 16.9677 8.00413C16.6689 7.98219 16.3721 8.06833 16.1314 8.24686L12.2533 4.36873C12.4319 4.12817 12.5182 3.83144 12.4964 3.53261C12.4746 3.23377 12.3462 2.95271 12.1346 2.74061L10.8846 1.49061C10.7685 1.3745 10.6307 1.2824 10.479 1.21957C10.3273 1.15673 10.1647 1.12439 10.0006 1.12439C9.8364 1.12439 9.67383 1.15673 9.52215 1.21957C9.37048 1.2824 9.23266 1.3745 9.11659 1.49061L4.11659 6.49061C4.00048 6.60668 3.90838 6.7445 3.84555 6.89617C3.78271 7.04785 3.75037 7.21042 3.75037 7.37459C3.75037 7.53877 3.78271 7.70134 3.84555 7.85301C3.90838 8.00469 4.00048 8.1425 4.11659 8.25858L5.36659 9.50858C5.57864 9.72001 5.85955 9.8483 6.15821 9.87009C6.45687 9.89189 6.75343 9.80574 6.99393 9.62733L7.55409 10.1875L2.44627 15.2953C2.10693 15.6664 1.92382 16.1542 1.93506 16.6569C1.9463 17.1597 2.15103 17.6387 2.50663 17.9943C2.86222 18.3499 3.34127 18.5546 3.84403 18.5659C4.34679 18.5771 4.83452 18.394 5.20565 18.0547L10.3127 12.9469L10.8728 13.5062C10.6942 13.7468 10.6079 14.0435 10.6297 14.3424C10.6515 14.6412 10.7799 14.9223 10.9916 15.1344L12.2416 16.3844C12.3577 16.5005 12.4955 16.5926 12.6472 16.6554C12.7988 16.7182 12.9614 16.7506 13.1256 16.7506C13.2897 16.7506 13.4523 16.7182 13.604 16.6554C13.7557 16.5926 13.8935 16.5005 14.0096 16.3844L19.0096 11.3844C19.1257 11.2683 19.2178 11.1305 19.2806 10.9788C19.3434 10.8271 19.3758 10.6646 19.3758 10.5004C19.3758 10.3362 19.3434 10.1736 19.2806 10.022C19.2178 9.87028 19.1257 9.73247 19.0096 9.61639ZM6.25018 8.62498L5.00018 7.37498L10.0002 2.37498L11.2502 3.62498L6.25018 8.62498ZM4.32206 17.1703C4.19032 17.3013 4.01211 17.3748 3.82635 17.3748C3.6406 17.3748 3.46239 17.3013 3.33065 17.1703C3.19993 17.0386 3.12657 16.8605 3.12657 16.675C3.12657 16.4894 3.19993 16.3114 3.33065 16.1797L8.43768 11.0719L9.42909 12.0625L4.32206 17.1703ZM7.89081 8.75467L11.3799 5.26561L15.2346 9.1203L11.7463 12.6094L7.89081 8.75467ZM13.1252 15.5L11.8752 14.25L12.1877 13.9375L16.5627 9.56248L16.8752 9.24998L18.1252 10.5L13.1252 15.5Z"
                            fill="#424242"
                        />
                    </g>
                </svg>
            </InputGroupAddon>

            <MultiSelect
                @change="setTipoAsteSelezionate"
                v-model="selectedAuction"
                :options="auctions"
                optionLabel="name"
                placeholder="Seleziona aste"
                :maxSelectedLabels="3"
                class="w-[100%] rounded-r bg-primario-400/50 text-greyButton-400"
            />
        </InputGroup>

        <!-- BOTTONE CERCA-->
        <Button class="h-14 w-[100%]  lg:w-[10%]" @click="onClickCerca()">
            <span class="font-bold text-greyButton-400">Cerca Aste</span>
        </Button>
    </div>

    <AstePerRicerca
        :propAste="aste"
        :propLoading="isLoading"
        :propNumeroAste="numeroAsteTotali"
        :propCategoriaCercata="categoriaCercata"
        :propRicaricaComponenteCaratteristiche="ricaraComponenteCaratteristiche"
        @numeroDiPaginaSelezionato="setPagina($event)"
        @caratteristicheSelezionate="setCaratteristiche($event)"
        @ordineSelezionato="setCampiOrdinamento($event)"
        @prezzoMinSelezionato="setPrezzoMin($event)"
        @prezzoMaxSelezionato="setPrezzoMax($event)"
    />
        :propCaratteristicheselezionate="caratteristicheSelezionate"
        @numeroDiPaginaSelezionato="setPagina($event)" @caratteristicheSelezionate="setCaratteristiche($event)"
        @ordineSelezionato="setCampiOrdinamento($event)" @prezzoMinSelezionato="setPrezzoMin($event)"
        @prezzoMaxSelezionato="setPrezzoMax($event)" />

</template>

<script setup>
import Button from 'primevue/button'
import InputText from 'primevue/inputtext';
import TreeSelect from 'primevue/treeselect';
import InputGroup from 'primevue/inputgroup';
import InputGroupAddon from 'primevue/inputgroupaddon';
import MultiSelect from 'primevue/multiselect';
import 'primeflex/primeflex.css';
import 'primeicons/primeicons.css';

import { onMounted, ref, watch } from 'vue';

import { getCategorieRest } from '../scripts/categorie.js';
import { getInfoOrdinamento } from '../scripts/restOrdinamento.js';

import AstePerRicerca from '../components/vetrinaAndHomepage/AstePerRicerca.vue';

import axios from 'axios';

import { useRouter, useRoute } from 'vue-router';
const router = useRouter(); // Usa il router
const route = useRoute(); // Usa la route per accedere ai parametri

//Lista dei tipi di aste
const selectedAuction = ref();
const auctions = ref([
    { name: 'Asta inglese', code: 'AI' },
    { name: 'Asta silenziosa', code: 'AS' },
    { name: 'Asta inversa', code: 'AIV' },
]);

const selectedCategory = ref(route.query.categoria); //Variabile che contiene la categoria selezionata dall'utente
const gerarchiaCategorie = ref([]); //Variabile che contiene la lista gerarchica di tutte le categorie esistenti
const categoriaCercata = ref(route.query.categoria); //Variabile passata al body della richiesta axsios che contiene o "tutte" oppure la categoria selezionata
const aste = ref(); //Variabile che contiene tutte le aste corrispodenti alla ricerca (la risposta della richiesta axsios)
const isLoading = ref(true); //Variabile che true quando la richiesta axsios è in corso facendo caricare il tamplate di caricamento
const numeroAsteTotali = ref(); //Contiene il numero di aste totali corrispodente alla ricerca
const tipoAstaCercata = ref([]); //Contiene la lista dei tipi di asta cercata
const nomeProdottoCercato = ref(route.query.nomeProdotto); //contiene la sottostringa del titolo del prodotto cercato
const paginaSelezionata = ref(route.query.pagina); //Contiene la pagina selezionata
const listaDiCaratteristicheSelezionate = ref([]); //Contiene la lista delle caratteristiche del prodotto che l'utente ha filtrato
const ricaraComponenteCaratteristiche = ref(true); //Quando è false la componente delle caratterische non verrà ricaricata facendo si che non vengano perse le opzioni già selezionate dall'untente
const ultimaCategoriaCercata = ref(route.query.categoria); //Ogni volta che parte la richiesta axsios viene salvata la categoria cercata per poi essere confrontata con la prossima richiesta axsios, se il confronto non risulta uguale allora la categoria è cambiata quindi verrà impostato la la variabile ricaricaComponenteCaratteristica a true
const nomeOrdinamento = ref(route.query.campoOrdinamento);
const direzioneOrdinamento = ref(route.query.direzioneOrdinamento);
const prezzoMin = ref(route.query.prezzoMin);
const prezzoMax = ref(route.query.prezzoMax);
const caratteristicheSelezionate = ref([]);

onMounted(async () => {
    try {
        gerarchiaCategorie.value = await getCategorieRest();
    } catch (error) {
        console.error('Error categorie non trovate:', error);
    }

    richiestaRicercaFiltrata();

    caratteristicheSelezionate.value = JSON.parse(LZString.decompressFromEncodedURIComponent(route.query.caratteristiche));

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
        tipoAstaCercata.value = [];

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
        tipoAstaCercata.value = [];
    }
};

const setPagina = (pagina) => {
    paginaSelezionata.value = pagina;

    onClickCerca();
};

const setCaratteristiche = (caratteristicheSelezionate) => {
    listaDiCaratteristicheSelezionate.value = caratteristicheSelezionate;
};

const setCampiOrdinamento = (ordinamentoSelezionato) => {
    if (ordinamentoSelezionato != null) {
        const infoOrdinamento = getInfoOrdinamento(ordinamentoSelezionato.name);

        if (infoOrdinamento != null) {
            nomeOrdinamento.value = infoOrdinamento[0];
            direzioneOrdinamento.value = infoOrdinamento[1];
        } else {
            nomeOrdinamento.value = null;
            direzioneOrdinamento.value = null;
        }
    }
};

const setPrezzoMin = (prezzoMinSelezionato) => {
    prezzoMin.value = prezzoMinSelezionato;
};

const setPrezzoMax = (prezzoMaxSelezionato) => {
    prezzoMax.value = prezzoMaxSelezionato;
};

const onClickCerca = () => {
    if (ultimaCategoriaCercata.value !== categoriaCercata.value) {
        listaDiCaratteristicheSelezionate.value = [];
    }

    // Cambia l'URL e sposta l'utente
    router.push({
        path: '/aste',
        query: {
            pagina: paginaSelezionata.value,
            elementiPerPagina: 5,
            categoria: categoriaCercata.value,
            nomeProdotto: nomeProdottoCercato.value,
            tipoAsta: JSON.stringify(tipoAstaCercata.value),
            prezzoMin: prezzoMin.value,
            prezzoMax: prezzoMax.value,
            campoOrdinamento: nomeOrdinamento.value,
            direzioneOrdinamento: direzioneOrdinamento.value,
            lista: JSON.stringify(listaDiCaratteristicheSelezionate.value),
        },
    });
};

watch(
    () => route.query, // Osserva tutti i cambiamenti nella query
    (newQuery, oldQuery) => {
        if (newQuery !== oldQuery) {
            // Chiama la funzione che desideri eseguire quando cambia la query
            richiestaRicercaFiltrata();
        }
    },

    { deep: true } // Questa opzione serve per osservare anche i cambiamenti nei campi interni degli oggetti
);

const richiestaRicercaFiltrata = async () => {
    isLoading.value = true; // Imposta lo stato di caricamento su true prima di effettuare la richiesta

    //Confronta la categoria cercata con l'ultima categoria cercata prima di questa richiesta, se sono diversi allora la componente caratteristiche specifiche va ricarica e inizializzato ad una lista vuote le caratteristiche specifiche cercate dalla precedente richiesta
    if (ultimaCategoriaCercata.value !== categoriaCercata.value) {
        ricaraComponenteCaratteristiche.value = false;
        listaDiCaratteristicheSelezionate.value = [];
    }

    //impostazione dei parametri del body da amndare nella richiesta axios
    const parametriBody = {
        pagina: route.query.pagina,
        elementiPerPagina: 5,
        categoria: route.query.categoria,
        nomeProdotto: route.query.nomeProdotto,
        tipoAsta: JSON.parse(route.query.tipoAsta), 
        //caratteristicheSelezionate: JSON.parse(route.query.caratteristiche),
        caratteristicheSelezionate: JSON.parse(LZString.decompressFromEncodedURIComponent(route.query.caratteristiche)),
        prezzoMin: route.query.prezzoMin,
        prezzoMax: route.query.prezzoMax,
        campoOrdinamento: route.query.campoOrdinamento,
        direzioneOrdinamento: route.query.direzioneOrdinamento,
    };

    //tentativo di richesta axsios
    try {
        let response;

        //Richiesta per ottenere il numero totale di aste
        response = await axios.post(
            'http://localhost:8081/public/asta/getNumeroAste',
            parametriBody
        ); //Otteniamo il numero totale delle aste in base al criterio di ricerca (questo numero serve per calcolare il numero totali di pagine)
        numeroAsteTotali.value = response.data; //Assegniamo la risposta alla variabile condivisa tramite prop con la componente che carica le aste

        //Richiesta per ottenere le aste
        response = await axios.post('http://localhost:8081/public/asta/getAllAste', parametriBody); //Otteniamo tutte le aste che corrispondono al criterio di ricerca
        aste.value = response.data; // Assegniamo la risposta alla variabile condivisa tramite prop con la componente che carica le aste
    } catch (error) {
        console.error('Si è verificato un errore:', error);
    } finally {
        isLoading.value = false; // Imposta lo stato di caricamento su false dopo che la richiesta è completata (in modo che carica la componente delle aste cercate)

        paginaSelezionata.value = 1; // resetta la pagina selezionata a 1 in modo che ogni ricerca partita dal tasto cerca parta dalla pagina 1

        ultimaCategoriaCercata.value = categoriaCercata.value; // aggiorna sempre l'ultima categoria cercata in modo da confrontarla con la prossima richiesta e capire se ricaricare la componente caratteristiche

        //Se false allora la categoria è stata cambiata dall'ultima ricerca quindi settiamo a true la variabile ricaricaComponenteCaratteristiche
        if (ricaraComponenteCaratteristiche.value === false) {
            ricaraComponenteCaratteristiche.value = true;
        }
    }
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

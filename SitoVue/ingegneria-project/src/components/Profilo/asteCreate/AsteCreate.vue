<template>
    <div>
        <!-- Filtri -->
        <Filtro />
        <!-- Lista Aste -->
        <ul class="lista-aste">
            <li v-for="asta in asteVisibili" :key="asta.idAsta">
                <ElementoAsta :asta="asta"></ElementoAsta>
            </li>
        </ul>

        <!-- Paginazione -->
        <div class="paginazione">
            <Button
                v-for="pagina in numeroPagine"
                :key="pagina"
                :class="{ attiva: paginaCorrente === pagina }"
                @click="cambiaPagina(pagina)"
            >
                {{ pagina }}
            </Button>
        </div>
    </div>
</template>

<script setup>
import { usaAstaStore } from '../../../stores/astePersonaliStore';
import { getAsteCreate } from '../../../service/astaService';
import { ref, computed } from 'vue';
import Filtro from './Filtro.vue';
import ElementoAsta from './ElementoAsta.vue';
import Button from 'primevue/button';


const astaStore = usaAstaStore();
getAsteCreate().then((aste) => astaStore.impostaAste(aste));

const asteVisibili = computed(() => astaStore.asteVisibili);
const numeroPagine = computed(() => astaStore.numeroPagine);
const paginaCorrente = computed(() => astaStore.paginaCorrente);

// Filtri e ordini
const filtroRicerca = ref(astaStore.filtroRicerca);
const filtroTipoAsta = ref(astaStore.filtroTipoAsta);
const filtroScadenza = ref(astaStore.filtroScadenza); // Aggiunto il filtro per scadenza
const ordine = ref(astaStore.ordine);

const cambiaPagina = (pagina) => astaStore.cambiaPagina(pagina);
</script>

<style scoped>
.lista-aste {
    list-style: none;
    padding: 0;
}

.lista-aste li {
    border: 1px solid #ccc;
    margin: 10px 0;
    padding: 10px;
}

.paginazione button {
    margin: 0 5px;
    padding: 5px 10px;
    border: none;
    cursor: pointer;
    background: #ddd;
}

.paginazione button.attiva {
    background: #e879f9;
    color: white;
}
</style>

<template>
    <div class="contenitore-caratteristiche">
        <div class="nome-caratteristica">
            <h1>{{ props.propNomeCaratteristica }}</h1>
        </div>

        <MultiSelect
            @change="OnChangeOpzioniSelezionate"
            v-model="selectedOpzioni"
            :options="opzioni"
            filter
            optionLabel="name"
            placeholder="Opzioni"
            :maxSelectedLabels="3"
            class="md:w-20rem w-full"
        />
        <!--
    <div class="gruppo-opzioni" v-if="statoFinestra">
        <div class="flex-column flex gap-3 w-auto h-60 overflow-y-auto overflow-x-hidden scroll-personalizzato">
            <div v-for="opzione of props.propOpzioni" :key="opzione" class="align-items-center flex">
                <Checkbox @change="OnChangeOpzioniSelezionate" v-model="selectedOpzioni":inputId="opzione" name="opzione":value="opzione"/>
                <label :for="opzione">{{ opzione }}</label>
            </div>
        </div>
    </div>
-->
    </div>
</template>

<script setup>
import { ref, defineEmits, onMounted, watch } from 'vue';
import MultiSelect from 'primevue/multiselect';
import { getArrayOpzionePerMultiSelect } from '../../../service/carateristicheService';

import 'primeflex/primeflex.css';
//import 'primevue/resources/themes/aura-light-green/theme.css';
import 'primeicons/primeicons.css';

const props = defineProps([
    'propOpzioni',
    'propNomeCaratteristica',
    'propCaratteristicheselezionate',
]);
const emit = defineEmits(['recuperoValoriSelezionati']);

const opzioni = getArrayOpzionePerMultiSelect(props.propOpzioni);
const selectedOpzioni = ref([]);

const OnChangeOpzioniSelezionate = () => {
    emit('recuperoValoriSelezionati', selectedOpzioni.value);
};

onMounted(() => {
    selezionaOpzioni();
});

watch(
    () => props.propCaratteristicheselezionate,
    (newValue) => {
        selezionaOpzioni();
    }
);

// Funzione per controllare se i valori selezionati fanno match con le opzioni
const selezionaOpzioni = () => {
    console.log('props Caratteristicheeeeeeeeeeeeeeeee: ', props.propCaratteristicheselezionate);
    selectedOpzioni.value = [];

    props.propCaratteristicheselezionate.forEach((caratteristica) => {
        caratteristica.valoriSelezionati.forEach((valore) => {
            opzioni.forEach((opzione) => {
                if (valore === opzione.name) {
                    selectedOpzioni.value.push(opzione);
                }
            });
        });
    });
};
</script>

<style scoped>
.contenitore-caratteristiche {
    width: 100%;
    display: flex;
    flex-direction: column;
}

.nome-caratteristica {
    width: 100%;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    font-weight: bold;
}

.gruppo-opzioni {
    background-color: rgb(251, 249, 249);
    margin-top: 5px;
}

.p-checkbox {
    width: 20px !important;
    height: 20px !important;
    background-color: #f0f0f0 !important;
    border: 1px solid #ccc !important;
    margin-right: 6px;
}
</style>

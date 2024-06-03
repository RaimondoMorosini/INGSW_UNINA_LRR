<template>

    <div class="contenitore-caratteristiche">
        <div class="nome-caratteristica">
            <h1>{{ props.propNomeCaratteristica }}</h1>
        </div>

        <MultiSelect @change="OnChangeOpzioniSelezionate" v-model="selectedOpzioni" :options="opzioni" filter optionLabel="name"
            placeholder="Opzioni" :maxSelectedLabels="3" class="w-full md:w-20rem" />
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
import { ref, defineEmits } from 'vue';
import MultiSelect from 'primevue/multiselect';
import { getArrayOpzionePerMultiSelect } from '../../service/carateristicheService'

import 'primeflex/primeflex.css';
import 'primevue/resources/themes/aura-light-green/theme.css';
import 'primeicons/primeicons.css';

const props = defineProps(['propOpzioni', 'propNomeCaratteristica']);
const emit = defineEmits(['recuperoValoriSelezionati']);

const opzioni = getArrayOpzionePerMultiSelect(props.propOpzioni);
const selectedOpzioni = ref([]);

const OnChangeOpzioniSelezionate = () => {
    emit('recuperoValoriSelezionati', selectedOpzioni.value);
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

<template class="contenitore-caratteristiche">
    <div class="nome-caratteristica">
        <h1>{{ props.propNomeCaratteristica }}</h1>
        <img
            v-if="!statoFinestra"
            @click="triggerFinestra"
            src="../../assets/Icon/frecciaGiu.svg"
            class="cursor-pointer"
        />
        <img
            v-else
            @click="triggerFinestra"
            src="../../assets/Icon/freccia-su.svg"
            class="cursor-pointer"
        />
    </div>

    <div class="gruppo-opzioni" v-if="statoFinestra">
        <div class="flex flex-column gap-3">
            <div v-for="opzione of props.propOpzioni" :key="opzione" class="flex align-items-center">
                <Checkbox @change="OnChangeOpzioniSelezionate"  v-model="selectedOpzioni" :inputId="opzione" name="opzione"
                    :value="opzione" />
                <label :for="opzione">{{ opzione }}</label>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref,defineEmits } from "vue";

import Checkbox from 'primevue/checkbox';
import 'primeflex/primeflex.css';
import 'primevue/resources/themes/aura-light-green/theme.css';
import 'primeicons/primeicons.css';

const props = defineProps(['propOpzioni', 'propNomeCaratteristica']);
const emit = defineEmits(['recuperoValoriSelezionati']);

let statoFinestra = ref(true);

const triggerFinestra = () => {
    statoFinestra.value = !statoFinestra.value;
};

const selectedOpzioni = ref([]);

const OnChangeOpzioniSelezionate = () => {

    emit('recuperoValoriSelezionati', selectedOpzioni.value);
}

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

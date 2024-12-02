<template>
    <Stepper value="1">
        <div class="card flex justify-center">
            <StepList class="basis-[90%]">
                <Step value="1">
                    <span class="hidden lg:block">Descrizione Prodotto</span>
                    <span class="block lg:hidden">
                        <i class="pi pi-cart-arrow-down"></i>
                    </span>
                </Step>
                <Step value="2">
                    <span class="hidden lg:block">Seleziona Filtri</span>
                    <span class="block lg:hidden">
                        <i class="pi pi-filter"></i>
                    </span>
                </Step>
                <Step value="3">
                    <span class="hidden lg:block">Dettagli Asta</span>
                    <span class="block lg:hidden">
                        <i class="pi pi-barcode"></i>
                    </span>
                </Step>
                <Step value="4">
                    <span class="hidden lg:block">Revisione Dati Inseriti</span>
                    <span class="block lg:hidden">
                        <i class="pi pi-check-circle"></i>
                    </span>
                </Step>
            </StepList>
        </div>
        <StepPanels>
            <StepPanel v-slot="{ activateCallback }" value="1">
                <CreaProdotto @increase-page="activateCallback('2')" />
            </StepPanel>
            <StepPanel v-slot="{ activateCallback }" value="2">
                <SelezionaFiltri
                    @decrease-page="activateCallback('1')"
                    @increase-page="activateCallback('3')"
                />
            </StepPanel>
            <StepPanel v-slot="{ activateCallback }" value="3">
                <SelezioneTipoAsta
                    @decrease-page="activateCallback('2')"
                    @increase-page="activateCallback('4')"
                />
            </StepPanel>
            <StepPanel v-slot="{ activateCallback }" value="4">
                <Review @decrease-page="activateCallback('3')" />
            </StepPanel>
        </StepPanels>
    </Stepper>
</template>

<script setup>
import Stepper from 'primevue/stepper';
import StepPanels from 'primevue/steppanels';
import StepPanel from 'primevue/steppanel';
import StepList from 'primevue/steplist';
import Step from 'primevue/step';

import { ref } from 'vue';
import SelezioneTipoAsta from '../components/CreaAstaStepper/dettagliCreaAsta.vue';
import CreaProdotto from '../components/CreaAstaStepper/formCreaProdotto.vue';
import SelezionaFiltri from '../components/CreaAstaStepper/impostaFiltriCreaProdotto.vue';
import Review from '../components/CreaAstaStepper/reviewAsta.vue';

const loading = ref(false);
</script>

<style scoped>
.bottone {
    background-color: #cc85f5;
    padding: 10px 20px;
    color: white;
    border-radius: 5px;
    font-size: 1.1rem;
    font-weight: bold;
}
.bottone:hover {
    background-color: #7c3aed;
}

.label {
    text-align: center;
    display: block;
    margin: 25px 0 15px;
    text-transform: uppercase;
    letter-spacing: 1px;
    font-weight: bold;
}
</style>

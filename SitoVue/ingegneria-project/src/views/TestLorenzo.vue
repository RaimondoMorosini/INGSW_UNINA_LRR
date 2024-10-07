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
                <CreaProdotto @increase-page="activateCallback('2')"/>
                
            </StepPanel>
            <StepPanel v-slot="{ activateCallback }" value="2">
                <SelezionaFiltri @decrease-page="activateCallback('1')" @increase-page="activateCallback('3')"/>
                
            </StepPanel>
            <StepPanel v-slot="{ activateCallback }" value="3">
                <SelezioneTipoAsta @decrease-page="activateCallback('2')" @increase-page="activateCallback('4')" />
            </StepPanel>
            <StepPanel v-slot="{ activateCallback }" value="4">
                <Review @decrease-page="activateCallback('3')" />
                
            </StepPanel>
        </StepPanels>
    </Stepper>
</template>

<script setup>
import { ref,defineProps,watch } from 'vue';
import SelezioneTipoAsta from '../components/stepper/dettagliCreaAsta.vue';
import CreaProdotto from '../components/stepper/formCreaProdotto.vue';
import SelezionaFiltri from '../components/stepper/impostaFiltriCreaProdotto.vue';
import Review from '../components/stepper/reviewAsta.vue';

import Button from 'primevue/button';
import Stepper from 'primevue/stepper';
import StepList from 'primevue/steplist';
import StepPanels from 'primevue/steppanels';
import Step from 'primevue/step';
import StepPanel from 'primevue/steppanel';



</script>

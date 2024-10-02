<template>
<Stepper value="1">
    <div class="card flex justify-center">
        <StepList class="basis-[50rem]">
            <Step value="1">
                <span class="hidden lg:block" >Descrizione Prodotto</span>
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
                </span></Step>
        </StepList>
    </div>
    <StepPanels>
        <StepPanel v-slot="{ activateCallback }" value="1">
            <CreaProdotto/>
            <div class="flex justify-around">
                <Button 
                raised
                label="Successivo"
                size="large"
                icon="pi pi-arrow-right" 
                iconPos="right" 
                @click="datiProdottoInseriti;activateCallback('2')" 
                class="w-[50%]"
                >
                </Button>
            </div>
        </StepPanel>
        <StepPanel v-slot="{ activateCallback }" value="2">
            <SelezionaFiltri/>
            <Button label="Back" severity="secondary" icon="pi pi-arrow-left" @click="activateCallback('1')" />
            <Button 
                label="Next" 
                icon="pi pi-arrow-right" 
                iconPos="right" @click="activateCallback('3')" />
        </StepPanel>
        <StepPanel v-slot="{ activateCallback }" value="3">
            <SelezioneTipoAsta/>
            <Button label="Back" severity="secondary" icon="pi pi-arrow-left" @click="activateCallback('2')" />
            <Button label="Next" icon="pi pi-arrow-right" iconPos="right" @click="activateCallback('4')" />
        </StepPanel>
        <StepPanel v-slot="{ activateCallback }" value="4">
            <Review/>
            <Button label="Back" severity="secondary" icon="pi pi-arrow-left" @click="activateCallback('2')" />
            <Button label="Post" icon="pi pi-arrow-right" iconPos="right" />
        </StepPanel>
    </StepPanels>
</Stepper>
</template>

<script setup>
import SelezioneTipoAsta from '../components/stepper/dettagliCreaAsta.vue';
import CreaProdotto from '../components/stepper/formCreaProdotto.vue';
import SelezionaFiltri from '../components/stepper/impostaFiltriCreaProdotto.vue';
import Review from '../components/stepper/reviewAsta.vue';

import Button from 'primevue/button';
import Stepper from 'primevue/stepper';
import StepList from 'primevue/steplist';
import StepPanels from 'primevue/steppanels';
import StepItem from 'primevue/stepitem';
import Step from 'primevue/step';
import StepPanel from 'primevue/steppanel';

const datiProdottoInseriti = () => {
    if (!nomeProdotto.value || !descrizione.value || !prezzoBase.value || !categoriaSalvata) {
        alert('Compila tutti i campi.');
        return;
    }

    storeInstance.updateAsta({
        nomeProdotto: nomeProdotto.value,
        descrizione: descrizione.value,
        prezzoBase: prezzoBase.value,
        categoria: selectedCategory,
    });
    activateCallback('2')
};
</script>

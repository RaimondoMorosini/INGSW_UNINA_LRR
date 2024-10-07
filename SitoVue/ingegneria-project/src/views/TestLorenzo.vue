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
    activateCallback('2');
};

const tipoAstaInserito = () => {
    if (tipoAsta === 'asta_inglese') {
        if (!incrementoMinimo.value || !durataEstensione.value || !scadenzaAsta.value) {
            alert('Asta Inglese: Inserire tutti i campi');
            return;
        }
    } else if (!scadenzaAsta.value) {
        alert('Asta Silenziosa: Inserire tutti i campi');
        return;
    }

    // Emit event to notify parent component to move to the next form section
    if (tipoAsta.value) {
        storeInstance.updateAsta({
            tipoAsta: 'asta_silenziosa',
            scadenzaAsta: scadenzaAsta.value,
            step: 2,
        });
    } else {
        storeInstance.updateAsta({
            tipoAsta: 'asta_inglese',
            scadenzaAsta: scadenzaAsta.value,
            incrementoMinimo: incrementoMinimo.value,
            durataEstensione: durataEstensione.value,
            step: 2,
        });
    }
    emit('update:active', 3);
};

const postAsta = () => {
    //path = asta/creaAsta
    creaAsta()
        .then((response) => {
            console.log('response: ', response);
            success = true;
        })
        .catch((error) => {
            console.log('error: ', error);
            error = error;
        });
};
</script>

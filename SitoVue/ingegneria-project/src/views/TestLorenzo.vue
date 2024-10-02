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
                <CreaProdotto />
                <div class="flex justify-around px-2">
                    <Button
                        raised
                        label="Successivo"
                        size="large"
                        icon="pi pi-arrow-right"
                        iconPos="right"
                        @click="
                            datiProdottoInseriti();
                            activateCallback('2');
                        "
                        class="w-[45%]"
                    >
                    </Button>
                </div>
            </StepPanel>
            <StepPanel v-slot="{ activateCallback }" value="2">
                <SelezionaFiltri />
                <div class="flex justify-around gap-5">
                    <Button
                        label="Precedente"
                        class="w-[45%]"
                        severity="secondary"
                        icon="pi pi-arrow-left"
                        @click="activateCallback('1')"
                    />
                    <Button
                        label="Successivo"
                        icon="pi pi-arrow-right"
                        class="w-[45%]"
                        iconPos="right"
                        @click="activateCallback('3')"
                    />
                </div>
            </StepPanel>
            <StepPanel v-slot="{ activateCallback }" value="3">
                <SelezioneTipoAsta />
                <div class="flex justify-around">
                    <Button
                        class="w-[45%]"
                        label="Precedente"
                        severity="secondary"
                        icon="pi pi-arrow-left"
                        @click="activateCallback('2')"
                    />
                    <Button
                        label="Successivo"
                        icon="pi pi-arrow-right"
                        iconPos="right"
                        class="w-[45%]"
                        @click="
                            tipoAstaInserito();
                            activateCallback('4');
                        "
                    />
                </div>
            </StepPanel>
            <StepPanel v-slot="{ activateCallback }" value="4">
                <Review />
                <div class="flex justify-around">
                    <Button
                        label="Precedente"
                        class="w-[45%]"
                        severity="secondary"
                        icon="pi pi-arrow-left"
                        @click="activateCallback('3')"
                    />

                    <Button v-slot="slotProps" asChild>
                        <button
                            v-bind="slotProps.a11yAttrs"
                            @click="postAsta"
                            class="w-[45%] rounded bg-gradient-to-tl from-danger-300/90 via-primario-100 to-secondario-300 hover:from-danger-400 hover:via-[#7c3aed] hover:to-secondario-400"
                        >
                            <span class="text-bold">
                                Finalizza
                                <i class="pi pi-check"></i>
                            </span>
                        </button>
                    </Button>
                </div>
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

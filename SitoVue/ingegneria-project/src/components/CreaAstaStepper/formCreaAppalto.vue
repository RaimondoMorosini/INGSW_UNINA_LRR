<template>
    <form @submit.prevent="gestioneInvio">
        <div class="flex flex-col justify-around gap-2 lg:flex-row">
            <Card class="lg:w-[60%]">
                <template #header>
                    <h2 class="text-xl font-bold">Informazioni sul prodotto</h2>
                </template>
                <template #content>
                    <div>
                        <div class="formSpace pt-6">
                            <FloatLabel variant="on">
                                <InputText fluid id="nomeProdotto" v-model="nomeProdotto" />
                                <label for="nomeProdotto">Nome Prodotto</label>
                            </FloatLabel>
                        </div>

                        <div class="formSpace pt-6">
                            <FloatLabel variant="on">
                                <InputText
                                    fluid
                                    id="descrizione"
                                    class="min-h-[10rem] rounded"
                                    v-model="descrizione"
                                />
                                <label for="descrizione">Descrizione Prodotto</label>
                            </FloatLabel>
                        </div>
                        <div class="formSpace pt-6">
                            <FloatLabel variant="on">
                                <InputNumber
                                    mode="currency"
                                    currency="EUR"
                                    :min="0"
                                    fluid
                                    class="rounded"
                                    id="prezzoBase"
                                    v-model="prezzoBase"
                                />
                                <label for="prezzoBase">Prezzo Base</label>
                            </FloatLabel>
                        </div>
                        <div class="formSpace pt-6">
                            <FloatLabel variant="on">
                                <DatePicker
                                    dateFormat="dd/mm/yy"
                                    :minDate="minDate"
                                    showIcon="true"
                                    fluid
                                    v-model="scadenzaAsta"
                                    id="scadenzaAsta"
                                    inputId="birth_date"
                                    class="rounded"
                                />
                                <label for="scadenzaAsta">Data Scadenza</label>
                            </FloatLabel>
                        </div>
                        <InputGroup class="categoriaSelector pt-6">
                            <InputGroupAddon class=" ">
                                <i class="pi pi-th-large"></i>
                            </InputGroupAddon>
                            <FloatLabel variant="on">
                                <TreeSelect
                                    v-model="selectedCategory"
                                    :options="nodes"
                                    optionLabel="nome"
                                    optionValue="id"
                                    placeholder="Seleziona Categoria"
                                />
                                <label for="categoria">Categoria</label>
                            </FloatLabel>
                        </InputGroup>
                    </div>
                </template>
            </Card>
            <div class="lg:w-[40%]">
                <div class="flex w-[100%] justify-between p-2 lg:justify-start">
                    <!--viene dato l'array contenente le immagini dallo store e flag per verificare se si possono mettere multiple immagini-->
                    <ImageUploader
                        :storeInstance="astaStoreInstance.asta.immaginiSalvate"
                        class="mx-5 w-[100%] lg:mx-0"
                    />
                </div>
            </div>
        </div>

        <div class="areaBottoni my-4 flex justify-around gap-5 px-10">
            <Button class="w-[45%]" size="large" @click="gestioneInvio"
                ><span class="font-bold">Successivo <i class="pi pi-arrow-right"></i></span
            ></Button>
        </div>
    </form>
</template>

<script setup>
import Card from 'primevue/card';
import Button from 'primevue/button';
import InputNumber from 'primevue/inputnumber';
import DatePicker from 'primevue/datepicker';
import FloatLabel from 'primevue/floatlabel';
import InputText from 'primevue/inputtext';
import InputGroup from 'primevue/inputgroup';
import InputGroupAddon from 'primevue/inputgroupaddon';
import TreeSelect from 'primevue/treeselect';
import ImageUploader from './ImageUploader.vue';
import { onMounted, onUnmounted, ref, defineEmits } from 'vue';
import { useAstaStore } from '../../stores/astaStore.js';
import { getCategorie } from '../../service/categoriaService.js';

const astaStoreInstance = useAstaStore();

const nodes = ref([]);
const emit = defineEmits('increase-page', 'decreasse-page');
const selectedCategory = ref(astaStoreInstance.asta.categoria);
const nomeProdotto = ref(astaStoreInstance.asta.nomeProdotto);
const descrizione = ref(astaStoreInstance.asta.descrizione);
const prezzoBase = ref(astaStoreInstance.asta.prezzoBase);
const scadenzaAsta = ref(astaStoreInstance.asta.scadenzaAsta);

const categoriaSelezionata = function (obj) {
    let keys = '';
    for (let key in obj) {
        keys = key;
    }
    return keys;
};

const categoriaSalvata = ref(categoriaSelezionata(astaStoreInstance.asta.categoria));

onMounted(() => {
    astaStoreInstance.updateAsta({
        step: 0,
    });
    getCategorie().then((response) => {
        nodes.value = response;
    });
});
onUnmounted(() => {
    astaStoreInstance.updateAsta({
        nomeProdotto: nomeProdotto.value,
        descrizione: descrizione.value,
        prezzoBase: prezzoBase.value,
        categoria: selectedCategory,
        scadenzaAsta: scadenzaAsta,
    });
});

const gestioneInvio = () => {
    if (
        !scadenzaAsta ||
        !nomeProdotto.value ||
        !descrizione.value ||
        !prezzoBase.value ||
        !categoriaSalvata
    ) {
        alert('Compila tutti i campi.');
        return;
    }

    astaStoreInstance.updateAsta({
        nomeProdotto: nomeProdotto.value,
        descrizione: descrizione.value,
        prezzoBase: prezzoBase.value,
        categoria: selectedCategory,
        scadenzaAsta: scadenzaAsta,
    });
    emit('increase-page');
};
</script>

<style scoped>
button.bottone {
    background-color: #cc85f5;
    padding: 10px 20px;
    color: white;
    border-radius: 5px;
    font-size: 1.1rem;
    font-weight: bold;
    width: 50%;
    margin: 10px;
}

button.bottone:hover {
    background-color: #7c3aed;
}

textarea {
    resize: none;
}

/* Chrome, Safari, Edge, Opera */
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
}

/* Firefox */
input[type='number'] {
    -moz-appearance: textfield;
}
</style>

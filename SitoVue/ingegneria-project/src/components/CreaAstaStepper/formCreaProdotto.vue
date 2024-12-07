<template>
    <Form >
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

                        <InputGroup class="categoriaSelector pt-6">
                            <InputGroupAddon>
                                <i class="pi pi-th-large"></i>
                            </InputGroupAddon>
                            <FloatLabel variant="on">
                                <TreeSelect
                                    v-model="categoria"
                                    :options="categoriaOption"
                                    optionLabel="nome"
                                    optionValue="nome"
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
                    <ImageUploader
                        :storeInstance="astaStoreInstance.asta.immaginiSalvate"
                        class="mx-5 w-[100%] lg:mx-0"
                    />
                </div>
            </div>
        </div>

        <div class="areaBottoni my-4 flex justify-around gap-5 px-10">
            <Button class="w-[45%]" size="large" @click="gestioneInvio">
                <span class="font-bold">
                    Successivo <i class="pi pi-arrow-right"></i>
                </span>
            </Button>
        </div>
    </Form>
   </template>

<script setup>
import { defineEmits,ref, computed, onMounted } from 'vue';
import { useAstaStore } from '../../stores/astaStore.js';
import { getCategorie } from '../../service/categoriaService.js';
import Card from 'primevue/card';
import Button from 'primevue/button';
import InputNumber from 'primevue/inputnumber';
import FloatLabel from 'primevue/floatlabel';
import InputText from 'primevue/inputtext';
import InputGroup from 'primevue/inputgroup';
import InputGroupAddon from 'primevue/inputgroupaddon';
import TreeSelect from 'primevue/treeselect';
import ImageUploader from './ImageUploader.vue';

// Emits
const emit = defineEmits('increase-page');

// Store
const astaStoreInstance = useAstaStore();

// Valori iniziali centralizzati
const initialValues = {
    nomeProdotto: astaStoreInstance.asta.nomeProdotto || '',
    descrizione: astaStoreInstance.asta.descrizione || '',
    prezzoBase: astaStoreInstance.asta.prezzoBase || 0,
    categoria: astaStoreInstance.asta.categoria || null,
};

// Computed properties per sincronizzare i dati con lo store
const nomeProdotto = computed({
    get: () => astaStoreInstance.asta.nomeProdotto,
    set: (value) => astaStoreInstance.updateAsta({ nomeProdotto: value }),
});

const descrizione = computed({
    get: () => astaStoreInstance.asta.descrizione,
    set: (value) => astaStoreInstance.updateAsta({ descrizione: value }),
});

const prezzoBase = computed({
    get: () => astaStoreInstance.asta.prezzoBase,
    set: (value) => astaStoreInstance.updateAsta({ prezzoBase: value }),
});

const categoria = computed({
    get: () => {
        // Converti lo store in formato leggibile per il TreeSelect
        const value = astaStoreInstance.asta.categoria;
        if (typeof value === 'string') {
            // Rendi compatibile con il formato del TreeSelect
            return { [value]: true };
        }
        return value;
    },
    set: (value) => {
        // Normalizza e salva nello store solo la stringa
        const normalizedValue = typeof value === 'object' && value !== null
            ? Object.keys(value)[0] // Prendi la chiave
            : value; // Usa direttamente se è una stringa
        astaStoreInstance.updateAsta({ categoria: normalizedValue });
    },
});

// Opzioni per la selezione delle categorie
const categoriaOption = ref([]);

onMounted(() => {
    getCategorie().then((response) => {
        categoriaOption.value = response;
    });
});

// Gestione invio
const gestioneInvio = () => {
    if (!nomeProdotto || !descrizione || !prezzoBase || !categoria) {
        alert('Compila tutti i campi.');
        return;
    }
    emit('increase-page');
};
</script>

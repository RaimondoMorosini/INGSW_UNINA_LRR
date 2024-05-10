<template>

    <CaricamentoAste v-if="props.propLoading" />
    
    <div class="card" v-else>
        <DataView :value="products">
            <template #list="slotProps">
                <div class="grid grid-nogutter">
                    <div v-for="(item, index) in slotProps.items" :key="index" class="col-12">
                        <div class="flex flex-column sm:flex-row sm:align-items-center p-4 gap-3"
                            :class="{ 'border-top-1 surface-border': index !== 0 }">
                            <div class="md:w-10rem relative">
                                <img class="block xl:block mx-auto border-round w-full" :src="`${item.immagini[0]}`"
                                    :alt="item.nome" />
                                <Tag :value="item.tipoAsta" :severity="danger" class="absolute"
                                    style="left: 4px; top: 4px"></Tag>
                            </div>
                            <div
                                class="flex flex-column md:flex-row justify-content-between md:align-items-center flex-1 gap-4">
                                <div
                                    class="flex flex-row md:flex-column justify-content-between align-items-start gap-2">
                                    <div>
                                        <span class="font-medium text-secondary text-sm">{{ item.categoria }}</span>
                                        <div class="text-lg font-medium text-900 mt-2">{{ item.nome }}</div>
                                        <div>Venditore: {{ item.emailUtenteCreatore }}</div>
                                        <span class="text-xl font-semibold text-900">BASE ASTA: EURO {{ item.baseAsta
                                            }}</span>
                                    </div>
                                </div>
                                <div class="flex flex-column md:align-items-end gap-5">
                                    <div class="contenitore-bottone flex flex-row-reverse md:flex-row gap-2">
                                        <Button icon="pi pi-shopping-cart" label="Partecipa all'asta"
                                            :disabled="item.inventoryStatus === 'OUTOFSTOCK'"
                                            class="flex-auto md:flex-initial white-space-nowrap"></Button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </template>
        </DataView>
    </div>
</template>

<script setup>
import axios from 'axios';
import DataView from 'primevue/dataview';
import DataViewLayoutOptions from 'primevue/dataviewlayoutoptions'   // optional
import Tag from 'primevue/tag';
import Button from 'primevue/button';
import "primeflex/primeflex.css";
import "primevue/resources/themes/aura-light-green/theme.css";
import "primeicons/primeicons.css";
import { ref, watch } from "vue";
import CaricamentoAste from '../components/CaricamentoAste.vue';

const props = defineProps(['propAste', 'propLoading']);


const products = ref();
products.value = props.propAste;

watch(() => props.propAste, (newValue) => {
    // Aggiorna products.values quando props.propAste cambia
    products.value = newValue;
});

</script>

<style scoped>
.contenitore-bottone {

    background-color: green;
    border: 2px solid #c5c5c58a;
    border-radius: 1rem;
    padding: 1rem;
    color: white;
}
</style>
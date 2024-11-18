<template>
    <div class="flex flex-col gap-4 rounded-md border p-4 shadow-md md:flex-row">
        <ProductImage
            class="w-full max-w-xs md:w-1/3"
            :urlFotoCopertina="item.immagini[0]"
            :tipoAsta="item.tipoAsta"
        />

        <div class="flex-1">
            <ProductDetails
                class="mb-4 rounded bg-slate-200/20 px-2"
                :categoria="item.categoria"
                :titoloProdotto="item.nome"
                :venditore="item.emailUtenteCreatore"
                :baseAsta="item.baseAsta"
            />

            <Button
                @click.stop="apriUnNuovoTab(item)"
                icon="pi pi-shopping-cart"
                label="Partecipa all'asta"
                :disabled="item.inventoryStatus === 'OUTOFSTOCK'"
                class="w-full bg-primario-400 hover:bg-primario-300 focus:ring focus:ring-primario-400 md:w-auto"
            />
        </div>
    </div>
</template>

<script setup>
import { defineProps } from 'vue';
import Button from 'primevue/button';
import ProductDetails from './ProductDetails.vue';
import ProductImage from './ProductImage.vue';

const props = defineProps({
    item: Object,
    index: Number,
});

function apriUnNuovoTab(item) {
    const newWindow = window.open(`/asta/${item.idAsta}`, '_blank');
}
</script>

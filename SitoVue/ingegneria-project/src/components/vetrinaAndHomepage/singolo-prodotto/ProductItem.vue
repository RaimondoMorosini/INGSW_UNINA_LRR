<template>
  <div class="flex flex-col md:flex-row p-4 gap-4 border rounded-md shadow-md">
    <ProductImage 
    class="w-full md:w-1/3 max-w-xs"
    :urlFotoCopertina="item.immagini[0]" 
      :tipoAsta="item.tipoAsta" 
    />

    <div class="flex-1">
      <ProductDetails 
        class="mb-4"
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
        class="w-full md:w-auto bg-primario-100 text-white hover:bg-primario-200 focus:ring focus:ring-primario-300"
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
  index: Number
});

function apriUnNuovoTab(item) {
  const newWindow = window.open(`/asta/${item.idAsta}`, '_blank');
}
</script>

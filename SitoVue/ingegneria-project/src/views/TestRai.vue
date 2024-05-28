<template>
    <div class="card">
        <Carousel
            :value="products"
            :numVisible="5"
            :numScroll="1"
            :responsiveOptions="responsiveOptions"
            circular
            :autoplayInterval="3000"
        >
            <template #item="slotProps">
                <div class="border-1 surface-border border-round m-2 p-3">
                    <div class="mb-3">
                        <div class="relative mx-auto">
                            <img
                                :src="
                                    'https://primefaces.org/cdn/primevue/images/product/' +
                                    slotProps.data.image
                                "
                                :alt="slotProps.data.name"
                                class="border-round w-full"
                            />
                            <Tag
                                :value="slotProps.data.inventoryStatus"
                                :severity="getSeverity(slotProps.data.inventoryStatus)"
                                class="absolute"
                                style="left: 5px; top: 5px"
                            />
                        </div>
                    </div>
                    <div class="mb-3 font-medium">{{ slotProps.data.name }}</div>
                    <div class="justify-content-between align-items-center flex">
                        <div class="mt-0 text-xl font-semibold">${{ slotProps.data.price }}</div>
                        <span>
                            <Button icon="pi pi-heart" severity="secondary" outlined />
                            <Button icon="pi pi-shopping-cart" class="ml-2" />
                        </span>
                    </div>
                </div>
            </template>
        </Carousel>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { ProductService } from '@/service/ProductService';
import Carousel from 'primevue/carousel';
import Tag from 'primevue/tag';
import Button from 'primevue/button';

import 'primeflex/primeflex.css';

onMounted(() => {
    ProductService.getProductsSmall().then((data) => (products.value = data.slice(0, 9)));
});

const products = ref();
const responsiveOptions = ref([
    {
        breakpoint: '1400px',
        numVisible: 2,
        numScroll: 1,
    },
    {
        breakpoint: '1199px',
        numVisible: 3,
        numScroll: 1,
    },
    {
        breakpoint: '767px',
        numVisible: 2,
        numScroll: 1,
    },
    {
        breakpoint: '575px',
        numVisible: 1,
        numScroll: 1,
    },
]);

const getSeverity = (status) => {
    switch (status) {
        case 'INSTOCK':
            return 'success';

        case 'LOWSTOCK':
            return 'warning';

        case 'OUTOFSTOCK':
            return 'danger';

        default:
            return null;
    }
};
</script>

<style>
.p-carousel-indicator {
    background-color: rgb(215, 206, 206);
    border: 1px solid #ccc;
    border-radius: 5px;
}

.p-carousel-indicators {
    display: none;
}
</style>

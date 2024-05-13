<template>
    <div class="card">
        <DataView :value="products" :layout="layout">
            <template #list>
                <div class="grid grid-nogutter">
                    <div v-for="i in 5" :key="i" class="col-12">
                        <div class="flex flex-column xl:flex-row xl:align-items-start p-4 gap-4"
                            :class="{ 'border-top-1 surface-border': i !== 0 }">
                            <Skeleton class="w-9 sm:w-16rem xl:w-10rem h-6rem block xl:block mx-auto border-round" />
                            <div
                                class="flex flex-column sm:flex-row justify-content-between align-items-center xl:align-items-start flex-1 gap-4">
                                <div class="flex flex-column align-items-center sm:align-items-start gap-3">
                                    <Skeleton class="w-8rem border-round h-2rem" />
                                    <Skeleton class="w-6rem border-round h-1rem" />
                                    <div class="flex align-items-center gap-3">
                                        <Skeleton class="w-6rem border-round h-1rem" />
                                        <Skeleton class="w-3rem border-round h-1rem" />
                                    </div>
                                </div>
                                <div class="flex sm:flex-column align-items-center sm:align-items-end gap-3 sm:gap-2">
                                    <Skeleton class="w-4rem border-round h-2rem" />
                                    <Skeleton shape="circle" class="w-3rem h-3rem" />
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

import DataView from 'primevue/dataview';
import { ref, onMounted } from "vue";
import { ProductService } from '../service/ProductService.js';
import Skeleton from 'primevue/skeleton';

import "primeflex/primeflex.css";
import "primevue/resources/themes/aura-light-green/theme.css";
import "primeicons/primeicons.css";
import "../assets/css/flag.css";
import "../assets/css/style.css";

onMounted(() => {
    ProductService.getProductsSmall().then((data) => (products.value = data.slice(0, 5)));
});

const products = ref();
const layout = ref('list');

</script>

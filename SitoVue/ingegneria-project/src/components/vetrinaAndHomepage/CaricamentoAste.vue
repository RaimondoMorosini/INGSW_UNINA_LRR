<template>
    <div class="card">
        <DataView :value="products" :layout="layout">
            <template #list>
                <div class="grid-nogutter grid">
                    <div v-for="i in 5" :key="i" class="col-12">
                        <div
                            class="flex-column xl:align-items-start flex gap-4 p-4 xl:flex-row"
                            :class="{ 'border-top-1 surface-border': i !== 0 }"
                        >
                            <Skeleton
                                class="sm:w-16rem xl:w-10rem h-6rem border-round mx-auto block w-9 xl:block"
                            />
                            <div
                                class="flex-column justify-content-between align-items-center xl:align-items-start flex flex-1 gap-4 sm:flex-row"
                            >
                                <div
                                    class="flex-column align-items-center sm:align-items-start flex gap-3"
                                >
                                    <Skeleton class="w-8rem border-round h-2rem" />
                                    <Skeleton class="w-6rem border-round h-1rem" />
                                    <div class="align-items-center flex gap-3">
                                        <Skeleton class="w-6rem border-round h-1rem" />
                                        <Skeleton class="w-3rem border-round h-1rem" />
                                    </div>
                                </div>
                                <div
                                    class="sm:flex-column align-items-center sm:align-items-end flex gap-3 sm:gap-2"
                                >
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
import { ref, onMounted } from 'vue';
import { ProductService } from '../../service/ProductService';
import Skeleton from 'primevue/skeleton';

onMounted(() => {
    ProductService.getProductsSmall().then((data) => (products.value = data.slice(0, 5)));
});

const products = ref();
const layout = ref('list');
</script>

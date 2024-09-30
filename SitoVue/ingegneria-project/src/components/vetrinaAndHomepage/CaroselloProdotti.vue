<template>
    <div class="card">
        <Carousel
            :value="products"
            :numVisible="4"
            :numScroll="1"
            :responsiveOptions="responsiveOptions"
            circular
            :autoplayInterval="3000"
        >
            <template #item="slotProps">
                <div class="contenitore-articolo m-2 cursor-pointer p-3">
                    <div class="mb-3">
                        <div class="relative mx-auto">
                            <img
                                :src="slotProps.data.immagini[0]"
                                :alt="slotProps.data.name"
                                class="border-round w-full"
                            />
                            <Tag
                                :value="slotProps.data.tipoAsta"
                                :severity="getSeverity(slotProps.data.inventoryStatus)"
                                class="absolute"
                                style="left: 5px; top: 5px"
                            />
                        </div>
                    </div>
                    <div class="nome-articolo mb-4 font-medium">{{ slotProps.data.nome }}</div>
                    <div class="justify-content-between align-items-center flex">
                        <div class="mt-0 text-xl font-semibold">${{ slotProps.data.baseAsta }}</div>
                    </div>
                </div>
            </template>
        </Carousel>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import Carousel from 'primevue/carousel';
import Tag from 'primevue/tag';
import 'primeflex/primeflex.css';

const props = defineProps(['propProdotti']);

const products = ref(props.propProdotti.slice(0, 8));

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
.p-carousel-indicators {
    display: none;
}

.p-carousel-prev {
    border: 1px solid #ccc;
    border-radius: 20px;
}

.p-carousel-next {
    border: 1px solid #ccc;
    border-radius: 20px;
}

.contenitore-articolo {
    background-color: #f2f2f2;
    border: 1px #ccc;
    border-radius: 10px;
}

.nome-articolo {
    font-size: large;
}

.nome-articolo:hover {
    font-size: x-large;
    text-decoration: underline;
}
</style>

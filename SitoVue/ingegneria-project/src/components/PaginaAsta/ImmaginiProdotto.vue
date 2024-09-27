<template>
    <div class="card">
        <Galleria :value="images" :responsiveOptions="responsiveOptions" :numVisible="3"
            containerStyle="max-width: 640px">
            <template #item="slotProps">
                <img :src="slotProps.item.itemImageSrc" :alt="slotProps.item.alt" style="width: 100%" />
            </template>
            <template #thumbnail="slotProps">
                <img :src="slotProps.item.thumbnailImageSrc" :alt="slotProps.item.alt" />
            </template>
        </Galleria>
    </div>
</template>

<script setup>

import Galleria from 'primevue/galleria';
import { ref, onMounted } from "vue";
import { PhotoService } from '../../service/PhotoService.js';

const props = defineProps([
    'prodotto'
]);

onMounted(() => {
    PhotoService.getImages(props.prodotto.immagini).then((data) => (images.value = data));
});

const images = ref();
const responsiveOptions = ref([
    {
        breakpoint: '1300px',
        numVisible: 4
    },
    {
        breakpoint: '575px',
        numVisible: 1
    }
]);
</script>

<style>

.p-galleria-item img {
    width: 100%;                     /* Larghezza al 100% del contenitore */
    height: 200px;                   /* Altezza fissa per le immagini */
    object-fit: contain;               /*Ritaglia le immagini per riempire il contenitore senza deformarle */
    border-radius: 8px;              /* Arrotonda gli angoli delle immagini */
}

.p-galleria-thumbnail img {
    width: 80px;                     /* Larghezza per le miniature */
    height: 80px;                    /* Altezza per le miniature */
    object-fit: contain;               /* Mantiene il ritaglio per le miniature */
    border-radius: 4px;              /* Arrotonda gli angoli delle miniature */
}

</style>
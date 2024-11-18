<template>
    <h1 class="my-2 text-3xl font-bold">{{ prodotto.nome }}</h1>
    <Galleria
        :value="images"
        :responsiveOptions="responsiveOptions"
        :numVisible="3"
        containerStyle="max-width: 640px"
    >
        <template #item="slotProps">
            <img :src="slotProps.item.itemImageSrc" :alt="slotProps.item.alt" style="width: 100%" />
        </template>
        <template #thumbnail="slotProps">
            <img :src="slotProps.item.thumbnailImageSrc" :alt="slotProps.item.alt" />
        </template>
    </Galleria>
    <Card class="my-2">
        <template #content>
            <Accordion value="0">
                <AccordionPanel value="0">
                    <AccordionHeader>Descrizione prodotto</AccordionHeader>
                    <AccordionContent>
                        <p class="descrizione-prodotto">
                            {{ props.prodotto.descrizione }}
                        </p>
                    </AccordionContent>
                </AccordionPanel>
            </Accordion>
        </template>
    </Card>
</template>

<script setup>
import Galleria from 'primevue/galleria';
import { ref, onMounted } from 'vue';
import { PhotoService } from '../../service/PhotoService.js';
import Card from 'primevue/card';
import Accordion from 'primevue/accordion';
import AccordionPanel from 'primevue/accordionpanel';
import AccordionHeader from 'primevue/accordionheader';
import AccordionContent from 'primevue/accordioncontent';

const props = defineProps(['prodotto']);

onMounted(() => {
    PhotoService.getImages(props.prodotto.immagini).then((data) => (images.value = data));
});

const images = ref();
const responsiveOptions = ref([
    {
        breakpoint: '1300px',
        numVisible: 4,
    },
    {
        breakpoint: '575px',
        numVisible: 1,
    },
]);
</script>

<style>
.p-galleria-item img {
    width: 100%;
    /* Larghezza al 100% del contenitore */
    height: 200px;
    /* Altezza fissa per le immagini */
    object-fit: contain;
    /*Ritaglia le immagini per riempire il contenitore senza deformarle */
    border-radius: 8px;
    /* Arrotonda gli angoli delle immagini */
}

.p-galleria-thumbnail img {
    width: 80px;
    /* Larghezza per le miniature */
    height: 80px;
    /* Altezza per le miniature */
    object-fit: contain;
    /* Mantiene il ritaglio per le miniature */
    border-radius: 4px;
    /* Arrotonda gli angoli delle miniature */
}

.descrizione-prodotto {
    font-family: 'Lora', serif;
    /* Font elegante */
    font-size: 16px;
    /* Dimensione del testo leggibile e chiara */
    font-weight: 400;
    /* Peso normale per un aspetto naturale */
    line-height: 1.6;
    /* Aumenta la leggibilità con più spazio tra le righe */
    color: #333;
    /* Un colore scuro ma non completamente nero per un aspetto più morbido */
    letter-spacing: 0.5px;
    /* Leggero spazio tra le lettere per migliorare la leggibilità */
    text-align: justify;
    /* Giustifica il testo per un aspetto più professionale */
    margin: 20px 0;
}
</style>

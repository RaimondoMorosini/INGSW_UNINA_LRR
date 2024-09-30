<template>
    <div class="relative mx-auto overflow-hidden rounded">
        <img
            class="h-full w-full object-cover"
            :src="urlFotoCopertina"
            alt="Immagine del prodotto in asta"
        />
        <!-- Aumento dello z-index e regolazione del posizionamento -->
        <Tag
            :value="tipoAsta"
            :severity="'danger'"
            class="absolute z-10"
            style="left: 4px; top: 4px"
        ></Tag>
    </div>
</template>

<script setup>
import Tag from 'primevue/tag';
const props = defineProps({
    urlFotoCopertina: String,
    tipoAsta: String,
});
</script>

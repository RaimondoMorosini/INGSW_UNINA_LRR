<template>
    <div class="md:flex-column justify-content-between align-items-start flex flex-row gap-2">
        <div>
            <span class="text-sm font-medium">{{ categoria }}</span>
            <div class="text-900 titolo-articolo mt-2 text-lg font-medium">
                {{ titoloProdotto }}
            </div>
            <div>Venditore: {{ venditore }}</div>
            <span class="text-900 text-xl font-semibold">BASE ASTA: {{ baseAsta }}€</span>
        </div>
    </div>
</template>
<script setup>
const props = defineProps({
    categoria: String,
    titoloProdotto: String,
    venditore: String,
    baseAsta: String,
});
</script>

<template>
    <div class="pagination">
        <button @click="prevPage" :disabled="currentPage === 1">Indietro</button>
        <span>pagina {{ currentPage }} di {{ totalPages }}</span>
        <button @click="nextPage" :disabled="currentPage === totalPages">Avanti</button>
    </div>
</template>

<script setup>
import { defineProps, defineEmits } from 'vue';

const props = defineProps({
    currentPage: {
        type: Number,
        required: true,
    },
    totalPages: {
        type: Number,
        required: true,
    },
});

const emit = defineEmits(['prevPage', 'nextPage']);
</script>

<style scoped>
.pagination {
    display: flex;
    justify-content: space-between;
    margin-bottom: 20px;
}

.pagination button {
    margin: 0 10px;
}
</style>

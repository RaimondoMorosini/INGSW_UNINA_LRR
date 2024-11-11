<template>
    <p v-if="!isMessageVisible" class="notification-message-preview">
        {{ message }}
    </p>
    <p v-if="isMessageVisible" class="notification-message-full">
        {{ message }}
        <strong>Asta ID:</strong> {{ astaId }}
    </p>
</template>

<script setup>
import { defineProps } from 'vue';

const props = defineProps({
    message: String,
    isMessageVisible: Boolean,
    toggleMessage: Function,
});
</script>

<style scoped>
.notification-message-preview {
    font-size: 1em;
    color: #555;
    margin-top: 5px;
    display: -webkit-box;
    -webkit-line-clamp: 2; /* Limita a due righe visibili */
    -webkit-box-orient: vertical;
    overflow: hidden;
    text-overflow: ellipsis; /* Mostra i puntini di sospensione per il testo troncato */
    cursor: pointer;
    transition: max-height 0.3s ease;
}
.notification-message-full {
    font-size: 0.95em;
    color: #333;
    margin-top: 10px;
    transition: max-height 0.3s ease;
}
</style>

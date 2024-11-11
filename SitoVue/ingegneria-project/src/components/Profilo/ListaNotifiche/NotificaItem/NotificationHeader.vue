<template>
    <div class="notification-header">
        <h3 class="notification-title">{{ title }}</h3>
        <span class="notification-date">{{ formattedDate }}</span>
    </div>
</template>

<script setup>
import { defineProps } from 'vue';
const props = defineProps({
    title: String,
    isRead: Boolean,
    formattedDate: String,
});
</script>

<style scoped>
.notification-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px;
    border-radius: 4px;
    transition: background-color 0.3s ease;
}

.notification-title {
    font-size: 1.25em;
    font-weight: bold;
    margin: 0;
}

.notification-date {
    font-size: 0.85em;
    color: #777;
    font-style: italic;
}
</style>

<template>
    <div class="custom-toast" :class="`toast-${severity}`">
        <div class="toast-header">
            <strong class="mr-auto">{{ summary }}</strong>
            <button type="button" class="close" @click="closeToast">&times;</button>
        </div>
        <div class="toast-body">
            {{ detail }}
        </div>
    </div>
</template>

<script setup>
import { defineProps, defineEmits } from 'vue';

const props = defineProps({
    severity: String,
    summary: String,
    detail: String,
});

const emit = defineEmits(['close']);

const closeToast = () => {
    emit('close');
};
</script>

<style scoped>
.custom-toast {
    padding: 1rem;
    border-radius: 0.5rem;
    margin: 0.5rem 0;
    position: relative;
    transition: opacity 0.3s ease;
}

.toast-info {
    background-color: #e7f3fe;
    color: #31708f;
}

.toast-header {
    display: flex;
    justify-content: space-between;
}

.toast-body {
    margin-top: 0.5rem;
}
</style>

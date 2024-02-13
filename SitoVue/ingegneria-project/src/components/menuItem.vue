<script setup>
import { defineProps } from 'vue';

const props = defineProps({
    IconaSx: Object,
    IconaDx: Object
});
</script>

<template>
    <button>
        <component :is="IconaSx" />        
        <slot></slot>
        <component :is="IconaDx" />
    </button>
</template>

<style scoped>
button {
    padding: 10px;
    margin: 10px;
    background-color: #2c3e50;
    color: white;
    stroke:white;
    fill:white;
    border: 0;
    border-radius: 5px;
}

button:hover {
    background-color: #42b983;
}
</style>

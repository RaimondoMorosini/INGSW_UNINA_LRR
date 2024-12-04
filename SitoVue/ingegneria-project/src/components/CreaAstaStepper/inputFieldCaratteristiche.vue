<template>
    <div>
        <FloatLabel variant="on">
        <Dropdown inputId="selettore" fluid v-model="selectedValue" :options="options" placeholder="Seleziona" />
        
        <label for="selettore">{{ label }}</label>
        </FloatLabel>
        <div v-if="production">
            valore selezionato: {{ selectedValue }}
        </div>

    </div>
</template>

<script setup>
import Dropdown from 'primevue/dropdown';
import  FloatLabel  from 'primevue/floatlabel';
import { ref } from 'vue';

const production = true;

const props = defineProps({
    label: String,
    options: Array,
    modelValue: {
        type: [String, Number, Boolean],
        default: '',
    },
});

const selectedValue = ref(props.modelValue);

const updateSelectedValue = (value) => {
    selectedValue.value = value;
};
</script>

<style scoped>
/* Stili rimossi per brevità */
</style>

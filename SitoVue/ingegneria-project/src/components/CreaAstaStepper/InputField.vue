<script setup>
import  FloatLabel  from 'primevue/floatlabel';
import Dropdown from 'primevue/dropdown';
import { defineProps, ref, defineEmits, onMounted, onUnmounted } from 'vue';
const props = defineProps({
    label: String,
    options: Array,
    modelValue: {
        type: [String, Number, Boolean],
        default: '',
    },
});

const selectedValue = ref(null);

const emit = defineEmits(['update:modelValue']);
const handleChange = (newValue) => {
    selectedValue.value = newValue.value;
    emit('update:modelValue', newValue.value);
};

onMounted(() => {
    selectedValue.value = props.modelValue;
    
});

//how to capitalize string

const production = false;
</script>

<template>
    <div class="flex mb-2">
        <FloatLabel variant="on">
        <Dropdown
            fluid
            showClear
            v-model="selectedValue"
            :options="options"
            @change="handleChange"
        />
        <label>{{ label.charAt(0).toUpperCase() + label.slice(1) }}</label>
        </FloatLabel>
        <div v-if="production">
            Valore selezionato: {{ selectedValue }}
        </div>
    </div>
</template>

<script setup>
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
</script>

<template>
    <div>
        <label>{{ label }}</label>
        <br />
        <Dropdown
            showClear
            v-model="selectedValue"
            :options="options"
            placeholder="Seleziona"
            @change="handleChange"
        />
        <br />
        Valore selezionato: {{ selectedValue }}
    </div>
</template>

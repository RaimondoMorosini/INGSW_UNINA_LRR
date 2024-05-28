<script setup>
import Dropdown from 'primevue/dropdown';
import { defineProps, ref ,defineEmits} from 'vue';

const props = defineProps({
  label: String,
  options: Array,
  modelValue: String,
});

const selectedValue = ref(null);

const emit = defineEmits(['update:valoreSelezionato','update:modelValue']);
const handleChange = (newValue) => {
  selectedValue.value = newValue.value;
  emit('update:valoreSelezionato', newValue.value);
  emit('update:modelValue', newValue.value);
};
</script>

<template>
  <div>
    <label>{{ label }}</label>
    <br>
    <Dropdown showClear  v-model="selectedValue" :options="options" placeholder="Seleziona" @change="handleChange" />
    <br>
    Valore selezionato: {{ selectedValue }}
  </div>
</template>

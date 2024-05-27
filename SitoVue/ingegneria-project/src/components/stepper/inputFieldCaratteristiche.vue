<template>
    <div>
      <label>{{ label }}</label>
      <br>
      <Dropdown v-model="selectedValue" :options="options" placeholder="Seleziona" />
      <br>
      valore selezionato: {{ selectedValue }}
    </div>
  </template>
  
  <script setup>
  import Dropdown from 'primevue/dropdown';
  import { ref } from 'vue';
  
  const props = defineProps({
    label: String,
    options: Array,
    modelValue: {
      type: [String, Number, Boolean],
      default: ''
    }
  });
  
  const selectedValue = ref(props.modelValue);
  
  const updateSelectedValue = (value) => {
    selectedValue.value = value;
  };
  </script>
  
  <style scoped>
  /* Stili rimossi per brevità */
  </style>
  
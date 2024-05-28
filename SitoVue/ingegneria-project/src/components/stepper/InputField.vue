
<template>
  <div>
    <label>{{label}}</label>
    <br>
    <Dropdown showClear  v-model="selectedValue" :options="options" placeholder="Seleziona" @change="handleChange" />
    <br>
  </div>
</template>


<script setup>
import Dropdown from 'primevue/dropdown';
import { defineProps, ref ,defineEmits,onMounted} from 'vue';

const props = defineProps({
  label: String,
  options: Array,
  modelValue: String,
});

const selectedValue = ref(null);

const emit = defineEmits(['update:modelValue']);
const handleChange = (newValue) => {
  selectedValue.value = newValue.value;
  emit('update:modelValue', newValue.value);
};

onMounted(() => {
  
  // ordina le option in ordine alfabetico props.options
  //props.options.sort();

});
</script>

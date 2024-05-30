
<template>
    <div class="card flex justify-content-center">
        <MultiSelect v-model="selectedCities" :options="cities" filter optionLabel="name" placeholder="Select Cities"
            :maxSelectedLabels="3" class="w-full md:w-20rem" />
    </div>
</template>

<script setup>
import { ref } from "vue";
import MultiSelect from "primevue/multiselect";
const selectedCities = ref();
const cities = ref([
{ name: 'New York', code: 'NY' },
{ name: 'New York1', code: 'NY1' },
{ name: 'New York2', code: 'NY2' },
{ name: 'New York3', code: 'NY3' },
{ name: 'New York4', code: 'NY4' },
{ name: 'New York5', code: 'NY5' },
{ name: 'New York6', code: 'NY6' },

{ name: 'Rome', code: 'RM' },
    { name: 'London', code: 'LDN' },
    { name: 'Istanbul', code: 'IST' },
    { name: 'Paris', code: 'PRS' }
]);
</script>

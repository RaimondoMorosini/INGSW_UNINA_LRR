<template>
    <Toast position="center" group="c" />

    <!-- Button to add a new input field -->
    <Button label="Add Input" icon="pi pi-plus" @click="addInputField" />
    <div class="form-container flex flex-col">
        <!-- Dynamically added text inputs -->
        <div
            v-for="(input, index) in inputs"
            :key="index"
            class="p-field p-grid flex w-[100%] flex-col gap-2 px-2 py-2"
        >
            <FloatLabel variant="on">
                <InputText
                    name="name"
                    label="label"
                    id="id"
                    fluid
                    v-model="input.value"
                    class="w-full"
                    @keyup.enter="isValidUrl(input.value)"
                />
                <label for="social" class="mb-2 block font-bold text-gray-700">Social Media</label>
            </FloatLabel>
        </div>
    </div>
</template>

<script setup>
import { socialMediaService } from '../service/socialMediaService.js';
import { ref, onMounted } from 'vue';
import Button from 'primevue/button';
import { useToast } from 'primevue/usetoast';
import Toast from 'primevue/toast';

import FloatLabel from 'primevue/floatlabel';
import InputText from 'primevue/inputtext';

const inputs = ref([]);
const site = ref([]);
const toast = useToast();

// Method to add a new input field
const addInputField = () => {
    console.log('Add Input Field', inputs.value);
    if (inputs.value === null) {
        toast.add({
            severity: 'error',
            summary: 'Errore',
            detail: 'Inserire un URL valido',
            life: 3000,
        });
        return;
    }
    inputs.value.push({ value: '' }); // Add an empty object for each new input
};

onMounted(() => {
    socialMediaService.getSocialMedia().then((data) => (site.value = data));
    inputs.value.push({ value: '' }); // Add an empty object for each new input
});

function isValidUrl(url) {
    const urlPattern =
        /^(https?:\/\/)?(www\.)?(facebook\.com|twitter\.com|x\.com|bsky\.app|instagram\.com|linkedin\.com|tiktok\.com|youtube\.com|pinterest\.com)\/[A-Za-z0-9_.-]+\/?$/;
    const test = urlPattern.test(url);
    console.log('Test: ' + test);
    if (test) {
        addInputField();
    } else {
        toast.add({
            severity: 'error',
            summary: 'Errore',
            detail: 'Inserire un URL valido',
            life: 3000,
        });
    }
}
</script>

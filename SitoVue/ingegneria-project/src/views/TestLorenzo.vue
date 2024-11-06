<template>
<<<<<<< Updated upstream
    <div>
      <ImageUploader :storeInstance="profiloStoreInstance.profilo.immagineSalvata" :multi="false" />
=======
    <!-- Button to add a new input field -->
    <Button label="Add Input" icon="pi pi-plus" @click="addInputField" />
    <div class="form-container flex flex-col">

      <!-- Dynamically added text inputs -->
      <div  v-for="(input, index) in inputs" :key="index" class="p-field w-[100%] py-2 px-2 p-grid flex gap-2 flex-col">
        
        <FloatLabel variant="on">
             <InputText
                name="name"
                label="label"
                id="id"
                fluid
                v-model="input.value"
                class="w-full"
                @keyup.enter="(isValidUrl(input.value)) , addInputField()"
                />
            <template v-slot:content>
                <div v-if="!check" class="text-red-500 text-sm mt-1">
                    Please enter a valid URL.
                </div>
            </template>
            <label for="social" class="mb-2 block font-bold text-gray-700">Social Media</label>
        </FloatLabel>
        
        
      </div>
>>>>>>> Stashed changes
    </div>
  </template>
  
<script setup>
import {socialMediaService} from '../service/socialMediaService.js';
import { ref,onMounted } from 'vue';
import Button from 'primevue/button';
<<<<<<< Updated upstream
import ImageUploader from '../components/CreaAstaStepper/ImageUploader.vue';
import {useProfiloStore} from '../stores/profiloStore.js'
=======
import FloatLabel from 'primevue/floatlabel';
import InputText from 'primevue/inputtext';
>>>>>>> Stashed changes

const inputs = ref([]);
const site = ref([]);
const check = ref(false);
const selectedSites = ref([]);
const filteredSites = ref('');

<<<<<<< Updated upstream
const toast = useToast();

const showTopLeft = () => {
    toast.add({ severity: 'info', summary: 'Info Message', detail: 'Message Content', group: 'tr', life: 3000 });
=======
// Method to add a new input field
const addInputField = () => {
    if (check.value){
        inputs.value.push({ value: '' }); // Add an empty object for each new input
    }
>>>>>>> Stashed changes
};

onMounted(() => {
    socialMediaService.getSocialMedia().then((data) => (site.value = data));
    inputs.value.push({ value: '' }); // Add an empty object for each new input
});

function isValidUrl(url) {
    try {
        const parsedUrl = new URL(url);
        return site.value.some((socialMedia) => parsedUrl.hostname.includes(socialMedia));
    } catch (e) {
        return false;
    }
}

const search = (event) => {
    setTimeout(() => {
        if (!event.query.trim().length) {
            filteredSites.value = [...site.value];
        } else {
            filteredSites.value = site.value.filter((site) => {
                return site.toLowerCase().startsWith(event.query.toLowerCase());
            });
        }
    }, 250);
}
</script>
<<<<<<< Updated upstream
  
=======


>>>>>>> Stashed changes

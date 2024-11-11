<template>
    <div
        class="fluid flex w-screen flex-row items-center justify-between rounded-lg bg-zinc-200 p-8 shadow-md"
    >
        <form @submit.prevent="handleSubmit"></form>

        <div class="inline">
            <div class="avatar-container">
                <div class="image-container">
                    <img
                        v-if="newImageURL?.value"
                        :src="newImageURL"
                        alt="preview-image"
                        class="preview-image overflow-hidden rounded-full"
                    />
                    <img
                        v-else
                        src="../../assets/img/placeholder/PlaceholderProfile.png"
                        alt="default image"
                        class="preview-image overflow-hidden rounded-full"
                    />
                </div>
                <label for="avatar-input-file">
                    <div class="icon-wrapper">
                        <input
                            type="file"
                            id="avatar-input-file"
                            accept="image/*"
                            @change="handleImageUpload"
                        />
                        <i class="pi pi-camera"></i>
                    </div>
                </label>
            </div>
        </div>

        <div class="fluid w-full">
            <div class="rounded-lg bg-white p-8 shadow-md">
                <h2 class="mb-4 text-2xl font-bold">Inserimento Dati Personali</h2>
                <form>
                    <div class="my-6">
                        <FloatLabel variant="on">
                            <label for="nome" class="mb-2 block font-bold text-gray-700"
                                >Nome</label
                            >
                            <InputText
                                v-model="newNome"
                                id="nome"
                                fluid
                                class="focus:shadow-outline w-full rounded border px-3 py-2 leading-tight text-gray-700 shadow focus:outline-none"
                            />
                        </FloatLabel>
                    </div>
                   
                    <div class="my-6">
                        <FloatLabel variant="on">
                            <label for="password" class="mb-2 block font-bold text-gray-700"
                                >Password</label
                            >
                            <InputText
                                v-model="newPassword"
                                type="password"
                                id="password"
                                fluid
                                class="focus:shadow-outline w-full rounded border px-3 py-2 leading-tight text-gray-700 shadow focus:outline-none"
                            />
                        </FloatLabel>
                    </div>

                    <div class="my-6">
                        <FloatLabel variant="on">
                            <label for="bio" class="mb-2 block font-bold text-gray-700"
                                >Biografia</label
                            >
                            <Textarea
                                rows="4"
                                cols="50"
                                type="bio"
                                v-model="newbio"
                                id="bio"
                                class="textarea focus:shadow-outline w-full rounded border px-3 py-2 leading-tight text-gray-700 shadow focus:outline-none"
                            />
                        </FloatLabel>
                    </div>
                    <div class="flex flex-row pb-2">
                      <Toast position="center" group="c" />
                      <div>
                        <Button label="Add Input" icon="pi pi-plus" @click="addInputField" />
                      </div>
                    <!-- Button to add a new input field -->
                      <div class="form-container flex flex-col">
                          <!-- Dynamically added text inputs -->
                          <div
                              v-for="(input, index) in inputs"
                              :key="index"
                              class="p-field p-grid flex w-[100%] flex-col gap-2 px-2 pb-2"
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
                                  <label for="social" class="mb-2 block font-bold text-gray-700"
                                      >Social Media</label
                                  >
                              </FloatLabel>
                          </div>
                      </div>
                    </div>
                    <div class="flex items-center justify-between">
                        <Button
                            @click="handleSubmit"
                            class="rounded bg-primario-500 p-2 font-bold text-white hover:bg-primario-600"
                            >Invia Dati</Button
                        >
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<script setup>
import Button from 'primevue/button';
import InputText from 'primevue/inputtext';
import Textarea from 'primevue/textarea';
import { ref, onMounted } from 'vue';
import FloatLabel from 'primevue/floatlabel';
import { useProfiloStore } from '../../stores/profiloStore.js';
import { modificaProfiloPublico } from '../../service/profiloService';
import { socialMediaService } from '../../service/socialMediaService';
import { useToast } from 'primevue/usetoast';
import Toast from 'primevue/toast';

const profiloStoreInstance = useProfiloStore();

const newNome = ref(profiloStoreInstance.profilo.nome);
const newbio = ref(profiloStoreInstance.profilo.bio);
const newPassword = ref(profiloStoreInstance.profilo.password);
const newImageURL = ref(null);
const inputs = ref([]);
const site = ref([]);
const toast = useToast();

function handleImageUpload(event) {
    const file = event.target.files[0];

    if (file && file.type.startsWith('image/')) {
        // Create a blob URL for the image preview
        newImageURL.value = URL.createObjectURL(file);

        // Convert image to base64 URL (for storage in a database)
        const reader = new FileReader();
        reader.onload = () => {
            newImageURL.value = reader.result; // Use reader.result to save to the database
        };
        reader.readAsDataURL(file);
    } else {
        alert('Please upload a valid image file.');
    }
}

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

function handleSubmit() {
    function ModificaProfilo() {
        console.log('Modifica Profilo in corso');
        console.log('IMG VALUE ' + newImageURL.value);
        modificaProfiloPublico(
            newNome,
            newCognome,
            newIndirizzo,
            newBio,
            newSitiSocial,
            newImageURL.value,
            nomeImage
        )
            .then((response) => {
                console.log('Modifica avvenuta con successo!!!: ' + response);
            })
            .catch((error) => {
                console.error('Errore durante la modifica del profilo: ', error);
            });
    }
}

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

<style scoped>
.avatar-container {
    position: relative;
    width: fit-content;
}

.image-container {
    height: 200px;
    width: 200px;
    z-index: 1;
    border-radius: 50%;
    background-color: #fff;
    background-position: center;
    background-size: cover;
    box-shadow: 0 10px 20px 20px #e879f91a;
    overflow: hidden;
}

.icon-wrapper {
    position: absolute;
    height: 40px;
    width: 40px;
    padding: 0.35rem;
    background-color: #e879f9;
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    right: 0;
    top: 75%;
    z-index: 2;
    cursor: pointer;

    &:hover {
        transition: all 0.25s;
        background-color: darken(#e879f9, 15%);
    }

    .icon {
        color: white;
    }

    label {
        cursor: pointer;
        display: flex;
        align-items: center;
        justify-content: center;
    }

    input[type='file'] {
        display: none;
    }
}

.preview-image {
    max-width: 100%;
    max-height: 300px;
    margin-top: 10px;
}
.textarea {
    resize: none;
}
</style>

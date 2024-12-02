<template>
    <div class="flex flex-col items-center justify-center align-middle">
        <form @submit.prevent="handleSubmit" class="mb-5 w-screen px-3">
            <div
                class="fluid mb-5 flex w-[100%] flex-col items-center justify-between gap-5 rounded-lg bg-zinc-200 p-8 shadow-md lg:flex-row lg:items-start lg:gap-0"
            >
                <div class="inline lg:mr-20">
                    <div class="avatar-container">
                        <div class="image-container">
                            <img
                                v-if="newImageURL"
                                :src="newImageURL"
                                alt="Profile"
                                class="preview overflow-hidden rounded-full"
                            />
                            <img
                                v-else
                                src="../../assets/img/placeholder/PlaceholderProfile.png"
                                alt="Default"
                                class="placeholder overflow-hidden rounded-full p-4"
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

                        <div class="my-3">
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
                        <div class="my-3">
                            <FloatLabel variant="on">
                                <label for="cognome" class="mb-2 block font-bold text-gray-700"
                                    >Cognome</label
                                >
                                <InputText
                                    v-model="newSurname"
                                    id="cognome"
                                    fluid
                                    class="focus:shadow-outline w-full rounded border px-3 py-2 leading-tight text-gray-700 shadow focus:outline-none"
                                />
                            </FloatLabel>
                        </div>

                        <div class="my-3">
                            <FloatLabel variant="on">
                                <label for="bio" class="mb-2 block font-bold text-gray-700"
                                    >Biografia</label
                                >
                                <Textarea
                                    rows="10"
                                    cols="50"
                                    v-model="newBio"
                                    id="bio"
                                    class="textarea focus:shadow-outline w-full rounded border px-3 py-2 leading-tight text-gray-700 shadow focus:outline-none"
                                />
                            </FloatLabel>
                        </div>
                        <div class="my-3">
                            <FloatLabel variant="on">
                                <label for="adress" class="mb-2 block font-bold text-gray-700"
                                    >Area Geografica</label
                                >
                                <InputText
                                    v-model="newAddress"
                                    type="text"
                                    id="adress"
                                    fluid
                                    class="focus:shadow-outline w-full rounded border px-3 py-2 leading-tight text-gray-700 shadow focus:outline-none"
                                />
                            </FloatLabel>
                        </div>
                        <div class="flex flex-row pb-2">
                            <Toast />
                            <div class="flex flex-col gap-2">
                                <Button icon="pi pi-plus" @click="addInputField" />
                            </div>
                            <!-- Button to add a new input field -->
                            <div class="flex w-[100%] flex-col">
                                <!-- Dynamically added text inputs -->
                                <div
                                    v-for="(input, index) in newSitiSocialArray"
                                    :key="index"
                                    class="p-field p-grid flex w-[100%] flex-col gap-2 pb-2 pl-2"
                                >
                                    <InputGroup>
                                        <FloatLabel variant="on">
                                            <InputText
                                                name="name"
                                                label="label"
                                                type="url"
                                                id="id"
                                                fluid
                                                v-model="input.value"
                                                class="w-full"
                                                @keyup.enter="addInputField"
                                                @keyup.delete="removeInputField"
                                            />
                                            <label
                                                for="social"
                                                class="mb-2 block font-bold text-gray-700"
                                                >Social Media</label
                                            >
                                        </FloatLabel>
                                        <Button
                                            v-if="isRemoveButtonVisible"
                                            icon="pi pi-trash"
                                            @click="removeInputFieldIndex(index)"
                                        />
                                    </InputGroup>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <Button
                size="large"
                @click="handleSubmit"
                class="w-[50%] rounded bg-primario-500 font-bold text-white hover:bg-primario-600"
                >Invia Dati</Button
            >
        </form>
    </div>
</template>

<script setup>
import Button from 'primevue/button';
import InputGroup from 'primevue/inputgroup';
import InputText from 'primevue/inputtext';
import Textarea from 'primevue/textarea';
import { ref, onMounted, computed } from 'vue';
import FloatLabel from 'primevue/floatlabel';
import { useProfiloStore } from '../../stores/profiloStore.js';
import { modificaProfiloPublico } from '../../service/profiloService';
import { useToast } from 'primevue/usetoast';
import Toast from 'primevue/toast';
import { useRoute, useRouter } from 'vue-router';

const profiloStoreInstance = useProfiloStore();

const newNome = ref(profiloStoreInstance.profilo.nome);
const newSurname = ref(profiloStoreInstance.profilo.cognome);
const newBio = ref(profiloStoreInstance.profilo.bio);
const newAddress = ref(profiloStoreInstance.profilo.area_geografica);
const newImageName = ref(profiloStoreInstance.profilo.nomeImmagine);
const newImageURL = ref(profiloStoreInstance.profilo.imageURL);
const newSitiSocialArray = ref([]);

const toast = useToast();
const router = useRouter();

function handleImageUpload(event) {
    const file = event.target.files[0];

    if (file && file.type.startsWith('image/')) {
        // Create a blob URL for the image preview
        newImageURL.value = URL.createObjectURL(file);

        // Convert image to base64 URL (for storage in a database)
        const reader = new FileReader();
        reader.onload = () => {
            newImageURL.value = reader.result; // Use reader.result to save to the database
            newImageName.value = file.name;
        };
        reader.readAsDataURL(file);
    } else {
        alert('Please upload a valid image file.');
    }
}

// Method to add a new input field
const addInputField = () => {
    console.log(
        'Add Input Field: ',
        newSitiSocialArray.value[newSitiSocialArray.value.length - 1].value === ''
    );
    const lastValue = newSitiSocialArray.value[newSitiSocialArray.value.length - 1].value;
    if (
        lastValue === null ||
        !isValidUrl(newSitiSocialArray.value[newSitiSocialArray.value.length - 1].value)
    ) {
        console.log('URL non valido');
        toast.add({
            severity: 'error',
            summary: 'Errore',
            detail: 'Inserire un URL ad un profilo social media valido',
            life: 3000,
        });
        return;
    }
    newSitiSocialArray.value.push({ value: '' }); // Add an empty object for each new input
};

//computed value that returns true if the newSitiSocialArray.value.length is 2 or higher
const isRemoveButtonVisible = computed(() => newSitiSocialArray.value.length > 1);

const removeInputField = () => {
    const lastValue = newSitiSocialArray.value[newSitiSocialArray.value.length - 1].value;
    const newLenght = newSitiSocialArray.value.length - 1;
    if (isRemoveButtonVisible && lastValue === '' && newLenght > 0) {
        console.log('Remove Input Field');
        newSitiSocialArray.value.pop();
    }
};

function removeInputFieldIndex(index) {
    const lastValue = newSitiSocialArray.value[newSitiSocialArray.value.length - 1].value;
    const newLenght = newSitiSocialArray.value.length - 1;
    if (isRemoveButtonVisible && newLenght > 0) {
        console.log('Remove Input Field');
        //remove the index element from the array
        newSitiSocialArray.value.splice(index, 1);
    }
}

function handleSubmit() {
    console.log('Modifica Profilo in corso');
    profiloStoreInstance.updateProfilo({
        nome: newNome.value,
        cognome: newSurname.value,
        bio: newBio.value,
        area_geografica: newAddress.value,
        imageURL: newImageURL?.value,
        siti_social: newSitiSocialArray.value,
    });
    console.log('Profilo Store: ', profiloStoreInstance.profilo);
    console.log('Profilo object: ', {
        nome: newNome.value,
        cognome: newSurname.value,
        bio: newBio.value,
        area_geografica: newAddress.value,
        imageURL: newImageURL?.value,
        siti_social: newSitiSocialArray.value,
    });
    modificaProfiloPublico(
        newNome.value,
        newSurname.value,
        newAddress.value,
        newBio.value,
        newSitiSocialArray.value,
        newImageURL?.value,
        newImageName?.value
    )
        .then((response) => {
            console.log('Modifica avvenuta con successo!!!: ' + response);
            //add a router to redirect to the profile page
            router.push({ name: 'profilo' });
        })
        .catch((error) => {
            console.error('Errore durante la modifica del profilo: ', error);
        });
}

onMounted(() => {
    console.log('Profilo Store: ', profiloStoreInstance.profilo.siti_social);
    if (profiloStoreInstance.profilo.siti_social.length === 0) {
        newSitiSocialArray.value.push({ value: '' });
    } else {
        for (let i = 0; i < profiloStoreInstance.profilo.siti_social.length; i++) {
            newSitiSocialArray.value.push({
                value: profiloStoreInstance.profilo.siti_social[i].value,
            });
        }
    }
});

function isValidUrl(url) {
    const urlPattern =
        /^(https?:\/\/)?(www\.)?(facebook\.com|twitter\.com|x\.com|bsky\.app|instagram\.com|linkedin\.com|tiktok\.com|youtube\.com|pinterest\.com)\/[A-Za-z0-9_.-]+\/?$/;
    const test = urlPattern.test(url);
    console.log('Test: ' + test);
    return test;
}
</script>

<style scoped>
.avatar-container {
    position: relative;
    width: fit-content;
}

.image-container {
    height: 300px;
    width: 300px;
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
    height: 50px;
    width: 50px;
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

.preview {
    min-height: 300px;
    max-width: 100%;
    max-height: 1000px;
    align-content: center;
    background-color: #e879f9;
}
.placeholder {
    max-width: 100%;
    max-height: 1000px;
    align-self: center;
}
.textarea {
    resize: none;
}
</style>

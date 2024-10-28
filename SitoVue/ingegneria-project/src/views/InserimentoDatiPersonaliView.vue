<template>
    <div>
        <div class="flex justify-center items-center h-screen w-[100%]">
            <div class="bg-zinc-200 rounded-lg shadow-md p-8 w-full max-w-md">
                <h2 class="text-2xl font-bold mb-4">Inserimento Dati Personali</h2>
                <form>
                    <div class="my-6">
                        <FloatLabel>
                            <label for="nome" class="block text-gray-700 font-bold mb-2">Nome</label>
                            <InputText v-model="newNome" id="nome" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" />
                        </FloatLabel>
                    </div>
                    <div class="my-6">
                        <FloatLabel>
                            <label for="email" class="block text-gray-700 font-bold mb-2">Email</label>
                            <InputText type="email" v-model="newEmail" id="email" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" />
                        </FloatLabel>
                    </div>
                    <div class="my-6">
                        <FloatLabel>
                            <label for="password" class="block text-gray-700 font-bold mb-2">Password</label>
                            <InputText v-model="newPassword" type="password" id="password" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" />
                        </FloatLabel>
                    </div>
                    <div v-if(newImg.value.length) class="my-6">
                        <ImageUploader :storeInstance="newImg" :multi="false" />
                    </div>
                    <div class="flex items-center justify-between">
                        <Button @click="saveData" class="bg-primario-500 rounded p-2 hover:bg-primario-600 text-white font-bold">Invia
                            Dati</Button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<script setup>
import Button from 'primevue/button';
import InputText from 'primevue/inputtext';
import { ref } from 'vue';
import FloatLabel from 'primevue/floatlabel';

import ImageUploader from '../components/CreaAstaStepper/ImageUploader.vue';
import {useProfiloStore} from '../stores/profiloStore.js'

const profiloStoreInstance = useProfiloStore();

const newNome = ref(profiloStoreInstance.profilo.nome);
const newEmail = ref(profiloStoreInstance.profilo.email);
const newPassword = ref(profiloStoreInstance.profilo.password)
const newImg = ref(profiloStoreInstance.profilo.immagineSalvata)

</script>

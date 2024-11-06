<template>
  <div class="flex flex-row justify-between items-center fluid w-screen bg-zinc-200 rounded-lg shadow-md p-8">
    
    <form @submit.prevent="handleSubmit">

<<<<<<< Updated upstream
    <div class="inline">
    <div class="avatar-container">
    <div class="image-container ">
      <img v-if="newImageURL?.value" :src="newImageURL" alt="preview-image" class="preview-image overflow-hidden rounded-full"/>
      <img v-else src="../../assets/img/placeholder/PlaceholderProfile.png" alt="default image" class="preview-image overflow-hidden rounded-full"/>
    </div> 
    <label for="avatar-input-file">
      <div class="icon-wrapper">
        <input type="file" id="avatar-input-file" accept="image/*" @change="handleImageUpload"/>
        <i class="pi pi-camera"></i>
      </div>
    </label>
    </div>
    </div>


    <div class="bg-white rounded-lg shadow-md p-8">
      <h2 class="text-2xl font-bold mb-4">Inserimento Dati Personali</h2>
      <form>
        <div class="my-6">
        <FloatLabel variant="on">
          <label for="nome" class="block text-gray-700 font-bold mb-2">Nome</label>
          <InputText v-model="newNome" id="nome" class=" shadow border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" />
        </FloatLabel>
=======
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
                                <label for="bio" class="mb-2 block font-bold text-gray-700"
                                    >Biografia</label
                                >
                                <Textarea
                                    fluid
                                    rows="4"
                                    cols="50"
                                    type="bio"
                                    v-model="newbio"
                                    id="bio"
                                    class="textarea focus:shadow-outline w-full rounded border px-3 py-2 leading-tight text-gray-700 shadow focus:outline-none"
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
                                <label for="social" class="mb-2 block font-bold text-gray-700">Social Media</label>
                                <AutoComplete
                                    v-model="selectedSites"
                                    :suggestions="filteredSites"
                                    completeMethod="search"
                                    field="name"
                                    multiple
                                    class="w-full"
                                />
                            </FloatLabel>
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
>>>>>>> Stashed changes
        </div>
        <div class="my-6">
        <FloatLabel variant="on">
          <label for="bio" class="block text-gray-700 font-bold mb-2">Biografia</label>
          <Textarea rows="4" cols="50" type="bio" v-model="newbio" id="bio" class="textarea shadow border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" />
        </FloatLabel>
        </div>
        <div class="my-6">
        <FloatLabel variant="on">
          <label for="password" class="block text-gray-700 font-bold mb-2">Password</label>
          <InputText v-model="newPassword" type="password" id="password" class="shadow border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" />
        </FloatLabel>
        </div>
        <div class="flex items-center justify-between">
        <Button @click="handleSubmit" class="bg-primario-500 rounded p-2 hover:bg-primario-600 text-white font-bold">Invia
          Dati</Button>
        </div>
      </form>
    </div>
    </form>
  </div>
</template>

<script setup>
import Button from 'primevue/button';
import InputText from 'primevue/inputtext';
import Textarea from 'primevue/textarea';
import { ref } from 'vue';
import FloatLabel from 'primevue/floatlabel';
import {useProfiloStore} from '../../stores/profiloStore.js'
import { modificaProfiloPublico } from '../../service/profiloService';

const profiloStoreInstance = useProfiloStore();


const newNome = ref(profiloStoreInstance.profilo.nome);
const newbio = ref(profiloStoreInstance.profilo.bio);
const newPassword = ref(profiloStoreInstance.profilo.password);
<<<<<<< Updated upstream

const newImageURL = ref(null);

=======
const newImageURL = ref(null);



>>>>>>> Stashed changes
function handleImageUpload(event) {
  const file = event.target.files[0];
  
  if (file && file.type.startsWith("image/")) {
  // Create a blob URL for the image preview
  newImageURL.value = URL.createObjectURL(file);
  
  // Convert image to base64 URL (for storage in a database)
  const reader = new FileReader();
  reader.onload = () => {
  newImageURL.value = reader.result; // Use reader.result to save to the database
  };
  reader.readAsDataURL(file);
  } else {
  alert("Please upload a valid image file.");
  }
  
}

function handleSubmit() {
  function ModificaProfilo() {
  console.log("Modifica Profilo in corso");
  console.log("IMG VALUE " + newImageURL.value);
  modificaProfiloPublico(newNome, newCognome, newIndirizzo, newBio, newSitiSocial, newImageURL.value,nomeImage)
  .then(response => {
  console.log("Modifica avvenuta con successo!!!: " + response);
  })
  .catch(error => {
  console.error("Errore durante la modifica del profilo: ", error);
  });
}
}

<<<<<<< Updated upstream
=======
const site = ref("");
const selectedSites = ref("");
const filteredSites = ref("");

onMounted(() => {
    socialMediaService.getSocialMedia().then((data) => (site.value = data));
});

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

>>>>>>> Stashed changes
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
  padding: .35rem;
  background-color:#e879f9;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  right: 0;
  top: 75%;
  z-index: 2;
  cursor: pointer;

  &:hover {
    transition: all .25s;
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
.textarea{
    resize: none;
}
</style>
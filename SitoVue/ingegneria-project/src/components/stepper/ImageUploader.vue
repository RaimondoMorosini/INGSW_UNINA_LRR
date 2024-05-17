<template>
  <div>
      <label for="file-upload" class="custom-file-upload">
      Custom Upload
  </label>
      <input id="file-upload" type="file" accept="image/*" multiple @change="handleChange">
      <div v-for="image in store.asta.immaginiSalvate">
      <img :src="image" alt="immagine caricata" class="w-24 h-24">
      </div>
      
  </div>

  
</template>

<script setup>
import {useAstaStore} from '../../stores/astaStore'
const store = useAstaStore()

const handleChange = (event) => {
  let test = [];
  for (let image of event.target.files) {
      test.push (URL.createObjectURL(image) );
  }
  console.log(test)
  store.asta.immaginiSalvate = test;
  console.log("in store",store.asta.immaginiSalvate)
}
</script>

<style scoped>
input[type="file"] {
display: none;
}

.custom-file-upload {
  background-color: #cc85f5;
  padding: 10px 20px;
  color: white;
  border-radius: 5px;
  font-size: 1.1rem;
  font-weight: bold;
  width: 50%;
  margin: 10px;
cursor: pointer;
}
.custom-file-upload:hover {
  background-color: #7c3aed;
}
</style>

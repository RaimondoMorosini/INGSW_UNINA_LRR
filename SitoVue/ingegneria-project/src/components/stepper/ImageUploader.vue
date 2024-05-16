<template>
<div id="app">
  <input type="file" @change="showFilePreview" />
  <div id="preview">
    <img v-if="image" :src="image" />
  </div>
</div>
</template>

<script setup>
import {ref} from 'vue'

let image =ref(null)
function showFilePreview(e) {
      let files = e.target.files || e.dataTransfer.files;
      if (!files.length) return;
      createImage(files[0]);
    }

function createImage(file) {
      let reader = new FileReader();
      let vm = this;
      reader.onload = (e) => {
        vm.image = e.target.result;
      };
      reader.readAsDataURL(file);
    }
</script>
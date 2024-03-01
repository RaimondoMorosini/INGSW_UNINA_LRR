<template>
    <div class="relative items-center sm:flex-col">
        <button @click="isOpen = !isOpen"  class="mt-1 block px-2 py-1 font-semibold rounded hover:bg-indigo-500">Categorie</button>
        <button v-if="isOpen" @click="isOpen = false" tabindex="-1" class="fixed bg-black inset-0 h-full w-full opacity-40"></button>
        <div v-if="isOpen" class="absolute left-0 mt-2 py-2 w-48 rounded-lg shadow-xl bg-white">
          <router-link to="/categorie/elettronica"><p class="mt-1 block px-2 py-1 font-semibold rounded hover:bg-indigo-500">Elettronica</p></router-link>
          <router-link to="/categorie/perlacasa"><p class="mt-1 block px-2 py-1 font-semibold rounded hover:bg-indigo-500">Per la casa</p></router-link>
          <router-link to="/categorie/abbigliamento"><p class="mt-1 block px-2 py-1 font-semibold rounded hover:bg-indigo-500">Abbigliamento</p></router-link>
          <router-link to="/categorie/sports"><p class="mt-1 block px-2 py-1 font-semibold rounded hover:bg-indigo-500">Sports</p></router-link>
          <router-link to="/categorie/hobby"><p class="mt-1 block px-2 py-1 font-semibold rounded hover:bg-indigo-500">Hobby</p></router-link>
        </div>
    </div>
</template>

<script setup>
import { ref } from "vue";
isOpen = ref (false); 
function created() {
    const handleEscape = e => {
      if (e.key === "Esc" || e.key === "Escape") {
        this.isOpen = false
      }
    }
    document.addEventListener("keydown", handleEscape)
  }
</script>
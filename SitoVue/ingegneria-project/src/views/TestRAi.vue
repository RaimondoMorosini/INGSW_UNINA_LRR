<script setup>
import icona from '../components/Icone/icona.vue';

</script>

<template>
  <div class="test-rai">
    <h1>TestRAi</h1>
    <p>TestRAi</p>
<icona class="prova" />
  </div>
    icona:

    <menuItem :IconaSx="icona" testo2="Start"><input type="text"></menuItem>

  <menuItem :IconaSx="icona" ><icona /></menuItem>


  <div class="max-w-md mx-auto bg-white rounded-xl shadow-md overflow-hidden md:max-w-2xl">
    <div class="md:flex">
      <div class="md:flex-shrink-0">
        <img class="h-48 w-full object-cover md:h-full md:w-48" src="../../../../Immagini prodotti/hobbista.jpg" alt="Descrizione Immagine">
      </div>
      <div class="p-8">
        <div class="uppercase tracking-wide text-sm text-indigo-500 font-semibold">Categoria</div>
        <a href="#" class="block mt-1 text-lg leading-tight font-medium text-black hover:underline">Titolo dell'articolo</a>
        <p class="mt-2 text-gray-500">Questo è un breve riepilogo dell'articolo. Tailwind CSS rende incredibilmente semplice stilizzare i tuoi componenti Vue.</p>
        <p class=""></p>
      </div>
    </div>
  </div>

<div class="text-white bg-black">
test
</div>

</template>




<style>
.prova {
    background-color: green;
fill:orange !important;
}
</style>
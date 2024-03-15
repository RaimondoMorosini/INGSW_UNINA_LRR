<template>
  <RouterLink to="/">
    <div
      class="mt-1 flex content-evenly rounded bg-inherit px-3 font-bold hover:bg-primario-100 hover:ring-2 hover:ring-slate-300/80 md:items-center md:px-0 md:pb-2"
    >
      <img src="../../assets/Icon/LogoSVGSito.svg" alt="Logo" class="h-1col w-1col" />
      <div class="flex pt-[1.1rem] md:pt-0">
        <h1 class="text-2xl text-slate-900">Deals24</h1>
        <h1 class="pt-[0.8rem] text-xs text-slate-900">RRL</h1>
      </div>
    </div>
  </RouterLink>
</template>

<script setup>
import { RouterLink } from 'vue-router';
</script>

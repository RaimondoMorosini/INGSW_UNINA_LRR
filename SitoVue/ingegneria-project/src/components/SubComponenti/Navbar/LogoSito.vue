<template>
    <RouterLink to="/">
    <div class="font-bold content-evenly  bg-inherit hover:bg-primario-300 rounded mt-1 flex px-5 md:items-center md:px-0 md:pb-2">
        
        <img src="../../../assets/Icon/LogoSVGSito.svg" alt="Logo" class="w-1col  h-1col" />
        <div class="flex pt-[1.1rem] md:pt-0">
        <h1 class="text-2xl text-slate-900">Deals24</h1>  
        <h1 class="text-xs pt-[0.8rem] text-slate-900">RRL</h1>
        </div>
    </div>
    </RouterLink>
</template>

<script setup>
    import { RouterLink } from 'vue-router';
    
</script>
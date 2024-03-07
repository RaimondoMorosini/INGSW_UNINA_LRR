<template>
    <RouterLink to="/">
    <div class="font-bold content-evenly  bg-inherit hover:bg-primario-500 rounded mt-1 pr-5 flex px-3 py-2 md:items-center md:px-0 md:pb-1">
        <!-- <logoIcon/> -->
        <img src="../assets/Icon/LogoSVGSito.svg" alt="Logo" class="w-1col  h-1col" />
        <h1 class="text-2xl text-slate-900">Deals24</h1>  
        <h1 class="text-xs pt-[0.8rem] text-slate-900">RRL</h1>
     
    </div>
    </RouterLink>
</template>

<script setup>
    import { RouterLink } from 'vue-router';
    import logoIcon from "./Icone/logo.vue";
</script>
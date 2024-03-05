<template>
    <RouterLink to="/">
    <div class="font-bold content-evenly w-40 bg-inherit hover:bg-primario-500 rounded mt-1 flex px-2 pl-3 py-1 pt-2 md:items-center md:px-0 md:pb-1">
        <logoIcon/>
        <h1 class="text-2xl text-slate-900">Deals24</h1>  
        <h1 class="text-xs pt-[0.8rem] text-slate-900">RRL</h1>
     
    </div>
    </RouterLink>
</template>

<script setup>
    import { RouterLink } from 'vue-router';
    import logoIcon from "./Icone/logo.vue";
</script>
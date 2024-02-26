<template>
    <header class="sticky top-0 shadow-lg min-w-full z-50">
        <nav class="w-fill bg-indigo-300 md:flex flex-col sm:flex-row justify-between gap-5 py-1">
            
            <RouterLink to="/">
                <div class="font-bold content-evenly w-40 bg-indigo-300 hover:bg-indigo-500 rounded mt-1 flex px-2 pl-3 py-1 pt-2 md:items-center md:px-0 md:pb-1">
                    <img src="../assets/Logo sito web [Optimized].svg" alt="">
                    <h1 class="text-2xl ">Deals24</h1>  
                    <h1 class="text-xs pt-4 ">RRL</h1>
                </div>
            </RouterLink>
           
            
            


            <span @click="MenuOpen()" class="absolute md:hidden right-6 top-1.5 hover:bg-indigo-500 rounded text-4xl">
                <i :class="[open ? 'bi bi-x ' : 'bi bi-filter-left' ]"></i>    
            </span>

            <div>
            <ul class="px-6 pt-2 md:flex md:items-center md:px-0 md:pb-1 md:static absolute bg-indigo-300 md:w-auto w-full top-14 duration-500 ease-in"
            :class="[open ? 'left-0' : 'left-[-100%]']">
               <li class="md:mx-4 md:my-0 my-6"> 
                    <RouterLink :to="{name:'about'}">
                    <p class="md:mx-4 mt-1 block px-2 py-1 font-semibold rounded hover:bg-indigo-500">Ciao {{ profilo }}</p>
                    </RouterLink> 
                </li>
                <li class="md:mx-4 md:my-0 my-6">    
                    <RouterLink :to="{name:'about'}">
                    <p class="md:mx-4 mt-1 block px-2 py-1 font-semibold rounded hover:bg-indigo-500">Tue Aste</p>
                    </RouterLink>
                </li>
                <li class="md:mx-4 md:my-0 my-6">
                    <router-link :to="{ name : 'notifiche'}">
                    <p class="md:mx-4 mt-1 block px-2 py-1 font-semibold rounded hover:bg-indigo-500">Notifiche</p>
                    </router-link>
                </li>
                <li class="md:mx-4 md:my-0 my-6">
                    <InvetoryDropDown/>
                </li>
                <li class="md:mx-4 md:my-0 my-6">
                <p class="bg-indigo-400 md:mx-4 mt-1 block px-2 py-1 font-semibold rounded hover:bg-indigo-500">Crea Asta</p>
                </li>
            </ul>
            </div>
        </nav>
    </header>
</template>

<script >
import { RouterLink } from 'vue-router'
import InvetoryDropDown from './InventoryDropDown.vue'
import { ref } from 'vue'
import LogoSito from './LogoSito.vue'

export default {

    setup(){
    let open = ref(false);
    
    function MenuOpen(){
        open.value = !open.value
    };

    return {open,MenuOpen}
    },

   

    props:{
        'profilo':{type:String, default:'Utente'}
    },
    name: 'SiteHeader',
    components: {
        RouterLink,
        InvetoryDropDown
    },
    
    
}
</script>


<template>
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
                    <p class="bg-indigo-400 md:mx-4 mt-1 block px-2 py-1 font-semibold rounded hover:bg-indigo-500">Crea Asta</p>
                    </li>
                </ul>
            </div>
            
            <span @click="MenuOpen()" class="absolute md:hidden right-6 top-1.5 hover:bg-indigo-500 rounded text-4xl">
                <i :class="[open ? 'bi bi-x ' : 'bi bi-filter-left' ]"></i>    
            </span>

</template>

<script setup>
import {ref} from 'vue';
import { RouterLink } from 'vue-router'

const open = ref(false);
function MenuOpen(){
        open.value = !open.value
    };

</script>
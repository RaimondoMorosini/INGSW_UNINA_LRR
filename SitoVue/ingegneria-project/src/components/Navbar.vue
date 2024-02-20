<template>
    <header class="sticky top-0 shadow-lg min-w-full ">
        <nav class="w-fill bg-indigo-300 flex flex-col sm:flex-row  gap-5 py-1 	">
            <div class="px-2 pt-2 sm:flex ">
            <RouterLink to="/">
                <div class="content-evenly flex  hover:bg-slate-500 rounded mt-1 block px-2 py-1">
                    <img src="../assets/Logo sito web [Optimized].svg" alt="">
                    <h1 class="text-2xl">Deals24</h1>  
                    <h2 class="text-xs mx-12px">RRL</h2>  
                </div>
            </RouterLink>
            </div>

            <div>
            <ul class="px-2 pt-2 sm:flex">
                <RouterLink :to="{name:'about'}">
                    <p class=" mt-1 block px-2 py-1 font-semibold rounded hover:bg-indigo-500">Ciao {{ profilo }}</p>
                </RouterLink>
                <RouterLink :to="{name:'about'}">
                    <p class="mt-1 block px-2 py-1 font-semibold rounded hover:bg-indigo-500">Le tue Aste</p>
                </RouterLink>
                <router-link :to="{ name : 'notifiche'}">
                    <p class="mt-1 block px-2 py-1 font-semibold rounded hover:bg-indigo-500">Notifiche</p>
                </router-link>
                <InvetoryDropDown />
                <p class="bg-indigo-400 mt-1 block px-2 py-1 font-semibold rounded hover:bg-indigo-500">Crea Asta</p>
            </ul>
            </div>
        </nav>
    </header>
</template>

<script >
import { RouterLink } from 'vue-router'
import InvetoryDropDown from './InventoryDropDown.vue'


export default {

    props:['profilo'],
    name: 'SiteHeader',
    components: {
        RouterLink,
        InvetoryDropDown
    },
    data() {
        return {
            profilo: "Utente"
            }
    }
}
</script>


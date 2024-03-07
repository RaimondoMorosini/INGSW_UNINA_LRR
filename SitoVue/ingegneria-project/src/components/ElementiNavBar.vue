<template>
      <div>
        <ul class="border-t-2 border-b-2 border-slate-300 bg-primario-100 md:border-transparent px-6 pt-2 md:flex md:items-center md:px-0 md:pb-1 md:static absolute md:bg-inherit md:w-auto w-full top-[85px] duration-500 ease-in"
        :class="[open ? 'left-0 ': 'left-[-100%] ']">
            
            <template v-if="!isAuthenticated">
                <li class="md:mx-4 md:my-0 my-6">
                    <SignupButton class="md:mx-4 mt-1 block px-2 py-1 font-semibold rounded hover:bg-primario-400 w-[100%]"/>
                </li>
                <li class="md:mx-4 md:my-0 my-6">
                    <LoginButton class="md:mx-4 mt-1 block px-2 py-1 font-semibold rounded hover:bg-primario-400 w-[100%] "/>
                </li>    
            </template>

            <template v-if="isAuthenticated">
                <li class="md:mx-4 md:my-0 my-6"> 
                    <RouterLink :to="{name:'profilo'}">
                        <p class="text-center md:mx-4 mt-1 block px-2 py-1 font-semibold rounded hover:bg-primario-400 !sm:w-[100%]">
                                Ciao {{ user.name }}
                            </p>
                        
                    </RouterLink> 
                </li>
                <li class="md:mx-4 md:my-0 my-6">
                    <router-link :to="{ name : 'notifiche'}">
                        <p class="md:mx-4 mt-1 block px-2 py-1 font-semibold rounded hover:bg-primario-400 !sm:w-[100%] text-center">Notifiche</p>
                    </router-link>
                </li>
                <li class="md:mx-4 md:my-0 my-6">    
                    <RouterLink :to="{name:'about'}">
                        <p class="md:mx-4 mt-1 block px-2 py-1 font-semibold rounded itemss-center hover:bg-primario-400 !sm:w-[100%] text-center">Tue Aste</p>
                    </RouterLink>
                </li>
                <li class="md:mx-4 md:my-0 my-6">
                <LogoutButton class="md:mx-4 mt-1 block px-2 py-1 font-semibold rounded hover:bg-primario-400 w-[100%] text-center"/>
                </li>
                <li class="md:mx-4 md:my-0 my-6">
                    <p class="bg-primario-400 md:mx-4 mt-1 block px-2 py-1 font-semibold rounded hover:bg-primario-400 !sm:w-[100%] text-center">Crea Asta</p>
                </li>
                
            </template>

            </ul>
            
    
            </div>
            
            <span @click="MenuOpen()" class="absolute md:hidden right-6 top-5 hover:bg-primario-400 rounded text-4xl">
                <i :class="[open ? 'bi bi-x ' : 'bi bi-filter-left' ]"></i>    
            </span>

</template>

<script setup>
import {ref} from 'vue';
import LoginButton from "@/components/buttons/LoginButtom.vue";
import LogoutButton from "@/components/buttons/LogOut.vue";
import SignupButton from "@/components/buttons/SignUp.vue";
import { useAuth0 } from "@auth0/auth0-vue";

const { isAuthenticated } = useAuth0();

const { user } = useAuth0();

const open = ref(false);
function MenuOpen(){
        open.value = !open.value
    };

const profilo = ref('Utente');

</script>
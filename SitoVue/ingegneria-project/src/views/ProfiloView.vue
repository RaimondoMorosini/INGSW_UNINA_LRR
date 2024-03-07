<template>
  
    <div  class="content-layout">
      <div class="content__body">
        <div class="profile-grid flex">
          <div class="min-w-3col h-[1200px] border-r-2  border-slate-300 flex-row">
            <ul class="border-b-2 border-slate-300 text-2xl">
              <li v-for="(pagina,index) in pagine" v-bind:key="index" class="hover:bg-primario-300 rounded py-2 px-2">
                {{ pagina }}
              </li>
            </ul>
            <p class="text-2xl">Settings</p>
          </div>

          <div class="Componente-Inserito">
            <InfoProfilo/>
            
          </div>
          
        </div>
      </div>
    </div>

</template>

<script setup>
import { useAuth0 } from "@auth0/auth0-vue";
import { onMounted } from "vue";
import InfoProfilo from "@/components/SubComponenti/Profilo/InformazioniProfilo.vue";
const pagine = ["Account", "Messaggi", "Tue Aste in Corso"];
const { user } = useAuth0();

const code = user ? JSON.stringify(user.value, null, 2) : "";

</script>
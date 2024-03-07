<template>
  
    <div  class="content-layout">
      <div class="content__body">
        < class="profile-grid flex">
          <div class="min-w-3colflex-row">
            <ul class="border-b-2 border-slate-300 text-2xl">
              <li v-for="(pagina,index) in pagine" v-bind:key="index" class="hover:bg-primario-300 rounded py-2 px-2">
                {{ pagina }}
              </li>
            </ul>
            <p class="text-2xl rounded py-2 px-2 hover:bg-primario-300">Settings</p>
          </div>
          
          <div class="Componente-Inserito w-full px-3 py-2 gap-2 flex flex-col">
            <InfoProfilo/>
            
          </div>
          
        
      </div>
    </div>

</template>

<script setup>
import InfoProfilo from "@/components/SubComponenti/Profilo/InformazioniProfilo.vue";
const pagine = ["Account", "Messaggi", "Tue Aste in Corso"];

</script>
<template>
  
    <div  class="content-layout">
      <div class="content__body">
        <div class="profile-grid flex">
          <aside class="md:max-w-3col h-screen border-r-2  bg-primario-100 border-slate-300 md:static absolute md:bg-inherit md:w-auto top-[85px] duration-500 ease-in"
          :class="[open ? 'left-0 ': 'left-[-100%] ']">
            
          <ul class="border-b-2 border-slate-300 text-2xl">
              <li v-for="(pagina,index) in pagine" v-bind:key="index" class="hover:bg-primario-300 rounded py-2 px-2">
                {{ pagina }}
              </li>
            </ul>
            <p class="text-2xl rounded py-2 px-2 hover:bg-primario-300">Settings</p>
            <div @click="MenuOpen()" class="md:hidden right-0 top-0 hover:bg-primario-300 rounded text-4xl w-[36px]">
              <i :class="'bi bi-x '"></i>    
            </div>
          </aside>
          <div @click="MenuOpen()" class="md:hidden right-0 top-0 hover:bg-primario-300 rounded text-4xl w-[36px]">
                   
                <i :class=" 'bi bi-filter-left' "></i>  
            </div>
          
          <div class="Componente-Inserito w-full px-3 py-2 gap-2 flex flex-col min-w-4col">
            <InfoProfilo/>
            <div class="Bio bg-slate-100">
              <h1>Bio:</h1>
              <p>
                Lorem, ipsum dolor sit amet consectetur adipisicing elit. Maxime ex delectus obcaecati! Molestiae, dicta in error cupiditate, soluta similique cumque deleniti impedit sapiente ex itaque mollitia amet dolores perspiciatis. Provident?
              </p>
            </div>
            <ul class="bg-slate-100">
              <li v-for="(link,index) in linkUtili" v-bind:key="index" >
                {{ link }}
              </li>
            </ul>
          </div>
          
        </div >
      </div>
    </div>

</template>

<script setup>
import { ref } from "vue";
import InfoProfilo from "@/components/SubComponenti/Profilo/InformazioniProfilo.vue";
const pagine = ["Account", "Messaggi", "Tue Aste in Corso"];
const linkUtili = ["GitHub", "LinkedIn", "Twitter", "Facebook"];
const open = ref(false);
const MenuOpen = () => {
  open.value = !open.value;
};
</script>
<template>
  
  <ul class="flex flex-row justify-between py-2 px-3">
    <li class="flex rounded hover:bg-primario-100 hover:ring-2 hover:ring-slate-300/80 px-2 py-1">
      <span>
        <i class="pi pi-user" style="font-size: 1.5rem"></i>
      </span>
      <router-link to="/profilo" class="hidden pl-1 md:inline ">Informazioni Profilo</router-link>
    </li>
    <li class="flex rounded hover:bg-primario-100 hover:ring-2 hover:ring-slate-300/80 px-2 py-1">
      <span>
        <i class="pi pi-inbox" style="font-size: 1.5rem"></i>
      </span>
      <router-link to="/profilo/notifiche" class="hidden pl-1 md:inline ">Notifiche</router-link>
    </li>
    <li class="flex rounded hover:bg-primario-100 hover:ring-2 hover:ring-slate-300/80 px-2 py-1">
      <span>
        <i class="pi pi-shopping-bag" style="font-size: 1.5rem"></i>
      </span>
      <router-link to="/profilo/astePersonali" class="hidden pl-1 md:inline ">Aste Personali</router-link>
    </li>
    <li class="flex rounded hover:bg-primario-100 hover:ring-2 hover:ring-slate-300/80 px-2 py-1">
      <span>
        <i class="pi pi-cog" style="font-size: 1.5rem"></i>
      </span>
      <router-link to="/profilo/impostazioni" class="hidden pl-1 md:inline ">Impostzioni</router-link>
    </li>

  </ul>
          
  <RouterView/>

</template>

<script setup>
import { RouterView } from "vue-router";

import { useToast } from "primevue/usetoast";

const toast = useToast();





</script>
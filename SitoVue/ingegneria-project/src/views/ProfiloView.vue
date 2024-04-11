<template>
  <ul class="flex flex-row justify-between border-b-2 border-slate-300 px-3 py-2">
    <li class="flex rounded px-2 py-1 hover:bg-primario-100 hover:ring-2 hover:ring-slate-300/80">
      <span>
        <i class="pi pi-user" style="font-size: 1.5rem"></i>
      </span>
      <router-link to="/profilo" class="hidden pl-1 md:inline">Informazioni Profilo</router-link>
    </li>
    <li class="flex rounded px-2 py-1 hover:bg-primario-100 hover:ring-2 hover:ring-slate-300/80">
      <span>
        <i class="pi pi-inbox" style="font-size: 1.5rem"></i>
      </span>
      <router-link to="/profilo/notifiche" class="hidden pl-1 md:inline">Notifiche</router-link>
    </li>
    <li class="flex rounded px-2 py-1 hover:bg-primario-100 hover:ring-2 hover:ring-slate-300/80">
      <span>
        <i class="pi pi-shopping-bag" style="font-size: 1.5rem"></i>
      </span>
      <router-link to="/profilo/astePersonali" class="hidden pl-1 md:inline"
        >Aste Personali</router-link
      >
    </li>
    <li class="flex rounded px-2 py-1 hover:bg-primario-100 hover:ring-2 hover:ring-slate-300/80">
      <span>
        <i class="pi pi-cog" style="font-size: 1.5rem"></i>
      </span>
      <router-link to="/profilo/impostazioni" class="hidden pl-1 md:inline"
        >Impostzioni</router-link
      >
    </li>
  </ul>

  <RouterView />
</template>

<script setup>
import { useToast } from 'primevue/usetoast';
import { RouterView } from 'vue-router';

const toast = useToast();
</script>

<template>
    <header class="sticky top-0 shadow-lg min-w-full z-50 min-h-1col">
        <nav class="w-fill bg-gradient-to-tl from-danger-300/90 via-primario-100 to-secondario-300 flex justify-between gap-5 py-1">
            
            <Logo class="min-w-56 pl-3 flex-wrap"/>
                   
            <ElementiNavBar/>
        </nav>
    </header>
</template>

<script setup>
import ElementiNavBar from './Navbar/ElementiNavBar.vue';
import Logo from "./Navbar/LogoSito.vue";
</script>


<style>
.slide-fade-enter-active {
  transition: all 0.3s ease-out;
}

.slide-fade-leave-active {
  transition: all 0.8s cubic-bezier(1, 0.5, 0.8, 1);
}

.slide-fade-enter-from,
.slide-fade-leave-to {
  transform: translateX(20px);
  opacity: 0;
}
</style>
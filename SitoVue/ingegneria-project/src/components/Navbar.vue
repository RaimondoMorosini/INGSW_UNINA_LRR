<template>
    <header class="sticky top-0 shadow-lg min-w-full z-50">
        <nav class="w-fill bg-indigo-300 flex justify-between gap-5 py-1">
            
            <Logo class="min-w-1col pl-3"/>
                   
            <ElementiNavBar/>
        </nav>
    </header>
</template>

<script setup>
import ElementiNavBar from './ElementiNavBar.vue';
import Logo from "./LogoSito.vue";
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
<template>
    <header class="navbar sticky top-0 z-50 min-h-1col min-w-full shadow-lg">
        <nav
            class="w-fill flex justify-between gap-5 bg-gradient-to-tl from-danger-300 via-primario-400 to-secondario-300 py-1"
        >
            <Logo class="min-w-56 py-1 pl-3" />

            <ElementiNavBar />
        </nav>
    </header>
</template>

<script setup>
import ElementiNavBar from './ElementiNavBar.vue';
import Logo from './LogoSito.vue';
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

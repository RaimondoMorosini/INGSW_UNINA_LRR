<script setup>
import { RouterView } from "vue-router";
import Navbar from "./components/Navbar/Navbar.vue";
import Footer from "./components/Navbar/FooterApp.vue"
import Toast from 'primevue/toast'

</script>

<template>

    <header>
        <Navbar class="z-10"/>
    </header>
        
    <body class="relative h-[100%]">
        <Toast position="top-right" group="tr"/>
        <router-view class="z-0"/> 
        <Footer class="z-10 absolute bottom-[-100vh] left-0 right-0 "/>       
    </body>

</template>

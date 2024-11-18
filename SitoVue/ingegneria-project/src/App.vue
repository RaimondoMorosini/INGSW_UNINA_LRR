<script setup>
import { RouterView } from "vue-router";
import Navbar from "./components/Navbar/Navbar.vue";
import Footer from "./components/Navbar/FooterApp.vue"
import LogicaSessionDialog from "./components/logicaSessionDialog.vue";
</script>

<template>

    <header>
        <Navbar class="z-10"/>
    </header>
        
    <body class="relative h-[100%]">
        
        <LogicaSessionDialog/>
        <router-view class="z-0"/> 
        <Footer class="z-10 absolute bottom-[-100vh] left-0 right-0 "/>       
    </body>

</template>

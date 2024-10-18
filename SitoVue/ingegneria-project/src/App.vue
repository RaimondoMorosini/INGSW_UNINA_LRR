<script setup>
import { RouterView } from "vue-router";
import Navbar from "./components/Navbar/Navbar.vue";
import Footer from "./components/Navbar/FooterApp.vue"

</script>

<template>
  
    <body class="relative h-[100%]">
      <Navbar />
  
      <router-view/>

      <Footer class="absolute bottom-[-100vh] left-0 right-0 "/>
    </body>
    
    


</template>



<style>



:root {
    --secondary-color: #27272A; 
    --secondary-color-text: #D4D4D8;     /* Optional: Text color for better contrast */
}
.my-custom-button:hover {
    --secondary-color: #3F3F46; 
    --secondary-color-text: #D4D4D8;     /* Optional: Text color for better contrast */
}
</style>

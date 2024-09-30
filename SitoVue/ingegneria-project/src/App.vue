<script setup>
import { RouterView } from "vue-router";
import Navbar from "./components/Navbar/Navbar.vue";
</script>

<template>
  
    <body class="min-w-[600px]">
      <Navbar />
  
      <router-view/>
    </body>
  
 

</template>

<style>
body{
  margin: 0;
  padding: 0%;
  
}
</style>
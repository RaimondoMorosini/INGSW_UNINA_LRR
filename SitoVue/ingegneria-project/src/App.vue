<script setup>
import { RouterView } from "vue-router";
import Navbar from "./components/Navbar/Navbar.vue";
</script>

<template>
  <Navbar />
  
  <router-view/>
</template>

<style>
body{
  margin: 0;
}
</style>./components/Navbar/Navbar.vue
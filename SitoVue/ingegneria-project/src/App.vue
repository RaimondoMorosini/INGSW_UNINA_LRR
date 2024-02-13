<script setup>
import { RouterLink,RouterView } from "vue-router";
import SiteHeader from "./components/SiteHeader.vue";


</script>

<template>
  <SiteHeader />
  <nav>
    <router-link to="/">Home</router-link> |
    <router-link :to="{ name : 'about'}">About</router-link>
  </nav>
  <router-view/>
</template>


<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #000;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #000;
  text-decoration: none;
}

nav a.router-link-exact-active {
  color:  #000;;
}
</style>

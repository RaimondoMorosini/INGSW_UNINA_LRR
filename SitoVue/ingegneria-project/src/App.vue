<script >
import { RouterLink,RouterView } from "vue-router";
import Navbar from "./components/Navbar.vue";


export default {
  name: "App",
  components: {
    RouterLink,
    RouterView,
    Navbar,
    
  }
};

</script>

<template>
  <div class="flex flex-col min-h-screen bg-primary">
    <Navbar />
    <router-view/>
  </div>
  
  
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

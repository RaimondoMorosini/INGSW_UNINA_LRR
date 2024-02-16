<script >
import { RouterLink,RouterView } from "vue-router";
import SiteHeader from "./components/SiteHeader.vue";
import Carosello from "./components/Carosello.vue";
import { DropDownListComponent } from "@syncfusion/ej2-vue-dropdowns";

export default {
  name: "App",
  components: {
    SiteHeader,
    Carosello,
    RouterLink,
    RouterView,
    
  }
};

</script>

<template>
  <SiteHeader />
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

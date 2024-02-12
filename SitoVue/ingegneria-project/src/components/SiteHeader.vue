<template>
   <header>
    <nav class="container">
        <div class="branding">
            <img src="../assets/Logo sito web [Optimized].svg" alt="">
            <h1>Deals24</h1>  
            <h2>RRL</h2>  
        </div>
        <ul class="nav-routes">
            <RouterLink to="/">Home</RouterLink>
            <router-link :to="{ name : 'about'}">About</router-link>
        </ul>

    </nav>
   </header>
</template>

<script setup>
import { RouterLink } from 'vue-router';
</script>


<style scoped>
header {
  background-color: #f1f1f1;
  nav {
    display: flex;
    align-items: center;
    padding: 1px 10px;

    .branding {
      display: flex;
      align-items: center;
      gap: 8px;
      
      img {
        max-width: 36px;
      }

      h1 {
        font-size: 24px;
        line-height: 120.83%;
        font-weight: 800; 
      }

      h2{
        font-size: 10px;
        line-height: 120.83%;
        font-weight: 800; 
        margin-top: 20px;
      }
    }

    .nav-routes {
      display: flex;
      flex: 1;
      justify-content: flex-end;
      gap: 12px;
      list-style: none;

      a {
        text-decoration: none;
        color: inherit;
      }
    }
  }
}
</style>
<template>
   <header :class="{'sticky-header': isSticky}">
    <nav class="container">
      <RouterLink to="/">
        <div class="branding">
            <img src="../assets/Logo sito web [Optimized].svg" alt="">
            <h1>Deals24</h1>  
            <h2>RRL</h2>  
        </div>
      </RouterLink>
        <ul class="nav-routes">
            <router-link :to="{ name : 'about'}">Ciao {{profilo}}</router-link>
            
            <Categorie class="flex" title="Categorie"/>

            <router-link :to="{ name : 'astePersonali'}">Le tue Aste</router-link>
            <router-link :to="{ name : 'notifiche'}">Notifiche</router-link>
        </ul>

    </nav>
   </header>
   
</template>

<script>
import { RouterLink } from 'vue-router';
import Categorie from '@/components/Categorie.vue'


export default {
  name: 'SiteHeader',
  components: {
    RouterLink,
    Categorie
  },
  data() {
    return {
      isSticky: false,
      profilo: "Utente"
    }
  },
  mounted() {
    window.addEventListener('scroll', this.handleScroll);
  },
  beforeDestroy() {
    window.removeEventListener('scroll', this.handleScroll);
  },
  methods: {
    handleScroll() {
      this.isSticky = window.scrollY > 0;
    }
  }
}

</script>


<style scoped>
header {
  background-color: #f1f1f1;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  width: 100%;
  z-index: 999;
 

  nav {
    color: #000;
    display: flex;
    align-items: center;
    text-align: left;
    padding: 5px 20px;
    
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
        margin-top: 10px;
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
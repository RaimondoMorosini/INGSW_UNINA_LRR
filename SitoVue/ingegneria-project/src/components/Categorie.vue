<template>
    <div class="menu-item" @click="isOpen = !isOpen" >
        <a href ='#'>
            {{ title }}
        </a>
        <svg viewBox="0 0 1030 638" width="10">
            <path d="M1017 68L541 626q-11 12-26 12t-26-12L13 68Q-3 49 6 24.5T39 0h952q24 0 33 24.5t-7 43.5z" fill="#000"></path>
        </svg>
        <transition name="fade" apear>
         <div class="sub-menu" v-if="isOpen">
            <div class="menu-item">
              <router-link to="/categorie/elettronica">Elettronica</router-link>
            </div>
            <div class="menu-item">
              <router-link to="/categorie/perlacasa">Per la Casa</router-link>
            </div>
            <div class="menu-item">
              <router-link to="/categorie/abbigliamento">Abbigliamentp</router-link>
            </div>
            <div class="menu-item">
              <router-link to="/categorie/sports">Sports</router-link>
            </div>
            <div class="menu-item">
              <router-link to="/categorie/hobby">Hobby</router-link>
            </div>
            
        </div>
        </transition>
    </div>
</template>

<script>
export default {
  name: 'Categorie',
  props: ['title'],
  data () {
    return {
      isOpen: false,
      }
    }
}
</script>

<style>


</style>
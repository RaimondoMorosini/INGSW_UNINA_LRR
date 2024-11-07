<template>
    <div class="notification-image" v-if="images.length">
      <img :src="images[0]" alt="Immagine dell'asta" />
    </div>
  </template>
  
  <script setup>
  import { defineProps } from 'vue';
  
  const props = defineProps({
    images: Array,
  });
  </script>
  
  <style scoped>
  .notification-image {
    width: 80px; /* Puoi aumentare questa dimensione se necessario */
    height: 80px; /* Puoi aumentare questa dimensione se necessario */
    margin-right: 15px;
    border-radius: 50%;
    overflow: hidden; 
  }
  .notification-image img {
    width: 100%; 
    height: auto; /* Mantieni le proporzioni dell'immagine */
  }


  </style>
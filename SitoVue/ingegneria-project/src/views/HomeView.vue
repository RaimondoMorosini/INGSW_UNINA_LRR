
<template>
  
  <SwipeCarosello />
  
  <div class="areaRicerca">

    <div class="barraOpzioneAsta">
      <BarraOpzioneAsta />
    </div>
  </div>

  
</template>

<script setup>
import BarraOpzioneAsta from '../components/BarraTipoAsta.vue';
import SwipeCarosello from '../components/SwipeCarosello.vue'

</script>

<style scoped>


.areaRicerca{

  width: 100%;
  height: auto;
  display: flex;
  text-align: center;
  justify-content: center;
  margin-top: 1%;
}

.barraRicerca{

  margin-right: 3rem;
}

.barraCategorie{

  margin-right: 3rem;
}

</style>

<template>
  
  
  <Carosello />
  
  <div class="areaRicerca">

    <div class="barraOpzioneAsta">
      <BarraOpzioneAsta />
    </div>
  </div>

  
</template>

<script>
import Carosello from '../components/Carosello.vue';
import BarraRicerca from '../components/BarraRicerca.vue';
import BarraCategorie from '../components/ContenitoreCategorie.vue';
import BarraOpzioneAsta from '../components/BarraTipoAsta.vue';

export default {
  name: 'HomeView',
  components: { Carosello, BarraRicerca, BarraCategorie, BarraOpzioneAsta},
  
}
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
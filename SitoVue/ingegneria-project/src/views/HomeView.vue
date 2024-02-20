<template>
  <div class="carosello">
    <Carosello />
  </div>
  <div class="areaRicerca">
    <div class="barraRicerca">
      <BarraRicerca />
    </div>
    <div class="barraCategorie">
      <BarraCategorie />
    </div>
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
  components: { Carosello, BarraRicerca, BarraCategorie, BarraOpzioneAsta}
}
</script>

<style scoped>

.carosello{

  width: 100%;
  height: 30rem;
  background-color: #EEEEEE;
  position: relative;
}

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
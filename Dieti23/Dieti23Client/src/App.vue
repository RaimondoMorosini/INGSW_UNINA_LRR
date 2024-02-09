
<template>

  <div class="contenitoreHeader">
    <Header />
  </div>
  <div v-if="booleanFiltro" class="contenitoreBarraLateraleSxChiuso">
    <div class="pulsanteFiltro">
        <img src="./assets/img/BarraLateraleSx/clickFiltro.png" @click="toggleFiltro"/>
    </div>
  </div>
  <div v-else class="contenitoreBarraLateraleSxAperto">
    <div class="pulsanteFiltro">
        <img src="./assets/img/BarraLateraleSx/clickFiltro.png" @click="toggleFiltro"/>
    </div>
  </div>
  
</template>

<script>
import Header from './components/componenti_index/Header.vue'
import FiltroSxChiuso from './components/FiltroSxChiuso.vue'
import FiltroSxAperto from './components/FiltroSxAperto.vue'

export default {
  components: {
    Header,
    FiltroSxChiuso,
    FiltroSxAperto
  },

  data() {
    return {
      booleanFiltro: true
    }
  },

  methods: {
    toggleFiltro() {
      this.booleanFiltro = !this.booleanFiltro;
    }
  }
};
</script>

<style scoped>

.contenitoreHeader{

  margin-left: 1rem;
  margin-right: 1rem;
  padding: 0rem 14rem 0rem 5rem;
  background-color: #d42222;
}

.contenitoreBarraLateraleSxChiuso{

  height:50rem;
  width:5%;
  margin-left: 1rem;
  margin-right: 1rem;
  margin-top: 5rem;
  background-color: #9e9e9e;

}

.contenitoreBarraLateraleSxAperto{

height:50rem;
width:15%;
margin-left: 1rem;
margin-right: 1rem;
margin-top: 5rem;
background-color: #9e9e9e;

}

.pulsanteFiltro{

display: flex;
justify-content: center;
cursor: pointer;
}

</style>

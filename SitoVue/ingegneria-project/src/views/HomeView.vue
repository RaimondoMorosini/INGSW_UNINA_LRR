<template>
<<<<<<< Updated upstream
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
=======
  <div class="home ">
    <h1>Homepage</h1>
    <p>Welcome to the homepage of our awesome app!</p>
    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quas ut perferendis aliquam, incidunt praesentium est fuga enim vero expedita neque error totam facere pariatur. Ullam sunt, qui perferendis beatae voluptatem culpa corrupti nihil assumenda veritatis doloribus saepe laboriosam. Ea doloremque dolores nostrum, dolor nam natus officia delectus sunt excepturi laboriosam dolore aperiam repellat voluptatum exercitationem vitae ullam possimus? Ipsa amet incidunt temporibus et harum dicta libero quo sapiente dolorum nihil perferendis modi deleniti quasi officia a repellat dolorem possimus quas, unde, voluptatem repudiandae ab. Deleniti aspernatur culpa quasi temporibus maiores? Sunt harum rem praesentium dolorum laudantium, architecto expedita delectus cupiditate.</p>
>>>>>>> Stashed changes
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
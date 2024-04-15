<template>
  <div class="w-fill mx-5 my-3 flex flex-col justify-between gap-3 md:flex-row">
    <aside class="bg-slate-100 ring-2 ring-black md:w-[25%]">
      <Galleria
        :value="images"
        :responsiveOptions="responsiveOptions"
        :numVisible="5"
        containerStyle="max-width: 100%"
      >
        <template #item="slotProps">
          <img :src="slotProps.item.itemImageSrc" :alt="slotProps.item.alt" style="width: 100%" />
        </template>
        <template #thumbnail="slotProps">
          <img :src="slotProps.item.thumbnailImageSrc" :alt="slotProps.item.alt" />
        </template>
      </Galleria>
    </aside>

    <div class="bg-slate-100 ring-2 ring-black md:w-[75%]">
      <h1>{{ auction.title }}</h1>
      <p>{{ auction.description }}</p>
      <p>Current Price: {{ auction.currentPrice }}</p>
    </div>

    <div class="bg-slate-100 ring-2 ring-black md:w-[25%]">
      <h2 class="text-lg">Offers</h2>
      <ul>
        <li>Offer 1</li>
        <li>Offer 2</li>
        <li>Offer 3</li>
      </ul>
    </div>
  </div>
  <div class="mx-5 my-3 bg-slate-100 ring-2 ring-black">
    <Inglese />
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import Inglese from '../components/astaInglese/SezioneInglese.vue';
import { PhotoService } from '../scripts/PhotoService';

import Galleria from 'primevue/galleria';

const auction = ref({
  title: 'Sample Auction',
  description: 'This is a sample auction',
  currentPrice: 100,
});

onMounted(() => {
  PhotoService.getImages().then((data) => (images.value = data));
});

const images = ref();
const responsiveOptions = ref([
  {
    breakpoint: '1300px',
    numVisible: 4,
  },
  {
    breakpoint: '575px',
    numVisible: 1,
  },
]);

function placeOffer() {
  // Add your logic to place an offer here
}
</script>

<style scoped>
html {
  box-sizing: border-box;
}

*,
*::before,
*::after {
  box-sizing: inherit;
}

#app {
  text-align: center;
}

body {
  font-family: sans-serif;
  padding: 0;
  margin: 0;
}
#app {
  padding: 10px;
  margin: 0 auto;
  max-width: 1000px;
}

input[type='number'] {
  padding: 10px;
  font-size: inherit;
  width: 100%;
}

button {
  padding: 10px;
}

[v-cloak] {
  opacity: 0;
}

label {
  width: 20%;
  padding: 10px 0;
  display: inline-block;
}

.timer {
  font-size: 2em;
  margin: 20px;
}
</style>

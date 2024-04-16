<script>
import Timer from './Timer.vue';

function placeOffer() {
  // Add your logic to place an offer here
}
</script>

<template>
  <div clasS="flex  ">
    <Timer />
    <button @click="placeOffer" class="mx-1 my-1 ring-2 ring-black">Place Offer</button>
  </div>
</template>

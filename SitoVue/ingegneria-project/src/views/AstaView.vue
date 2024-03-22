<template>
  <aside></aside>

  <div>
    <h1>{{ auction.title }}</h1>
    <p>{{ auction.description }}</p>
    <p>Current Price: {{ auction.currentPrice }}</p>
    <button @click="placeOffer">Place Offer</button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import Avatar from ;
const auction = ref({
  title: 'Sample Auction',
  description: 'This is a sample auction',
  currentPrice: 100,
});

function placeOffer() {
  // Add your logic to place an offer here
}
</script>

<style scoped>
/* Add your custom styles here */
</style>

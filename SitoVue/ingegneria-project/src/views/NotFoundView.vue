<template>
  <div class="not-found">
    <h1>404 Not Found</h1>
    <p>The page you are looking for does not exist.</p>
    <router-link :to="{name : 'home'}">Back to home</router-link>
  </div>
</template>

<script setup>
import { RouterLink } from 'vue-router'
</script>

<style scoped>
.not-found {
  text-align: center;
  margin-top: 100px;
}

h1 {
  font-size: 36px;
  color: #ff0000;
}

p {
  font-size: 18px;
  color: #333333;
}
</style>

<template>
    <div>
      <h1>WebSocket Example</h1>
      <ul>
        <li v-for="message in messages" :key="message.id">{{ message }}</li>
      </ul>
    </div>
  </template>
  
  <script setup>
  </script>
  
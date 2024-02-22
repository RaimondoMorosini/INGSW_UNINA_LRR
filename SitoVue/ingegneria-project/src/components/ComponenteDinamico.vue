<template>
    <component :is="as" v-bind="{ value }">
      {{ value }}
    </component>
  </template>
  
  <script setup>
  // Importa defineProps per definire le props nel convalue di <script setup>
  defineProps({
    as: {
      type: [String, Object],
      default: 'button'
    },
    value: String
  });
  </script>
  
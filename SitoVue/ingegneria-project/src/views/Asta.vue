<template>
Asta numero: {{ $route.params.id }}
</template>

<script setup>
defineProps({
  id: String
})
</script>
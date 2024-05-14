<template>
  <Paginator :rows="10" :totalRecords="100"> </Paginator>
</template>

<script setup>
import Paginator from 'primevue/paginator';

import { ref } from 'vue';
</script>

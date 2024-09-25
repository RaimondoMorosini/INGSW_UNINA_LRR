<template>
  Asta numero: {{ $route.params.id }}
  <button @click="stampaItem">Stampa item</button>
  {{ item }}
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';

import { getInfoAstaProdotto } from '../service/PaginaProdottoAstaService';

const route = useRoute();
const astaId = route.params.id;
const item = ref(null);

onMounted(async () => {
  console.log("sono in onMounted");
  try {
    item.value = await getInfoAstaProdotto(astaId);
    console.log(item.value); // Usa il dato come necessario
  } catch (e) {
    console.log(e);
  }
});

function stampaItem() {
  console.log("stampa item");
  console.log(item.value);
}
</script>

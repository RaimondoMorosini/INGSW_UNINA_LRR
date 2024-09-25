<template>Asta numero: {{ $route.params.id }}
    <button @click="stampaItem">Stampa item</button>
    {{ item }}

</template>

<script setup>
import { defineProps, onMounted ,ref} from 'vue';
import { useRoute } from 'vue-router';

import { getInfoAstaProdotto } from '../service/PaginaProdottoAstaService';


const route = useRoute();
const astaId = route.params.id;
const item = ref(null);
onMounted(() => {
    console.log("sono in OnMounted");
  try{
    item.value = getInfoAstaProdotto(astaId);
    console.log(item); // Usa il dato come necessario
  }catch(e){
  console.log(e);
}

}
);

function stampaItem() {
    console.log(item);
}
</script>

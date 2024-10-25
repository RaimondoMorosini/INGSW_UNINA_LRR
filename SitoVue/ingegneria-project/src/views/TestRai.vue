<template>
    test Rai
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount } from 'vue';
import {apriWebSocketNotifichePersonali} from '../service/notifiche.js';
const stomp1 = ref(null);

stomp1.value=apriWebSocketNotifichePersonali("morosini.ragusa@gmail.com",callback);

function callback(data){
    console.log("callback: ");
  console.log(data);
}
onBeforeUnmount
</script>

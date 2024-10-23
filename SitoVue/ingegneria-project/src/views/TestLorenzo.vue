<template>
    <Timer />
    </template>

<script setup>
import { useAstaStore } from '../stores/astaStore';
import Timer from '../components/astaInglese/Timer.vue';

const astaInstance = useAstaStore()
let scadenza = astaInstance.asta.scadenzaAsta;

function isoToSeconds(isoString) {
    // Parse the ISO string into a Date object
    const date = new Date(isoString);
    console.log(date);
    // Convert the date to seconds since Unix Epoch
    const seconds = Math.floor(date.getTime() / 1000);
    
    return seconds;
}

function getScadenzaSeconds(){
    let secondsOnTimer=isoToSeconds(new Date( scadenza ));
    let secondsToday=isoToSeconds(new Date());
    console.log('scadenza:', secondsOnTimer-secondsToday)
    return secondsOnTimer-secondsToday;
}

let seconds = getScadenzaSeconds();

</script>


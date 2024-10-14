<template>
    <div>{{scadenzaNew}}</div>
    <div class="px-3 py-3">
        <div class="text-2xl">
            <span class="rounded bg-slate-100/20 px-1 py-1" :class="{'double-digit': timer.days.value >= 10, 'single-digit': timer.days.value < 10}">{{ timer.days }}</span>
            :<span class="rounded bg-slate-100/20 px-1 py-1" :class="{'double-digit': timer.hours.value >= 10, 'single-digit': timer.hours.value < 10}">{{ timer.hours }}</span>
            :<span class="rounded bg-slate-100/20 px-1 py-1" :class="{'double-digit': timer.minutes.value >= 10, 'single-digit': timer.minutes.value < 10}">{{ timer.minutes }}</span>
            :<span class="rounded bg-slate-100/20 px-1 py-1" :class="{'double-digit': timer.seconds.value >= 10, 'single-digit': timer.seconds.value < 10}">{{ timer.seconds }}</span>
 
        </div>
        <div class="bg-slate-100/20 p-2 rounded">
            <Countdown 
            :deadline="scadenzaNew.value"
            labelColor="white"
            mainColor="white"
            :labels="{
                days: 'GIORNI',
                hours: 'ORE',
                minutes: 'MIN',
                seconds: 'SEC',
            }"
            />
        </div>  
        <p>{{ timer.isRunning ? 'Running' : 'Not running' }}</p>
        <button
            class="mx-1 rounded bg-primario-400 px-1 py-1 hover:bg-slate-100/20"
            @click="timer.start()"
        >
            Start
        </button>
        <button
            class="mx-1 rounded bg-primario-400 px-1 py-1 hover:bg-slate-100/20"
            @click="timer.pause()"
        >
            Pause
        </button>
        <button
            class="mx-1 rounded bg-primario-400 px-1 py-1 hover:bg-slate-100/20"
            @click="timer.resume()"
        >
            Resume
        </button>
        <button
            class="mx-1 rounded bg-primario-400 px-1 py-1 hover:bg-slate-100/20"
            @click="restartFive()"
        >
            Restart
        </button>
    </div>
</template>

<script setup>
import {Countdown} from 'vue3-flip-countdown'
import { onMounted, watchEffect,ref, watch } from 'vue';
import { useTimer } from 'vue-timer-hook';
import { defineProps } from 'vue';
import { useAstaStore } from '../../stores/astaStore';

const storeInstance = useAstaStore();
/*
const props = defineProps({
    scadenza: {
        type: Date,
        required: true,
    },
})*/

let time = new Date();
time.setSeconds(time.getSeconds() + 600); // 10 minutes timer
const timer = useTimer(time);

const scadenzaFormatted = ref(storeInstance.asta.scadenzaAsta)

// Split the date and time
const [dateScadenza, timeWithMilliseconds] = scadenzaFormatted.value.split('T');

// Remove the 'Z' at the end of the time string and milliseconds
const timeScadenza = timeWithMilliseconds.split('.')[0];  // "23:00:00"

const scadenzaNew = dateScadenza +' '+timeScadenza;

const minutesDigit = ref(0);

const restartFive = () => {
    // Restarts to 5 minutes timer
    const time = new Date();
    time.setSeconds(time.getSeconds() + 300);
    timer.restart(time);
};
onMounted(() => {
    watchEffect(async () => {
        if (timer.isExpired.value) {
            console.warn('IsExpired');
        }
    });
});




</script>

<style scoped>

.single-digit::before {
    content: '0';
}
</style>

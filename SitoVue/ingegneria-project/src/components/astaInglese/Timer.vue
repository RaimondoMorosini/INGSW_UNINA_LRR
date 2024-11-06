<template>
    <div>{{ timeOnTimer }}</div>

    <div class="px-3 py-3">
        <div class="text-2xl">
            <span
                class="rounded bg-slate-300/50 px-1 py-1"
                :class="{
                    'double-digit': timer.days.value >= 10,
                    'single-digit': timer.days.value < 10,
                }"
                >{{ timer.days }}</span
            >
            :<span
                class="rounded bg-slate-300/50 px-1 py-1"
                :class="{
                    'double-digit': timer.hours.value >= 10,
                    'single-digit': timer.hours.value < 10,
                }"
                >{{ timer.hours }}</span
            >
            :<span
                class="rounded bg-slate-300/50 px-1 py-1"
                :class="{
                    'double-digit': timer.minutes.value >= 10,
                    'single-digit': timer.minutes.value < 10,
                }"
                >{{ timer.minutes }}</span
            >
            :<span
                class="rounded bg-slate-300/50 px-1 py-1"
                :class="{
                    'double-digit': timer.seconds.value >= 10,
                    'single-digit': timer.seconds.value < 10,
                }"
                >{{ timer.seconds }}</span
            >
        </div>

        <p>{{ timer.isRunning ? 'Running' : 'Not running' }}</p>
        <button class="mx-1 rounded bg-primario-400 px-1 py-1" @click="timer.start()">Start</button>
        <button class="mx-1 rounded bg-primario-400 px-1 py-1" @click="timer.pause()">Pause</button>
        <button class="mx-1 rounded bg-primario-400 px-1 py-1" @click="timer.resume()">
            Resume
        </button>
        <button class="mx-1 rounded bg-primario-400 px-1 py-1" @click="restartFive()">
            Restart
        </button>
        <Button @click="addHour"><span class="font-bold">Aggiungi un'ora</span></Button>
    </div>
</template>

<script setup>
import { Countdown } from 'vue3-flip-countdown';
import { onMounted, watchEffect, ref, watch } from 'vue';
import { useTimer } from 'vue-timer-hook';
import { defineProps } from 'vue';
import { useAstaStore } from '../../stores/astaStore';
import Button from 'primevue/button';

const storeInstance = useAstaStore();
/*
const props = defineProps({
    scadenza: {
        type: Date,
        required: true,
    },
})*/

const scadenzaFormatted = ref(storeInstance.asta.scadenzaAsta);

// Split the date and time
const [dateScadenza, timeWithMilliseconds] = scadenzaFormatted.value.split('T');

// Remove the 'Z' at the end of the time string and milliseconds
const timeScadenza = timeWithMilliseconds.split('.')[0]; // "23:00:00"

const scadenzaNew = dateScadenza + ' ' + timeScadenza;

const minutesDigit = ref(0);

const restartFive = () => {
    // Restarts to 5 minutes timer
    const time = new Date();
    time.setSeconds(time.getSeconds() + 300);
    timer.restart(time);
};

const addHour = () => {
    const newExpiry = new Date();
    newExpiry.setDate(newExpiry.getDate() + timer.days.value);
    newExpiry.setHours(newExpiry.getHours() + timer.hours.value);
    newExpiry.setMinutes(newExpiry.getMinutes() + timer.minutes.value);
    newExpiry.setSeconds(newExpiry.getSeconds() + timer.seconds.value);
    console.log('nuova scadenza before h+1:', newExpiry);

    newExpiry.setMinutes(newExpiry.getMinutes() + 59);
    console.log('nuova scadenza after h+1:', newExpiry);
    timer.restart(newExpiry); // Restart the timer with the new expiry time
};

function isoToSeconds(isoString) {
    // Parse the ISO string into a Date object
    const date = new Date(isoString);

    // Convert the date to seconds since Unix Epoch
    const seconds = Math.floor(date.getTime() / 1000);

    return seconds;
}

function getScadenzaSeconds() {
    let secondsOnTimer = isoToSeconds(new Date(scadenzaFormatted.value));
    let secondsToday = isoToSeconds(new Date());
    return secondsOnTimer - secondsToday;
}

let timeOnTimer = getScadenzaSeconds();

let time = new Date();
time.setSeconds(time.getSeconds() + timeOnTimer); // 10 minutes timer
const timer = useTimer(time);

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

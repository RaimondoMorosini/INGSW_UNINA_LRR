<template>
    <div>
        <p class="timer" v-if="!timerScaduto">
            {{ giorni }} giorni, {{ ore }} ore, {{ minuti }} minuti, {{ secondi }} secondi rimanenti
        </p>
        <p class="timer" v-else>
            0 giorni, 0 ore, 0 minuti, 0 secondi rimanenti
        </p>
    </div>
</template>

<script setup>
import { ref, computed, onMounted, onBeforeUnmount, watch } from 'vue';

const props = defineProps({
    unixTimestamp: {
        type: Number,
        required: true,
    },
});

const emit = defineEmits(['update:remainingTime']);

const remainingTime = ref(0);

const giorni = computed(() => Math.floor(remainingTime.value / (60 * 60 * 24)));
const ore = computed(() => Math.floor((remainingTime.value % (60 * 60 * 24)) / (60 * 60)));
const minuti = computed(() => Math.floor((remainingTime.value % (60 * 60)) / 60));
const secondi = computed(() => remainingTime.value % 60);
const timerScaduto = computed(() => remainingTime.value <= 0);
let countdownInterval;

const startCountdown = () => {
    clearInterval(countdownInterval);
    countdownInterval = setInterval(() => {
        const currentTime = Math.floor(Date.now()); // Ottieni il tempo corrente in secondi
        remainingTime.value = Math.floor((props.unixTimestamp - currentTime) / 1000);
        if (remainingTime.value > 0) {
            emit('update:remainingTime', remainingTime.value);
        } else {
            clearInterval(countdownInterval);
        }
    }, 1000);
};
watch(
    () => props.unixTimestamp,
    (newTimestamp, oldTimestamp) => {
        if (newTimestamp !== oldTimestamp) {
            startCountdown();
        }
    }
);

// Inizializza il countdown al montaggio
onMounted(() => {
    startCountdown();
});

// Pulisci l'intervallo quando il componente viene smontato
onBeforeUnmount(() => {
    clearInterval(countdownInterval);
});
</script>

<style>
.timer {
    font-size: 16px;
    font-style: italic;
}
</style>

<template>
  <div>
    <p>{{ giorni }} giorni, {{ ore }} ore, {{ minuti }} minuti, {{ secondi }} secondi rimanenti</p>
    {{ modelValue }}
 </div>
</template>

<script setup>
import { computed, onMounted, onBeforeUnmount } from 'vue';

const props = defineProps({
  modelValue: {
    type: Number,
    required: true
  }
});

const emit = defineEmits(['update:modelValue']);

const giorni = computed(() => Math.floor(props.modelValue / (60 * 60 * 24)));
const ore = computed(() => Math.floor((props.modelValue % (60 * 60 * 24)) / (60 * 60)));
const minuti = computed(() => Math.floor((props.modelValue % (60 * 60)) / 60));
const secondi = computed(() => props.modelValue % 60);

let countdownInterval;

const startCountdown = () => {
  clearInterval(countdownInterval);
  countdownInterval = setInterval(() => {
    if (props.modelValue > 0) {
      emit('update:modelValue', props.modelValue - 1); // Emissione dell'evento per aggiornare il genitore
    } else {
      clearInterval(countdownInterval);
    }
  }, 1000);
};

// Inizializza il countdown al montaggio
onMounted(() => {
  startCountdown();
});

// Pulisci l'intervallo quando il componente viene smontato
onBeforeUnmount(() => {
  clearInterval(countdownInterval);
});
</script>
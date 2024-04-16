<template>
  <div>
    <h1>vue-timer-hook</h1>
    <p>Timer Demo</p>
    <div class="text-2xl">
      <span class="rounded bg-slate-200 px-1 py-1">{{ timer.days }}</span
      >:<span class="rounded bg-slate-200 px-1 py-1">{{ timer.hours }}</span
      >:<span class="rounded bg-slate-200 px-1 py-1">{{ timer.minutes }}</span
      >:<span class="rounded bg-slate-200 px-1 py-1">{{ timer.seconds }}</span>
    </div>
    <p>{{ timer.isRunning ? 'Running' : 'Not running' }}</p>
    <button class="mx-1 rounded bg-indigo-200 px-1 py-1 hover:bg-slate-200" @click="timer.start()">
      Start
    </button>
    <button class="mx-1 rounded bg-indigo-200 px-1 py-1 hover:bg-slate-200" @click="timer.pause()">
      Pause
    </button>
    <button class="mx-1 rounded bg-indigo-200 px-1 py-1 hover:bg-slate-200" @click="timer.resume()">
      Resume
    </button>
    <button class="mx-1 rounded bg-indigo-200 px-1 py-1 hover:bg-slate-200" @click="restartFive()">
      Restart
    </button>
  </div>
</template>

<script setup>
import { onMounted, watchEffect } from 'vue';
import { useTimer } from 'vue-timer-hook';

let time = new Date();
time.setSeconds(time.getSeconds() + 600); // 10 minutes timer
const timer = useTimer(time);
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

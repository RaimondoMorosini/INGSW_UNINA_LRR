<template>
    <div class="rounded bg-slate-200/20 p-2">
        <!-- Countdown Timer Component -->
        <Countdown
            :deadline="scadenza.value"
            labelColor="white"
            mainColor="red"
            :labels="{
                days: 'GIORNI',
                hours: 'ORE',
                minutes: 'MIN',
                seconds: 'SEC',
            }"
        />

        <!-- Button to add an hour -->
        <button @click="addHour" class="mt-4 rounded bg-blue-500 px-4 py-2 text-white">
            Add 1 Hour
        </button>

        <div v-if="loading">Loading...</div>
    </div>
</template>

<script setup>
import { ref, watch } from 'vue';
import { Countdown } from 'vue3-flip-countdown';

const scadenza = ref('2024-03-01 00:00:00');
const loading = ref(false);

// Function to add 1 hour to the timer
const addHour = () => {
    loading.value = true; // Start loading state
    const currentDate = new Date(scadenza.value);

    // Check if the current date is valid
    if (isNaN(currentDate.getTime())) {
        console.error('Invalid date format');
        loading.value = false; // End loading state
        return;
    }

    // Incrementing by 1 hour
    currentDate.setHours(currentDate.getHours() + 1);
    scadenza.value = currentDate.toISOString().replace('T', ' ').substring(0, 19); // Format back to "YYYY-MM-DD HH:MM:SS"

    loading.value = false; // End loading state
};

// Watcher to observe changes in the `scadenza` variable
watch(scadenza, (newValue, oldValue) => {
    console.log('Timer updated from:', oldValue, 'to:', newValue);
    // You can trigger additional logic here if needed, but the Countdown component will automatically update
});
</script>

<style scoped>
button {
    cursor: pointer;
}
</style>

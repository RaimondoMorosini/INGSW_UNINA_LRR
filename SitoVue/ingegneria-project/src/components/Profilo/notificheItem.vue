<template>
    <div class="notification-item" :class="{ read: isRead }" @click="toggleMessage">
        <div class="notification-header">
            <h3 class="notification-title">{{ title }}</h3>
            <span class="notification-icon" :class="{ read: isRead }">
                <i v-if="isRead" class="icon-read">✓</i>
                <i v-else class="icon-unread">🔵</i>
            </span>
            <span class="notification-date">{{ formattedDate }}</span>
        </div>
        <p v-if="isMessageVisible" class="notification-message">
            {{ message }}
            <br />
            <strong>Asta ID:</strong> {{ astaId }}
        </p>
    </div>
</template>

<script setup>
import { defineProps, computed, ref, defineEmits } from 'vue';

const props = defineProps({
    id: {
        type: Number,
        required: true,
    },

    astaId: {
        type: Number,
        required: true,
    },
    title: {
        type: String,
        required: true,
    },
    message: {
        type: String,
        required: true,
    },
    date: {
        type: String,
        required: true,
    },
    isRead: {
        type: Boolean,
        required: true,
    },
});

const emit = defineEmits(['mark-as-read']);
const isMessageVisible = ref(false);

const formattedDate = computed(() => {
    const dataNotifica = new Date(props.date);
    return dataNotifica.toLocaleDateString('it-IT', {
        day: '2-digit',
        month: 'long',
        year: 'numeric',
        hour: '2-digit',
        minute: '2-digit',
    });
});

function toggleMessage() {
    isMessageVisible.value = !isMessageVisible.value;

    if (!props.isRead) {
        emit('mark-as-read', props.id); // Comunica l'ID della notifica letta
    }
}
</script>

<style scoped>
/* Stili invariati */
.notification-item {
    border: 1px solid #ccc;
    border-radius: 8px;
    padding: 15px;
    margin: 10px 0;
    background-color: #ffffff;
    transition:
        background-color 0.3s,
        box-shadow 0.3s;
    cursor: pointer;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.notification-item:hover {
    background-color: #f5f5f5;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
}

.notification-item.read {
    background-color: #f0f0f0;
    color: #888;
}

.notification-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.notification-title {
    font-size: 1.25em;
    font-weight: bold;
    color: #333;
}

.notification-icon {
    font-size: 1.1em;
    margin-left: 8px;
    color: #555;
}

.notification-icon.read {
    color: green;
}

.icon-unread {
    color: #3498db;
}

.notification-date {
    font-size: 0.85em;
    color: #777;
    font-style: italic;
}

.notification-message {
    font-size: 1em;
    color: #555;
    margin-top: 10px;
    transition:
        max-height 0.3s ease,
        opacity 0.3s ease;
}
</style>

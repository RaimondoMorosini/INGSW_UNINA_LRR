<template>
  <div class="notification-item" :class="{ read: isRead }" @click="toggleMessage">
    <div class="notification-image" v-if="asta.immagini.length">
      <img :src="asta.immagini[0]" alt="Immagine dell'asta" />
    </div>
    <div class="notification-content">
      <div class="notification-header">
        <h3 class="notification-title">{{ title }}</h3>
        <span class="notification-icon" :class="{ read: isRead }">
          <i v-if="isRead" class="icon-read">✓</i>
          <i v-else class="icon-unread">🔵</i>
        </span>
        <span class="notification-date">{{ formattedDate }}</span>
      </div>
      <p v-if="!isMessageVisible" class="notification-message-preview">
        {{ message }}
      </p>
      <p v-if="isMessageVisible" class="notification-message-full">
        {{ message }}
        <strong>Asta ID:</strong> {{ astaId }}
      </p>
    </div>
  </div>
</template>

<script setup>
import { defineProps, computed, ref, defineEmits } from 'vue';
import { useAstaChacheStore } from '../../../stores/astaStore.js';

const props = defineProps({
  id: Number,
  astaId: Number,
  title: String,
  message: String,
  date: String,
  isRead: Boolean,
});

const astaStore = useAstaChacheStore();
const asta = ref({ idAsta: props.astaId, nome: '', descrizione: '', immagini
: [] });

astaStore.getAstaById(props.astaId).then((data) => {
  asta.value = data;
});

const emit = defineEmits(['mark-as-read']);
const isMessageVisible = ref(false);

const formattedDate = computed(() => {
  const [data, ora] = props.date.split(", ");
  const [giorno, mese, anno] = data.split("/");
  const dataIso = `${anno}-${mese}-${giorno}T${ora}`;
  const dataNotifica = new Date(dataIso);
  return isNaN(dataNotifica) ? "Data non valida" : dataNotifica.toLocaleDateString('it-IT', {
    day: '2-digit',
    month: 'long',
    year: 'numeric',
    hour: '2-digit',
    minute: '2-digit',
  });
});

function toggleMessage() {
  isMessageVisible.value = !isMessageVisible.value;
  if (!props.isRead) emit('mark-as-read', props.id);
}
</script>

<style scoped>
.notification-item {
  display: flex;
  align-items: flex-start;
  border: 1px solid #ccc;
  border-radius: 8px;
  padding: 15px;
  margin: 10px 0;
  background-color: #ffffff;
  transition: background-color 0.3s, box-shadow 0.3s;
  cursor: pointer;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}
.notification-item.read {
  background-color: #f9f9f9;
  color: #888;
}
.notification-image {
  width: 80px;
  height: 80px;
  margin-right: 15px;
  border-radius: 50%;
}
.notification-image img {
  width: 100%;
  height: auto;
}
.notification-content {
  flex: 1;
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
  margin: 0;

}
.notification-icon {
  font-size: 1.1em;
  margin-left: 10px;
  color: #555;
}
.icon-read {
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
.notification-message-preview {

  font-size: 1em;
  color: #555;
  margin-top: 5px;
  display: -webkit-box;
  -webkit-line-clamp: 2; /* Limita a due righe visibili */
  -webkit-box-orient: vertical;
  overflow: hidden;
  text-overflow: ellipsis; /* Mostra i puntini di sospensione per il testo troncato */
  cursor: pointer;
  transition: max-height 0.3s ease;
}
.notification-message-full {
  font-size: 0.95em;
  color: #333;
  margin-top: 10px;
  transition: max-height 0.3s ease; 
}
</style>

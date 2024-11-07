<template>
  <div class="notification-item" :class="{ read: isRead }" @click="toggleMessage">
    <NotificationImage :images="asta.immagini" />
    <div class="notification-content">
      <NotificationHeader :title="title" :isRead="isRead" :formattedDate="formattedDate" />
      <NotificationMessage :message="message" :isMessageVisible="isMessageVisible" :toggleMessage="toggleMessage" />
    </div>
  </div>
</template>

<script setup>
import { defineProps, computed, ref, defineEmits } from 'vue';
import { useAstaChacheStore } from '../../../stores/astaStore.js';
import NotificationImage from './NotificationImage.vue';
import NotificationHeader from './NotificationHeader.vue';
import NotificationMessage from './NotificationMessage.vue';

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
  transition: background-color 0.3s, box-shadow 0.3s;
  cursor: pointer;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  @apply bg-primario-300/50; /* Colore di sfondo per notifiche non lette */
  @apply border-l-4 border-primario-400; /* Barra laterale per evidenziare la notifica non letta */
}
.notification-item.read {
  background-color: #ffffff; /* Colore di sfondo per notifiche lette */
  color: #555; /* Colore più scuro per il titolo delle notifiche lette */
}




.notification-content {
  flex: 1;
}

</style>

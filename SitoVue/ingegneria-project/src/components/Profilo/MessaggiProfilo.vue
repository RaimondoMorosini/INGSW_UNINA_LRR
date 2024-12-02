<template>
    <Card fluid class="notification-container mx-4">
        <template #title>
            <h1 class="mb-4 text-2xl font-bold">Notifiche</h1>
        </template>
        <template #content>
            <div class="notification-summary mb-4">
                <p>Numero totale di notifiche: {{ totalNotifications }}</p>
                <p>Numero di notifiche da leggere: {{ unreadNotifications }}</p>
            </div>

            <div class="mb-4 flex flex-col justify-around gap-3 lg:flex-row">
                <div class="mb-2 flex w-[100%] flex-row justify-between lg:mb-0">
                    <label for="filter" class="mr-2">Filtra:</label>
                    <select v-model="filter" id="filter" class="w-[80%] rounded border p-2">
                        <option value="all">Tutte</option>
                        <option value="read">Visualizzate</option>
                        <option value="unread">Non visualizzate</option>
                    </select>
                </div>

                <div class="flex w-[100%] flex-row justify-between">
                    <label for="sort" class="mr-2">Ordina per data:</label>
                    <select v-model="sortOrder" id="sort" class="w-[80%] rounded border p-2">
                        <option value="asc">Crescente</option>
                        <option value="desc">Decrescente</option>
                    </select>
                </div>
            </div>

            <ul>
                <li v-for="notification in filteredNotifications" :key="notification.id">
                    <notificheItem
                        :id="notification.id"
                        :astaId="notification.AstaId"
                        :title="'Notifica ' + notification.oggettoDellaNotifica"
                        :message="notification.messaggio"
                        :date="new Date(notification.dataUnixTimeMilliseconds).toLocaleString()"
                        :isRead="notification.visualizzato"
                        @mark-as-read="segnaNotificaComeLetta"
                    />
                </li>
            </ul>
            <Button fluid @click="loadMore">Carica di più</Button>
        </template>
    </Card>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import {
    getNotifiche,
    getNumeroDiNotificheNonLette,
    getNumeroNotifiche,
} from '../../service/notificheService';
import notificheItem from './ListaNotifiche/NotificaItem/notificheItem.vue';
import Button from 'primevue/button';
import Card from 'primevue/card';

const notifications = ref([]);
const totalNotifications = ref(0);
const unreadNotifications = ref(0);
const currentPage = ref(1);
const notificationsPerPage = 5;
const filter = ref('all');
const sortOrder = ref('desc');

const fetchNotifications = async () => {
    try {
        totalNotifications.value = await getNumeroNotifiche();
        console.log('totalNotifications:', totalNotifications.value);
        unreadNotifications.value = await getNumeroDiNotificheNonLette();
        notifications.value = await getNotifiche(currentPage.value, notificationsPerPage);
        console.log('notifications:', notifications.value);
    } catch (error) {
        console.error('Error fetching notifications:', error);
    }
};

const loadMore = async () => {
    currentPage.value++;
    const newNotifications = await getNotifiche(currentPage.value, notificationsPerPage);
    notifications.value.push(...newNotifications);
};

function segnaNotificaComeLetta(id) {
    const notification = notifications.value.find((n) => n.id === id);
    if (notification) {
        notification.visualizzato = true;
        alert(`Segna come letta la notifica ${notification.id}`);
    }
}

const filteredNotifications = computed(() => {
    let filtered = notifications.value;

    // Filtro in base allo stato di lettura
    if (filter.value === 'read') {
        filtered = filtered.filter((n) => n.visualizzato);
    } else if (filter.value === 'unread') {
        filtered = filtered.filter((n) => !n.visualizzato);
    }

    // Ordinamento in base alla data
    filtered.sort((a, b) => {
        const dateA = new Date(a.dataUnixTimeMilliseconds);
        const dateB = new Date(b.dataUnixTimeMilliseconds);
        return sortOrder.value === 'asc' ? dateA - dateB : dateB - dateA;
    });

    return filtered;
});

onMounted(() => {
    fetchNotifications();
});
</script>

<style scoped>
.notification-container {
    margin: 10px auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 8px;
    background-color: #f9f9f9;
}

.notification-summary {
    margin-bottom: 20px;
}

ul {
    list-style-type: none;
    padding: 0;
}
</style>

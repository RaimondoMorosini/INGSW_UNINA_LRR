<template>
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
</template>
<script setup>
import { ref, onMounted, computed } from 'vue';

// eventBus.js
import { ref } from 'vue';

const eventBus = {
  events: ref({}),
  
  emit(event, data) {
    console.log('eventBus emit', event, data);
    console.log('eventBus events', this.events.value);
    if (!this.events.value[event]) return;
    this.events.value[event].forEach(callback => callback(data));
  },
  
  on(event, callback) {
    if (!this.events.value[event]) {
      this.events.value[event] = [];
    }
    this.events.value[event].push(callback);
  },
  
  off(event, callback) {
    if (!this.events.value[event]) return;
    this.events.value[event] = this.events.value[event].filter(cb => cb !== callback);
  }
};

export default eventBus;
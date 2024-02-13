import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import './assets/css/tailwind.css';
import menuItem from './components/menuItem.vue';



// Crea l'istanza dell'app Vue e usa il router
const app = createApp(App).use(router);

app.component('menuItem', menuItem );


// Monta l'app Vue
app.mount('#app');

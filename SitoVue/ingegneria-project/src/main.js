import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import './assets/css/tailwind.css';
import './components/menuItem.vue';
//da mettere nei file dove si vuole usare axios
import axios from 'axios';



// Crea l'istanza dell'app Vue e usa il router
const app = createApp(App).use(router);



// Monta l'app Vue
app.mount('#app');

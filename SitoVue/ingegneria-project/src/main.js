import { createAuth0 } from '@auth0/auth0-vue'; // auth0
import '@babel/polyfill'; // primevue e primeflex (richiedono babel)
import axios from 'axios';
import 'mutationobserver-shim'; // primevue e primeflex (richiedono mutationobserver)
import { createPinia } from 'pinia'; // pinia
import 'primeicons/primeicons.css'; // primevue icons
import PrimeVue from 'primevue/config'; // primevue config
import 'primevue/resources/primevue.min.css'; // core CSS (primevue)
import 'primevue/resources/themes/aura-light-primary/theme.css'; // theme (primevue)
import ToastService from 'primevue/toastservice'; // toast (primevue)
import { createApp } from 'vue'; // Vue 3
import App from './App.vue'; // App.vue
import './assets/css/tailwind.css'; // tailwindcss
import router from './router'; // router
import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';



import { plugin, defaultConfig } from '@formkit/vue' //formkit
import config from '../formkit.config' //formkit config

// Crea l'istanza dell'app Vue e usa il router
const app = createApp(App).use(router);

//iniziolizzazione formkit
app.use(plugin, defaultConfig(config));

// inizializzazione axios
axios.defaults.baseURL = 'localhost:8081/';
axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem('token');

// inizializzazione auth0
app.use(
  createAuth0({
    domain: 'dev-bmqxc24leqwhyhec.eu.auth0.com',
    clientId: 'YR70390mlpskaBwBrMYclzGmkCLAXfaB',
    authorizationParams: {
      redirect_uri: 'http://localhost:8080/callback',
      //redirect_uri: 'http://www.dietideals.it/callback', 
    },
  })
);

// inizializazione primevue
app.use(PrimeVue);
app.use(ToastService);

// inizializzazione pinia
app.use(createPinia());

// Monta l'app Vue
app.mount('#app');

import { createAuth0 } from '@auth0/auth0-vue'; // auth0
import '@babel/polyfill'; // primevue e primeflex (richiedono babel)
import axios from 'axios';
import 'mutationobserver-shim'; // primevue e primeflex (richiedono mutationobserver)
import { createPinia } from 'pinia'; // pinia
import piniaPluginPersistedState from 'pinia-plugin-persistedstate'; //permanenza stati pinia
import 'primeicons/primeicons.css'; // primevue icons
import PrimeVue from 'primevue/config'; // primevue config
import 'primevue/resources/primevue.min.css'; // core CSS (primevue)
import 'primevue/resources/themes/aura-light-primary/theme.css'; // theme (primevue)
import ToastService from 'primevue/toastservice'; // toast (primevue)
import { createApp } from 'vue'; // Vue 3
import App from './App.vue'; // App.vue
import './assets/css/tailwind.css'; // tailwindcss
import router from './router'; // router
import SockJS from 'sockjs-client'; //modulo per socket
import { Stomp } from '@stomp/stompjs'; //stomp canale 1 via per messaggi

// Crea l'istanza dell'app Vue e usa il router
const app = createApp(App).use(router);

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
const pinia = createPinia();
pinia.use(piniaPluginPersistedState);
app.use(pinia);

// Monta l'app Vue
app.mount('#app');

import { createAuth0 } from '@auth0/auth0-vue'; // auth0 
import '@babel/polyfill'; // primevue e primeflex (richiedono babel)
import 'mutationobserver-shim'; // primevue e primeflex (richiedono mutationobserver)
import { createPinia } from "pinia"; // pinia 
import piniaPluginPersistedstate from "pinia-plugin-persistedstate"; // pinia 
import 'primeicons/primeicons.css'; // primevue icons
import PrimeVue from 'primevue/config'; // primevue config
import 'primevue/resources/primevue.min.css'; // core CSS (primevue)
import 'primevue/resources/themes/md-light-indigo/theme.css'; // theme (primevue)
import ToastService from 'primevue/toastservice'; // toast (primevue)
import { createApp } from 'vue'; // Vue 3 
import App from './App.vue'; // App.vue 
import './assets/css/tailwind.css'; // tailwindcss 
import router from './router'; // router 

// Crea l'istanza dell'app Vue e usa il router
const app = createApp(App).use(router);

// inizializzazione auth0
app.use(
  createAuth0({
    domain: 'dev-bmqxc24leqwhyhec.eu.auth0.com',
    clientId: 'YR70390mlpskaBwBrMYclzGmkCLAXfaB',
    authorizationParams: {
      redirect_uri: 'http://localhost:8080/callback',
    },
  })
);

// inizializazione primevue
app.use(PrimeVue);
app.use(ToastService);

// inizializzazione pinia
const pinia = createPinia();
pinia.use(piniaPluginPersistedstate);
app.use(createPinia());
app.use(pinia);

// Monta l'app Vue
app.mount('#app');

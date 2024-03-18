import { createAuth0 } from '@auth0/auth0-vue';
import '@babel/polyfill';
import 'mutationobserver-shim';
import 'primeicons/primeicons.css'; // icons
import PrimeVue from 'primevue/config'; // here
import 'primevue/resources/primevue.min.css'; // core CSS
import 'primevue/resources/themes/md-light-indigo/theme.css'; // theme
import ToastService from 'primevue/toastservice';
import { createApp } from 'vue';
import App from './App.vue';
import './assets/css/tailwind.css';
import router from './router';




// Crea l'istanza dell'app Vue e usa il router
const app = createApp(App).use(router);
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

// Monta l'app Vue
app.mount('#app');

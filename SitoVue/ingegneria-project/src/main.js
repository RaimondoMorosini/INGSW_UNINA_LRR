import '@babel/polyfill';
import 'mutationobserver-shim';
import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import './assets/css/tailwind.css';
import 'primevue/resources/themes/saga-blue/theme.css'; // theme
import 'primevue/resources/primevue.min.css'; // core CSS
import 'primeicons/primeicons.css'; // icons
import ToastService from 'primevue/toastservice';
import PrimeVue from 'primevue/config'; // here
import { createAuth0 } from '@auth0/auth0-vue';

// da mettere per il login google
import vue3GoogleLogin from 'vue3-google-login';

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

app.use(vue3GoogleLogin, {
  clientId: '523712309350-64ooftdu5bto8ln30pbkfjtr88fjpp15.apps.googleusercontent.com',
});

// Monta l'app Vue
app.mount('#app');

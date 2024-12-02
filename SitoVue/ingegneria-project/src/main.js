import { createAuth0 } from '@auth0/auth0-vue'; // auth0
import '@babel/polyfill'; // primevue e primeflex (richiedono babel)
import axios from 'axios';
import 'mutationobserver-shim'; // primevue e primeflex (richiedono mutationobserver)
import { createPinia } from 'pinia'; // pinia
import piniaPluginPersistedState from 'pinia-plugin-persistedstate'; //permanenza stati pinia
import 'primeicons/primeicons.css'; // primevue icons
import PrimeVue from 'primevue/config'; // primevue config
import Aura from '@primevue/themes/aura';
import { definePreset } from '@primevue/themes';
import ConfirmationService from 'primevue/confirmationservice';
import DialogService from 'primevue/dialogservice';
import ToastService from 'primevue/toastservice';
import { createApp, watch } from 'vue'; // Vue 3
import App from './App.vue'; // App.vue
import './assets/css/tailwind.css'; // tailwindcss
import router from './router'; // router
import { useTokenStore } from './stores/tokenStore';
import AppState from './plugin/appState';
import { Tooltip } from 'primevue';

// Crea l'istanza dell'app Vue e usa il router
const app = createApp(App).use(router);

// inizializzazione pinia
const pinia = createPinia();
pinia.use(piniaPluginPersistedState);

// import state dagli store, testing nuovi modi per fare il persist
//if (localStorage.getItem('state')) {
//    pinia.state.value = JSON.parse(localStorage.getItem('state'));
//}

watch(pinia.state, (state) => {
    localStorage.setItem('state', JSON.stringify(state));
});

app.use(pinia);

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

const tokenStoreInstance = useTokenStore();

const MyPreset = definePreset(Aura, {
    semantic: {
        primary: {
            50: '{fuchsia.50}',
            100: '{fuchsia.100}',
            200: '{fuchsia.200}',
            300: '{fuchsia.300}',
            400: '{fuchsia.400}',
            500: '{fuchsia.500}',
            600: '{fuchsia.600}',
            700: '{fuchsia.700}',
            800: '{fuchsia.800}',
            900: '{fuchsia.900}',
            950: '{fuchsia.950}',
        },
    },
});

// inizializazione primevue
app.use(PrimeVue, {
    ripple: true,
    theme: {
        preset: MyPreset,
        options: {
            prefix: 'p',
            darkModeSelector: 'false',
            cssLayer: false,
        },
    },
});

app.use(AppState);
app.use(ConfirmationService);
app.use(ToastService);
app.use(DialogService);
app.directive('tooltip', Tooltip);
// Monta l'app Vue
app.mount('#app');

import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import './assets/css/tailwind.css';
import './components/menuItem.vue';
//da mettere nei file dove si vuole usare axios
import { createAuth0 } from '@auth0/auth0-vue';


// Crea l'istanza dell'app Vue e usa il router
const app = createApp(App).use(router);
app.use(
    createAuth0({
      domain: "dev-16vw3v8k1jpn1obn.us.auth0.com",
      clientId: "v61nKLi01o3tIHfJivJOsgdJk1PYit1L",
      authorizationParams: {
        redirect_uri: window.location.origin
      }
    })
  );


// Monta l'app Vue
app.mount('#app');

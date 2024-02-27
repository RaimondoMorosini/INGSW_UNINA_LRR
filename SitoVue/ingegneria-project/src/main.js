import '@babel/polyfill'
import 'mutationobserver-shim'
import './plugins/bootstrap-vue'
import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import './assets/css/tailwind.css';
import './components/menuItem.vue';
//da mettere nei file dove si vuole usare axios
import { createAuth0 } from '@auth0/auth0-vue';

//da mettere per il login google
import vue3GoogleLogin from 'vue3-google-login';


// Crea l'istanza dell'app Vue e usa il router
const app = createApp(App).use(router);
/*app.use(
  createAuth0({
    domain: "dev-16vw3v8k1jpn1obn.us.auth0.com",
    clientId: "v61nKLi01o3tIHfJivJOsgdJk1PYit1L",
    authorizationParams: {
      redirect_uri: window.location.origin
    }
  })
);*/

app.use(vue3GoogleLogin,{
    clientId: '523712309350-64ooftdu5bto8ln30pbkfjtr88fjpp15.apps.googleusercontent.com'
  });

// Monta l'app Vue
app.mount('#app');

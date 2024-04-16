import { createRouter, createWebHistory } from 'vue-router';

const token = localStorage.getItem('token');

const ifAuthenticated = (to, from, next) => {
  if (localStorage.getItem(token)) {
    next();
    return;
  }
  router.push({ 
    name: 'login',
    params: {
      returnTo: to.path,
      query: to.query,
    },
  });
 };

const routes = [
  {
    path: '/',
    name: 'home',
    component: () => import('@/views/HomeView.vue'),
  },

  {
    path: '/profilo',
    name: 'profilo',
    
    component: () => import('@/views/ProfiloView.vue'),
    children: [
      { path: '', component: () => import('@/components/Profilo/InformazioniProfilo.vue') },
      {
        path: 'astePersonali',
        component: () => import('@/components/Profilo/AstePersonaliProfilo.vue'),
      },
      { path: 'notifiche', component: () => import('@/components/Profilo/MessaggiProfilo.vue') },
      {
        path: 'impostazioni',
        component: () => import('@/components/Profilo/ImpostazioniProfilo.vue'),
      },
    ],
  },

  {
    path: '/callback',
    name: 'callback',
    component: () => import('@/views/CallbackView.vue'),
  },

  {
    path: '/about-us',
    name: 'about',
    component: () => import('@/views/AboutView.vue'),
  },

  {
    path: '/loginTest',
    name: 'loginTest',
    component: () => import('@/views/LoginView.vue'),
  },

  {
    path: '/astePersonali',
    name: 'astePersonali',
    component: () => import('@/views/AboutView.vue'),
  },

  {
    path: '/categorie',
    name: 'categorie',
    component: () => import('@/views/AboutView.vue'),
  },

  {
    path: '/categorie/elettronica',
    name: 'elettronica',
    component: () => import('@/views/AboutView.vue'),
  },

  {
    path: '/categorie/perlacasa',
    name: 'perLaCasa',
    component: () => import('@/views/AboutView.vue'),
  },
  {
    path: '/categorie/abbigliamento',
    name: 'abbigliamento',
    component: () => import('@/views/AboutView.vue'),
  },
  {
    path: '/categorie/sports',
    name: 'sports',
    component: () => import('@/views/AboutView.vue'),
  },
  {
    path: '/categorie/hobby',
    name: 'hobby',
    component: () => import('@/views/AboutView.vue'),
  },
  {
    path: '/categorie/altro',
    name: 'altro',
    component: () => import('@/views/AboutView.vue'),
  },

  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/LoginView.vue'),
  },

  {
    path: '/asta/:id',
    name: 'asta',
    component: () => import('@/views/AstaView.vue'),
  },

  {
    path: '/creaAsta',
    name: 'creaAsta', 
    component: () => import('@/views/CreazioneAstaView.vue'),
  },

  // testing pages TODO: remove

  {
    path: '/TestRai',
    name: 'TestRai',
    component: () => import('@/views/TestRai.vue'),
  },

  {
    path: '/TestLorenzo',
    name: 'TestLorenzo',
    component: () => import('@/views/TestLorenzo.vue'),
  },

  {
    path: '/testCategorie',
    name: 'testCategorie',
    component: () => import('@/views/TestCategorie.vue'),
  },

  // 404 page catch all
  {
    path: '/:catchAll(.*)',
    name: 'not-found',
    component: () => import('../views/NotFoundView.vue'),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});



export default router;

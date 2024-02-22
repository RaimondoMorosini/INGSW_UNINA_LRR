import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import AboutView from '../views/AboutView.vue'
import TestRai from '../views/TestRai.vue'
import TestCategorie from '../views/TestCategorie.vue'
import AuthLogin from '@/components/AuthLogin'

const routes = [

  {
    path: '/',
    name: 'home',
    component: HomeView
  },

  {
    path: '/about-us',
    name: 'about',
    component: AboutView
  },

  {
    path: '/TestRai',
    name: 'TestRai',
    component : TestRai
  },

  {
    path: '/testCategorie',
    name: 'testCategorie',
    component : TestCategorie
  },
  
  {
    path: '/profilo/astePersonali',
    name: 'astePersonali',
    component: AboutView
  },

  {
    path: '/categorie',
    name: 'categorie',
    component: AboutView
  },

  {
    path: '/categorie/elettronica',
    name: 'elettronica',
    component: AboutView
  },

  {
    path: '/categorie/perlacasa',
    name: 'perLaCasa',
    component: AboutView
  },
  {
    path: '/categorie/abbigliamento',
    name: 'abbigliamento',
    component: AboutView
  },
  {
    path: '/categorie/sports',
    name: 'sports',
    component: AboutView
  },
  {
    path: '/categorie/hobby',
    name: 'hobby',
    component: AboutView
  },

  {
    path: '/profilo/notifiche',
    name: 'notifiche',
    component: AboutView
  },
  {
    path: '/profilo',
    name: 'profilo',
    component: AboutView
  },
  {
    path: '/login',
    name: 'login',
    component: AuthLogin
  },

  // 404 page catch all
  {
    path: '/:catchAll(.*)',
    name: 'not-found',
    component: () => import('../views/NotFoundView.vue')
  }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router

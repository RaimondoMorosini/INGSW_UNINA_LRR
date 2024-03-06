import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import AboutView from '../views/AboutView.vue'
import TestRai from '../views/TestRai.vue'
import TestLorenzo from '../views/TestLorenzo.vue'
import TestCategorie from '../views/TestCategorie.vue'
import AuthLogin from '@/components/AuthLogin'
import LoginView from '@/views/LoginView'
import Callback from '../views/CallbackView.vue'
import Profilo from '../views/ProfiloView.vue'

const routes = [

  {
    path: '/',
    name: 'home',
    component: () => import('@/views/HomeView.vue')
  },

  {
    path: '/profilo',
    name: 'profilo',
    component: () => import('@/views/ProfiloView.vue')
  },

  {
    path: '/callback',
    name: 'callback',
    component: () => import ('@/views/CallbackView.vue')
  },

  {
    path: '/about-us',
    name: 'about',
    component: () => import('@/views/AboutView.vue')
  },

  {
    path: '/loginTest',
    name: 'loginTest',
    component: () => import('@/views/LoginView.vue')
  },
  
  {
    path: '/profilo/astePersonali',
    name: 'astePersonali',
    component: () => import('@/views/AboutView.vue')
  },

  {
    path: '/categorie',
    name: 'categorie',
    component: () => import('@/views/AboutView.vue')
  },

  {
    path: '/categorie/elettronica',
    name: 'elettronica',
    component: () => import('@/views/AboutView.vue')
  },

  {
    path: '/categorie/perlacasa',
    name: 'perLaCasa',
    component: () => import('@/views/AboutView.vue')
  },
  {
    path: '/categorie/abbigliamento',
    name: 'abbigliamento',
    component: () => import('@/views/AboutView.vue')
  },
  {
    path: '/categorie/sports',
    name: 'sports',
    component: () => import('@/views/AboutView.vue')
  },
  {
    path: '/categorie/hobby',
    name: 'hobby',
    component: () => import('@/views/AboutView.vue')
  },
  {
    path: '/categorie/altro',
    name: 'altro',
    component: () => import('@/views/AboutView.vue')
  },

  {
    path: '/profilo/notifiche',
    name: 'notifiche',
    component: () => import('@/views/AboutView.vue')
  },
  {
    path: '/profilo',
    name: 'profilo',
    component: () => import('@/views/AboutView.vue')
  },
  
  {
    path: '/login',
    name: 'login',
    component: () => import ('@/components/AuthLogin.vue')
    component: LoginView
  },

  // testing pages TODO: remove

  {
    path: '/TestRai',
    name: 'TestRai',
    component :() => import ('@/views/TestRai.vue')
  },


  {
    path: '/TestLorenzo',
    name: 'TestLorenzo',
    component :  () => import ('@/views/TestLorenzo.vue')
  },

  {
    path: '/testCategorie',
    name: 'testCategorie',
    component : () => import ('@/views/TestCategorie.vue')
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

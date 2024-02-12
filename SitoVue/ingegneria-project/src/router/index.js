import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import AboutView from '../views/AboutView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },

  {
    path: '/about',
    name: 'about',
    component: AboutView
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

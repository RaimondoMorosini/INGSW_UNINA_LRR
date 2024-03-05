import { createRouter, createWebHistory } from 'vue-router'

const routes = [

  {
    path: '/',
    name: 'home',
    component: () => import('@/views/HomeView.vue')
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

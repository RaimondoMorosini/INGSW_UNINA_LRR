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
    path: '/about-us',
    name: 'about',
    component: AboutView
  },

  {
    path: '/testRai',
    name: 'testRai',
    component: () => import('../views/TestRAi.vue')
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

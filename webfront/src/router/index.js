 import Layout from '@/layout/Layout.vue'
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path:'/',
      name:'layout',
      component:Layout,
      children:[
        {
          path:'user',
          name:'user',
          component: () => import('../views/User.vue')
        },
        {
          path:'club',
          name:'club',
          component:()=> import('../views/Club.vue')
        },
        {
          path:'password',
          name:'password',
          component:()=> import('../views/Password.vue')

        },
        {
          path:'myApplyToJoin',
          name:'myApplyToJoin',
          component:()=> import('../views/MyApplyToJoin.vue')

        },
        {
          path:'MyClubAdmission',
          name:'MyClubAdmission.vue',
          component:()=> import('../views/MyClubAdmission.vue')
        },
        {
          path:'MyClubs',
          name:'MyClubs',
          component:()=> import('../views/MyClubs.vue')
        },
        {
          path:'starClub',
          name:'starClub',
          component:()=> import('../views/StarClub.vue')
        },
        {
          path:'applyToSet',
          name:'applyToSet',
          component:()=> import('../views/ApplyToSet.vue')
        },
        {
          path:'person',
          name:'person',
          component:()=> import('../views/Person.vue')
        },
        {
          path:'Im',
          name:'Im',
          component:()=> import('../views/Im.vue')
        },
        {
          path:'setClubAddmission',
          name:'setClubAddmission',
          component:()=> import('../views/SetClubAddmission.vue')
        }
        
        
      ]
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('../views/RegisterView.vue')
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/LoginView.vue')
    }
  ]
})

export default router

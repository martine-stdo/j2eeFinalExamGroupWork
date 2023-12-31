import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'login',
        component: () => import('../views/Login.vue')
    },
    {
        path: '/main',
        name: 'Index',
        component: () => import('../views/Index.vue'),
        children: [
            {
                path: '/main',
                name: 'Main',
                component: () => import('../views/Main.vue')
            },
            {
                path: '/admin',
                name: 'Admin',
                component: () => import('../views/Admin.vue')
            }
        ]
    },
    /*{
        path: '/about',
        name: 'about',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/!* webpackChunkName: "about" *!/ '../views/AboutView.vue')
    }*/
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router

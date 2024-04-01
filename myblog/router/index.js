import {createRouter, createWebHistory} from 'vue-router'
import routes from './routes'

const router = createRouter({
    base: '/myblog/',
    history: createWebHistory(), 
    routes
})


export default router

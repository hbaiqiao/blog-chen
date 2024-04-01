import homePage from '../view/homePage.vue'
import Home from '../view/Home'
const routes = [
    {  
        path: '/',
        component: Home
    },
    {
        name: 'detailPage',
        path: '/detail',
        component: () => import('../src/components/Main.vue')
    },
    {
        name:'home',
        path:'/home',
        component:() => import('../view/Home')
    },
    {
        name:'carousel',
        path:'/carousel',
        component:() => import('../view/Carousel')
    },
    {
        name:'index',
        path:'/index',
        component:()=> import('../view/Index')
    },
    {
        name:'article',
        path:'/article',
        component:()=> import('../view/Article')
    },
    {
        name:'articleDetail',
        path:'/articleDetail',
        component:()=> import('../view/ArticleDetail')
    },
    {
        name:'music',
        path:'/music',
        component:()=> import('../view/Music')
    },
    {
        name:'musicDetail',
        path:'/musicDetail',
        component:()=> import('../view/MusicDetail')
    },
    {
        name:'vlog',
        path:'/vlog',
        component:()=> import('../view/Vlog')
    },
    {
        name:'vlogDetail',
        path:'/vlogDetail',
        component:()=> import('../view/VlogDetail')
    },
    {
        name:'photo',
        path:'/photo',
        component:()=> import('../view/Photo')
    },
    {
        name:'photoDetail',
        path:'/photoDetail',
        component:()=> import('../view/PhotoDetail')
    },
    {
        name:'login',
        path:'/login',
        component:()=> import('../view/Login')
    },
    {
        name:'manager',
        path:'/manager',
        // component:()=> import('../view/Article'),
        children:[
            {
                name:'articleAdd',
                path:'article',
                component:()=> import('../viewManager/ArticleAdd'),
                meta: {                               //路由拦截标志
                    requireAuth: true
                }
            },
            {
                name:'musicAdd',
                path:'music',
                component:()=> import('../viewManager/MusicAdd'),
                meta: {
                    requireAuth: true
                }
            },
            {
                name:'vlogAdd',
                path:'vlog',
                component:()=> import('../viewManager/VlogAdd'),
                meta: {
                    requireAuth: true
                }
            },
            {
                name:'photoAdd',
                path:'photo',
                component:()=> import('../viewManager/PhotoAdd'),
                meta: {
                    requireAuth: true
                }
            },
        ]
    },
];

export default routes

import { createRouter, createWebHistory } from 'vue-router';
import TodoMain from './views/TodoMain.vue';

const routes = [
    {
        path: '/',
        name: 'TodoMain',
        component: TodoMain
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;
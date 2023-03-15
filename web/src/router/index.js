import { createRouter, createWebHashHistory } from 'vue-router';



const routes = [
    {
        path: '/',
        redirect: '/student/login'
    },
    {
        path: '/student/login',
        component: () => import('../views/student/Login.vue')
    },
    {
        path: '/student/register',
        component: () => import('../views/student/Register.vue')
    },
    {
        path: '/student/home',
        component: () => import('../views/student/Index.vue'),
        children: [
            {
                path: '/student/home/UserInfo',
                component: () => import('../components/student/UserInfo.vue')
            },
            {
                path: '/student/home/StudentError',
                component: () => import('../components/student/StudentError.vue')
            },
            {
                path: '/student/home/StudentHomework/:id',
                component: () => import('../components/student/StudentHomework.vue')
            },
            {
                path: '/student/home/HomeWorkDetail',
                component: () => import('../components/student/HomeWorkDetail.vue')
            },
            {
                path: '/student/home/TeacherLook',
                component: () => import('../components/student/TeacherLook.vue')
            },
            {
                path: '/student/home/TeacherNotLook',
                component: () => import('../components/student/TeacherNotLook.vue')
            },
            {
                path: '/student/home/ResultDetail',
                component: () => import('../components/student/ResultDetail.vue')
            },
            {
                path: '/student/home/Native',
                component: () => import('../components/student/Native.vue')
            },
            {
                path: '/student/home/NativeDetail',
                component: () => import('../components/student/NativeDetail.vue')
            },
        ]
    },
    {
        path: '/teacher',
        component: () => import('../views/teacher/Login.vue')
    },
    {
        path: '/teacher/login',
        component: () => import('../views/teacher/Login.vue')
    },
    {
        path: '/teacher/register',
        component: () => import('../views/teacher/Register.vue')
    },
    {
        path: '/teacher/home',
        component: () => import('../views/teacher/Index.vue'),
        children: [
            {
                path: '/teacher/home/HomwWork',
                component: () => import('../components/teacher/HomeWork.vue')
            },
            {
                path: '/teacher/home/HomwWorkAdd',
                component: () => import('../components/teacher/HomwWorkAdd.vue')
            },
            {
                path: '/teacher/home/TeacherInfo',
                component: () => import('../components/teacher/TeacherInfo.vue')
            },
            {
                path: '/teacher/home/UpdataWork',
                component: () => import('../components/teacher/UpdataWork.vue')
            },
            {
                path: '/teacher/home/UpdataWorkDetail',
                component: () => import('../components/teacher/UpdateWorkDetail.vue')
            },
            {
                path: '/teacher/home/DataInfo',
                component: () => import('../components/teacher/DataInfo.vue')
            }
        ]
    },
    {
        path: '/admin',
        component: () => import('../views/admin/Login.vue')
    },
    {
        path: '/admin/login',
        component: () => import('../views/admin/Login.vue')
    },
    {
        path: '/admin/home',
        component: () => import('../views/admin/Index.vue'),
        children: [
            {
                path: '/admin/home/Student',
                component: () => import('../components/admin/Student.vue')
            },
            {
                path: '/admin/home/Teacher',
                component: () => import('../components/admin/Teacher.vue')
            },
            {
                path: '/admin/home/ClassRoom',
                component: () => import('../components/admin/ClassRoom.vue')
            },
            {
                path: '/admin/home/ClassRoomAdd',
                component: () => import('../components/admin/ClassRoomAdd.vue')
            },
            {
                path: '/admin/home/Subject',
                component: () => import('../components/admin/Subject.vue')
            },
            {
                path: '/admin/home/SubjectAdd',
                component: () => import('../components/admin/SubjectAdd.vue')
            },
            {
                path: '/admin/home/Notice',
                component: () => import('../components/admin/Notice.vue')
            },
            {
                path: '/admin/home/NoticeAdd',
                component: () => import('../components/admin/NoticeAdd.vue')
            },
            {
                path: '/admin/home/NoticeUpdate',
                component: () => import('../components/admin/NoticeUpdate.vue')
            }
        ]
    },
]

const router = createRouter(
    {
        history: createWebHashHistory(),
        routes: routes
    }
)

export default router
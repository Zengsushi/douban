import { createRouter, createWebHistory } from 'vue-router'


import Login from "../views/User/Login.vue"
import Register from "../views/User/Register.vue"
import Home from "../views/Home.vue"

import MovieList from "../views/Movie/MovieList.vue"

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path : '/' ,
      name : "登录" ,
      component: Login,
    } ,
    {
      path : "/register" ,
      name : "注册" ,
      component : Register
    }, {
      path : "/home" ,
      name : "首页" ,
      component : Home, 
      children: [
        {
          path: "movieList", 
          name : "电影列表" ,
          component : MovieList
        }
      ]

    }
  ],
})

export default router

<template>
    <div class="loginP">
      <div class="login-form">
        <h1>注册</h1>
        <label for="username">账号</label>
        <el-input 
            id="username"
            v-model="user.name" 
            class="login-input" 
            placeholder="请输入账号" 
        />
        
        <label for="password">密码</label>
        <el-input 
            id="password"
            v-model="user.pass" 
            class="login-input" 
            placeholder="请输入密码" 
            type="password" 
        />
        
        <label for="confirm-password">确认密码</label>
        <el-input 
            id="confirm-password"
            v-model="newpass" 
            class="login-input" 
            placeholder="请确认密码" 
            type="password" 
        />
        <el-button class="login-button" type="primary" @click="handleRegister">
          注册
        </el-button>
        <router-link to="/">已有账户? 登录</router-link>
      </div>
    </div>
  </template>
  
  <script lang="ts" setup>
  import { ref, useTransitionState } from 'vue'
  import router from "../../router/index"
  import { UesrRegister }  from "../api/index"
    const user = ref({
      name: '',
      pass: ''
    })
    const newpass = ref('')
    
    const isSubmitting = ref(false)  // 控制提交状态
    const errors = ref({
      user: false,
      pass: false
    })

    const handleRegister = async () => {
        if(user.value.name === '' || user.value.pass === '') {
            alert('账号或密码不能为空')
            return
        }
        if(user.pass !== newpass){
            alert('两次密码输入不一致')
            return
        }
        
        const res = await UesrRegister(user.value)
        if(res.data.code === 0) {
            alert("注册成功")
            router.push('/login')
        }else if(res.data.code == 1){
            alert(res.data.message)
        }else{
            alert("服务器异常!")
        }
    }



  </script>
  
  <style scoped>
  /* 登录框父容器 */
  .loginP {
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh; /* 让登录框垂直居中，填满整个视口 */
      width: 100%;   /* 宽度适应 */
      background-color: #f0f8ff; /* 更柔和的背景颜色 */
      box-sizing: border-box;
      padding: 20px;
  }
  
  /* 表单容器 */
  .login-form {
      background-color: #ffffff; /* 白色背景 */
      padding: 40px;
      border-radius: 10px; /* 圆角 */
      box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.1); /* 阴影效果 */
      width: 100%;
      max-width: 450px; /* 最大宽度 */
      display: flex;
      flex-direction: column;
      gap: 20px; /* 元素间距 */
      text-align: center;
  }
  
  /* 标题样式 */
  h1 {
      font-size: 28px;
      font-weight: bold;
      margin-bottom: 20px; /* 标题和表单的间距 */
      color: #333;
  }
  
  /* 标签样式 */
  label {
      font-size: 16px;
      font-weight: 500;
      margin-bottom: 8px;
      text-align: left;
      color: #666;
  }
  
  /* 输入框样式 */
  .login-input {
      width: 100%;
      margin: 0 auto;
      margin-bottom: 12px; /* 输入框底部间距 */
  }
  
  /* 按钮样式 */
  .login-button {
      width: 100%;
      height: 45px;
      font-size: 16px;
      margin-top: 20px; /* 上方间距 */
      border-radius: 5px; /* 按钮圆角 */
  }
  
  /* 悬停按钮效果 */
  .login-button:hover {
      background-color: #3f7bff; /* 悬停时按钮颜色变化 */
      border-color: #3f7bff;
  }
  
  /* 响应式设计：小屏幕下缩小登录框 */
  @media (max-width: 768px) {
      .login-form {
          width: 80%; /* 宽度为视口的80% */
      }
  }
  
  /* 小屏幕时，登录框更大 */
  @media (max-width: 480px) {
      .login-form {
          width: 90%; /* 更适合小屏幕 */
      }
  }
  
  /* 处理链接样式 */
  .router-link {
      font-size: 14px;
      color: #007BFF;
      text-decoration: none;
      margin-top: 10px;
  }
  
  /* 链接悬停效果 */
  .router-link:hover {
      text-decoration: underline;
  }
  </style>
  
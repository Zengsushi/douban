<template>
    <div class="loginP">
        <div class="login-form">
            <h1>登录</h1>
            <label for="username">账号</label>
            <el-input 
                id="username"
                v-model="user.name" 
                class="login-input" 
                placeholder="请输入账号" 
                :class="{'input-error': errors.username}" 
            />
            
            <label for="password">密码</label>
            <el-input 
                id="password"
                v-model="user.pass" 
                class="login-input" 
                placeholder="请输入密码" 
                type="password" 
                :class="{'input-error': errors.password}" 
            />
            
            <el-button 
                class="login-button" 
                type="primary" 
                @click="handleLogin" 
                :disabled="isSubmitting"
            >
                登录
            </el-button>
            <div class="my-4 ml-4">
                <el-radio-group v-model="radio2">
                <el-radio value="0" size="small">管理员</el-radio>
                <el-radio value="1" size="small">普通用户</el-radio>
                </el-radio-group>
            </div>
            <router-link to="/register" class="register-link">没有账号？注册</router-link>
        </div>
    </div>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import { AdminLogin  ,UserLogin} from '../api/index'
import router from "../../router/index"
    const user = ref({
      name: '',
      pass: ''
    })
    const radio2 = ref("0")
    
    const isSubmitting = ref(false)  // 控制提交状态
    const errors = ref({
      username: false,
      password: false
    })

    const handleLogin = async () => {
        if(user.value.name === '' || user.value.pass === '') {
            alert('账号或密码不能为空')
            return
        }
        if( radio2.value === "0"){
            const res = await AdminLogin(user.value) 
            if(res.data.code === 0) {
                alert("登录成功")
                router.push('/home')
            }else if(res.data.code == 1){
                alert(res.data.message)
            }else{
                alert("服务器异常!")
            }
        }else {
            const res = await UserLogin(user.value) 
            if(res.data.code === 0) {
                alert("登录成功")
                // router.push('/')
            }else if(res.data.code == 1){
                alert(res.data.message)
            }else{
                alert("服务器异常!")
            }
        }
    }



</script>

<style scoped>
/* 登录框父容器 */
.loginP {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    width: 100%;
    background-color: #f0f8ff;
    box-sizing: border-box;
    text-align: center;
}

/* 标题样式 */
h1 {
    font-size: 32px;
    font-weight: bold;
    margin-bottom: 20px;
    color: #333;
}

/* 表单容器 */
.login-form {
    background-color: #ffffff;
    padding: 40px;
    border-radius: 10px;
    box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.1);
    width: 100%;
    max-width: 400px;
    display: flex;
    flex-direction: column;
    gap: 20px;
}

/* 标签样式 */
label {
    font-size: 16px;
    font-weight: 500;
    margin-bottom: 5px;
    text-align: left;
    color: #666;
}

/* 输入框样式 */
.login-input {
    width: 100%;
    margin: 0 auto;
}

/* 错误样式 */
.input-error {
    border-color: red !important;
}

/* 按钮样式 */
.login-button {
    width: 100%;
    height: 40px;
    font-size: 16px;
    margin-top: 20px;
}

/* 禁用按钮的样式 */
.login-button[disabled] {
    background-color: #e0e0e0;
    cursor: not-allowed;
}

/* 悬停按钮效果 */
.login-button:hover:not([disabled]) {
    background-color: #3f7bff;
    border-color: #3f7bff;
}

/* 注册链接 */
.register-link {
    display: block;
    margin-top: 10px;
    color: #007bff;
    text-decoration: none;
}

.register-link:hover {
    text-decoration: underline;
}

/* 响应式设计：小屏幕下缩小登录框 */
@media (max-width: 768px) {
    .login-form {
        width: 80%;
    }
}

/* 小屏幕时，登录框更大 */
@media (max-width: 480px) {
    .login-form {
        width: 90%;
    }
}
</style>

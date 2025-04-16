<template>
  <div class="common-layout">
    <el-container class="layout-container">
      <!-- Aside -->
      <el-aside width="228px" class="layout-aside">
        <el-col :span="24">
          <h5 class="mb-2">系统</h5>
          <el-menu
            default-active="2"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose"
            :collapse="isCollapsed"
            :unique-opened="true"
          >
            <el-sub-menu index="1">
              <template #title>
                <el-icon><location /></el-icon>
                <span>电影</span>
              </template>
              <el-menu-item index="4" @click="toList()">
                <span>信息列表</span>
              </el-menu-item>
              <el-menu-item index="5" @click="toUpdate()">
                <span>信息修改</span>
              </el-menu-item>
            </el-sub-menu>
          </el-menu>
        </el-col>
      </el-aside>

      <el-container class="main-container">
        <!-- Header -->
        <el-header class="layout-header">
          标题
          
        </el-header>

        <!-- Main Content -->
        <el-main class="layout-main">
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script lang="ts" setup>
import { ref } from 'vue';
import router from '@/router/index';
import { Location, Document, Setting } from '@element-plus/icons-vue';
import { RouterView } from 'vue-router';

const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath);
};

const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath);
};

// 控制菜单的折叠状态
const isCollapsed = ref(false);

const toList = () => {
  router.push("/home/movieList");
};

const toUpdate = () => {
  router.push("/home/movieUpdate");
};
</script>

<style scoped>
.layout-container {
  display: flex;
  min-height: 100vh;
}

.layout-aside {
  background-color: #f4f6f9;
  padding: 20px;
  box-shadow: 2px 0 10px rgba(0, 0, 0, 0.1);
  border-right: 1px solid #e4e7ed;
  transition: all 0.3s ease;
}

.layout-header {
  background-color: #409EFF;
  color: white;
  text-align: center;
  padding: 14px 0;
  font-size: 18px;
  font-weight: bold;
}

.layout-main {
  flex-grow: 1;
  padding: 20px;
  background-color: #ffffff;
  box-shadow: inset 0 0 10px rgba(0, 0, 0, 0.05);
  transition: all 0.3s ease;
}

.el-menu-vertical-demo {
  width: 100%;
}

.el-menu-item,
.el-sub-menu__title {
  font-size: 14px;
  padding: 12px 20px;
  border-radius: 4px;
  transition: background-color 0.2s ease, color 0.2s ease;
}

.el-menu-item:hover,
.el-sub-menu__title:hover {
  background-color: #409eff;
  color: white;
}

.el-menu-item.disabled {
  color: #ccc;
}

@media (max-width: 768px) {
  .layout-container {
    flex-direction: column;
  }

  .layout-aside {
    width: 100%;
    padding: 15px;
    box-shadow: none;
    margin-bottom: 10px;
  }

  .layout-header {
    font-size: 16px;
  }

  .layout-main {
    padding: 15px;
  }
}

</style>

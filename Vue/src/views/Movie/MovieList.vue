<template>
    <div class="search-container">
      <!-- 搜索框和筛选器 -->
      <el-input v-model="searchStr" placeholder="请输入电影名称" clearable class="search-input" />
      <el-button @click="searchMovie" type="primary" size="small" class="search-button">搜索</el-button>
  
      <el-form-item label="地区">
        <el-select v-model="movieType" placeholder="选择地区" class="search-region">
          <el-option v-for="value in region" :key="value.id" :label="value.name" :value="value.name" />
        </el-select>
      </el-form-item>
  
      <el-form-item label="类型">
        <el-select v-model="movieGenre" placeholder="选择类型" class="search-region">
          <el-option v-for="value in type" :key="value.id" :label="value.name" :value="value.name" />
        </el-select>
      </el-form-item>
    </div>
  
    <!-- 电影数据表格 -->
    <el-table :data="arr" style="width: 100%">
      <el-table-column fixed prop="id" label="id" width="150" />
      <el-table-column prop="title" label="名称" width="180" />
      <el-table-column prop="year" label="上映年份" width="120" />
      <el-table-column prop="region" label="出品国家" width="120" />
      <el-table-column prop="type" label="类型" width="120" />
      <el-table-column prop="director" label="导演" width="150" />
      <el-table-column prop="ctors" label="主要演员" width="150" />
      <el-table-column prop="count" label="评分人数" width="120" />
      <el-table-column prop="score" label="评分" width="120" />
      <el-table-column prop="star" label="评分星级" width="120" />
      <el-table-column prop="url" label="海报" width="180">
        <template #default="{ row }">
          <img v-if="row.url" :src="row.url" alt="加载失败" class="movie-poster" />
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" min-width="150">
        <template #default="{ row }">
          <el-popconfirm title="确认删除?" @confirm="handleDelete(row)" placement="left">
            <template #reference>
              <el-button link type="primary" size="small">删除</el-button>
            </template>
          </el-popconfirm>
          <el-button link type="primary" size="small" @click="openDrawer(row)">修改</el-button>
        </template>
      </el-table-column>
    </el-table>
  
    <!-- 分页 -->
    <el-pagination
    :current-page="mvPage.page"
    :page-size="mvPage.size"
    :total="totalItems"
    layout="total, sizes, prev, pager, next"
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange"
  />
    <el-drawer v-model="drawer" title="修改电影信息" :with-header="false">
      <h3>修改电影信息</h3>
      <el-form :model="tempMovie" label-width="auto" style="max-width: 600px">
        <el-form-item label="电影名称">
          <el-input v-model="tempMovie.title" />
        </el-form-item>
        <el-form-item label="上映时间">
          <el-input v-model="tempMovie.year" />
        </el-form-item>
        <el-form-item label="出品国家">
          <el-input v-model="tempMovie.region" />
        </el-form-item>
        <el-form-item label="类型">
          <el-input v-model="tempMovie.type" />
        </el-form-item>
        <el-form-item label="导演">
          <el-input v-model="tempMovie.director" />
        </el-form-item>
        <el-form-item label="主要演员">
          <el-input v-model="tempMovie.ctors" />
        </el-form-item>
        <el-form-item label="评分人数">
          <el-input v-model="tempMovie.count" />
        </el-form-item>
        <el-form-item label="评分">
          <el-input v-model="tempMovie.score" />
        </el-form-item>
        <el-form-item label="评分星级">
          <el-input v-model="tempMovie.star" />
        </el-form-item>
        <el-form-item label="海报地址">
          <el-input v-model="tempMovie.url" />
        </el-form-item>
        <el-button type="primary" @click="updateMovie()">保存</el-button>
        <el-button type="primary" @click="drawer = false;">取消</el-button>
      </el-form>
    </el-drawer>
  </template>
  


  <script lang="ts" setup>
  import { ref, onMounted, watch } from 'vue';
  import { searchMovieInfo, MovieList, MovieDetail,  getRegionList, getTypeList , 
    getMovieCount , updateMovieInfo
   } from "../api/index";
  import { type Movie, type Region, type Type , type MvPage } from "../../pojo/index";
  import { ElMessage } from 'element-plus'


  const arr = ref<Movie[]>([]);  // 存储所有电影数据
  const region = ref<Region[]>([]);  // 地区列表
  const type = ref<Type[]>([]);  // 类型列表

  const searchStr = ref("");  // 搜索字符串
  const movieType = ref<string | null>(null);  // 选中的地区
  const movieGenre = ref<string | null>(null);  // 选中的类型

  const drawer = ref(false);  // 抽屉是否显示
  const tempMovie = ref<Movie | null>(null);  // 临时保存的电影信息
  
  const totalItems = ref(0);  // 总条数
  const pageSize = ref(10);  // 每页显示的条数，设置为10
  const currentPage = ref(1);  // 当前页码
  
  const mvPage = ref<MvPage>({ page: 1, size: 10 });  // 电影分页信息

  
  // 获取电影数据
  const getMovie = async () => {
    const res = await MovieList(mvPage.value);
    if(res.data.code === 0){
        arr.value = res.data.data 
    }else{
        alert(res.data.message)
    }
  };
  const updateMovie =async () => {
        const res = await updateMovieInfo(tempMovie.value) ;
        if(res.data.code === 0 ){
            drawer.value = false;
            ElMessage({
                message: '提交成功.',
                type: 'success',
            })
            getMovie() ;
        }else{
            ElMessage({
                message: '提交失败.',
                type: 'error',
            })
        }
  }
  
  // 分页数量
  const handleSizeChange = (newSize: number) => {    
    mvPage.value.size = newSize;  // 更新每页显示的条数
    mvPage.value.page = 1;  // 每次改变页大小时，重新设置为第一页
    getMovie()  // 重新获取数据
  };
  
  // 当前页变更
  const handleCurrentChange = (newPage: number) => {
    mvPage.value.page = newPage;  // 更新当前页码
    getMovie()  // 重新获取数据
  };

  
  // 搜索电影
  const searchMovie = async() => {
    const res = await searchMovieInfo({ title: searchStr.value,
        region: movieType.value, 
        type: movieGenre.value });
    arr.value = res.data.data;  
    
  };
  
  // 删除电影
  const handleDelete = async (row: Movie) => {
    try {
      // 假设有一个删除接口 MovieDetail
      const res = await MovieDetail(row);
      if (res.data.code === 0) {
        arr.value = arr.value.filter(movie => movie.id !== row.id);  // 从列表中删除电影
        totalItems.value -= 1;  // 总条数减1
        alert("删除成功");
      } else {
        alert("删除失败");
      }
    } catch (error) {
      console.error("删除电影失败:", error);
      alert("删除失败");
    }
  };

  const getMvTotal = async() => {
    const res = await getMovieCount() ;
    if( res.data.code === 0 ){
        totalItems.value = res.data.data ;        
    }else{
        alert(res.data.message) ;
    }
  }
  
  // 打开修改抽屉
  const openDrawer = (row: Movie) => {
    tempMovie.value = { ...row };  // 将选中的电影信息存入临时变量
    drawer.value = true;  // 打开抽屉
  };
  
  // 获取地区和类型数据
  const getList = async (fun: () => Promise<any>, data: any) => {
    const res = await fun();
    if (res.data.code === 0) {
      data.value = res.data.data;  // 更新地区或类型列表
    } else {
      alert(res.data.message);
    }
  };
  

  // 初始化获取电影数据，地区，和类型
  onMounted(() => {
    getMovie() ;
    getMvTotal() ;    
    getList(getRegionList, region);  // 获取地区数据
    getList(getTypeList, type);  // 获取类型数据
  });
  </script>
  

<style scoped>
.movie-poster {
  width: 100%;
  height: auto;
  border-radius: 8px;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
  object-fit: cover;
}

.search-container {
  margin-bottom: 20px;
  display: flex;
  flex-wrap: wrap;
}

.search-input {
  width: 40%;
  height: 32px;
  margin-right: 20px;
}

.search-button {
  width: 80px;
  height: 32px;
  margin-right: 20px;
}

.search-region {
  width: 100px;
  margin-right: 20px;
}

.el-drawer__header {
  display: none;
}
</style>  

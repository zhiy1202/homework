<template>
  <div class="index-container">
    <el-container>
      <!-- <el-header class="header">
        <el-carousel :interval="5000" arrow="always">
          <el-carousel-item v-for="(item, index) in tableData" :key="index">
            <img :src="item.path" alt="图片加载失败" />
          </el-carousel-item>
        </el-carousel>
      </el-header> -->
      <el-container>
        <el-aside class="aside">
          <el-menu
            default-active="2"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose"
            :router="true"
          >
            <el-sub-menu index="1">
              <template #title>
                <el-icon><location /></el-icon>
                <span>作业管理</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/teacher/home/HomwWork">所有作业</el-menu-item>
              </el-menu-item-group>
              <el-menu-item-group title="留守儿童问题">
                <template #title><span>操作</span></template>
                <el-menu-item index="/teacher/home/HomwWorkAdd">发布新作业</el-menu-item>
                <el-menu-item index="/teacher/home/UpdataWork">修改学生作业</el-menu-item>
              </el-menu-item-group>
            </el-sub-menu>

            <el-sub-menu index="2">
              <template #title>
                <el-icon><location /></el-icon>
                <span>查看数据统计</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/teacher/home/DataInfo">及格统计</el-menu-item>
              </el-menu-item-group>
            </el-sub-menu>

            <el-menu-item index="/teacher/home/TeacherInfo">
              <el-icon><setting /></el-icon>
              <template #title>个人信息</template>
            </el-menu-item>
            <el-menu-item @click="exit" index="/teacher">
              <el-icon><setting /></el-icon>
              <template #title>退出登录</template>
            </el-menu-item>
          </el-menu>
        </el-aside>

        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
const exit = ()=>{
  $axios.get('front/user/teacher/exit')
}
</script>

<style>
</style>
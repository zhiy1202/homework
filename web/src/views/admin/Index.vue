<template>
  <div class="index-container">
    <el-container>
      <el-header class="header">
        <el-carousel :interval="5000" arrow="always">
          <el-carousel-item v-for="(item, index) in tableData" :key="index">
            <img :src="item.path" alt="图片加载失败" />
          </el-carousel-item>
        </el-carousel>
      </el-header>
      <el-container>
        <el-aside class="aside">
          <el-menu
            default-active="2"
            class="el-menu-vertical-demo"
            :collapse="isCollapse"
            @open="handleOpen"
            @close="handleClose"
            :router="true"
          >
          <el-sub-menu index="1">
              <template #title>
                <el-icon><location /></el-icon>
                <span>学生管理</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/admin/home/Teacher">教师</el-menu-item>
                <el-menu-item index="/admin/home/Student">学生</el-menu-item>
              </el-menu-item-group>
            </el-sub-menu>

            <el-sub-menu index="2">
              <template #title>
                <el-icon><location /></el-icon>
                <span>班级管理</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/admin/home/ClassRoom">所有班级</el-menu-item>
              </el-menu-item-group>
              <el-menu-item-group title="操作">
                <el-menu-item index="/admin/home/ClassRoomAdd">新增</el-menu-item>
              </el-menu-item-group>
            </el-sub-menu>

            <el-sub-menu index="3">
              <template #title>
                <el-icon><document /></el-icon>
                <span>科目管理</span>
              </template>
              <el-menu-item-group>
                <!-- <template #title><span>孩子所需</span></template> -->
                <el-menu-item index="/admin/home/Subject">所有科目</el-menu-item>
              </el-menu-item-group>
              <el-menu-item-group title="操作">
                <el-menu-item index="/admin/home/SubjectAdd">新增</el-menu-item>
              </el-menu-item-group>
            </el-sub-menu>

            <el-sub-menu index="4">
              <template #title>
                <el-icon><document /></el-icon>
                <span>公告管理</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/admin/home/Notice">所有公告</el-menu-item>
              </el-menu-item-group>
              <el-menu-item-group title="操作">
                <el-menu-item index="/admin/home/NoticeAdd">新增</el-menu-item>
              </el-menu-item-group>
            </el-sub-menu>

            <el-menu-item @click="exit" index="/admin">
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
const exit = async ()=>{
  let res = await $axios.get('/admin/user/exit')
  console.log(res.data.data);
  if (res.data.data) {
    alert(res.data.message)
  }
}
</script>

<style>
</style>
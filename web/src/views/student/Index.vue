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
            :collapse="isCollapse"
            @open="handleOpen"
            @close="handleClose"
            :router="true"
          >
            <el-sub-menu index="1">
              <template #title>
                <el-icon><location /></el-icon>
                <span>我的作业</span>
              </template>
              <el-menu-item-group>
                <template #title><span>科目</span></template>
                <template v-for="item in tableData" :key="item.id">
                  <el-menu-item index="#" @click="go(item.id)">{{
                    item.subjectName
                  }}</el-menu-item>
                </template>
              </el-menu-item-group>
            </el-sub-menu>
            <!-- <el-menu-item index="/student/home/StudentError">
              <el-icon><setting /></el-icon>
              <template #title>我的错题</template>
            </el-menu-item> -->
            <el-sub-menu index="2">
              <template #title>
                <el-icon><location /></el-icon>
                <span>我的答题</span>
              </template>
              <el-menu-item-group>
                <template #title><span>批阅</span></template>
                <el-menu-item index="/student/home/TeacherLook">已批阅</el-menu-item>
                <el-menu-item index="/student/home/TeacherNotLook">未批阅</el-menu-item>
              </el-menu-item-group>
            </el-sub-menu>

            <el-menu-item index="/student/home/Native">
              <el-icon><setting /></el-icon>
              <template #title>公告</template>
            </el-menu-item>
            <el-menu-item index="/student/home/UserInfo">
              <el-icon><setting /></el-icon>
              <template #title>个人信息</template>
            </el-menu-item>
            <el-menu-item @click="exit" index="/">
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
import { ref } from "vue";
import { useRouter } from "vue-router";
const router = useRouter();
const exit = () => {
  $axios.get("front/user/student/exit");
};
const go = (id) => {
  console.log(id);
  router.push(`/student/home/StudentHomework/${id}`);
};
var tableData = ref({});
const getData = async () => {
  let res = await $axios.get("/admin/subject/all");
  console.log(res.data);
  tableData.value = res.data.data;
};
getData();
</script>
  
  <style>
</style>
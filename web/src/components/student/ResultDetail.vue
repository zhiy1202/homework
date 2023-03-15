<template>
    <div>
      <el-button @click="post">提交</el-button>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column label="题目" width="180">
          <template #default="scope">
            <div style="display: flex; align-items: center">
              <el-icon><timer /></el-icon>
              <span style="margin-left: 10px">{{ scope.row.title }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="答案" width="180">
          <template #default="scope">
            <div style="display: flex; align-items: center">
              <el-icon><timer /></el-icon>
              <span style="margin-left: 10px">{{ scope.row.answer }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="分数" width="180">
          <template #default="scope">
            <div style="display: flex; align-items: center">
              <el-icon><timer /></el-icon>
              <span style="margin-left: 10px">{{ scope.row.score }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="学生作答" width="180">
          <template #default="scope">
            <div style="display: flex; align-items: center">
              <el-icon><timer /></el-icon>
              <span style="margin-left: 10px">{{ scope.row.myAnswer }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="评分" width="180">
          <template #default="scope">
            <div style="display: flex; align-items: center">
              <el-icon><timer /></el-icon>
              <el-input v-model.number="scope.row.myScore" disabled></el-input>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="评语" width="180">
          <template #default="scope">
            <div style="display: flex; align-items: center">
              <el-icon><timer /></el-icon>
              <el-input v-model="scope.row.comment" disabled></el-input>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </template>
  
  <script setup>
  import { ref } from "vue";
  import { useRouter } from "vue-router"
  
  let router = useRouter()
  let tableData = ref({})
  let {id} = router.currentRoute.value.query
  console.log(id);
  
  
  const getData = async ()=>{
      let res = await $axios.get(`/resultEnd/allResultDetail/${id}`)
      console.log(res.data);
      tableData.value = res.data.data
  }
  getData()
  </script>
  
  <style>
  </style>
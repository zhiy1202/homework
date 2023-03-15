<template>
    <div>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column label="作业名" prop="p1.homeworkName" />
        <el-table-column label="及格人数" prop="p2.pass" />
        <el-table-column label="不及格人数" prop="p2.notPass" />
      </el-table>
    </div>
  </template>
  
  <script setup>
  import { ref } from "vue"
  
  let tableData = ref([])
  let getData = async ()=>{
      let res = await $axios.get('/resultEnd/isPass')
      console.log(res.data);
      tableData.value = res.data.data
  }
  getData()
  </script>
  
  <style>
  </style>
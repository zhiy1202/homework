<template>
    <div>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column label="作业名" prop="p1.homeworkName" />
        <el-table-column label="时间" prop="p1.createTime" />
        <el-table-column align="right">
          <template #default="scope">
            <el-button
              size="small"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
              >重做</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>
  </template>
  
  <script setup>
  import { ref } from "vue"
import { useRouter } from "vue-router";
  
  let tableData = ref([])
  let router = useRouter()
  let handleDelete = (index,row)=>{
    console.log(row.p1.id);
    router.push(`/student/home/HomeWorkDetail?id=${row.p1.id}`)
  }
  let getData = async ()=>{
      let res = await $axios.get('/result/teacherNotLook')
      console.log(res.data);
      tableData.value = res.data.data
  }
  getData()
  </script>
  
  <style>
  </style>
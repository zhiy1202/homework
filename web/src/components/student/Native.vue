<template>
    <div>
      <el-table :data="tableData" style="width: 100%">
          <el-table-column label="公告名" prop="title" />
          <el-table-column label="简介" prop="intro" />
          <el-table-column align="right">
            <template #default="scope">
              <el-button
                size="small"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)">详情</el-button
              >
            </template>
          </el-table-column>
        </el-table>
    </div>
  </template>
  
  <script setup>
  import { ref } from "vue";
  import { useRouter } from "vue-router";
  
  let router = useRouter()
  let tableData = ref([])
  let handleDelete = async (index,row)=>{
    router.push('/student/home/NativeDetail?id='+row.id)
  }
  const getData = async ()=>{
      let res = await $axios.get('/native/all')
      console.log(res.data);
      tableData.value = res.data.data
  }
  getData()
  </script>
  
  <style>
  
  </style>
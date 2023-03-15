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
              @click="update(scope.$index, scope.row)">修改</el-button
            >
            <el-button
              size="small"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除</el-button
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
let update = async (index,row)=>{
    router.push(`/admin/home/NoticeUpdate?id=${row.id}`)
}
let handleDelete = async (index,row)=>{
    let res = await $axios.delete(`/native/del/${row.id}`)
    if (res.data.data) {
        alert('删除成功')
    }
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
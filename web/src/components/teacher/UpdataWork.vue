<template>
  <div>
    <el-table :data="tableData" style="width: 100%">
    <el-table-column label="作业名" width="180">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <el-icon><timer /></el-icon>
          <span style="margin-left: 10px">{{ scope.row.homeworkName }}</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="操作">
      <template #default="scope">
        <el-button size="small" @click="handleEdit(scope.$index, scope.row)"
          >批阅</el-button
        >
      </template>
    </el-table-column>
  </el-table>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";

let tableData = ref([])
const router = useRouter()

const handleEdit = (index,row)=>{
    let homeworkId = row.homeworkId
    let userId = row.userId
    router.push(`/teacher/home/UpdataWorkDetail?userId=${userId}&homeworkId=${homeworkId}`)
}
const getData = async ()=>{
    let res = await $axios.get('/lookWork/allByTeacher')
    console.log(res.data);
    tableData.value = res.data.data
}
getData()
</script>

<style>

</style>
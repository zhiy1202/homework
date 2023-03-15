<template>
  <div>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column label="班级名" prop="p3.className" />
      <el-table-column label="科目" prop="p2.subjectName" />
      <el-table-column label="作业名" prop="p1.homeworkName" />
      <el-table-column label="创建时间" prop="p1.createTime" />
      <el-table-column align="right" label="操作">
        <template #default="scope">
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

const handleDelete = async (index,row)=>{
    console.log(index,row);
    let res = await $axios.delete(`/front/homework/del/${row.p1.id}`)
    console.log(res.data); 
    if (res.data.data) {
        alert('删除成功')
        getData()
    }else{
        alert('删除失败 稍后再试')
    }
}
var tableData = ref({})

const getData = async ()=>{
    let res = await $axios.get('/front/homework/byTeacher')
    console.log(res.data);
    tableData.value = res.data.data
}
getData()
</script>

<style scoped>
</style>
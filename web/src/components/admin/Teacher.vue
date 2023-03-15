<template>
    <div>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column label="用户名" prop="username" />
        <el-table-column label="密码" prop="password" />
        <el-table-column label="班级" prop="classRoom" />
        <el-table-column label="联系方式" prop="phone" />
        <el-table-column align="right">
          <template #header>
            <el-input
              v-model="value"
              size="small"
              placeholder="输入姓名输入"
              @keyup.enter="search"/>
          </template>
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
      let res = await $axios.delete(`/admin/user/del/${row.id}`)
      console.log(res.data); 
      if (res.data.data) {
          alert('删除成功')
      }else{
          alert('删除失败 稍后再试')
      }
  }
  var tableData = ref({})
  var value = ref('')
  
  const search = async ()=>{
      let res = await $axios.get(`/admin/user/queryUserByUsername/${value.value}`)
      tableData.value = []
      tableData.value.push(res.data.data)
  
  }
  
  const getData = async ()=>{
      let res = await $axios.get('/admin/user/allTeacher')
      console.log(res.data);
      tableData.value = res.data.data
  }
  getData()
  </script>
  
  <style scoped>
  </style>
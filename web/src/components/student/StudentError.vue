<template>
    <div>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column label="作业名" prop="p1.homeworkName" />
        <el-table-column label="题目" prop="p2.title" />
        <el-table-column label="答案" prop="p2.answer" />
        <el-table-column label="我的作答" prop="p3.answer" />
        <el-table-column label="评分" prop="p3.score" />
        <el-table-column label="老师评语" prop="p3.comment" />
        <el-table-column align="right">
          <template #default="scope">
            <el-button
              size="small"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>
  </template>
    
    <script setup>
  import { ref } from "vue";
  
  const handleDelete = async (index, row) => {
    console.log(index, row);
    let res = await $axios.delete(`/result/del/${row.p3.id}`);
    console.log(res.data);
    if (res.data.data) {
      alert("删除成功");
      getData();
    } else {
      alert("删除失败 稍后再试");
    }
  };
  var tableData = ref({});
  
  const getData = async () => {
    let res = await $axios.get("/result/error");
    console.log(res.data);
    tableData.value = res.data.data;
  };
  getData();
  </script>
    
    <style scoped>
  </style>
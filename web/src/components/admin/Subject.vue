<template>
  <div>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column label="学科名" prop="subjectName" />
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
  let res = await $axios.delete(`/admin/subject/del/${row.id}`);
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
  let res = await $axios.get("/admin/subject/all");
  console.log(res.data);
  tableData.value = res.data.data;
};
getData();
</script>
  
  <style scoped>
</style>
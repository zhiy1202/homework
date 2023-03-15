<template>
  <div>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column label="作业名" prop="homeworkName" />
      <el-table-column label="时间" prop="createTime" />
      <el-table-column align="right">
        <template #default="scope">
          <el-button
            size="small"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
            >答题</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
    
    <script setup>
import { ref, watch } from "vue";
import { useRouter } from "vue-router";
var tableData = ref({});
let router = useRouter();

const handleDelete = async (index, row) => {
  console.log(row.id);
  router.push(`/student/home/HomeWorkDetail?id=${row.id}`)
};

watch(
  () => router.currentRoute.value.path,
  async (newValue, oldValue) => {
    console.log("watch", newValue);
    let arr = newValue.split("/");
    let path = arr[arr.length - 1];
    let res = await $axios.get(`/front/homework/queryNotTodo/${path}`);
    console.log(res.data);
    tableData.value = res.data.data;
  },
  { immediate: true }
);
</script>
    
    <style scoped>
</style>
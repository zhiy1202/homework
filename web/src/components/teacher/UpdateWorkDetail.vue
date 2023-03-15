<template>
  <div>
    <el-button @click="post">提交</el-button>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column label="题目" width="180">
        <template #default="scope">
          <div style="display: flex; align-items: center">
            <el-icon><timer /></el-icon>
            <span style="margin-left: 10px">{{ scope.row.title }}</span>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="答案" width="180">
        <template #default="scope">
          <div style="display: flex; align-items: center">
            <el-icon><timer /></el-icon>
            <span style="margin-left: 10px">{{ scope.row.answer }}</span>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="分数" width="180">
        <template #default="scope">
          <div style="display: flex; align-items: center">
            <el-icon><timer /></el-icon>
            <span style="margin-left: 10px">{{ scope.row.score }}</span>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="学生作答" width="180">
        <template #default="scope">
          <div style="display: flex; align-items: center">
            <el-icon><timer /></el-icon>
            <span style="margin-left: 10px">{{ scope.row.myAnswer }}</span>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="评分" width="180">
        <template #default="scope">
          <div style="display: flex; align-items: center">
            <el-icon><timer /></el-icon>
            <el-input v-model.number="scope.row.myScore"></el-input>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="评语" width="180">
        <template #default="scope">
          <div style="display: flex; align-items: center">
            <el-icon><timer /></el-icon>
            <el-input v-model="scope.row.comment"></el-input>
          </div>
        </template>
      </el-table-column>
      <!-- <el-table-column label="操作">
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.$index, scope.row)"
            >批阅</el-button
          >
        </template>
      </el-table-column> -->
    </el-table>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router"

let router = useRouter()
let tableData = ref({})
let {userId,homeworkId} = router.currentRoute.value.query
console.log(userId,homeworkId);

let post = ()=>{
    console.log(tableData.value);
    let homeWorkResults = JSON.stringify(tableData.value)
    $axios.post(`/resultEnd/addResult`,homeWorkResults,{
        headers: {
        'content-type': 'application/json'
    }
    }).then(res=>{
        console.log(res.data);
        if (res.data.data) {
            alert('批改成功')
        }
    })
}
const handleEdit = (index,row)=>{
    console.log(index,row);
    if (row.p3.score < row.p2.score) {
        row.p3.isTrue = false
    }else{
        row.p3.isTrue = true
    }
    let a = {
        comment:row.p3.comment,
        score:row.p3.score,
        homeworkId:row.p1.id,
        homeworkDetailId:row.p2.id,
        isTrue:row.p3.isTrue,
        answer:row.p3.answer,
        userId:row.p3.userId
    }
    arr.value.push(a)
}  

const getData = async ()=>{
    let res = await $axios.get(`/result/UpdataWorkDetail/${userId}/${homeworkId}`)
    console.log(res.data);
    tableData.value = res.data.data
}
getData()
</script>

<style>
</style>
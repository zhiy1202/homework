<template>
  <div>
    <!-- 题目名 时间 科目 -->
    <div>
      <h1>{{ tableData.homework.homeworkName }}</h1>
      <p>{{ tableData.homework.createTime }}</p>
      <el-button type="primary" @click="add()">提交</el-button>
    </div>
    <!-- 选择题 -->
    <div>选择题</div>
    <div>
      <div v-for="item in tableData.select" :key="item.id">
        <p>{{ item.title }} ()</p>
        <div>
          <el-radio-group v-model="item.myAnswer">
            <el-radio :label="item.p1">{{ item.p1 }}</el-radio>
            <el-radio :label="item.p2">{{ item.p2 }}</el-radio>
            <el-radio :label="item.p3">{{ item.p3 }}</el-radio>
            <el-radio :label="item.p4">{{ item.p4 }}</el-radio>
          </el-radio-group>
        </div>
      </div>
    </div>
    <!-- 填空题 -->
    <div>填空题</div>
    <div>
      <div v-for="item in tableData.fill" :key="item.id">
        <p>{{ item.title }}</p>
        <el-input v-model="item.myAnswer" placeholder="输入答案" />
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useRoute } from "vue-router";

let route = useRoute();
let { id } = route.query;
console.log("id", id);

let tableData = ref({
  homework: {},
  select: [],
  fill: [],
});
const add =()=>{
    let teacherLookWorks = []
    console.log(tableData.value.select);
    console.log(tableData.value.fill);
    for (let index = 0; index < tableData.value.select.length; index++) {
      const element = tableData.value.select[index];
      teacherLookWorks.push(element)
      
    }
    for (let index = 0; index < tableData.value.fill.length; index++) {
      const element = tableData.value.fill[index];
      teacherLookWorks.push(element)
      
    }
    console.log(teacherLookWorks);
    let data = {
      homeworkId:id,
      homeworkResultDetails:teacherLookWorks
    }
    let formData = JSON.stringify(data)
    $axios.post('/resultEnd/add',formData,{
      headers: {
        'content-type': 'application/json'
    }
    }).then((res)=>{
      console.log(res.data);
      if (res.data.data) {
        alert('提交成功')
      }
    })
    
}
const getData = async () => {
  //获取所有题目
  let res = await $axios.get(`/front/homework/allDetail/${id}`);
  console.log(res.data.data);
  let result = res.data.data;
  tableData.value.homework = result[0].p1;

  for (let index = 0; index < result[0].p2.length; index++) {
    const element = result[0].p2[index];

    if (element.type === 1) {
      tableData.value.select.push(element);
    } else {
      tableData.value.fill.push(element);
    }
  }
};
getData();
</script>

<style>
</style>
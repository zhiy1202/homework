<template>
    <div>
      <el-form
        ref="ruleFormRef"
        :model="ruleForm"
        status-icon
        label-width="120px"
        class="demo-ruleForm"
      >
        <el-form-item label="学科名" prop="pass">
          <el-input v-model="ruleForm.subjectName" type="text" autocomplete="off" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm()"
            >提交</el-button
          >
        </el-form-item>
      </el-form>
    </div>
  </template>
  
  <script setup>
  import { ref } from "vue";
  
  var ruleForm = ref({
    subjectName: ""
  });
  
  const submitForm = async () => {
    let formData = new FormData();
    formData.append("subjectName", ruleForm.value.subjectName);
    let res = await $axios.post("/admin/subject/add", formData);
    console.log(res.data);
    if (res.data.data) {
      alert(res.data.message);
    } else {
      alert("出错了稍后再试");
    }
  };
  </script>
  
  <style>
  </style>
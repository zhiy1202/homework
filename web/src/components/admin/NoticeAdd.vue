<template>
  <div>
    <el-form
      ref="ruleFormRef"
      :model="ruleForm"
      status-icon
      label-width="120px"
      class="demo-ruleForm"
    >
      <el-form-item label="公告标题" prop="pass">
        <el-input v-model="ruleForm.title" type="text" autocomplete="off" />
      </el-form-item>
      <el-form-item label="公告简介" prop="pass">
        <el-input v-model="ruleForm.intro" type="text" autocomplete="off" />
      </el-form-item>
      <el-form-item label="公告内容" prop="pass">
        <el-input v-model="ruleForm.content" type="text" autocomplete="off" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm()">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
import { ref } from "vue";

let ruleForm = ref({})
let submitForm = async ()=>{
    let formData = new FormData()
    formData.append('title',ruleForm.value.title)
    formData.append('intro',ruleForm.value.intro)
    formData.append('content',ruleForm.value.content)
    let res = await $axios.post(`/native/add`,formData)
    console.log(res.data);
    if(res.data.data){
        console.log(res.data.message);
    }
}
</script>

<style>
</style>
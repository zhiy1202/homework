<template>
  <div>
    <el-form
      ref="ruleFormRef"
      :model="ruleForm"
      status-icon
      label-width="120px"
      class="demo-ruleForm"
    >
      <el-form-item label="班级名" prop="pass">
        <el-input v-model="ruleForm.classRoom" type="text" autocomplete="off" />
      </el-form-item>
      <el-form-item label="年级" prop="age">
        <el-select v-model="ruleForm.grade" placeholder="Select">
          <el-option
            v-for="item in ruleForm.classRooms"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
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
  classRoom: "",
  grade: 1,
  classRooms: [
    {
      label: "初一",
      value: 1,
    },
    {
      label: "初二",
      value: 2,
    },
    {
      label: "初三",
      value: 3,
    },
  ],
});

const submitForm = async () => {
  let formData = new FormData();
  formData.append("className", ruleForm.value.classRoom);
  formData.append("grade", ruleForm.value.grade);
  let res = await $axios.post("/admin/room/add", formData);
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
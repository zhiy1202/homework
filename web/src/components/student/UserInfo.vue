<template>
  <div class="container">
    <div class="bar">
      <el-form
        ref="ruleFormRef"
        :model="ruleForm"
        status-icon
        label-width="120px"
        class="demo-ruleForm"
      >
        <el-form-item label="用户名" prop="pass">
          <el-input
            v-model="ruleForm.username"
            type="text"
            autocomplete="off"
          />
        </el-form-item>
        <el-form-item label="密码" prop="checkPass">
          <el-input
            v-model="ruleForm.password"
            type="password"
            autocomplete="off"
          />
        </el-form-item>
        <el-form-item label="手机号" prop="age">
          <el-input v-model.number="ruleForm.phone" />
        </el-form-item>
        <el-form-item label="地址" prop="age">
          <el-input v-model.number="ruleForm.address" />
        </el-form-item>
        <el-form-item label="班级" prop="age">
          <el-select v-model="ruleForm.classRoom" placeholder="Select">
            <el-option
              v-for="item in ruleForm.classRooms"
              :key="item.id"
              :label="item.className"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="update">修改</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
    
    <script setup>
import { ref } from "vue";

var ruleForm = ref({
  username: "",
  password: "",
  phone: "",
  address: "",
  classRoom: "",
  classRooms:[]
});
const update = async () => {
  let formData = new FormData();
  formData.append("username", ruleForm.value.username);
  formData.append("password", ruleForm.value.password);
  formData.append("phone", ruleForm.value.phone);
  formData.append("address", ruleForm.value.address);
  formData.append("classRoom", ruleForm.value.classRoom);
  let res = await $axios.post(`/front/user/update`, formData);
  console.log(res.data.data);
  if (res.data.data) {
    alert("修改成功");
  }
};
const getData = async () => {
  let res = await $axios.get(`/front/user/userInfo`);
  console.log(res.data.data);
  ruleForm.value = res.data.data;
};
getData();

const getData2 = async ()=>{
  let res = await $axios.get('/admin/room/all')
  console.log(res.data);
  ruleForm.value.classRooms = res.data.data
}
getData2()
</script>
    
  <style scoped>
</style>
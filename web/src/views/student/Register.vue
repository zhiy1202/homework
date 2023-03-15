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
          <el-button type="primary" @click="login">登录</el-button>
          <el-button @click="register">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
  
  <script setup>
import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";

var ruleForm = ref({
  username: "",
  password: "",
  phone: "",
  address: "",
  classRoom: '',
  classRooms: [],
});

const router = useRouter();

const getData = async ()=>{
  let res = await $axios.get('/admin/room/all')
  console.log(res.data);
  ruleForm.value.classRooms = res.data.data
}
getData()

const register = async () => {
  let formData = new FormData();
  formData.append("username", ruleForm.value.username);
  formData.append("password", ruleForm.value.password);
  formData.append("phone", ruleForm.value.phone);
  formData.append("address", ruleForm.value.address);
  formData.append("classRoom", ruleForm.value.classRoom);
  let result = await $axios.post(`/front/user/student/register`, formData);
  console.log(result);
  if (result.data.data) {
    alert(result.data.message);
  } else {
    alert(result.data.message);
  }
};

const login = () => {
  router.push("/student/login");
};

onMounted(() => {
  let width = window.innerWidth;
  let height = window.innerHeight;
  let bar = document.querySelector(".container");
  bar.style.width = width + "px";
  bar.style.height = height + "px";
});
</script>
  
  <style scoped>
.container {
  background-image: url("../../assets/1.jpg");
  background-repeat: no-repeat;
  background-size: 100% 100%;
  padding: 1px;
}
.bar{
  width: 50%;
  height: 500px;
  margin: 20% auto;
}
</style>
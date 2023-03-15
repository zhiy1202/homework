<template>
  <div class="container">
    <div class="bar">
      <h1>管理员登录</h1>
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
        <el-form-item>
          <el-button type="primary" @click="login">登录</el-button>
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
});

const router = useRouter();

const login = async () => {
  let formData = new FormData();
  formData.append("username", ruleForm.value.username);
  formData.append("password", ruleForm.value.password);
  let result = await $axios.post(`/admin/user/login`, formData);
  console.log(result.data);
  if (result.data.data) {
    alert(result.data.message);
    router.push('/admin/home')
  } else {
    alert(result.data.message);
  }
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
  height: 300px;
  margin: 20% auto;
  text-align: center;
}
</style>
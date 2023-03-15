<template>
  <div>
    <el-form
      ref="ruleFormRef"
      :model="ruleForm"
      status-icon
      label-width="120px"
      class="demo-ruleForm"
    >
      <el-form-item label="题目名" prop="pass">
        <el-input v-model="ruleForm.title" type="text" autocomplete="off" />
      </el-form-item>
      <el-form-item label="答案" prop="pass">
        <el-input v-model="ruleForm.answer" type="text" autocomplete="off" />
      </el-form-item>
      <el-form-item label="分数" prop="pass">
        <el-input v-model.number="ruleForm.score" type="text" autocomplete="off" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm()">暂存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
  
<script setup>
import { ref,defineEmits} from "vue"

var ruleForm = ref({})
const emit = defineEmits(['AddFill'])

const submitForm = async ()=>{
  ruleForm.value.type = 2
  console.log(ruleForm.value);
  emit('AddFill',ruleForm.value)
}
</script>
  
  <style>
</style>
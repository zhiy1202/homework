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
        <el-input
          v-model="ruleForm.homeworkName"
          type="text"
          autocomplete="off"
        />
      </el-form-item>
      <el-form-item label="班级" prop="age">
        <el-select v-model="ruleForm.classRoomId" placeholder="选择班级">
          <el-option
            v-for="item in tableData.classRoom"
            :key="item.id"
            :label="item.className"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="科目" prop="age">
        <el-select v-model="ruleForm.subjectId" placeholder="选择科目">
          <el-option
            v-for="item in tableData.subjucts"
            :key="item.id"
            :label="item.subjectName"
            :value="item.id"
          />
        </el-select>
      </el-form-item>

      <div>添加题目</div>
      <el-form-item>
        <el-button type="warning" @click="addSelect()">选择</el-button>
        <el-button type="warning" @click="addFill()">填空</el-button>
      </el-form-item>
      <div>
        <!-- <router-view></router-view> -->
        <HomeWorkSelect
          @AddSelect="AddSelect"
          v-show="isShow.select"
        ></HomeWorkSelect>
        <HomeWorkFill @AddFill="AddFill" v-show="isShow.fill"></HomeWorkFill>
      </div>
      <el-form-item>
        <el-button type="primary" @click="submitForm()">提交</el-button>
      </el-form-item>
      <div>
        <p>添加的题目</p>
        <el-table :data="ruleForm.homeworkDetails" style="width: 100%">
          <el-table-column label="title" width="180">
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
              <el-tag>{{ scope.row.score }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="操作">
            <template #default="scope">
              <el-button
                size="small"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)"
                >Delete</el-button
              >
            </template>
          </el-table-column>
        </el-table>
      </div>
    </el-form>
  </div>
</template>

<script setup>
import { ref } from "vue";
import HomeWorkSelect from "./handler/HomeWorkSelect.vue";
import HomeWorkFill from "./handler/HomeWorkFill.vue";

var isShow = ref({
  select: false,
  fill: false,
});
const addSelect = () => {
  isShow.value.select = true;
  isShow.value.fill = false;
};
const addFill = () => {
  isShow.value.select = false;
  isShow.value.fill = true;
};

const AddSelect = (val) => {
  ruleForm.value.homeworkDetails.push(val);
};
const AddFill = (val) => {
  ruleForm.value.homeworkDetails.push(val);
};
const handleDelete = (index, row) => {
  ruleForm.value.homeworkDetails.pop(index);
};

var ruleForm = ref({
  homeworkName: '',
  homeworkDetails: [],
  classRoomId: "",
  subjectId: "",
});
var tableData = ref({});

const submitForm = async () => {
    console.log(ruleForm);
  // let formData = new FormData();
  // formData.append("homeworkName", ruleForm.value.homeworkName);
  // formData.append("subjectId", ruleForm.value.subjectId);
  // formData.append("classRoomId", ruleForm.value.classRoomId);
  // for (let index = 0; index < ruleForm.value.homeworkDetails.length; index++) {
  //   const element = ruleForm.value.homeworkDetails[index]
  //   formData.append('homeworkDetails',element)
  // }
  let formData = JSON.stringify(ruleForm.value)
  let res = await $axios.post('/front/homework/add',formData,{
    headers: {
        'content-type': 'application/json'
    },
  })
  console.log(res.data);
  if (res.data.data) {
    alert('新增题目成功')
    ruleForm.value.homeworkDetails = ref([])
    ruleForm.value.homework = ref({})
  }
};

const getData = async () => {
  let rooms = await $axios.get("/admin/room/all");
  let subjucts = await $axios.get("/admin/subject/all");
  console.log(rooms.data);
  console.log(subjucts.data);
  tableData.value.classRoom = rooms.data.data;
  tableData.value.subjucts = subjucts.data.data;
};
getData();
</script>

<style>
</style>
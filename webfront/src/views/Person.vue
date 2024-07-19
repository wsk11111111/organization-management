<template>
    <div>
      <div class="card">
        <h2 class="card-title">个人信息</h2>
        <el-form :model="form" ref="formRef" label-width="100px">
          <el-form-item label="姓名">
            <el-input class="input" v-model="form.username" disabled></el-input>
          </el-form-item>
          <el-form-item label="用户名">
            <el-input class="input" v-model="form.nickName"></el-input>
          </el-form-item>
          
          
          <el-form-item label="邮箱">
            <el-input class="input" v-model="form.email"></el-input>
          </el-form-item>
          <el-form-item>
            <div class="button-container">
              <el-button type="primary" @click="update">保存</el-button>
            </div>
          </el-form-item>
        </el-form>
        
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import { ElMessage } from 'element-plus';
  import { useUserStore } from '@/stores/userStore.js';
  import { updateUserAPI } from '@/api/user.js';
  
  const form = ref({});
  
  const user = ref({});
  const userStore = useUserStore();
  user.value = userStore.userInfo;
  
  const update = async () => {
        ElMessage.success('更新用户信息成功');
        // 刷新用户信息
  }
       
  </script>
  
  <style scoped>
  .card {
    width: 150%;
    margin-left: 120px;
    margin-top: 40px;
  }
  
  .card-title {
    padding: 30px;
  }
  
  .input {
    width: 80%;
  }
  
  .textarea {
    width: 80%;
  }
  
  .button-container {
    text-align: center;
  }
  
  </style>
  
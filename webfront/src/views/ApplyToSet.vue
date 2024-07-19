<template>
    <div class="home">
      <!-- 搜索 -->
      <div>
        <el-form inline size="large">
          <el-form-item label="名称">
            <el-input v-model="name" placeholder="请输入名称" clearable>
              <template #prefix>
                <el-icon class="el-input__icon">
                  <search />
                </el-icon>
              </template>
            </el-input>
          </el-form-item>
            <el-select v-model="category" placeholder="请选择" size="large"  clearable>
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          <el-form-item>
            <el-button type="primary" style="margin-left: 50" @click="apply" size="Large">发送申请</el-button>
          </el-form-item>
          
        </el-form>
      
        
    </div>
    </div>
  </template>
  
  <script setup>
  import { onMounted, reactive, ref } from 'vue';
  import { ElMessage } from 'element-plus';
  import { deleteClubAPI, getClubsAPI, updateClubAPI} from '@/api/club.js';
  import {applyToJoinAPI,starClubAPI } from '@/api/user.js'
  import { useUserStore } from '@/stores/userStore.js';
  import{applyToSetAPI} from '@/api/applyToJoinClubs.js'
  
  const name = ref('');
  const category = ref('');
  const value = ref('')
  const options=[
    {
      value:'学习',
      label:'学习'
    },
    {
      value:'艺术',
      label:'艺术'
    },
    {
      value:'体育',
      label:'体育'
      
    },
    {
      value:'志愿公益',
      label:'志愿公益'
   
    },
    {
      value:'创新创业',
      label:'创新创业'
   
    },
    {
      value:'其他',
      label:'其他'
   
    }
  ]
  
  const form = reactive({});
  // 使用当前user
  const user = ref({});
  const userStore = useUserStore();
  user.value = userStore.userInfo;
  
  
//   const selectedReaderIds = ref([]);
  
//   const handleSelectionChange = (val) => {
//     selectedReaderIds.value = val.map((v) => v.id);
//   };
  
  
  const apply = async () => {
    const params = {
      category: category.value,
      name: name.value,
      userId: user.value.id
    };
    const res = await applyToSetAPI(params);
    if(res.data.code===200){
        ElMessage.success('申请成功');
    }
  };
  
  const deleteReader = async (id) => {
    const res = await deleteClubAPI(id);
    if (res.data.code === 200) {
      ElMessage.success('删除成功');
    } else {
      ElMessage.error('删除失败');
    }
    await loadReaders();
  
  };
  const applyToJoin = async(userId,clubId) =>{
    const res = await applyToJoinAPI({userId:userId,clubId:clubId});
    if(res.data.code === 200){
      ElMessage.success('已发送申请');
    } else if(res.data.code === 400){
      ElMessage.error('您已在社团中');
    }else if(res.data.code === 401){
      ElMessage.error('已发送过申请');
    }
    await loadReaders();
  }
  
  
  
  
  </script>
  
  <style scoped>
  .home {
    width: 100%;
    padding: 10px;
  }
  
  .dialog-footer {
    display: flex;
    justify-content: flex-end;
  }
  </style>
  
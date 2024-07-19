<template>
  <div class="home">
    <!-- 搜索 -->
    <div>
      <el-form inline size="large">
        <el-form-item label="名称">
          <el-input v-model="searchName" placeholder="请输入名称" clearable>
            <template #prefix>
              <el-icon class="el-input__icon">
                <search />
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
          <el-select v-model="searchCategory" placeholder="请选择" size="large"  clearable>
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        <el-form-item>
          <el-button type="primary" style="margin-left: 50" @click="loadReaders" size="Large">查询</el-button>
        </el-form-item>
        
      </el-form>
    <el-table :data="readers" stripe border @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="name" label="名称" />
      <el-table-column prop="category" label="分类" />
      <el-table-column prop="comId" label="负责人ID" />
      <el-table-column fixed="right" label="操作">
        <template v-slot="scope">         
          <el-button size="small" @click="starClub(user.id,scope.row.id)">收藏社团</el-button> 
          <el-button v-if="user.role===2" size="small" @click="applyToJoin(user.id,scope.row.id)">申请加入</el-button> 
          <el-popconfirm title="确认删除?" @confirm="deleteReader(scope.row.id)">
            <template #reference>
              <el-button type="danger" size="small" v-if="user.role===1">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <div>
      <el-pagination
          v-model:currentPage="currentPage"
          :page-sizes="[5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="totalReaders"
          @size-change="handlePageSizeChange"
          @current-change="handleCurrentPageChange"
      ></el-pagination>

      <!-- <el-dialog v-model="dialogVisible" title="社团信息" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="名称">
            <el-input style="width: 80%" v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="分类">
            <el-input style="width: 80%" v-model="form.category"></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="saveReader">确定</el-button>
          </span>
        </template>
      </el-dialog> -->
    </div>
  </div>
  </div>
</template>

<script setup>
import { onMounted, reactive, ref } from 'vue';
import { ElMessage } from 'element-plus';
import { deleteClubAPI, getClubsAPI, updateClubAPI} from '@/api/club.js';
import {applyToJoinAPI,starClubAPI } from '@/api/user.js'
import { useUserStore } from '@/stores/userStore.js';

const searchName = ref('');
const searchCategory = ref('');
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
const dialogVisible = ref(false);

const totalReaders = ref(10);
const currentPage = ref(1);
const pageSize = ref(10);
const readers = ref([]);

// 使用当前user
const user = ref({});
const userStore = useUserStore();
user.value = userStore.userInfo;


const selectedReaderIds = ref([]);

const handleSelectionChange = (val) => {
  selectedReaderIds.value = val.map((v) => v.id);
};
const starClub = async(userId,clubId)=>{
      const res = await starClubAPI({userId:userId,clubId:clubId});
      if(res.data.code === 200){
        ElMessage.success('成功收藏');
      } else{
        ElMessage.error('您已收藏该社团');
        
      }
      await loadReaders();
    }


const loadReaders = async () => {
  const params = {
    pageNum: currentPage.value,
    pageSize: pageSize.value,
    category: searchCategory.value,
    name: searchName.value,
  };
  const res = await getClubsAPI(params);
  readers.value = res.data.data.records;
  totalReaders.value = res.data.data.total;
  
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

const saveReader = async () => {
  try {
    const res = await updateClubAPI(form);
    if (res.data.code === 200) {
      ElMessage.success('更新用户信息成功');
      // 刷新用户信息
      await loadReaders();
      dialogVisible.value = false
    } else {
      ElMessage.error(res.data.message);
    }
  } catch (error) {
    ElMessage.error(error.response.data.message);
  }
};




const handlePageSizeChange = (newSize) => {
  pageSize.value = newSize;
  loadReaders();
};

const handleCurrentPageChange = (newPage) => {
  currentPage.value = newPage;
  loadReaders();
};

onMounted(() => {
  loadReaders();
});
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

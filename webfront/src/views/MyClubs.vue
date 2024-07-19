<template>
    <div class="home" style="margin-top: 50px;width: 100%;">
        <el-table :data="myClubs" >
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="name" label="名称" width="200" />
      <el-table-column prop="category" label="分类" />
      <el-table-column prop="comId" label="负责人ID" />
      <el-table-column fixed="right" label="操作" width="400">
        <template v-slot="scope">         
          <el-button size="small" @click="drawer=true,goClubHome(scope.row.id)">社团活动</el-button> 
          <el-button size="small" @click="setAct(scope.row)">创建活动</el-button> 
          <router-link to="/Im">
            <el-button size="small" >社团聊天</el-button> 
          </router-link>



          <el-dialog v-model="dialogVisible" title="编辑活动内容" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="活动内容">
            <el-input style="width: 80%" v-model="form.act"></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="saveAct">确定</el-button>
          </span>
        </template>
      </el-dialog>


      
          <el-drawer
            v-model="drawer"
            title="社团信息:"
            :direction="ltr"
            :before-close="handleClose"
            size="35%"
          >
            

            <span>社团活动：<br>

            <li v-for="item in clubAct">

              <span> {{ item }}</span>


            </li>
            
            </span>
            
          </el-drawer>

          <el-popconfirm title="确认退出?" @confirm="quitClub(user.id,scope.row.id)">
            <template #reference>
              <el-button type="danger" size="small" >退出社团</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div>
      <el-pagination
          v-model:currentPage="currentPage"
          :page-sizes="[5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="totalClubs"
          @size-change="handlePageSizeChange"
          @current-change="handleCurrentPageChange"
      ></el-pagination>
    </div>
    </div>
  </template>
  
  <script lang="ts" setup>
    import { onMounted, reactive, ref } from 'vue';
    import { useUserStore } from '@/stores/userStore.js';
    import { ElMessage } from 'element-plus';
    import{getClubsAPI,getClubAct,setActAPI}from '@/api/myClubs.js';
    import { ElMessageBox } from 'element-plus';
    const form = reactive({});
    const dialogVisible = ref(false);
    const drawer = ref(false)
    const totalClubs = ref(10);
    const currentPage = ref(1);
    const pageSize = ref(10);
    const myClubs = ref([]);

    //使用当前user
    const user = ref({});
    const userStore = useUserStore();
    user.value = userStore.userInfo;
    const handleClose =(done: ()=>void) => {
      ElMessageBox.confirm('确定要关闭吗')
    .then(() => {
      done()
    })
    .catch(() => {
      // catch error
    })
}
    

    const loadClubs = async () => {
        const params = {
            pageNum: currentPage.value,
            pageSize: pageSize.value,
            userId: user.value.id,
        };
        const res = await getClubsAPI(params);
        myClubs.value = res.data.data.records;
        totalClubs.value = res.data.data.total;
    }
    const clubAct = ref ([]);

    const goClubHome =async (clubId)=>{
        const res = await getClubAct(clubId);

        clubAct.value = res.data.data

    }

    const setAct =async (row)=>{
      Object.assign(form, row);
      dialogVisible.value = true;
      

    }
    const saveAct = async () => {
      try {
        const res = await setActAPI(form);
        if (res.data.code === 200) {
          ElMessage.success('活动设置成功');
          // 刷新用户信息
          await loadClubs();
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
        loadClubs();
    };

    const handleCurrentPageChange = (newPage) => {
        currentPage.value = newPage;
        loadClubs();
    };

    onMounted(() => {
        loadClubs();
    });
    

  </script>
  <style>

  </style>
  
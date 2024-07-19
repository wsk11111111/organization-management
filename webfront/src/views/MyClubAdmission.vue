<template>
    <div class="home" style="margin-top: 50px;width: 100%;">
      <el-table :data="myAddmission" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="trueName" label="申请人姓名" />
      <el-table-column prop="name" label="申请社团名称" />
      <el-table-column prop="category" label="分类" />
      <el-table-column fixed="right" label="操作">
        <template v-slot="scope">         
          <el-button size="small" @click="approve(scope.row.id)">同意</el-button> 
          <el-popconfirm title="确认拒绝?" @confirm="disApprove(scope.row.id)">
            <template #reference>
              <el-button type="danger" size="small">拒绝</el-button>
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
  
  <script setup>
    import { onMounted, reactive, ref } from 'vue';
    import { ElMessage } from 'element-plus';
    import { useUserStore } from '@/stores/userStore.js';
    import{getClubComAPI,approveAPI,disApproveAPI} from '@/api/applyToJoinClubs.js';
    const totalClubs = ref(10);
    const currentPage = ref(1);
    const pageSize = ref(10);
    const myAddmission = ref([]);
    const loadClubs = async () => {
        const params = {
            pageNum: currentPage.value,
            pageSize: pageSize.value
        };
        const res = await getClubComAPI(params);
        myAddmission.value = res.data.data.records;
        totalClubs.value = res.data.data.total;
    }
    const approve = async (id) =>{
      const res = await approveAPI(id);
      loadClubs();
    }
    const disApprove = async (id) =>{
      const res = await disApproveAPI(id);
      loadClubs();
    }

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
  
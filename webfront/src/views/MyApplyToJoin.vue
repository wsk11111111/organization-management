<template>
    <div class="home" style="margin-top: 50px;width: 100%;">
        <el-table :data="myClubsToJoin" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="name" label="名称" />
      <el-table-column prop="category" label="分类" />
      <el-table-column prop="comId" label="负责人ID" />
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
    import{getApplyClubsAPI}from '@/api/applyToJoinClubs.js'
    const totalClubs = ref(10);
    const currentPage = ref(1);
    const pageSize = ref(10);
    const myClubsToJoin = ref([]);

    // 使用当前user
    
    const user = ref({});
    const userStore = useUserStore();
    user.value = userStore.userInfo;
    const loadClubs = async () => {
        const params = {
            pageNum: currentPage.value,
            pageSize: pageSize.value,
            userId: user.value.id
        };
        const res = await getApplyClubsAPI(params);
        myClubsToJoin.value = res.data.data.records;
        totalClubs.value = res.data.data.total;
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
  
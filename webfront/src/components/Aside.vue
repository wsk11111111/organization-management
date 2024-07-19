<template>
    <div>
      <el-menu
          style="width: 200px; min-height: calc(100vh - 50px);"
          :default-active="path"
          router
          background-color="#f5f5f5"
          text-color="#000000"
          select="switchMenu"
      > 
        <el-menu-item index="/club">
          <svg class="icon" aria-hidden="true">
            <use xlink:href="#icondashboard "></use>
          </svg>
          <span>社团查询</span>
        </el-menu-item>
        <!-- <el-menu-item index="/book" v-if="user.role === 1">
          <svg class="icon" aria-hidden="true">
            <use xlink:href="#iconbook "></use>
          </svg>
          <span>学生管理</span>
        </el-menu-item> -->
        <el-menu-item index="/MyClubs" v-if="user.role === 2">
          <svg class="icon" aria-hidden="true">
            <use xlink:href="#iconbook "></use>
          </svg>
          <span>我的社团</span>
        </el-menu-item>

        <el-menu-item index="/user" v-if="user.role === 1">
          <svg class="icon" aria-hidden="true">
            <use xlink:href="#iconreader "></use>
          </svg>
          <span>学生管理</span>
        </el-menu-item>
        <el-menu-item index="/starClub" v-if="user.role === 1 || user.role===2">
          <svg class="icon" aria-hidden="true">
            <use xlink:href="#iconlend-record"></use>
          </svg>
          <span>我的收藏</span>
        </el-menu-item>
        <el-menu-item index="/applyToSet" v-if="user.role === 2">
          <el-icon>
            <grid/>
          </el-icon>
          <span>创建社团</span>
        </el-menu-item>
        <el-menu-item index="/MyClubAdmission" v-if="user.role === 2">
          <el-icon>
            <grid/>
          </el-icon>
          <span>加入申请</span>
        </el-menu-item>
        <el-menu-item index="/myApplyToJoin" v-if="user.role === 2">
          <el-icon>
            <grid/>
          </el-icon>
          <span>我的申请</span>
        </el-menu-item>
        <el-menu-item index="/setClubAddmission" v-if="user.role === 1">
          <el-icon>
            <grid/>
          </el-icon>
          <span>创建社团申请</span>
        </el-menu-item>
        <el-sub-menu index="2" text-color="#fff" v-if="user.role === 1||user.role===2">
          <template #title>
            <svg class="icon" aria-hidden="true">
              <use xlink:href="#icon-mingpian "></use>
            </svg>
            <span>个人信息</span>
          </template>
          <el-menu-item index="/person">
            <svg class="icon" aria-hidden="true">
              <use xlink:href="#icon-a-bianji1 "></use>
            </svg>
            <span>修改个人信息</span>
          </el-menu-item>
          <el-menu-item index="/password">
            <svg class="icon" aria-hidden="true">
              <use xlink:href="#icon-mima "></use>
            </svg>
            <span>修改密码</span>
          </el-menu-item>
        </el-sub-menu>
      </el-menu>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import { useRoute } from 'vue-router';
  import { useUserStore } from '@/stores/userStore.js';
  
  const user = ref({});
  const route = useRoute();
  const path = ref(route.path);
  const userStore = useUserStore();
  user.value = userStore.userInfo;
  
  </script>
  
  <style scoped>
  .icon {
    width: 30px;
    height: 30px;
    padding-top: 5px;
    padding-right: 10px;
  }
  </style>
  
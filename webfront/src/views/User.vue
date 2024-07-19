<template>
  <div class="home">
    <!-- 搜索 -->
    <div>
      <el-form inline size="large">
        <el-form-item label="姓名">
          <el-input v-model="searchName" placeholder="请输入姓名" clearable>
            <template #prefix>
              <el-icon class="el-input__icon">
                <search />
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="searchEmail" placeholder="请输入地址" clearable>
            <template #prefix>
              <el-icon class="el-input__icon">
                <search />
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" style="margin-left: 1%" @click="loadReaders" size="large">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button size="large" type="danger" @click="clearSearch">重置</el-button>
        </el-form-item>
      </el-form>
<!--    </div>-->
<!--    &lt;!&ndash; 按钮 &ndash;&gt;-->
<!--    <div style="margin: 10px 0;">-->
<!--      <el-popconfirm title="确认删除?" @confirm="deleteSelectedReaders">-->
<!--        <template #reference>-->
<!--          <el-button type="danger" size="small">批量删除</el-button>-->
<!--        </template>-->
<!--      </el-popconfirm>-->
<!--    </div>-->
    <!-- 数据字段 -->
    <el-table :data="readers" stripe border @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="username" label="用户名" />
      <el-table-column prop="trueName" label="姓名" />
      <el-table-column prop="stuNum" label="学号" />
      <el-table-column prop="email" label="邮箱" />
      <el-table-column fixed="right" label="操作">
        <template v-slot="scope">
          <el-button size="small" @click="editReader(scope.row)">编辑</el-button>
          <el-popconfirm title="确认删除?" @confirm="deleteReader(scope.row.id)">
            <template #reference>
              <el-button type="danger" size="small">删除</el-button>
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

      <el-dialog v-model="dialogVisible" title="编辑学生信息" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="用户名">
            <el-input style="width: 80%" v-model="form.username"></el-input>
          </el-form-item>
          <el-form-item label="学号">
            <el-input style="width: 80%" v-model="form.stuNum"></el-input>
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input style="width: 80%" v-model="form.email"></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="saveReader">确定</el-button>
          </span>
        </template>
      </el-dialog>
    </div>
  </div>
  </div>
</template>

<script setup>
import { onMounted, reactive, ref } from 'vue';
import { ElMessage } from 'element-plus';
import { deleteUserAPI, getUsersAPI, updateUserAPI } from '@/api/user.js';

const searchName = ref('');
const searchEmail = ref('');

const form = reactive({});
const dialogVisible = ref(false);

const totalReaders = ref(10);
const currentPage = ref(1);
const pageSize = ref(10);
const readers = ref([]);

const user = reactive({});
const selectedReaderIds = ref([]);

const handleSelectionChange = (val) => {
  selectedReaderIds.value = val.map((v) => v.id);
};



const loadReaders = async () => {
  const params = {
    pageNum: currentPage.value,
    pageSize: pageSize.value,
    trueName: searchName.value,
    email: searchEmail.value,
  };
  const res = await getUsersAPI(params);
  readers.value = res.data.data.records;
  totalReaders.value = res.data.data.total;
  console.log(readers.value);
};

const clearSearch = () => {
  searchName.value = '';
  searchEmail.value = '';
  loadReaders();
};

const deleteReader = async (id) => {
  const res = await deleteUserAPI(id);
  if (res.data.code === 200) {
    ElMessage.success('删除成功');
  } else {
    ElMessage.error('删除失败');
  }
  await loadReaders();

};

const saveReader = async () => {
  try {
    const res = await updateUserAPI(form);
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


const editReader = (row) => {
  Object.assign(form, row);
  dialogVisible.value = true;
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

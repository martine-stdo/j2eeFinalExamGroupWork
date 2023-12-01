<template>
  <!-- 主容器 -->
  <el-container style="width: 100%; height: 100%">
    <!-- 顶部导航栏 -->
    <el-header style="
        height: 70px;
        background-color: black;
        display: flex;
        justify-content: space-between;
        align-items: center;
      ">
      <!-- 左侧Logo和标题 -->
      <div style="
          display: flex;
          justify-content: space-between;
          align-items: center;
        ">
        <img src="../assets/images/logo.png" style="object-fit: contain; width: 40px; height: 40px" />
        <span style="color: white; padding-left: 15px">
          <h1>课程信息管理系统</h1>
        </span>
      </div>
      <!-- 右侧用户信息和下拉菜单 -->
      <div style="
          height: 100%;
          display: flex;
          justify-content: space-around;
          align-items: center;
        ">
        <!-- 用户头像 -->
        <el-avatar v-if="user" :src="`${this.user.avator}`"></el-avatar>
        <div style="width: 15px"></div>
        <!-- 用户下拉菜单 -->
        <el-dropdown @command="handleCommand">
          <span class="el-dropdown-link" style="color: white">
            你好，{{ user.username }}<i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu>
            <el-dropdown-item command="exit">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </el-header>

    <!-- 主体内容容器 -->
    <el-container>
      <!-- 侧边栏 -->
      <el-aside width="15vw" style="
          display: flex;
          flex-direction: column;
          align-items: stretch;
          border-right: 1px solid #d9d9d9;
        ">
        <!-- 路由导航菜单 -->
        <el-menu router :default-active="$route.path" style="border: 0">
          <!-- 主页菜单项 -->
          <el-menu-item index="1" route="/main">
            <i class="el-icon-goods" />
            <span slot="title">主页</span>
          </el-menu-item>
          <!-- 仅在用户是管理员时显示的课程管理菜单项 -->
          <el-menu-item index="3" route="/admin" v-show="isAdmin">
            <i class="el-icon-data-analysis" />
            <span slot="title">课程管理</span>
          </el-menu-item>
        </el-menu>
      </el-aside>

      <!-- 主体内容区域 -->
      <el-main>
        <!-- 使用 Vue Router 渲染的组件视图 -->
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: "index",
  data() {
    return {
      // 是否打开路由
      openRouter: true,
      // 是否是管理员
      isAdmin: false,
      // 用户信息对象
      user: null,
    };
  },
  created() {
    // 发送 GET 请求获取用户信息
    this.$axios.get('/user/info',)
      .then(response => {
        if (response.code === 200) {
          // 保存用户信息到组件的 user 对象
          this.user = response.data;
          localStorage.setItem("user", JSON.stringify(this.user));
          // 设置 isAdmin 标志，仅在用户是管理员时为 true
          this.isAdmin = this.user ? this.user.admin : false;
        } else {
          console.log('获取用户信息失败');
        }
      })
      .catch(error => {
        console.log('获取用户信息失败', error);
      });
  },
  methods: {
    handleCommand(command) {
      if (command === "exit") {
        // 清除本地存储
        localStorage.clear();
        // 返回登录页面
        this.$router.push("/");
      }
    },
  },
};
</script>

<style scoped></style>

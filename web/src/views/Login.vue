<template>
  <div class="bg">
    <div class="bgOverlay"></div>
    <el-row style="width: 100%;">
      <el-col type="flex" justify="space-around" :xs="24" :sm="{ span: 10, offset: 10 }" :md="{ span: 8, offset: 12 }">
        <div class="login-panel">
          <div class="content">
            <div style="width: 100%; text-align: center; margin-top: 40px">
              <h1 v-show="!isSignIn">登录</h1>
              <h1 v-show="isSignIn">注册</h1>
              <el-input v-model="user.username" placeholder="用户名" class="input">
                <i slot="prefix" class="el-input__icon el-icon-user"></i>
              </el-input>
              <el-input v-model="user.password" placeholder="密码" :show-password="true" class="input">
                <i slot="prefix" class="el-input__icon el-icon-key"></i>
              </el-input>
              <el-collapse-transition>
                <div v-show="isSignIn">
                  <el-input v-model="user.confirmPassword" placeholder="重复密码" :show-password="true" class="input">
                    <i slot="prefix" class="el-input__icon el-icon-lock"></i>
                  </el-input>
                </div>
              </el-collapse-transition>

              <div class="login-items">
                <el-checkbox class="checkbox">记住密码</el-checkbox>
              </div>
              <div class="btn_link" v-show="!isSignIn">
                <el-button type="primary" class="btn_submit" @click="login">
                  <strong style="font-size: large">
                    <i class="el-icon-arrow-left"></i>
                    &nbsp;登录&nbsp;&nbsp;&nbsp;&nbsp;
                  </strong>
                </el-button>
                <el-button class="btn_submit" @click="isSignIn = true">没有账号？注册一个&nbsp;
                  <i class="el-icon-arrow-right"></i>
                </el-button>
              </div>
              <div class="btn_link" v-show="isSignIn">
                <el-button type="primary" class="btn_submit" @click="register">
                  <strong style="font-size: large">
                    <i class="el-icon-arrow-left"></i>
                    &nbsp;注册&nbsp;&nbsp;&nbsp;&nbsp;
                  </strong>
                </el-button>
                <el-button class="btn_submit" @click="isSignIn = false">已有账号？返回登录&nbsp;
                  <i class="el-icon-arrow-right"></i>
                </el-button>
              </div>
            </div>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      checked: false,
      isSignIn: false,
      user: {
        username: "",
        password: "",
        confirmPassword: "",
      },
    };
  },
  methods: {
    login() {
      this.$axios.post("/user/token", this.user).then(
        (result) => {
          let error_message = result.data.error_message;
          if (error_message === "success") {
            let token = result.data.token;
            localStorage.setItem("token", 'Bearer ' + token);

            this.$router.push("/main");
          } else {
            this.$notify.error({
              title: "登录错误",
              message: "密码或账号错误！",
            });
          }
        },
        (err) => {
          this.$notify.error({
            title: "登录失败",
            message: "服务器内部错误！",
          });
        }
      );

    },
    register() {
      this.$axios
        .post("/user/register", this.user)
        .then((result) => {
          let error_message = result.data.error_message;
          if (error_message === "success") {
          } else {
            this.$notify.error({
              title: "注册失败",
              message: error_message,
            });
            throw "success";
          }
        })
        .then(() => {
          this.isSignIn = false;
        })
        .catch((err) => {
          if (err !== "success") {
            this.$notify.error({
              title: "注册失败",
              message: "服务器内部错误！",
            });
          }
        });
    },
  },
};
</script>

<style scoped>
.bg {
  display: flex;
  box-sizing: border-box;
  padding: 10px;
  align-items: center;
  position: relative;

  /* Take full size */
  height: 100vh;
  width: 100%;

  /* Background */
  background: url("https://api.aqcoder.cn/random") center center / cover no-repeat;
}

.bgOverlay {
  opacity: 1;
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  transition: opacity 0.3s linear 0s;
  background-image: radial-gradient(rgba(0, 0, 0, 0) 0%,
      rgba(0, 0, 0, 0.5) 100%),
    radial-gradient(rgba(255, 255, 255, 0) 33%, rgba(255, 255, 255, 0.3) 166%);
}

.login-panel {
  /* 阴影/低阴影 */
  box-shadow: 0px 3px 8px 0px rgba(0, 0, 0, 0.2);
  padding: 5px;
  border-radius: 4px;
  padding-bottom: 15px;
  background-color: rgba(255, 255, 255, 0.6);
  z-index: 10;
  backdrop-filter: blur(10px);
  box-shadow: rgba(0, 0, 0, 0.3) 0px -2px 0px inset,
    rgba(0, 0, 0, 0.4) 0px 1px 1px;
  animation-duration: 1s;
  animation-name: slide-in;
}

@keyframes slide-in {
  from {
    transform: translate(0, 10%);
    opacity: 0;
  }

  to {
    transform: translate(0, 0);
    opacity: 1;
  }
}

.input {
  width: calc(100% - 50px);
  margin-top: 10px;
}

.center {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  /* height: 100%; */
}

.box {
  width: 400px;
  border: none;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.2);
  border-radius: 6px 6px 6px 6px;
  overflow: hidden;
}

.content {
  width: 100%;
  /* height: 100%; */
  /* background-color: white; */
  position: relative;
}

.login-items {
  display: flex;
  text-align: left;
  margin: 10px 25px;
  justify-content: space-between;
  align-items: center;
}

.checkbox {
  text-align: left;
}

.btn_submit {
  box-shadow: rgba(0, 0, 0, 0.15) 0px -2px 0px inset,
    rgba(0, 0, 0, 0.4) 0px 1px 1px;
  border: 0;
}

.btn_link {
  width: 100%;
  display: flex;
  justify-content: space-between;
  box-sizing: border-box;
  padding: 0 25px 10px 25px;
}

.docked-at-corner {
  position: relative;
}

.docked-at-corner__docker {
  position: absolute;
  right: 0;
  top: 0;
  transform: translate(30%, 50%);

  /* Center the content */
  align-items: center;
  display: flex;
  justify-content: center;
  box-shadow: rgba(0, 0, 0, 0.15) 0px -3px 0px inset,
    rgba(0, 0, 0, 0.4) 0px 1px 1px;
  border: 0;
  box-shadow: 0px 3px 8px 0px rgba(0, 0, 0, 0.2);
}
</style>
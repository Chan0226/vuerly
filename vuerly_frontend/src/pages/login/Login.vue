<template>
  <div class="grid">
    <!-- 로그인 제목 -->
    <div class="login-title">
      <h1>로그인</h1>
    </div>

    <!-- 로그인 -->
    <form v-on:submit="loginSubmitForm">
      <div class="login-part">
        <!-- 아이디 -->
        <div class="id-box">
          <input
            v-model="id"
            type="text"
            id="ID"
            class="id-input"
            placeholder="아이디를 입력해주세요"
          />
        </div>

        <!-- 비밀번호 -->
        <div class="pwd-box">
          <input
            v-model="pwd"
            type="password"
            id="pwd"
            class="pwd-input"
            placeholder="비밀번호를 입력해주세요"
          />
        </div>

        <!-- 아이디/비밀번호 찾기 -->
        <div class="find-id-pwd">
          <router-link to="/findId"> 아이디 찾기 </router-link>|
          <router-link to="/findPw"> 비밀번호 찾기</router-link>
        </div>

        <!-- 로그인 / 회원가입 버튼 -->
        <div class="button-part">
          <v-btn id="loginBtn" type="submit">로그인</v-btn>

          <!-- Modal Part -->
          <v-card v-if="!isIdAndPwd">
            <v-card-title></v-card-title>
            <v-card-text> 아이디 또는 비밀번호를 입력해주세요! </v-card-text>
            <v-divider></v-divider>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="primary" text @click="dialog = false">닫기</v-btn>
            </v-card-actions>
          </v-card>

          <!-- 회원가입 버튼 -->
          <router-link to="/join">
            <button type="button" id="joinBtn" class="join-btn">
              회원가입
            </button>
          </router-link>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import axios from "axios";
const HOST = "http://localhost:3000";
export default {
  data() {
    return {
      id: "",
      pwd: "",
      dialog: false,
      isIdAndPwd: true,
    };
  },
  methods: {
    // 값 없을 시 실행
    loginSubmitForm(e) {
      // form의 새로고침 막기
      e.preventDefault();
      // id, pwd 값이 있는지 확인
      if (!this.id) {
        // id, pwd값이 없으면
        this.isIdAndPwd = false;
        //console.log(this.isIdAndPwd);
        return;
      }
      if (!this.pwd) {
        this.isIdAndPwd = false;
        //console.log(this.isIdAndPwd);
        return;
      }
      this.login();
      // alert("로그인 되었습니다.");
    },
    login() {
      let data = {};
      data.id = this.id;
      data.password = this.pwd;
      axios
        .post(HOST + "/member/login", JSON.stringify(data), {
          headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Credentials": "true",
          },
        })
        .then((res) => {
          console.log(res.data);
          if (res.data == 0) {
            alert("존재하지 않는 아이디입니다.");
          } else if (res.data == -1) {
            alert("비밀번호가 일치하지 않습니다.");
          } else {
            this.$session.set("secretKey", res.data);
            this.$router.push("/", () => {
              this.$router.go(this.$router.currentRoute);
            });
          }
        })
        .catch((error) => {
          alert("아이디, 비밀번호를 확인해주세요");
          console.log(error);
        });
    },
  },
};
</script>

<style scoped>
div.grid {
  width: 1050px;
  margin: 0 auto;
  text-align: center;
}

.login-title {
  padding-top: 100px;
  padding-bottom: 20px;
}

hr {
  width: 600px;
  text-align: center;
  margin: 0px auto;
}

.login-part {
  width: 350px;
  margin: 0 auto;
  padding-bottom: 250px;
}

.id-box {
  margin-top: 10px;
}

.id-input,
.pwd-input {
  width: 350px;
  padding: 10px;
  text-align: left;
  margin: 10px auto;
  border-radius: 10px;
  border: 1px solid #dddddd;
}

.find-id-pwd {
  font-size: 12px;
  text-align: end;
}

.v-application a {
  text-decoration: none;
  color: black;
}

#loginBtn {
  width: 350px;
  height: 54px;
  padding: 22px;
  margin: 20px auto;
  border-radius: 10px;
  border: 1px solid #dddddd;
  color: white;
  font-weight: bold;
  background-color: #5f0080;
}

.v-btn--is-elevated {
  box-shadow: 0 !important;
}

#joinBtn {
  width: 350px;
  height: 54px;
  margin: 0px;
  padding: 10px;
  border-radius: 10px;
  border: 1px solid #5f0080;
  font-weight: bold;
  color: #5f0080;
  background-color: white;
}

.login-title > h1 {
  font-size: 20px;
}
</style>

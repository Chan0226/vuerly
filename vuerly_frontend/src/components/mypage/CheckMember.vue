<template>
  <div>
    <h1>비밀번호 재확인</h1>
    <p class="password-check-info">
      회원님의 정보를 안전하게 보호하기 위해 비밀번호를 다시 한번 확인해주세요
    </p>
    <div class="member-info-form">
      <div class="member-id">
        <div class="id-box">
          <div class="box1">
            <label for="ID">
              <span>아이디</span>
            </label>
          </div>
          <div class="box2">
            <input type="text" v-model="id" id="ID" class="id-input" readonly />
          </div>
        </div>
      </div>
      <div class="member-password">
        <div class="password-box">
          <div class="box1">
            <label for="password">
              <span>비밀번호</span>
            </label>
          </div>
          <div class="box2">
            <input
              type="password"
              v-model="password"
              id="password"
              class="pwd-input"
              placeholder="비밀번호를 입력해주세요"
              @keyup.enter="validMember"
            />
          </div>
        </div>
      </div>
    </div>
    <div class="button-area">
      <button @click="validMember" class="check-member">확인</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  props: ["propsdata"],
  data() {
    return {
      certi: "",
      id: "",
      pssword: "",
    };
  },
  methods: {
    // 비밀번호 일치 여부 확인
    validMember() {
      let data = {};
      data.id = this.id;
      data.password = this.password;
      axios
        .post("/member/checkMemberPassword", JSON.stringify(data), {
          headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Credentials": "true",
          },
        })
        .then((res) => {
          if (res.data == -1) {
            alert("비밀번호가 일치하지 않습니다.");
          } else {
            // 비밀번호 일치하면, 수정 컴포넌트로 교체
            this.certi = "EditProfile";
            this.$emit("pass", this.certi);
          }
        })
        .catch((error) => {
          alert("아이디, 비밀번호를 확인해주세요");
          console.log(error);
        });
    },
  },
  created() {
    this.certi = this.propsdata;
    // 로그인 세션
    this.secretKey = this.$session.get("secretKey");
    // 회원 id 불러오기
    axios
      .get("/member/getMemberInfo?secretKey=" + this.secretKey)
      .then((response) => {
        this.models = response.data;
        this.id = this.models.id;
      })
      .catch((error) => {
        console.log(error);
      });
  },
};
</script>

<style scoped>
h1 {
  font-size: 20px;
  padding-bottom: 10px;
}
.password-check-info {
  font-size: 13px;
}
.id-box,
.password-box {
  display: flex;
  align-items: center;
  margin-left: auto;
  margin-right: auto;
}
.member-info-form {
  border-top: 2px solid black;
  height: 200px;
}
.box1 {
  width: 100px;
}
.box2 {
  width: 400px;
  margin-left: 15px;
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
.check-member {
  margin-top: 40px;
}
.member-id,
.member-password {
  padding: 20px;
  display: flex;
  height: 100px;
}
.button-area {
  width: 100%;
  text-align: center;
  border-top: 0.1px solid #dddddd;
}
.check-member {
  width: 250px;
  height: 60px;
  padding: 10px;
  margin: 20px auto;
  border: 1px solid #dddddd;
  color: white;
  font-weight: bold;
  background-color: #5f0080;
}
</style>

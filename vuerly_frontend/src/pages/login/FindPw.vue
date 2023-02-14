<template>
  <div class="grid">
    <div :style="customStyle">
      <!-- 제목 -->
      <div class="find-id-and-pwd-title">
        <h1>비밀번호 찾기</h1>
      </div>

      <!-- 이메일 / 휴대폰 인증 버튼-->
      <div class="find-btn-div">
        <div v-for="(type, i) in types" :key="i" class="find-id-div">
          <button
            type="button"
            class="find-id-btn"
            :class="{ active: type.id === isActive }"
            @click="findIdBtn(type.id)"
          >
            {{ type.label }}
          </button>
        </div>
      </div>
    </div>

    <!-- 인증번호 받기 이전 -->
    <div class="enter-part" v-if="data == 0 && complete == 0">
      <!-- 아이디 -->
      <div>
        <label for="name" class="name-text"><span>아이디</span></label>
        <div class="name-box">
          <input
            type="text"
            v-model="id"
            id="name"
            class="name-input"
            placeholder="아이디를 입력해주세요"
          />
        </div>
      </div>
      <!-- 휴대폰, 이메일 -->
      <div>
        <label for="email" class="email-text"
          ><span>{{ idLabel }}</span></label
        >
        <!-- 이메일 인증 경우 -->
        <div class="email-box" v-if="idLabel == '이메일 주소'">
          <input
            type="text"
            v-model="email"
            id="email"
            class="email-input"
            :placeholder="idLabel + '를 입력해주세요'"
          />
        </div>
        <!-- 핸드폰 인증 경우 -->
        <div class="cellphone-box" v-else>
          <input
            type="text"
            v-model="phoneNumber"
            id="phoneNumber"
            class="cellphone-input"
            :placeholder="idLabel + '를 입력해주세요'"
            maxlength="13"
            @input="phoneVaild"
          />
        </div>
      </div>
      <!-- 인증번호 받기 버튼 -->
      <!-- 이메일 인증일 경우 -->
      <div class="button-part" v-if="idLabel == '이메일 주소'">
        <button
          type="submit"
          id="getCertificaationNum"
          class="certification-btn"
          @click="searchIdEmail"
        >
          인증번호 받기
        </button>
      </div>
      <!-- 휴대폰 인증일 경우 -->
      <div class="button-part" v-else>
        <button
          type="submit"
          id="getCertificaationNum"
          class="certification-btn"
          @click="searchIdPhone"
        >
          인증번호 받기
        </button>
      </div>
    </div>

    <!-- 인증번호 보낸 이후 -->
    <div class="enter-part" v-else-if="data > 0 && complete == 0">
      <!-- 아이디 -->
      <div>
        <label for="name" class="name-text"><span>아이디</span></label>
        <div class="name-box">
          <input
            type="text"
            v-model="id"
            id="name"
            class="name-input"
            readonly
          />
        </div>
      </div>
      <!-- 휴대폰, 이메일 -->
      <div>
        <label for="email" class="email-text"
          ><span>{{ idLabel }}</span></label
        >
        <!-- 이메일인 경우 -->
        <div class="email-box" v-if="idLabel == '이메일 주소'">
          <input
            type="text"
            v-model="email"
            id="email"
            class="email-input"
            readonly
          />
        </div>
        <!-- 핸드폰인 경우 -->
        <div class="cellphone-box" v-else>
          <input
            type="text"
            v-model="phoneNumber"
            id="phoneNumber"
            class="cellphone-input"
            readonly
          />
        </div>
      </div>
      <div>
        <label for="chkNum" class="chkNum-text"><span>인증번호</span></label>
        <div class="chkNum-box">
          <input
            type="text"
            v-model="chkNum"
            id="chkNum"
            class="chkNum-input"
            placeholder="인증번호 6자리"
            maxlength="6"
          />
          <!-- 이메일 인증일 경우 -->
          <button
            type="submit"
            id="reCertificaationNum"
            @click="emailCert"
            v-if="idLabel == '이메일 주소'"
          >
            재전송
          </button>
          <!-- 휴대폰 인증일 경우 -->
          <button
            type="submit"
            id="reCertificaationNum"
            @click="phoneCert"
            v-else
          >
            재전송
          </button>
        </div>
      </div>

      <!-- 인증번호 확인 버튼 -->
      <div class="button-part">
        <button
          type="submit"
          id="getCertificaationNum"
          class="certification-btn"
          @click="checkChkNum"
        >
          확인
        </button>
      </div>
    </div>

    <!-- 인증완료 후, 새로운 비밀번호입력 -->
    <div class="enter-part" v-else>
      <!-- 새 비밀번호 -->
      <div>
        <label for="name" class="name-text"
          ><span>새 비밀번호 등록</span></label
        >
        <div class="name-box">
          <input
            type="password"
            v-model="newPw"
            id="name"
            class="name-input"
            placeholder="새 비밀번호를 입력해주세요"
            @keyup="passwordValid"
          />
          <div v-if="!passwordValidFlag" class="passwordValidFlag">
            대문자 / 소문자 / 숫자가 1개 이상 존재하고 8자이상 16자 이하로 처리
          </div>
        </div>
      </div>
      <!-- 새 비밀번호 확인 -->
      <div>
        <label for="name" class="name-text"
          ><span>새 비밀번호 확인</span></label
        >
        <div class="name-box">
          <input
            type="password"
            v-model="passwordCheck"
            id="name"
            class="name-input"
            placeholder="새 비밀번호를 한 번 더 입력해주세요"
            @keyup="passwordCheckValid"
          />
          <div v-if="!passwordCheckFlag" class="passwordCheckFlag">
            비밀번호가 동일하지 않습니다.
          </div>
        </div>
      </div>
      <!-- 인증번호 받기 버튼 -->
      <div class="button-part">
        <button
          type="submit"
          id="getCertificaationNum"
          class="certification-btn"
          @click="changePassword"
        >
          확인
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
// const HOST = "http://localhost:3000";
export default {
  data() {
    return {
      isActive: 0,
      name: "",
      types: [
        { id: 0, label: "휴대폰 인증" },
        { id: 1, label: "이메일 인증" },
      ],
      idLabel: "휴대폰 번호",
      // 아이디 존재여부
      data: 0,
      email: "",
      // 인증번호
      chkNum: null,
      rightChkNum: null,
      // 인증완료 여부
      complete: 0,
      id: "",
      newPw: null,
      // 비밀번호 유효성검사
      passwordValidFlag: true,
      // 비밀번호 확인
      passwordCheck: null,
      // 비밀번호 일치여부
      passwordCheckFlag: true,
      // 제목, 인증 영역 보이게
      visibility: "visible",
      // 핸드폰 번호
      phoneNumber: "",
    };
  },
  methods: {
    findIdBtn(id) {
      this.isActive = id;
      if (id == 1) {
        this.idLabel = "이메일 주소";
      } else {
        this.idLabel = "휴대폰 번호";
      }
    },
    // 아이디,이메일 여부 확인
    searchIdEmail() {
      axios
        .get("/member/checkIdEmail?id=" + this.id + "&email=" + this.email)
        .then((response) => {
          this.data = response.data;
          if (this.data == 0) {
            alert("가입시 입력하신 회원정보가 맞는지 다시 한번 확인해 주세요");
            return;
          } else {
            // 이메일 전송
            this.emailCert();
            alert("입력하신 메일로 인증번호를 보냈습니다.");
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 아이디,휴대폰 번호 여부 확인
    searchIdPhone() {
      this.phoneNumber = this.phoneNumber.replaceAll("-", "");
      axios
        .get(
          "/member/checkIdPhone?id=" + this.id + "&phoneNum=" + this.phoneNumber
        )
        .then((response) => {
          this.data = response.data;
          if (this.data == 0) {
            alert("가입시 입력하신 회원정보가 맞는지 다시 한번 확인해 주세요");
            return;
          } else {
            // 이메일 전송
            this.phoneCert();
            alert("입력하신 휴대번호로 인증번호를 보냈습니다.");
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 이메일 인증번호 전송
    emailCert() {
      let params = new URLSearchParams();
      params.append("email", this.email);
      axios
        .post("member/mailConfirm", params)
        .then((res) => {
          // res.data : 인증번호
          this.rightChkNum = res.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },

    // 인증번호 일치 여부 확인
    checkChkNum() {
      if (this.chkNum == this.rightChkNum) {
        alert("인증번호 일치");
        this.complete = 1;
        this.visibility = "hidden";
      } else {
        alert("인증번호 불일치");
      }
    },

    // 비밀번호 유효성검사(대문자,소문자,숫자 1갱상 존재, 8자 이상 16자 이하)
    passwordValid() {
      if (/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,16}$/.test(this.newPw)) {
        this.passwordValidFlag = true;
      } else {
        this.passwordValidFlag = false;
      }
    },

    // 비밀번호 비교(비밀번호 - 비밀번호 확인)
    passwordCheckValid() {
      if (this.newPw === this.passwordCheck) {
        this.passwordCheckFlag = true;
      } else {
        this.passwordCheckFlag = false;
      }
    },

    // 비밀번호 수정
    changePassword() {
      if (this.passwordValidFlag == false) {
        alert("유효한 비밀번호로 입력해주세요");
      } else if (this.passwordCheckFlag == false)
        alert("비밀번호가 일치하는지 확인해주세요");
      else {
        let data = {
          password: this.newPw,
          id: this.id,
        };
        axios
          .patch("/member/updatePwd", JSON.stringify(data), {
            headers: {
              "Content-Type": "application/json",
              "Access-Control-Allow-Origin": "*",
              "Access-Control-Allow-Credentials": "true",
            },
          })
          .then(() => {
            alert("비밀번호 수정이 완료되었습니다.");
            this.$router.push("login");
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
    // 휴대폰 정규표현식
    phoneVaild() {
      this.phoneNumber = this.phoneNumber.replace(
        /^(\d{3})(\d{4})(\d{4})$/g,
        "$1-$2-$3"
      );
    },

    // 휴대폰 인증
    phoneCert() {
      let data = {
        to: this.phoneNumber,
      };
      axios
        .post("/sms/send", JSON.stringify(data), {
          headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Credentials": "true",
          },
        })
        .then((res) => {
          // res.data : 인증번호
          this.rightChkNum = res.data.smsConfirmNum;
          //console.log(this.rightChkNum);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  computed: {
    customStyle() {
      return {
        visibility: this.visibility,
      };
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

.find-id-and-pwd-title {
  padding-top: 100px;
  padding-bottom: 20px;
}
.find-id-and-pwd-title > h1 {
  font-size: 25px;
  margin-bottom: 30px;
}

.find-btn-div {
  width: 300px;
  margin: 0px auto;
  display: flex;
  justify-content: space-between;
}

.active {
  color: #5f0080;
  border-bottom: solid #5f0080;
  border-bottom-width: 1px;
}

.find-id-div {
  color: gray;
  font-weight: bold;
}

.name-text,
.email-text,
.chkNum-text {
  width: 350px;
  margin: 0px auto;
  display: flex;
  font-weight: bold;
  margin-top: 20px;
}

.enter-part {
  width: 350px;
  margin: 0 auto;
  padding-bottom: 250px;
}

.name-input,
.email-input,
.cellphone-input {
  width: 350px;
  padding: 10px;
  text-align: left;
  margin: 10px auto;
  border-radius: 10px;
  border: 1px solid #dddddd;
}
.chkNum-input {
  width: 240px;
  float: left;
  padding: 10px;
  text-align: left;
  margin: 10px auto;
  border-radius: 10px;
  border: 1px solid #dddddd;
}

#getCertificaationNum {
  width: 350px;
  height: 60px;
  padding: 10px;
  margin: 20px auto;
  border-radius: 10px;
  border: 1px solid #dddddd;
  color: white;
  font-weight: bold;
  background-color: #5f0080;
}

#reCertificaationNum {
  width: 100px;
  padding: 10px;
  margin: 10px auto;
  border-radius: 10px;
  border: 1px solid #dddddd;
  color: white;
  font-weight: bold;
  background-color: #5f0080;
}

.find-id-sentence {
  text-align: left;
}
#checkLogin {
  color: gray;
  margin-top: 5px;
}
.find-id-value {
  text-align: left;
  margin-top: 10px;
}
#findId {
  font-weight: 80px;
  font-size: 20px;
}

.button-area {
  margin-top: 40px;
}
#findPasswordButton {
  width: 350px;
  height: 60px;
  padding: 10px;
  margin: 30px auto;
  border-radius: 10px;
  border: 1px solid #5f0080;
  color: #5f0080;
  font-weight: bold;
  background-color: white;
}

#goLoginButton {
  width: 350px;
  height: 60px;
  padding: 10px;
  margin: 0px auto;
  border-radius: 10px;
  border: 1px solid #dddddd;
  color: white;
  font-weight: bold;
  background-color: #5f0080;
}

.passwordValidFlag {
  font-size: 10px;
  color: red;
  float: left;
}

.passwordCheckFlag {
  font-size: 10px;
  color: red;
  float: left;
}
</style>

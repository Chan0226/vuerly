<template>
  <div class="grid">
    <div :style="customStyle">
      <!-- 제목 -->
      <div class="find-id-and-pwd-title">
        <h1>아이디 찾기</h1>
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
      <!-- 이름 -->
      <div>
        <label for="name" class="name-text"><span>이름</span></label>
        <div class="name-box">
          <input
            type="text"
            v-model="name"
            id="name"
            class="name-input"
            placeholder="이름을 입력해주세요"
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
      <div class="button-part">
        <button
          type="submit"
          id="getCertificaationNum"
          class="certification-btn"
          @click="searchId"
        >
          인증번호 받기
        </button>
      </div>
    </div>

    <!-- 인증번호 보낸 이후 -->
    <div class="enter-part" v-else-if="data > 0 && complete == 0">
      <!-- 이름 -->
      <div>
        <label for="name" class="name-text"><span>이름</span></label>
        <div class="name-box">
          <input
            type="text"
            v-model="name"
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
    <!-- 인증완료 후, 아이디 보여주기 -->
    <div class="enter-part" v-else>
      <div class="find-id-sentence">
        <h2>고객님의 계정을 찾았습니다.</h2>
        <p id="checkLogin">아이디 확인 후 로그인해주세요</p>
      </div>
      <div class="find-id-value">
        <p id="findId">{{ id }}</p>
      </div>
      <div class="button-area">
        <router-link to="/findPw">
          <button
            type="submit"
            id="findPasswordButton"
            class="certification-btn"
          >
            비밀번호 찾기
          </button>
        </router-link>
        <router-link to="/login">
          <button type="submit" id="goLoginButton" class="certification-btn">
            로그인
          </button>
        </router-link>
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
      email: null,
      // 인증번호
      chkNum: null,
      rightChkNum: null,
      // 인증완료 여부
      complete: 0,
      id: "",
      // 제목, 인증 영역 보이게
      visibility: "visible",
      // 핸드폰 번호
      phoneNumber: null,
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
    // 회원 여부 확인
    searchId() {
      //console.log(this.idLabel);
      axios
        .get("/member/checkName?name=" + this.name)
        .then((response) => {
          this.data = response.data;
          if (this.data == 0) {
            alert("가입시 입력하신 회원정보가 맞는지 다시 한번 확인해 주세요");
            return;
          } else if (this.data > 0 && this.idLabel == "휴대폰 번호") {
            this.phoneNumber = this.phoneNumber.replaceAll("-", "");
            //console.log(this.idLabel);
            this.phoneCert();
            alert("입력하신 휴대폰으로 인증번호를 보냈습니다.");
          } else {
            this.emailCert();
            alert("입력하신 메일로 인증메일을 보냈습니다.");
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 인증완료 이후, 아이디 불러오기
    getId() {
      axios
        .get(
          "/member/getId?name=" +
            this.name +
            "&phoneNum=" +
            this.phoneNumber +
            "&email=" +
            this.email
        )
        .then((response) => {
          this.id = response.data;
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
        .post("/member/mailConfirm", params)
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
        this.getId();
      } else {
        alert("인증번호 불일치");
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
</style>

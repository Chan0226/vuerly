<template>
  <div class="change-profile">
    <div class="component-bg">
      <!-- header -->
      <div class="edit-profile-header">
        <h1>개인정보수정</h1>
      </div>

      <!-- 회원정보수정 -->
      <div class="edit-profile-part">
        <!-- 아이디 -->
        <div class="id-box">
          <div class="box1">
            <label for="ID">
              <span>아이디<span class="star-red">*</span></span>
            </label>
          </div>
          <div class="box2">
            <input type="text" v-model="id" id="ID" class="id-input" readonly />
          </div>
        </div>

        <!-- 새 비밀번호 -->
        <div class="new-pwd-box">
          <div class="box1">
            <label for="newPwd">
              <span>새 비밀번호<span class="star-red">*</span></span>
            </label>
          </div>
          <div class="box2">
            <input
              type="password"
              v-model="password"
              id="newPwd"
              class="new-pwd-input"
              placeholder="새 비밀번호를 입력해주세요"
              @keyup="passwordValid"
            />
            <div v-if="!passwordValidFlag" class="passwordValidFlag">
              대문자 / 소문자 / 숫자가 1개 이상 존재하고 8자이상 16자 이하로
              처리
            </div>
          </div>
        </div>

        <!-- 새 비밀번호 확인 -->
        <div class="check-new-pwd-box">
          <div class="box1">
            <label for="check-new-pwd">
              <span class="check-new-pwd-text"
                >비밀번호 확인<span class="star-red">*</span></span
              >
            </label>
          </div>
          <div class="box2">
            <input
              type="password"
              v-model="passwordCheck"
              id="checkNewPwd"
              class="check-new-pwd-input"
              placeholder="비밀번호를 다시 입력해주세요"
              @keyup="passwordCheckValid"
            />
            <div v-if="!passwordCheckFlag" class="passwordCheckFlag">
              비밀번호가 동일하지 않습니다.
            </div>
          </div>
        </div>

        <!-- 이름 -->
        <div class="name-box">
          <div class="box1">
            <label for="name">
              <span>이름<span class="star-red">*</span></span>
            </label>
          </div>
          <div class="box2">
            <input
              type="text"
              v-model="name"
              id="name"
              class="name-input"
              placeholder="이름을 입력해주세요"
              maxlength="20"
            />
          </div>
        </div>

        <!-- 전화번호 -->
        <div class="phone-box">
          <div class="box1">
            <label for="phone">
              <span>전화번호<span class="star-red">*</span></span>
            </label>
          </div>
          <div class="box2">
            <input
              type="text"
              v-model="phoneNum"
              id="phone"
              class="phone-input"
              placeholder="전화번호를 입력해주세요"
              maxlength="13"
              @keyup="phoneValid"
            />
          </div>
        </div>

        <!-- 이메일 -->
        <div class="email-box">
          <div class="box1">
            <label for="email">
              <span>이메일<span class="star-red">*</span></span>
            </label>
          </div>
          <div class="box2">
            <input
              type="text"
              v-model="email"
              id="email"
              class="email-input"
              placeholder="이메일을 입력해주세요"
              @keyup="emailValid"
            />
            <div v-if="!emailValidFlag" class="emailValidFlag">
              이메일 형식으로 입력해주세요.
            </div>
          </div>
          <div class="box3">
            <button
              type="button"
              id="duplicateEmailBtn"
              class="duplicate-btn"
              @click="checkEmail"
            >
              중복확인
            </button>
          </div>
        </div>

        <!-- 주소 검색 -->
        <div class="address-box">
          <div class="box1">
            <label for="address">
              <span>주소<span class="star-red">*</span></span>
            </label>
          </div>
          <div class="box2" v-if="roadAddress == null">
            <button
              type="button"
              id="searchAddrBtn"
              class="address-btn"
              @click="openPostcode"
            >
              <img
                src="https://res.kurly.com/pc/service/cart/2007/ico_search.svg"
              />주소 검색
            </button>
          </div>
          <div class="box2" v-else>
            <input
              type="text"
              v-model="roadAddress"
              class="address-input"
              placeholder="주소"
              readonly
            />
            <input
              type="text"
              v-model="detailAddress"
              id="name"
              class="address-input"
              placeholder="상세주소를 입력해주세요"
              maxlength="20"
            />
          </div>
        </div>

        <!-- 성별 -->
        <div class="sex-box">
          <div class="box1">
            <label for="sex">
              <span>성별<span class="star-red">*</span></span>
            </label>
          </div>
          <div class="box2">
            <input
              type="radio"
              v-model="sex"
              id="male"
              class="check-sex"
              name="sex"
              value="1"
            />
            <label for="male">남자</label>
            <input
              type="radio"
              v-model="sex"
              id="female"
              class="check-sex"
              name="female"
              value="2"
            />
            <label for="female">여자</label>
          </div>
        </div>

        <!-- 생년월일 -->
        <div class="birth-box">
          <div class="box1">
            <label for="birth">
              <span>생년월일<span class="star-red">*</span></span>
            </label>
          </div>
          <div class="box2">
            <input
              type="text"
              v-model="birth"
              id="birth"
              class="birth-input"
              placeholder="YYYY / MM / DD"
              @keyup="birthValid()"
              maxlength="8"
            />
          </div>
        </div>

        <hr />

        <!-- 가입하기 버튼 -->
        <div class="button-part">
          <button
            type="button"
            id="resignBtn"
            class="resign-btn"
            @click="deleteAxios"
          >
            탈퇴하기
          </button>
          <button
            type="submit"
            id="editBtn"
            class="edit-btn"
            @click="modifyAxios"
          >
            회원정보수정
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      num: 0,
      id: null,
      // 아이디 유효성검사
      idValidFlag: true,
      password: null,
      // 비밀번호 유효성검사
      passwordValidFlag: true,
      passwordCheck: null,
      // 비밀번호 일치여부
      passwordCheckFlag: true,
      name: "",
      email: null,
      // 이메일 유효성검사
      emailValidFlag: true,
      idchk: 1,
      emailchk: 1,
      // 전화번호
      phoneNum: "",
      // 주소
      roadAddress: null,
      // 상세주소
      detailAddress: null,
      sex: null,
      birth: null,
      birthFormat: null,
      letJoin: 0,
    };
  },
  methods: {
    // 비밀번호 유효성검사(대문자,소문자,숫자 1갱상 존재, 8자 이상 16자 이하)
    passwordValid() {
      if (/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,16}$/.test(this.password)) {
        this.passwordValidFlag = true;
      } else {
        this.passwordValidFlag = false;
      }
    },
    // 비밀번호 비교(비밀번호 - 비밀번호 확인)
    passwordCheckValid() {
      if (this.password === this.passwordCheck) {
        this.passwordCheckFlag = true;
      } else {
        this.passwordCheckFlag = false;
      }
    },
    // 이메일 유효성 검사(~@)
    emailValid() {
      if (
        /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/.test(
          this.email
        )
      ) {
        this.emailValidFlag = true;
      } else {
        this.emailValidFlag = false;
      }
    },
    openPostcode() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          //console.log(data);
          this.zonecode = data.zonecode;
          this.roadAddress = data.roadAddress;
        },
      }).open();
    },
    // 전화번호 정규식
    phoneValid() {
      this.phoneNum = this.phoneNum.replace(
        /(\d{3})(\d{4})(\d{4})/g,
        "$1-$2-$3"
      );
    },
    // 생년월일 /(슬래시) 설정
    birthValid() {
      this.birth = this.birth.replace(/(\d{4})(\d{2})(\d{2})/g, "$1/$2/$3");
    },
    // 이메일 중복체크
    checkEmail() {
      // 이메일값 NULL 이거나 없을경우 입력하도록
      if (this.email == null || this.email == "") {
        alert("이메일을 입력해주세요");
      } else if (this.emailValidFlag == false) {
        alert("이메일은 형식이 아닙니다.");
      } else {
        axios
          .get("/member/checkEmail?email=" + this.email)
          .then((response) => {
            this.emailchk = response.data;
            if (this.emailchk === 0) {
              alert("사용 가능한 이메일입니다.");
            } else {
              alert("이미 사용중인 이메일입니다.");
            }
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },

    //  회원 수정 axios
    modifyAxios() {
      // birth 데이트 타입으로 변환
      this.birth = this.birth.replaceAll("/", "");
      this.birth = this.birth.replace(/(\d{4})(\d{2})(\d{2})/g, "$1-$2-$3");

      // 전화번호 숫자로 변환
      this.phoneNum = this.phoneNum.replaceAll("-", "");

      let data = {
        id: this.id,
        password: this.password,
        name: this.name,
        email: this.email,
        addr: this.roadAddress + "," + this.detailAddress,
        sex: this.sex,
        phoneNum: this.phoneNum,
        birth: this.birth,
      };
      axios
        .patch("/member/updateMember", JSON.stringify(data), {
          headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Credentials": "true",
          },
        })
        .then(() => {
          alert("회원수정이 완료되었습니다.");
          this.$router.go(this.$router.currentRoute);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 회원 삭제
    deleteAxios() {
      axios
        .delete("/member/deleteMember?secretKey=" + this.secretKey)
        .then(() => {
          alert("탈퇴가 완료되었습니다.");
          this.$session.remove("secretKey");
          this.$router.push("/");
          this.$router.go(this.$router.currentRoute);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  created() {
    // 세션에서 회원정보 불러오기
    this.secretKey = this.$session.get("secretKey");
    axios
      .get("/member/updateMemberInfo?secretKey=" + this.secretKey)
      .then((response) => {
        this.id = response.data.id;
        this.name = response.data.name;
        this.email = response.data.email;
        this.sex = response.data.sex;
        this.phoneNum = response.data.phoneNum;
        this.phoneValid();
      })
      .catch((error) => {
        console.log(error);
      });
  },
};
</script>

<style scoped>
/* .bg {
  color: #333;
  margin: auto;
  max-width: 1050px;
  width: 100%;
  /* padding: 30px 0; */
/* }  */

.change-profile {
  display: inline-block;
  vertical-align: top;
  flex-grow: 1;
  /* border: 1px solid #d6d6d6; */
  /* padding: 20px; */
  width: 100%;
  height: 100%;
}

.component-bg h1 {
  font-size: 24px;
}

.component-bg {
  color: #333;
  margin: auto;
  height: 100%;
}

.edit-profile-header {
  display: flex;
  border-bottom: 1px solid #333;
  padding: 10px;
}

.edit-profile-part {
  padding: 20px;
  margin: 0px auto;
  width: 600px;
}

.star-red {
  color: red;
}

hr {
  width: 600px;
  text-align: center;
  margin: 0px auto;
}

.id-box,
.now-pwd-box,
.new-pwd-box,
.check-new-pwd-box,
.name-box,
.phone-box,
.address-box,
.sex-box,
.birth-box,
.agree-box {
  display: flex;
  align-items: center;
}
.email-box {
  display: flex;
  align-items: center;
  width: 650px;
}

.box1,
.box3 {
  width: 100px;
}

.box2 {
  width: 400px;
  margin-left: 15px;
}

.id-input,
.now-pwd-input,
.new-pwd-input,
.check-new-pwd-input,
.name-input,
.email-input,
.phone-input,
.address-input {
  width: 350px;
  padding: 10px;
  text-align: left;
  margin: 10px auto;
  border-radius: 10px;
  border: 1px solid #dddddd;
}

.birth-input {
  width: 350px;
  padding: 10px;
  text-align: center;
  margin: 10px auto;
  margin-bottom: 30px;
  border-radius: 10px;
  border: 1px solid #dddddd;
}

.duplicate-btn {
  width: 100%;
  padding: 10px;
  margin: 20px auto;
  border-radius: 10px;
  border: 1px solid #5f0080;
  color: #5f0080;
  font-weight: bold;
}

.now-pwd-text,
.check-new-pwd-text {
  font-size: 14px;
}

.address-btn {
  width: 350px;
  padding: 10px;
  margin: 10px auto;
  border-radius: 10px;
  border: 1px solid #5f0080;
  color: #5f0080;
  font-weight: bold;
}

.check-sex {
  margin: 20px;
}

[type="radio"] {
  vertical-align: middle;
  appearance: none;
  border: max(2px, 0.1em) solid gray;
  border-radius: 50%;
  width: 1.25em;
  height: 1.25em;
  transition: border 0.5s ease-in-out;
}

[type="radio"]:checked {
  border: 0.4em solid #5f0080;
}

.button-part {
  display: flex;
  margin-top: 20px;
}

#resignBtn {
  width: 150px;
  margin: 20px auto;
  padding: 10px;
  border-radius: 10px;
  border: 1px solid #dddddd;
  font-weight: bold;
}

#editBtn {
  width: 150px;
  padding: 10px;
  margin: 20px auto;
  border-radius: 10px;
  border: 1px solid #dddddd;
  color: white;
  font-weight: bold;
  background-color: #5f0080;
}

.passwordValidFlag {
  font-size: 13px;
  color: red;
  float: left;
  /* margin-left: 25px; */
}

.passwordCheckFlag {
  font-size: 13px;
  color: red;
  float: left;
  /* margin-left: 25px; */
}

.emailValidFlag {
  font-size: 13px;
  color: red;
  float: left;
  /* margin-left: 25px; */
}
</style>

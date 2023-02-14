<template>
  <div class="grid">
    <!-- 회원가입 제목 -->
    <div class="join-box">
      <h1>회원가입</h1>
      <h5 class="must-write">필수입력사항<span class="star-red">*</span></h5>
    </div>

    <hr />

    <!-- 회원가입 -->
    <div class="join-part">
      <!-- 아이디 -->
      <div class="id-box">
        <div class="box1">
          <label for="ID">
            <span>아이디<span class="star-red">*</span></span>
          </label>
        </div>
        <div class="box2">
          <input
            type="text"
            v-model="id"
            id="ID"
            class="id-input"
            placeholder="아이디를 입력해주세요"
            maxlength="16"
            @keyup="idValid"
          />
          <div v-if="!idValidFlag" class="notValidId">
            6자 이상 16자 이하의 영문 혹은 영문과 숫자를 조합
          </div>
        </div>
        <div class="box3">
          <button
            type="button"
            id="duplicateIDBtn"
            class="duplicate-btn"
            @click="checkID"
          >
            중복확인
          </button>
        </div>
      </div>

      <!-- 비밀번호 -->
      <div class="pwd-box">
        <div class="box1">
          <label for="pwd">
            <span>비밀번호<span class="star-red">*</span></span>
          </label>
        </div>
        <div class="box2">
          <input
            type="password"
            v-model="password"
            id="pwd"
            class="pwd-input"
            minlength="10"
            placeholder="비밀번호를 입력해주세요"
            @keyup="passwordValid"
          />
          <div v-if="!passwordValidFlag" class="passwordValidFlag">
            대문자 / 소문자 / 숫자가 1개 이상 존재하고 8자이상 16자 이하로 처리
          </div>
        </div>
      </div>

      <!-- 비밀번호 확인 -->
      <div class="check-pwd-box">
        <div class="box1">
          <label for="check-pwd">
            <span class="check-pwd-text"
              >비밀번호 확인<span class="star-red">*</span></span
            >
          </label>
        </div>
        <div class="box2">
          <input
            type="password"
            v-model="passwordCheck"
            id="checkPwd"
            class="check-pwd-input"
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

      <!-- 이용약관동의 -->
      <div class="agree-box">
        <div class="box1">
          <label for="agree">
            <span class="agree-text"
              >이용약관동의<span class="star-red">*</span></span
            >
          </label>
        </div>
        <div class="box4">
          <div class="agree-part">
            <!-- 전체동의 -->
            <input
              type="checkbox"
              id="checkAll"
              class="check-agree"
              v-model="checkAll"
              @change="toggleCheckAll"
            />
            <label for="checkAll"
              ><span class="bold-text">전체 동의합니다.</span></label
            >
            <p class="agree-little-part">
              선택항목에 동의하지 않은 경우 회원가입 및 일반적인 서비스를 이용할
              수 없습니다.
            </p>
          </div>
          <div class="agree-part">
            <!-- 체크1 -->
            <input
              type="checkbox"
              v-model="agreements[0].checked"
              id="agreeCheck"
              class="check-agree"
              @change="checkIfAllChecked"
            />
            <label for="agreeCheck">이용약관동의(필수)</label>
            <button type="button" class="agreePopUp">약관동의&gt;</button>
          </div>
          <div class="agree-part">
            <!-- 체크2 -->
            <input
              type="checkbox"
              v-model="agreements[1].checked"
              id="agreeCheck"
              class="check-agree"
              @change="checkIfAllChecked"
            />
            <label for="personalAgreeCheck"
              >개인정보 수집.이용 동의(필수)</label
            >
            <button type="button" class="agreePopUp">약관동의&gt;</button>
          </div>
          <div class="agree-part">
            <!-- 체크3 -->
            <input
              type="checkbox"
              v-model="agreements[2].checked"
              id="agreeCheck"
              class="check-agree"
              @change="checkIfAllChecked"
            />
            <label for="ageAgreeCheck">본인은 만 14세 이상입니다.(필수)</label>
          </div>
        </div>
      </div>

      <!-- 가입하기 버튼 -->
      <div class="button-part">
        <button type="submit" id="joinBtn" class="join-btn" @click="memberJoin">
          가입하기
        </button>
      </div>
      <!-- <img src="@/static/check.png" alt="" /> -->
    </div>
  </div>
</template>

<script>
import axios from "axios";
const HOST = "http://localhost:3000";
export default {
  data() {
    return {
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
      // 동의 항목
      selectedOption: "",
      checkAll: false,
      agreements: [
        { text: "Agreement 1", checked: false },
        { text: "Agreement 2", checked: false },
        { text: "Agreement 3", checked: false },
      ],
    };
  },
  methods: {
    // 아이디 유효성검사(6~16자 이하 영문 숫자 조합)
    idValid() {
      if (/^(?=.*[a-zA-Z])(?=.*[0-9]).{6,16}$/.test(this.id)) {
        this.idValidFlag = true;
      } else {
        this.idValidFlag = false;
      }
    },
    // 비밀번호 유효성검사(대문자,소문자,숫자 1이상 존재, 8자 이상 16자 이하)
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
    // 아이디 중복체크
    checkID() {
      // 아이디값 NULL이거나 없을경우 입력하도록
      if (this.id == null || this.id == "") {
        alert("아이디를 입력해주세요");
      } else if (this.idValidFlag == false) {
        alert("유효한 아이디 형식이 아닙니다.");
      } else {
        axios
          .get("/member/idCheck?id=" + this.id)
          .then((response) => {
            this.idchk = response.data;
            if (this.idchk === 0) {
              alert("사용 가능한 아이디입니다.");
            } else {
              alert("이미 사용중인 아이디입니다.");
            }
          })
          .catch((error) => {
            console.log(error);
          });
      }
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
    openPostcode() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          console.log(data);
          this.zonecode = data.zonecode;
          this.roadAddress = data.roadAddress;
        },
      }).open();
    },
    // 회원가입
    memberJoin() {
      // birth 데이트 타입으로 변환
      this.birth = this.birth.replaceAll("/", "");
      this.birth = this.birth.replace(/(\d{4})(\d{2})(\d{2})/g, "$1-$2-$3");

      // 전화번호 숫자로 변환
      this.phoneNum = this.phoneNum.replaceAll("-", "");

      // 아이디 중복 체크를 안했을 경우
      if (this.idchk !== 0) {
        alert("아이디 중복 체크를 해주세요");
      }
      // 이메일 중복 체크를 안했을 경우
      else if (this.emailchk !== 0) {
        alert("이메일 중복 체크를 해주세요");
      }
      // 아이디 유효성 검사 통과 못할시에
      else if (this.idValidFlag == false) {
        alert("유효한 아이디로 입력해주세요");
      }
      // 비밀번호 유효성 검사 통과 못할시에
      else if (this.passwordValidFlag == false) {
        alert("유효한 비밀번호로 입력해주세요");
      }
      // null값 존재시 가입 X
      else if (
        this.password == null ||
        this.passwordCheck == false ||
        this.name == null ||
        this.sex == null ||
        this.birth == null ||
        this.phoneNum == null ||
        this.roadAddress == null
      ) {
        alert("누락된 필수값이 존재합니다.");
      }

      // 하나씩 조건을 줄 때
      // 비밀번호 입력되지 않았습니다.
      // else if (this.password == null) {
      //   alert("비밀번호를 입력해주세요.");
      // } else if (this.passwordchk == false) {
      //   alert("비밀번호를 입력해주세요.");
      // } else if (this.name == null) {
      //   alert("이름을 입력해주세요.");
      // } else if (this.sex == null) {
      //   alert("성별을 선택해주세요.");
      // } else if (this.birth == null) {
      //   alert("생년월일을 입력해주세요.");
      // }

      // 이상 없을시에 회원가입 처리
      else {
        this.callAxios();
      }
    },
    callAxios() {
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
      // application/json 방식 -> @RequestBody
      axios
        .post(HOST + "/member/memberJoin", JSON.stringify(data), {
          headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Credentials": "true",
          },
        })
        .then(() => {
          alert("회원등록이 완료되었습니다.");
          this.$router.push("login");
        })
        .catch((error) => {
          console.log(error);
        });
    },

    // 전체동의
    toggleCheckAll() {
      this.agreements.forEach((agreement) => {
        agreement.checked = this.checkAll;
      });
    },
    checkIfAllChecked() {
      this.checkAll = this.agreements.every((agreement) => agreement.checked);
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

.join-box {
  padding-top: 100px;
  padding-bottom: 20px;
  width: 600px;
  margin: 0 auto;
}

.must-write {
  text-align: end;
}

.star-red {
  color: red;
}

hr {
  width: 600px;
  text-align: center;
  margin: 0px auto;
}

.join-part {
  width: 600px;
  margin: 0 auto;
  padding-bottom: 250px;
}

.id-box,
.pwd-box,
.check-pwd-box,
.name-box,
.email-box,
.phone-box,
.address-box,
.sex-box,
.birth-box,
.agree-box {
  display: flex;
  /* flex-direction: row; */
  align-items: center;
}

.box1,
.box3 {
  width: 100px;
}

.box2 {
  width: 400px;
}

.box4 {
  width: 500px;
  text-align: left;
  margin-left: 30px;
}

.id-input,
.pwd-input,
.check-pwd-input,
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
  width: 100px;
  padding: 10px;
  margin: 20px auto;
  border-radius: 10px;
  border: 1px solid #5f0080;
  color: #5f0080;
  font-weight: bold;
}

.check-pwd-text,
.agree-text {
  font-size: 14px;
}

.address-btn {
  width: 350px;
  padding: 10px;
  margin: 20px auto;
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

.bold-text {
  font-weight: bold;
  font-size: 18px;
}

.check-agree {
  display: inline-block;
  vertical-align: top;
  margin-right: 3%;
}

.agree-part {
  margin-top: 20px;
  margin-bottom: 5px;
}

.agree-little-part {
  margin-left: 35px;
  font-size: 11px;
  color: gray;
  padding-top: 5px;
}

.agreePopUp {
  float: right;
}

#joinBtn {
  /* width: 350px; */
  width: 100%;
  height: 60px;
  padding: 10px;
  margin: 50px auto;
  border-radius: 10px;
  border: 1px solid #dddddd;
  color: white;
  font-weight: bold;
  font-size: 20px;
  background-color: #5f0080;
}

.notValidId {
  font-size: 13px;
  color: red;
  float: left;
  margin-left: 25px;
}

.passwordValidFlag {
  font-size: 13px;
  color: red;
  float: left;
  margin-left: 25px;
}

.passwordCheckFlag {
  font-size: 13px;
  color: red;
  float: left;
  margin-left: 25px;
}

.emailValidFlag {
  font-size: 13px;
  color: red;
  float: left;
  margin-left: 25px;
}

input[type="checkbox"] {
  width: 20px;
  height: 20px;
  border-radius: 50%;
  border: 1px solid #999;
  appearance: none;
  cursor: pointer;
  transition: background 0.2s;
}

input[type="checkbox"]:checked {
  background: #5f0080;
  border: none;
  color: white;
  background-image: url("../../static/check.png");
  background-size: contain;
  cursor: pointer;
  outline: none;
  background-repeat: no-repeat;
  appearance: none;
}
</style>

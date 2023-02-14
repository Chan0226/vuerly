<template>
  <div class="review-form">
    <h1>후기 작성</h1>
    <hr />
    <div class="review-good-register">
      <div class="review-good-picture">
        <img src="@/static/상품예제이미지.jpeg" alt="" />
      </div>
      <div class="review-good-title">
        <p>[쌜모네키친]오로라 그라브락스 연어 200g</p>
      </div>
    </div>
    <div class="review-write-form">
      <table>
        <tbody>
          <tr align="center">
            <td width="200px" class="content-required">내용</td>
            <td>
              <textarea
                class="review-text"
                placeholder="상품 특성에 맞는 후기를 작성해주세요. 예)화장품 특성, 사용자의 피부타입 등(최소 10자이상)"
                required
                name="content"
                v-model="content"
              >
              </textarea>
              <div class="warning-content">
                <ul>
                  <li>
                    상품과 무관하거나 반복되는 동일 단어/문장을 사용하여 후기로
                    볼 수 없는 글, 판매자와 고객의 후기 이용을 방해한다고
                    판단되는 경우, 배송 박스, 구매 상품을 구분할 수 없는 전체
                    사진, 화면캡쳐, 음란 및 부적절하거나 불법적인 내용은
                    통보없이 삭제 및 적립금 회수될 수 있습니다.
                  </li>
                  <li>
                    전화번호, 이메일, 주소, 계좌번호 등 개인정보가 노출되지
                    않도록 주의해주세요.
                  </li>
                </ul>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="review-register">
      <div class="register-button">
        <v-btn
          style="
            height: 70px;
            color: black;
            background-color: white;
            font-size: 20px;
          "
          class="review-register-button"
          >취소</v-btn
        >
        <v-btn
          style="
            height: 70px;
            color: white;
            background-color: #5f0080;
            font-size: 20px;
          "
          class="review-register-button"
          @click="registerReview()"
          >등록</v-btn
        >
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
// const HOST = "http://localhost:8080";
export default {
  data: () => ({
    product_num: 1,
    memberNum: 0,
    content: "",
  }),
  methods: {
    registerReview() {
      axios
        .post(
          "/review/registerReview",
          {
            content: this.content,
            product_num: this.product_num,
            member_num: this.member_num,
          },
          {
            headers: {
              // 사진 추가 대비 multipart
              "Content-Type": "multipart/form-data",
              "Access-Control-Allow-Origin": "*",
              "Access-Control-Allow-Credentials": "true",
            },
          }
        )
        .then(() => {
          alert("리뷰가 등록되었습니다.");
          window.location.href = "/";
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  created() {
    this.secretKey = this.$session.get("secretKey");
    axios
      .get("/member/getUserNum?secretKey=" + this.secretKey)
      .then((response) => {
        this.memberNum = response.data;
        //console.log(this.models);
      })
      .catch((error) => {
        console.log(error);
      });
  },
  props: {
    show: Boolean,
  },
};
</script>

<style scoped>
.review-form {
  display: flex;
  flex-direction: column;
  height: 100%;
  margin-top: 50px;
  margin-bottom: 50px;
  width: 1050px;
  margin-left: auto;
  margin-right: auto;
}
.review-form > h1 {
  margin-bottom: 50px;
  font-size: 30px;
}
.review-form > hr {
  height: 1.7px;
  border: 0;
  background-color: #5f0080;
}
.review-good-register {
  display: flex;
}
.review-good-picture {
  width: 200px;
  height: 200px;
}
.review-good-picture > img {
  width: 110px;
  height: 130px;
  margin: 30px 0px 20px 20px;
  border-radius: 10px;
}
.review-good-title {
  line-height: 200px;
  font-size: 20px;
}
.review-write-form > table {
  width: 100%;
  height: 500px;
  border-top: 1px solid #cccaca;
  border-collapse: collapse;
}
.review-write-form > th,
td {
  border-bottom: 1px solid #cccaca;
  padding: 30px 5px 0px 5px;
  text-align: left;
  vertical-align: top;
}
.review-text {
  width: 95%;
  height: 350px;
  padding: 10px;
  box-sizing: border-box;
  border: solid 2px #cccaca;
  border-radius: 5px;
  font-size: 16px;
  resize: both;
}
.review-text::placeholder {
  color: #cccaca;
}
.review-register {
  margin-left: auto;
  margin-right: auto;
}
.review-register-button {
  width: 150px;
  margin: 40px 30px 0px 0px;
}
.content-required:after {
  content: " *";
  color: red;
}
.warning-content {
  padding: 20px 5px 30px 0px;
  color: gray;
}
li::marker {
  font-size: 10px;
}
</style>

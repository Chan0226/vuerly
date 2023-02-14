<template>
  <Transition name="modal">
    <div v-if="show" class="modal-mask">
      <div class="modal-container">
        <div class="review-form">
          <div class="review-form-title">
            <h1>후기 수정</h1>
            <button class="modal-default-button" @click="$emit('close')">
              X
            </button>
            <hr />
          </div>
          <div class="review-good">
            <div class="review-good-photo">
              <img :src="datas.productDTO.thumbnail" alt="" />
            </div>
            <div class="review-good-name">
              <!-- <p>[쌜모네키친]오로라 그라브락스 연어 200g</p> -->
              <p>{{ datas.productDTO.product_name }}</p>
            </div>
          </div>
          <div class="review-write-form">
            <table>
              <tbody>
                <tr align="center">
                  <td width="100px" class="content-required">내용</td>
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
                          상품과 무관하거나 반복되는 동일 단어/문장을 사용하여
                          후기로 볼 수 없는 글, 판매자와 고객의 후기 이용을
                          방해한다고 판단되는 경우, 배송 박스, 구매 상품을
                          구분할 수 없는 전체 사진, 화면캡쳐, 음란 및
                          부적절하거나 불법적인 내용은 통보없이 삭제 및 적립금
                          회수될 수 있습니다.
                        </li>
                        <li>
                          전화번호, 이메일, 주소, 계좌번호 등 개인정보가
                          노출되지 않도록 주의해주세요.
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
                @click="$emit('close')"
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
                @click="updateReview()"
                >수정</v-btn
              >
            </div>
          </div>
        </div>

        <!-- <div class="modal-footer">
          <slot name="footer">
            <button class="modal-default-button" @click="$emit('close')">
              OK
            </button>
          </slot>
        </div> -->
      </div>
    </div>
  </Transition>
</template>

<script>
import axios from "axios";
export default {
  data: () => ({
    product_num: 1,
    member_num: 1,
    // num: 1,
    datas: {},
    content: "",
  }),
  methods: {
    updateReview() {
      axios
        .patch(
          "/review/updateReview",
          {
            content: this.content,
            num: this.num,
          },
          {
            headers: {
              "Content-Type": "multipart/form-data",
              "Access-Control-Allow-Origin": "*",
              "Access-Control-Allow-Credentials": "true",
            },
          }
        )
        .then(() => {
          alert("리뷰가 수정되었습니다.");
          // window.location.href = "/";
          this.$emit("close");
          this.$router.go(this.$router.currentRoute);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  props: ["show", "num"],

  created() {},
  watch: {
    show() {
      axios
        .get("/review/getSelectReview?num=" + this.num)
        .then((response) => {
          this.datas = response.data;
          this.content = this.datas.content;
          //console.log(response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style scoped>
.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  transition: opacity 0.3s ease;
}

.modal-container {
  width: 60%;
  height: 90%;
  margin: auto;
  padding: 20px 30px;
  background-color: #fff;
  border-radius: 2px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
  transition: all 0.3s ease;
}

.modal-header h3 {
  margin-top: 0;
  color: #42b983;
}

.modal-body {
  margin: 20px 0;
}

.modal-default-button {
  float: right;
}

.modal-enter-from {
  opacity: 0;
}

.modal-leave-to {
  opacity: 0;
}

.modal-enter-from .modal-container,
.modal-leave-to .modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}

/*  */
.review-form {
  display: flex;
  flex-direction: column;
  /* justify-content: space-between; */
  height: 100%;
}
.review-form-title {
  height: 40px;
}
.review-form-title > button {
  font-size: 30px;
  color: #b9b6b6;
}
.review-form-title > h1 {
  font-size: 30px;
  font-weight: normal;
  width: 50%;
  height: 30px;
  display: inline;
}
.review-form-title > hr {
  height: 0.5px;
  border: 0;
  /* margin-top: 10px; */
  background-color: #cccaca;
}
.review-good {
  display: flex;
  border-bottom: 1px solid #cccaca;
  height: 150px;
}
.review-good-photo {
  width: 100px;
  height: 150px;
  line-height: 150px;
}
.review-good-photo > img {
  width: 90px;
  height: 100px;
  vertical-align: middle;
  border-radius: 10px;
}
.review-good-name {
  line-height: 150px;
  font-size: 20px;
  margin-left: 30px;
}
.review-write-form {
  height: 800px;
}
.review-write-form > table {
  width: 100%;
  height: 100%;
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
  height: 50%;
  padding: 10px;
  box-sizing: border-box;
  border: solid 2px #cccaca;
  border-radius: 5px;
  font-size: 16px;
  resize: none;
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

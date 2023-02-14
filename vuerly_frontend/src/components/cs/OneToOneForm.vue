<template>
  <div class="one-to-one-form">
    <h1>1:1문의 작성</h1>

    <div class="inquiry-form">
      <div class="inquiry-type">
        <div style="width: 20%">
          <label for="selecteType"> 유형<span class="star-red">*</span> </label>
        </div>
        <div style="width: 80%">
          <v-select
            v-model="selectedType"
            :items="items"
            item-value="index"
            id="selecteType"
            item-text="item"
            label="문의유형을 선택해주세요"
            solo
            style="width: 50%; margin-top: 30px"
          ></v-select>
        </div>
      </div>
      <div class="inquiry-title">
        <div style="width: 20%">
          <label for="inputTitle"> 제목<span class="star-red">*</span> </label>
        </div>
        <div style="width: 80%">
          <input
            type="text"
            v-model="title"
            id="inputTitle"
            placeholder="제목을 입력해주세요"
            class="title-input"
          />
        </div>
      </div>
      <div class="inquiry-content">
        <div style="width: 20%">
          <label for="inputContent">
            내용<span class="star-red">*</span>
          </label>
        </div>
        <div style="width: 80%">
          <textarea
            class="inquiry-text"
            placeholder="문의할 내용을 적어주세요"
            required
            name="content"
            id="inputContent"
            v-model="content"
          >
          </textarea>
        </div>
      </div>
    </div>

    <div class="to-list-button">
      <button
        class="to-list"
        @click="registerInquiry"
        v-if="content != '' && selectedType != '' && title != ''"
      >
        등록
      </button>
      <button class="register-disable" v-else>등록</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      num: 0,
      notice: "",
      title: "",
      content: "",

      selectedType: 0,
      items: [
        { item: "주문/결제/반품/교환문의", index: 1 },
        { item: "이벤트/쿠폰/적립금문의", index: 2 },
        { item: "상품문의", index: 3 },
        { item: "배송문의", index: 4 },
        { item: "상품 누락 문의", index: 5 },
        { item: "기타문의", index: 6 },
      ],
    };
  },
  methods: {
    registerInquiry() {
      this.secretKey = this.$session.get("secretKey");
      let data = {
        title: this.title,
        content: this.content,
        secretKey: this.secretKey,
        qnatype_num: this.selectedType,
      };
      //console.log(data);
      axios
        .post("/qna/insertInquiry", JSON.stringify(data), {
          headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Credentials": "true",
          },
        })
        .then(() => {
          alert("1:1문의가 등록되었습니다.");
          this.$router.go(-1);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style scoped>
h1 {
  font-size: 30px;
  display: inline-block;
  text-align: center;
  margin-top: 50px;
}
p {
  font-size: 15px;
  color: gray;
  display: inline-block;
  text-align: center;
}
table {
  margin-top: 30px;
  width: 100%;
  border-top: 1px solid #444444;
  text-align: center;
  font-size: 15px;
}
.one-to-one-form {
  color: #333;
  margin: auto;
  max-width: 1050px;
  display: flex;
  flex-direction: column;
  height: 100%;
}
.inquiry-form {
  margin-top: 50px;
  border-top: 1px solid black;
  font-weight: 500;
}
.inquiry-type,
.inquiry-title {
  display: flex;
  height: 100px;
  line-height: 100px;
  padding-left: 30px;
}
.inquiry-content {
  display: flex;
  height: 300px;
  padding: 20px 0px 0px 30px;
}
.to-list {
  margin: 30px auto 30px auto;
  border: 1px solid black;
  background-color: #5f0080;
  color: white;
  width: 170px;
  height: 70px;
  font-size: 20px;
}
.register-disable {
  margin: 30px auto 30px auto;
  border: 1px solid #dddddd;
  background-color: #dddddd;
  color: white;
  width: 170px;
  height: 70px;
  font-size: 20px;
}
.to-list-button {
  display: flex;
  justify-content: end;
}
.inquiry-type-select {
  width: 50%;
  height: 60%;
  padding: 10px;
  box-sizing: border-box;
  border: solid 2px #cccaca;
  border-radius: 5px;
  margin-top: 20px;
  font-size: 16px;
  resize: none;
}
.inquiry-text {
  width: 95%;
  height: 100%;
  padding: 10px;
  box-sizing: border-box;
  border: solid 2px #cccaca;
  border-radius: 5px;
  font-size: 16px;
  resize: none;
}

.title-input {
  width: 95%;
  height: 60%;
  padding: 10px;
  box-sizing: border-box;
  border: solid 2px #cccaca;
  border-radius: 5px;
  font-size: 16px;
  resize: none;
}
.star-red {
  color: red;
}
</style>

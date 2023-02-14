<template>
  <div class="inquiry">
    <div>
      <h1>1:1문의</h1>
    </div>
    <div class="inquiry-table">
      <div class="inquiry-table-head">
        <div style="width: 70%">제목</div>
        <div style="width: 20%">작성일</div>
        <div style="width: 10%">답변상태</div>
      </div>
      <div class="inquiry-table-body">
        <ul
          v-for="(rowData, idx) in models"
          :key="idx"
          class="table-row"
          @click="toggleAccordion(rowData.num)"
        >
          <li
            style="
              width: 70%;
              text-align: left;
              padding-left: 20px;
              font-weight: 600;
            "
          >
            {{ rowData.title }}
          </li>
          <li style="width: 20%; color: gray">
            {{ rowData.created_at | yyyyMMdd }}
          </li>
          <li style="width: 10%" v-if="rowData.status == 0" class="answer-wait">
            답변대기
          </li>
          <li style="width: 10%" v-else class="answer-complete">답변완료</li>

          <div v-if="rowData.isOpen" class="inquiry-content">
            <p style="margin-top: 20px">{{ rowData.qnaType }}</p>
            <span class="inquiry-answer">
              <div class="question-icon">Q</div>
              <b style="margin-left: 20px">{{ rowData.content }}</b>
            </span>
            <div class="inquiry-revise">
              <router-link
                :to="{
                  name: 'onetooneupdateform',
                  query: {
                    num: rowData.num,
                  },
                }"
              >
                <span class="update-inquiry">수정</span>
              </router-link>

              <span class="delete-inquiry" @click="deleteInquiry(rowData.num)"
                >삭제</span
              >
            </div>
          </div>
        </ul>
      </div>
    </div>
    <div class="write-inquiry-button">
      <router-link to="/inquiry">
        <button class="to-inquiry">문의하기</button>
      </router-link>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      memberNum: 0,
      models: [],
    };
  },
  methods: {
    toggleAccordion(num) {
      this.models = this.models.map((item) => {
        if (item.num === num) {
          item.isOpen = !item.isOpen;
        } else {
          item.isOpen = false;
        }
        return item;
      });
    },
    deleteInquiry(num) {
      axios
        .delete("/qna/deleteInquiry?num=" + num)
        .then(() => {
          alert("문의글이 삭제되었습니다.");
          this.$router.go(this.$router.currentRoute);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  created() {
    this.secretKey = this.$session.get("secretKey");
    axios
      .get("/qna/getMemberInquiry?secretKey=" + this.secretKey)
      .then((response) => {
        this.models = response.data;
        //console.log(this.models);
      })
      .catch((error) => {
        console.log(error);
      });
  },
  filters: {
    // filter로 쓸 filter ID 지정
    yyyyMMdd: function (value) {
      // 들어오는 value 값이 공백이면 그냥 공백으로 돌려줌
      if (value == "") return "";

      // 현재 Date 혹은 DateTime 데이터를 javaScript date 타입화
      var js_date = new Date(value);

      // 연도, 월, 일 추출
      var year = js_date.getFullYear();
      var month = js_date.getMonth() + 1;
      var day = js_date.getDate();

      // 월, 일의 경우 한자리 수 값이 있기 때문에 공백에 0 처리
      if (month < 10) {
        month = "0" + month;
      }

      if (day < 10) {
        day = "0" + day;
      }

      // 최종 포맷 (ex - '2021.10.08')
      return year + "." + month + "." + day;
    },
  },
};
</script>

<style scoped>
h1 {
  font-size: 25px;
  margin: 20px 0px 20px 0px;
  font-weight: 600;
  display: inline-block;
}
p {
  display: inline-block;
  margin-left: 15px;
  color: gray;
}
a {
  text-decoration: none;
  color: black;
}
.inquiry {
  height: 100%;
  margin-bottom: 200px;
  width: 100%;
  display: flex;
  flex-direction: column;
}
.inquiry-table {
  width: 100%;
  text-align: center;
  border-top: 1px solid #444444;
  border-collapse: collapse;
  display: flex;
  flex-direction: column;
}
.inquiry-table-head {
  width: 100%;
  text-align: center;
  border-top: 1px solid #444444;
  border-collapse: collapse;
  display: flex;
  border-bottom: 1px solid #444444;
  height: 70px;
  line-height: 70px;
}
ul {
  width: 100%;
  display: flex;
  padding: 0;
  flex-wrap: wrap;
}
li {
  list-style-type: none;
  text-align: center;
  height: 70px;
  line-height: 70px;
  font-size: 16px;
  font-weight: 400;
}
.table-row:hover {
  cursor: pointer;
}
.inquiry-content {
  width: 100%;
  height: 200px;
  background-color: #f8f8f8;
  display: flex;
  flex-direction: column;
  white-space: pre-line;
  text-align: left;
  margin-bottom: 10px;
  position: relative;
}
.answer-sentence {
  color: red;
  width: 100px;
  margin-top: 20px;
  text-align: center;
}
.inquiry-answer {
  margin-left: 10px;
  display: flex;
}
.no-content {
  height: 300px;
  line-height: 300px;
  font-size: 20px;
}
.write-inquiry-button {
  display: flex;
  justify-content: end;
}
.to-inquiry {
  margin-top: 20px;
  margin-bottom: 20px;
  border: 1px solid black;
  background-color: #5f0080;
  color: white;
  width: 150px;
  height: 50px;
}
.answer-complete {
  color: #5f0080;
}
.answer-wait {
  color: gray;
}
.question-icon {
  width: 25px;
  height: 25px;
  background-color: #c48ad8;
  color: white;
  text-align: center;
  line-height: 25px;
  font-size: 15px;
  border-radius: 30px;
}
.inquiry-revise {
  position: absolute;
  right: 40px;
  bottom: 30px;
}
.delete-inquiry {
  margin-left: 20px;
}
</style>

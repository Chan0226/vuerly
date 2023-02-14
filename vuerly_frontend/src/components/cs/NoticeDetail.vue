<template>
  <div class="notice-detail">
    <h1>공지사항</h1>
    <p>뷰얼리의 새로운 소식들과 유용한 정보들을 한눈에 확인해보세요</p>
    <table>
      <tr>
        <td><span>제목</span></td>
        <td>{{ notice.title }}</td>
      </tr>
      <tr>
        <td>작성자</td>
        <td>{{ notice.writer }}</td>
      </tr>
      <tr>
        <td>작성일</td>
        <td>{{ notice.created_at | yyyyMMdd }}</td>
      </tr>
    </table>
    <div class="notice-content">
      {{ notice.content }}
    </div>
    <div class="to-list-button">
      <router-link to="/cs">
        <button class="to-list">목록</button>
      </router-link>
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
    };
  },
  created() {
    this.num = this.$route.params.num;
    axios
      .get("/qna/getAnnouncementByNum?num=" + this.num)
      .then((response) => {
        this.notice = response.data;
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
  display: inline-block;
  text-align: center;
  margin-top: 30px;
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
.notice-detail {
  color: #333;
  margin: auto;
  max-width: 1050px;
  display: flex;
  flex-direction: column;
  height: 1000px;
  /* padding: 30px 0; */
}
td {
  text-align: left;
  border-bottom: 0.1px solid #eeeded;
  height: 40px;
}
td.title {
  text-align: center;
  width: 200px;
  height: 40px;
  background-color: rgb(230, 202, 255);
  font-size: 15px;
}

.notice-content {
  margin-top: 30px;
  height: 400px;
  width: 100%;
  white-space: pre-line;
  border-bottom: 1px solid #333;
}
.to-list {
  margin-top: 30px;
  border: 1px solid black;
  background-color: #5f0080;
  color: white;
  width: 150px;
  height: 50px;
}
.to-list-button {
  display: flex;
  justify-content: end;
}
</style>

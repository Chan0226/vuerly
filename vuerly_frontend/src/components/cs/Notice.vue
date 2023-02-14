<template>
  <div class="notice">
    <div>
      <h1>공지사항</h1>
      <p>뷰얼리의 새로운 소식들과 유용한 정보들을 한눈에 확인해보세요</p>
    </div>
    <div>
      <table class="notice-table">
        <th style="width: 20px">번호</th>
        <th style="width: 150px">제목</th>
        <th style="width: 20px">작성자</th>
        <th style="width: 20px">작성일</th>
        <tr
          v-for="(rowData, idx) in models"
          :key="idx"
          class="table-row"
          @click="goDetail(rowData.num)"
        >
          <!-- 첫번째 줄 시작 -->
          <td>{{ rowData.num }}</td>
          <td style="text-align: left">{{ rowData.title }}</td>
          <td>{{ rowData.writer }}</td>
          <td>{{ rowData.created_at | yyyyMMdd }}</td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import router from "../../routes";

export default {
  data() {
    return {
      models: [],
    };
  },
  methods: {
    goDetail(num) {
      router.push({ path: "/notice/detail/" + num });
    },
  },
  created() {
    axios
      .get("/qna/getAllProduct")
      .then((response) => {
        this.models = response.data;
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
.notice {
  height: 400px;
  width: 100%;
  margin-bottom: 100px;
  display: flex;
  flex-direction: column;
}
.notice-table {
  width: 100%;
  text-align: center;
  border-top: 1px solid #444444;
  border-collapse: collapse;
}
th {
  border-bottom: 1px solid #444444;
  height: 70px;
  line-height: 70px;
}

td {
  border-bottom: 1px solid #e7e6e6;
  height: 70px;
  line-height: 70px;
  font-size: 15px;
  font-weight: 500;
}
.table-row:hover {
  cursor: pointer;
}
</style>

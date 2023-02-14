<template>
  <div class="faq">
    <div>
      <h1>자주하는 질문</h1>
      <p>고객님들께서 가장 자주하시는 질문을 모두 모았습니다.</p>
    </div>
    <div class="faq-table">
      <div class="faq-table-head">
        <div style="width: 10%">번호</div>
        <div style="width: 20%">카테고리</div>
        <div style="width: 70%">제목</div>
      </div>
      <div class="faq-table-body">
        <ul
          v-for="(rowData, idx) in models"
          :key="idx"
          class="table-row"
          @click="toggleAccordion(rowData.num)"
        >
          <li style="width: 10%">{{ rowData.num }}</li>
          <li style="width: 20%">{{ rowData.category }}</li>
          <li style="width: 70%; text-align: left">{{ rowData.title }}</li>

          <div v-if="rowData.isOpen" class="faq-content">
            <span class="answer-sentence">A)</span>
            <span class="faq-answer">
              {{ rowData.answer }}
            </span>
          </div>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
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
  },
  created() {
    axios
      .get("/qna/getAllFaq")
      .then((response) => {
        // 아코디언 이벤트를 위해서 response.data에 isOpen: false로 초기화해줌
        this.models = response.data.map((item) => ({ ...item, isOpen: false }));
        //console.log(this.models);
      })
      .catch((error) => {
        console.log(error);
      });
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
.faq {
  height: 100%;
  width: 100%;
  margin-bottom: 200px;
  display: flex;
  flex-direction: column;
}
.faq-table {
  width: 100%;
  text-align: center;
  border-top: 1px solid #444444;
  border-collapse: collapse;
  display: flex;
  flex-direction: column;
}
.faq-table-head {
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
  font-size: 15px;
  font-weight: 600;
}
.table-row:hover {
  cursor: pointer;
}
.faq-content {
  width: 100%;
  display: flex;
  white-space: pre-line;
  text-align: left;
  margin-bottom: 10px;
}
.answer-sentence {
  color: red;
  width: 100px;
  margin-top: 20px;
  text-align: center;
}
.faq-answer {
  margin-left: 10px;
}
</style>

<template>
  <div>
    <div class="review-list" v-if="models.length > 0">
      <div class="review-count">총 {{ models.length }}개</div>
      <div class="review-item" v-for="(rowData, idx) in models" :key="idx">
        <div>
          <h2 class="review-product-name">
            {{ rowData.productDTO.product_name }}
          </h2>
          <p class="review-date">{{ rowData.created_at | yyyyMMdd }}</p>
          <p class="review-content">{{ rowData.content }}</p>
        </div>
        <div>
          <v-btn
            class="revise-button"
            @click="clickModal($event)"
            :value="rowData.num"
            >후기수정</v-btn
          >
        </div>
      </div>
      <Teleport to="body">
        <!-- use the modal component, pass in the prop -->
        <!-- showModal  전해줌 -->
        <!-- <modal :show="showModal" @close="showModal = false"> </modal> -->
        <modal :show="showModal" :num="num" @close="showModal = false"> </modal>
      </Teleport>
    </div>
    <div class="no-writed-review" v-else>
      <p>작성한 후기가 없습니다.</p>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import Modal from "@/pages/review/Modal.vue";

export default {
  components: {
    Modal,
  },
  data() {
    return {
      memberNum: 0,
      models: [],
      showModal: false,
      num: 0,
    };
  },
  methods: {
    clickModal(event) {
      (this.showModal = true), (this.num = event.currentTarget.value);
      // console.log(this.showModal);
      // console.log(this.num);
    },
  },
  created() {
    this.secretKey = this.$session.get("secretKey");
    axios
      .get("/review/getReview?secretKey=" + this.secretKey)
      .then((response) => {
        this.models = response.data;
        //console.log(this.models);
      })
      .catch((error) => {
        console.log(error);
      });

    // 회원번호받기
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

      // 최종 포맷 (ex - '2021-10-08')
      // return year + '-' + month + '-' + day;
      return year + "년 " + month + "월 " + day + "일";
    },
  },
};
</script>

<style scoped>
.review-product-name {
  font-size: 20px;
  white-space: pre-line;
}
.review-product-name:hover {
  cursor: pointer;
}

.review-content {
  width: 680px;
  white-space: pre-line;
}

.review-header span {
  color: #999;
  font-size: 14px;
  padding: 7px;
  flex-grow: 1;
}

.review-list {
  display: flex;
  flex-direction: column;
}
.review-item {
  display: flex;
  justify-content: space-between;
  margin-top: 10px;
  padding: 10px;
  border-bottom: 1px solid rgb(209, 209, 209);
}
.review-date {
  color: gray;
  font-size: 15px;
}
.review-count {
  margin-top: 10px;
  height: 50px;
  line-height: 50px;
  border-bottom: 1px solid black;
}

.no-writed-review {
  width: 100%;
  height: 200px;
  line-height: 200px;
  text-align: center;
}
</style>

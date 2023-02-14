<template>
  <div class="component-bg">
    <div class="order-header">
      <h1>주문내역</h1>
      <span>최대 지난 3년간의 주문 내역까지 확인 할 수 있어요</span>
      <label>
        <select class="dropdown" v-model="term" @change="dropChange">
          <option value="0">3개월</option>
          <option value="1">6개월</option>
          <option value="2">1년</option>
          <option value="3">3년</option>
        </select>
      </label>
    </div>
    <div v-if="models.length === 0">
      <p class="order-empty">주문한 상품이 없습니다.</p>
    </div>
    <div v-else>
      <div class="order-list" v-for="(rowData, i) in models" :key="i">
        <div class="order-date">{{ rowData.created_at }}</div>
        <div class="product-box">
          <img class="product-img" :src="rowData.thumbnail" />
          <div class="product-info-t">
            <div class="info-item">상품명</div>
            <div class="info-item">주문번호</div>
            <div class="info-item">결제방법</div>
            <div class="info-item">결제금액</div>
          </div>
          <div class="product-info">
            <div class="info-item">{{ rowData.product_name }}</div>
            <div class="info-item">{{ rowData.num }}</div>
            <div class="info-item">카드결제</div>
            <div class="info-item">{{ rowData.final_price }}원</div>
          </div>
          <div class="delivery-status" v-if="rowData.status === 1">배송중</div>
          <div class="delivery-status" v-else>배송완료</div>
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
      status: "",
      memberNum: 0,
      models: [],
      term: 0,
    };
  },
  methods: {
    dropChange() {
      axios
        .get(
          "/order/getOrderList?secretKey=" +
            this.secretKey +
            "&term=" +
            this.term
        )
        .then((response) => {
          this.models = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  created() {
    // 로그인 세션
    this.secretKey = this.$session.get("secretKey");

    this.dropChange();
  },
};
</script>

<style scoped>
h1 {
  font-size: 24px;
}

.component-bg {
  color: #333;
  margin: auto;
}

.order-header {
  display: flex;
  border-bottom: 1px solid #333;
  padding: 10px;
}

.order-header span {
  color: #999;
  font-size: 14px;
  padding: 7px;
  flex-grow: 1;
}

.dropdown {
  vertical-align: top;
  font-size: 14px;
  width: 90px;
  height: 35px;
  padding-left: 5px;
  border: 1px solid #d6d6d6;
  min-height: 30px;
  appearance: auto;
}

.order-list {
  padding: 20px;
}

.order-date {
  padding: 10px;
  border-bottom: 1px solid #d6d6d6;
}

.product-box {
  display: flex;
  justify-content: center;
  align-items: center;
}

.product-img {
  margin: 20px;
  width: 150px;
  height: 150px;
  object-fit: cover;
}

.product-info-t {
  display: flex;
  flex-direction: column;
  width: 80px;
  height: 150px;
  padding: 10px;
  font-size: 14px;
  font-weight: bold;
}

.product-info {
  display: flex;
  flex-direction: column;
  flex-grow: 1;
  height: 150px;
  padding: 10px;
  font-size: 14px;
}

.info-item {
  flex: 1 1 auto;
}

.delivery-status {
  margin: 20px;
  height: 35px;
  width: 90px;
  padding: 5px;
  text-align: center;
  font-weight: bold;
}

.order-empty {
  text-align: center;
  margin: 100px 0;
}
</style>

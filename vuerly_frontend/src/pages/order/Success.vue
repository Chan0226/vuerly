<template>
  <div class="bg">
    <div class="order-success">
      <div class="order-greet">
        <img src="../../static/order_done.png" class="check-icon" />
        <h1>{{ $route.params.name }}님의 주문이 완료되었습니다.</h1>
        <h2>내일 새벽에 만나요!</h2>
      </div>
      <div class="order-price">
        <div class="price-item">
          <div class="price-title">결제금액</div>
          <div class="price-sub">
            <div class="price">{{ $route.params.amount }}</div>
            <span>원</span>
          </div>
        </div>
        <div class="price-item">
          <div class="price-title">적립금</div>
          <div class="price-sub">
            <div class="point">+&nbsp;{{ addPoint }}</div>
            <span>원</span>
          </div>
        </div>
      </div>
      <div class="delivery-info">
        <ul>
          <li>
            [주문완료] 상태일 경우에만 주문내역 상세페이지에서 주문 취소가
            가능합니다.
          </li>
          <li>
            엘리베이터 이용이 어려운 경우 6층 이상부터는 공동 현관 앞 또는
            경비실로 대응 배송 될 수 있습니다.
          </li>
          <li>
            주문 / 배송 및 기타 문의가 있을 경우 1:1 문의에 남겨주시면 신속히
            해결해드리겠습니다.
          </li>
        </ul>
      </div>
      <button type="button" class="go-order-btn" @click="goMypage">
        주문상세보기
      </button>
      <button type="button" class="go-shopping-btn" @click="goMain">
        쇼핑계속하기
      </button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import router from "../../routes";

export default {
  name: "",
  components: {},
  data() {
    return {
      secretKey: "",
      addPoint: 0,
      productNum: [],
      orderNum: 0,
    };
  },
  methods: {
    goMypage() {
      router.push({ name: "mypage" });
    },
    goMain() {
      router.push({ name: "home" });
    },
  },
  created() {
    // 로그인 세션
    this.secretKey = this.$session.get("secretKey");

    this.addPoint = Math.floor(this.$route.params.amount / 100);
    this.productNum = this.$route.params.product_num;
    this.orderNum = this.$route.params.order_num;

    for (var i in this.productNum) {
      axios
        .post(
          "/order/insertOrderProduct",
          {
            product_num: this.productNum[i],
            order_num: this.orderNum,
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
          // alert("등록 성공")
        })
        .catch((error) => {
          console.log(error);
        });
    }

    axios
      .delete("/cart/deleteAllCart?secretKey=" + this.secretKey)
      .then(() => {
        //alert("삭제 성공")
      })
      .catch((error) => {
        console.log(error);
      });
  },
};
</script>

<style scoped>
h1 {
  font-size: 24px;
  font-weight: 500;
}

h2 {
  font-size: 24px;
  font-weight: 700;
}

.bg {
  color: #333;
  margin: auto;
  width: 100%;
  padding: 30px 0;
  background-color: #f9f6f4;
}

.order-success {
  margin: 50px auto;
  width: 500px;
  background-color: #fff;
  padding: 40px;
}

.order-greet {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 60px 0;
  border-bottom: 1px solid #d6d6d6;
}

.check-icon {
  width: 100px;
  height: 100px;
  margin: 20px auto;
}

.order-price {
  display: flex;
  flex-direction: column;
  padding: 20px 0;
}

.price-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.price-title {
  font-size: 20px;
  font-weight: 500;
  width: 120px;
}

.price-sub {
  display: flex;
  flex-direction: row;
}

.price-sub span {
  font-size: 20px;
  padding: 3px 0 0 5px;
}

.price {
  font-size: 24px;
  font-weight: 700;
}
.point {
  font-size: 24px;
  font-weight: 700;
  color: #5f0080;
}

.delivery-info li {
  color: #999;
  padding: 5px 0;
}

.go-order-btn {
  width: 100%;
  height: 60px;
  border: 1px solid #d6d6d6;
  margin: 20px 0;
  border-radius: 4px;
  font-weight: 700;
}

.go-shopping-btn {
  width: 100%;
  height: 60px;
  border-radius: 4px;
  background-color: #5f0080;
  color: #fff;
  font-weight: 700;
}
</style>

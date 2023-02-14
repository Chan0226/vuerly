<template>
  <div class="cart-side">
    <div class="cart-side-menu">
      <div class="address-menu">
        <div class="address">
          <svg style="width: 24px; height: 24px" viewBox="0 0 24 24">
            <path
              fill="currentColor"
              d="M12,6.5A2.5,2.5 0 0,1 14.5,9A2.5,2.5 0 0,1 12,11.5A2.5,2.5 0 0,1 9.5,9A2.5,2.5 0 0,1 12,6.5M12,2A7,7 0 0,1 19,9C19,14.25 12,22 12,22C12,22 5,14.25 5,9A7,7 0 0,1 12,2M12,4A5,5 0 0,0 7,9C7,10 7,12 12,18.71C17,12 17,10 17,9A5,5 0 0,0 12,4Z"
            />
          </svg>
          <b>배송지</b>
          <!-- <p>서울 영등포구 당산 생각공장 B동 1509호</p> -->
          <p>{{ address }}</p>
        </div>
        <div class="change-button">
          <v-btn
            class="change-address"
            style="
              color: #5f0080;
              background-color: white;
              border: 1px solid #5f0080;
            "
            @click="goAddress"
            >배송지 변경</v-btn
          >
        </div>
      </div>
      <div v-if="propsdata.goodprice != null">
        <div class="cost-menu">
          <div class="cost-good">
            <p>상품금액</p>
            <p>{{ propsdata.goodprice | currency }}원</p>
          </div>
          <div class="cost-sale">
            <p>상품할인금액</p>
            <p>-{{ propsdata.saleprice | currency }}원</p>
          </div>
          <div class="cost-delivery">
            <p>배송비</p>
            <p>{{ propsdata.deliveryFee | currency }}원</p>
          </div>
        </div>
        <div class="final-price">
          <div class="cost-final">
            <p>결제예정금액</p>
            <p>
              {{
                (propsdata.goodprice -
                  propsdata.saleprice +
                  propsdata.deliveryFee)
                  | currency
              }}원
            </p>
          </div>
        </div>
      </div>
      <div v-else>
        <div class="cost-menu">
          <div class="cost-good">
            <p>상품금액</p>
            <p>0원</p>
          </div>
          <div class="cost-sale">
            <p>상품할인금액</p>
            <p>-0원</p>
          </div>
          <div class="cost-delivery">
            <p>배송비</p>
            <p>0원</p>
          </div>
        </div>
        <div class="final-price">
          <div class="cost-final">
            <p>결제예정금액</p>
            <p>0원</p>
          </div>
        </div>
      </div>
    </div>
    <div class="order-button">
      <v-btn
        style="height: 50px; color: white; background-color: #5f0080"
        @click="orderActive"
        >주문하기</v-btn
      >
    </div>
  </div>
</template>

<script>
import axios from "axios";
import router from "../../routes";

export default {
  props: ["propsdata"],
  methods: {
    goAddress() {
      router.push({ path: "/address" });
    },
    getMemberAddress() {
      this.secretKey = this.$session.get("secretKey");
      axios
        .get("/cart/getMemberAddress?secretKey=" + this.secretKey)
        .then((response) => {
          this.address = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },

    orderActive() {
      this.secretKey = this.$session.get("secretKey");
      if (this.propsdata.goodprice != null) {
        router.push({
          name: "orderSheet",
          params: {
            secretKey: this.secretKey,
            goodprice: this.propsdata.goodprice,
            saleprice: this.propsdata.saleprice,
            deliveryFee: this.propsdata.deliveryFee,
            totalprice:
              this.propsdata.goodprice -
              this.propsdata.saleprice +
              this.propsdata.deliveryFee,
          },
        });
      } else {
        alert("장바구니에 상품이 존재하지 않습니다.");
      }
    },
  },
  data: () => ({
    goodprice: 0,
    saleprice: 0,
    deliveryFee: 0,
    totalprice: 0,
    // 장바구니 회원 아이디
    address: "",
    memberNum: 0,
  }),
  // 가격 3자리 컴마(,) 추가 기능
  filters: {
    currency: function (value) {
      var num = new Number(value);
      return num.toFixed(0).replace(/(\d)(?=(\d{3})+(?:\.\d+)?$)/g, "$1,");
    },
  },
  created() {
    this.memberNum = this.$session.get("userNum");
    this.getMemberAddress();
  },
};
</script>

<style>
.cart-side {
  display: flex;
  flex-direction: column;
}
.cart-side-menu {
  display: flex;
  flex-direction: column;
  width: 250px;
  border: 0.2px solid rgb(231, 231, 231);
}
.address {
  height: 90px;
  width: 100%;
}
.address > p {
  /* 줄바꿈 속성 */
  padding-top: 5px;
  word-wrap: break-word;
  font-weight: 600;
}
.address-menu {
  display: flex;
  flex-direction: column;
  border-bottom: 0.2px solid rgb(207, 207, 207);
  height: 150px;
  padding: 7px;
}
.address-menu > button {
  width: 200px;
  /* margin-top: 20px; */
  margin-left: 30px;
  position: static;
  top: 20px;
}
.cost-menu {
  margin-top: 30px;
  height: 120px;
}
.final-price {
  border-top: 0.2px solid rgb(207, 207, 207);
}
.cost-good,
.cost-sale,
.cost-delivery {
  display: flex;
  justify-content: space-between;
  padding: 0 7px 0 7px;
}
.cost-final {
  display: flex;
  justify-content: space-between;
  padding: 0 7px 0 7px;
  align-items: center;
  height: 70px;
  /* margin-top: 10px; */
}
.order-button {
  width: 250px;
  margin-top: 10px;
}
.change-button > button {
  margin-left: 15px;
  width: 200px;
}
.order-button > button {
  width: 250px;
}
</style>

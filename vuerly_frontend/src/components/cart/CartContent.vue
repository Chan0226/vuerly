<template>
  <div class="cart-content">
    <div class="select-option">총 {{ models.length }}개</div>
    <div class="cart-list" v-if="models.length > 0">
      <div class="cart-good" v-for="(rowData, idx) in models" :key="idx">
        <p class="cart-num">{{ idx + 1 }}</p>
        <img :src="rowData.productVO.thumbnail" alt="" class="good-image" />
        <div class="good-name">
          <p>{{ rowData.productVO.name }}</p>
        </div>
        <div class="good-count">
          <!-- 수량 1이하로는 클릭 비활성화 -->
          <button
            v-if="count[idx] == 1"
            class="count-minus-disabled"
            v-bind:disabled="count[idx] == 1"
          >
            -
          </button>
          <button v-else @click="minus(idx, rowData.idx)" class="count-minus">
            -
          </button>
          <div class="count" style="margin-top: 5px">{{ count[idx] }}</div>
          <button @click="plus(idx, rowData.idx)" class="count-plus">+</button>
        </div>
        <div v-if="rowData.productVO.sale > 0">
          <p class="good-cost-sale">
            {{
              (rowData.productVO.price * count[idx] -
                rowData.productVO.sale * count[idx])
                | currency
            }}원
          </p>
          <p class="good-cost-beforesale">
            {{ (rowData.productVO.price * count[idx]) | currency }}원
          </p>
        </div>
        <div class="good-cost" v-else>
          <p>{{ (rowData.productVO.price * count[idx]) | currency }}원</p>
        </div>

        <div class="cancel" @click="deleteCart(rowData.idx)">
          <p>X</p>
        </div>
      </div>
    </div>
    <div v-else>
      <p class="cart-empty">장바구니에 담긴 상품이 없습니다.</p>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data: () => ({
    // 배열은 감지가 안되기 때문에 감지시키는 동작을 추가해야 한다.
    count: [],
    models: [],
    memberNum: 0,
    check: 2,
    cost: {},
    goodprice: 0,
    saleprice: 0,
    deliveryFee: 0,
  }),
  methods: {
    // cart delete axios
    deleteAxios(idx) {
      axios
        .delete("/cart/deleteCart?idx=" + idx)
        .then(() => {
          // this.models = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },

    // count update axios
    updateAxios(idx, count) {
      axios
        .patch("/cart/updateCountCart?idx=" + idx + "&count=" + count)
        .then(() => {
          // this.models = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },

    // count 빼주기
    minus(idx, cartidx) {
      if (this.count[idx] > 0) {
        // count 변경 감지하도록 해주는 장치($set)
        this.$set(this.count, idx, this.count[idx] - 1);
        this.updateAxios(cartidx, this.count[idx]);
        // console.log(this.count[idx]);
      } else {
        //console.log("수량이 없습니다.");
      }
    },
    // count 더하기
    plus(idx, cartidx) {
      this.$set(this.count, idx, this.count[idx] + 1);
      this.updateAxios(cartidx, this.count[idx]);
    },
    // 상품금액 합계 구하기
    goodSumCost() {
      this.goodprice = 0;
      for (var i in this.models) {
        this.goodprice += this.models[i].productVO.price * this.count[i];
        // 객체,배열은 값 변경을 인식해주는 $set 사용해야된다
        this.$set(this.cost, "goodprice", this.goodprice);
      }
      // 할인금액 합계 구하기
      this.saleprice = 0;
      for (var j in this.models) {
        this.saleprice += this.models[j].productVO.sale * this.count[j];
        // 객체,배열은 값 변경을 인식해주는 $set 사용해야된다
        this.$set(this.cost, "saleprice", this.saleprice);
      }
      // 배송비 계산
      this.deliveryFee = 0;
      if (this.goodprice - this.saleprice >= 40000) {
        this.$set(this.cost, "deliveryFee", this.deliveryFee);
      } else {
        this.deliveryFee = 3000;
        this.$set(this.cost, "deliveryFee", this.deliveryFee);
      }
      this.$emit("pass", this.cost);
    },

    deleteCart(idx) {
      alert("장바구니에서 삭제하시겠습니까?");
      this.deleteAxios(idx);
      // 새로고침
      this.$router.go();
    },
  },
  // 가격 3자리 컴마(,) 추가 기능
  filters: {
    currency: function (value) {
      var num = new Number(value);
      return num.toFixed(0).replace(/(\d)(?=(\d{3})+(?:\.\d+)?$)/g, "$1,");
    },
  },
  created() {
    this.secretKey = this.$session.get("secretKey");

    axios
      .get("/cart/getCartInfo?secretKey=" + this.secretKey)
      .then((response) => {
        this.models = response.data;
        //console.log(this.models);
        this.count = this.models.map((i) => i.count);
        this.goodSumCost();
        // product 개수들 count 변수에 할당
        this.checkList = this.models;
        this.selected = this.models.map((i) => i.idx);
      })
      .catch((error) => {
        console.log(error);
      });
  },
  // count 변경시, 사이드 메뉴에도 변경값 전달
  watch: {
    count() {
      // console.log(newVal);
      this.goodSumCost();
      // console.log(this.cost);
    },
  },
};
</script>

<style>
.cart-content {
  display: flex;
  flex-direction: column;
  width: 750px;
  margin-right: 50px;
}
.select-option {
  display: flex;
  border-bottom: 1px solid black;
  padding-bottom: 10px;
}
.select-all {
  margin-left: 30px;
}
.select-delete {
  margin-left: 30px;
  cursor: pointer;
}
.cart-list {
  display: flex;
  flex-direction: column;
}
.cart-good {
  display: flex;
  height: 150px;
  border-bottom: 0.1px solid gray;
  padding-top: 25px;
  padding-bottom: 25px;
  align-content: center;
}
.good-image {
  width: 70px;
  margin-left: 30px;
}
.good-name {
  width: 300px;
  line-height: 100px;
  margin-left: 20px;
}
.good-name > p {
  width: 280px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.good-count {
  display: flex;
  width: 80px;
  height: 35px;
  text-align: center;
  align-content: center;
  border: 0.1px solid rgb(211, 209, 209);
  border-radius: 5px;
  justify-content: space-between;
  margin-top: 30px;
}
.good-count > button {
  font-size: 20px;
}

.good-cost {
  width: 150px;
  margin-left: 40px;
  text-align: center;
  line-height: 100px;
}
.good-cost-beforesale {
  width: 150px;
  height: 50px;
  margin-left: 40px;
  text-align: center;
  line-height: 20px;
  text-decoration: line-through;
  color: gray;
  font-size: 15px;
}
.good-cost-sale {
  width: 150px;
  height: 50px;
  margin-left: 40px;
  text-align: center;
  line-height: 100px;
}
.cancel {
  line-height: 100px;
  margin-left: 30px;
  color: gray;
  cursor: pointer;
}

.count-minus {
  width: 25px;
  height: 25px;
}
.count-minus-disabled {
  width: 25px;
  height: 25px;
  color: rgb(219, 219, 219);
}

.count-plus {
  width: 25px;
  /* height: 25px; */
}
/* round 체크박스 */
.round {
  position: relative;
}
.round label {
  background-color: #fff;
  border: 1px solid #ccc;
  border-radius: 50%;
  cursor: pointer;
  height: 28px;
  left: 0;
  position: absolute;
  top: 0;
  width: 28px;
}

.round label:after {
  border: 2px solid #fff;
  border-top: none;
  border-right: none;
  content: "";
  height: 6px;
  left: 7px;
  opacity: 0;
  position: absolute;
  top: 8px;
  transform: rotate(-45deg);
  width: 12px;
}

.round input[type="checkbox"] {
  visibility: hidden;
}

.round input[type="checkbox"]:checked + label {
  background-color: #5f0080;
  border-color: #5f0080;
}

.round input[type="checkbox"]:checked + label:after {
  opacity: 1;
}

.cart-num {
  line-height: 100px;
}

.cart-empty {
  text-align: center;
  margin: 30px 0;
}
</style>

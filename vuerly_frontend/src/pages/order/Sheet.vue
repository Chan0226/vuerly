<template>
  <div class="bg">
    <h1>주문서</h1>
    <div class="order-sheet">
      <div class="order-section-1">
        <div class="sheet-header">
          <h2>주문 상품</h2>
        </div>
        <div class="summary" v-if="models.length === 1">
          <span class="product-name">{{
            models[0].productDTO.product_name
          }}</span>
          상품을 주문합니다.
        </div>
        <div class="summary" v-else>
          <span class="product-name">{{
            models[0].productDTO.product_name
          }}</span
          >&nbsp;외&nbsp;
          <span class="product-cnt">{{ models.length - 1 }}개</span>의 상품을
          주문합니다.
        </div>
      </div>
      <div class="order-section-1">
        <div class="sheet-header">
          <h2>주문자 정보</h2>
        </div>
        <div class="info-box">
          <div class="info-item">
            <div class="info-title">보내는 분</div>
            <div class="info-title">이메일</div>
          </div>
          <div class="info-item">
            <div class="info-sub">{{ models[0].memberVO.name }}</div>
            <div class="info-sub">{{ models[0].memberVO.email }}</div>
          </div>
        </div>
      </div>
      <div class="order-section-1">
        <div class="sheet-header">
          <h2>배송 정보</h2>
        </div>
        <div class="info-box">
          <div class="info-item">
            <div class="info-title">배송지</div>
            <div class="info-title">받는 분</div>
          </div>
          <div class="info-item">
            <div class="info-sub">{{ models[0].memberVO.addr }}</div>
            <div class="info-sub">
              {{ models[0].memberVO.name }} (주문자와 동일)
            </div>
          </div>
        </div>
      </div>
      <div class="order-section-2">
        <div class="sheet-point">
          <div class="sheet-header">
            <h2>적립금</h2>
          </div>
          <div class="info-box">
            <div class="info-item">
              <div class="info-point-title">적립금 적용</div>
            </div>
            <div class="info-item">
              <div class="info-point-sub">
                <div class="info-sub">
                  <input
                    type="text"
                    class="input-point"
                    v-model="usePoint"
                    maxlength="8"
                    oninput="this.value = this.value.replace(/[^0-9.]/g, '');"
                    @change="changePoint(totalprice, allPoint)"
                  />
                  <button
                    type="button"
                    class="total-point-button"
                    @click="useAllPoint(totalprice, allPoint)"
                  >
                    모두사용
                  </button>
                </div>
                <div class="info-sub">
                  <div class="point-description">
                    <span class="won">보유 적립금</span>
                    <div class="point">{{ allPoint | currency }}</div>
                    <span class="won">원</span>
                  </div>
                  <span class="max-point">* 최소 결제금액 100원</span>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="receipt">
          <h2>결제 금액</h2>
          <div class="receipt-box">
            <div class="receipt-item">
              <div class="receipt-title">주문 금액</div>
              <div class="receipt-sub">
                <div class="price">{{ orderprice | currency }}</div>
                <span class="won">원</span>
              </div>
            </div>
            <div class="receipt-item">
              <div class="receipt-detail-title">
                &#9492;&nbsp;&nbsp;상품금액
              </div>
              <div class="receipt-detail-sub">
                <div class="price">{{ goodprice | currency }}</div>
                <span class="receipt-detail-won">원</span>
              </div>
            </div>
            <div class="receipt-item">
              <div class="receipt-detail-title">
                &#9492;&nbsp;&nbsp;상품할인 금액
              </div>
              <div class="receipt-detail-sub">
                <div class="price">-&nbsp;{{ saleprice | currency }}</div>
                <span class="receipt-detail-won">원</span>
              </div>
            </div>
            <div class="receipt-item">
              <div class="receipt-title">배송비</div>
              <div class="receipt-sub">
                <div class="price">{{ deliveryFee | currency }}</div>
                <span class="won">원</span>
              </div>
            </div>
            <div class="receipt-item">
              <div class="receipt-title">적립금 사용</div>
              <div class="receipt-sub">
                <div class="price">-&nbsp;{{ usePoint | currency }}</div>
                <span class="won">원</span>
              </div>
            </div>
            <div class="receipt-bot">
              <div class="receipt-title">최종결제 금액</div>
              <div class="receipt-sub">
                <div class="total-price">{{ amount | currency }}</div>
                <span class="total-won">원</span>
              </div>
            </div>
          </div>
          <div class="payment">
            <button type="submit" class="pay-button" @click="requestPay">
              결제하기
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import router from "../../routes";

// const HOST = "http://localhost:8080";

export default {
  data() {
    return {
      memberNum: 0,
      models: [],
      orderprice: 0,
      goodprice: 0,
      saleprice: 0,
      deliveryFee: 0,
      totalprice: 0,
      allPoint: 0,
      usePoint: 0,
      addPoint: 0,
      amount: 0,
      productNum: [],
      orderNum: 0,
    };
  },
  methods: {
    updatePoint() {
      //포인트 사용 patch
      this.addPoint = Math.floor(this.amount / 100);

      axios
        .patch(
          "/order/updatePoint",
          {
            member_num: this.memberNum,
            point: this.allPoint - this.usePoint + this.addPoint,
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
          // 페이지 이동
          router.push({
            name: "orderSuccess",
            params: {
              name: this.models[0].memberVO.name,
              amount: this.amount,
              product_num: this.productNum,
              order_num: this.orderNum,
            },
          });
        })
        .catch((error) => {
          console.log(error);
        });
    },
    insertOrder() {
      // 주문내역 insert
      axios
        .post(
          "/order/insertOrder",
          {
            status: 1,
            point: this.usePoint,
            addr: this.models[0].memberVO.addr,
            member_num: this.memberNum,
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
          // 결제내역 insert
          axios
            .post(
              "/order/insertPayment",
              {
                total_price: this.goodprice,
                discount_fee: this.saleprice,
                delivery_fee: this.deliveryFee,
                final_price: this.amount,
                used_point: this.usePoint,
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
              // 포인트 사용 및 적립
              this.updatePoint();
            })
            .catch((error) => {
              console.log(error);
            });
        })
        .catch((error) => {
          console.log(error);
        });
    },

    requestPay() {
      var IMP = window.IMP; // 생략 가능
      IMP.init("imp27853557"); // 예: imp00000000
      // IMP.request_pay(param, callback) 결제창 호출
      IMP.request_pay(
        {
          // param
          pg: "html5_inicis",
          pay_method: "card",
          merchant_uid: "ORD20230101-" + this.orderNum,
          name:
            this.models[0].productDTO.product_name +
            " 외 " +
            (this.models.length - 1) +
            "건",
          amount: this.amount,
          buyer_email: this.models[0].memberVO.email,
          buyer_name: this.models[0].memberVO.name,
          buyer_addr: this.models[0].memberVO.addr,
        },
        (rsp) => {
          // callback
          if (rsp.success) {
            //결제 성공 시
            this.insertOrder();
          } else {
            // 결제 실패 시
            alert("결제에 실패하였습니다.\n" + rsp.error_msg);
          }
        }
      );
    },
    getCost() {
      this.orderprice =
        this.$route.params.goodprice - this.$route.params.saleprice;
      this.goodprice = this.$route.params.goodprice;
      this.saleprice = this.$route.params.saleprice;
      this.deliveryFee = this.$route.params.deliveryFee;
      // 총 결제금액 초기값
      this.totalprice =
        this.$route.params.goodprice -
        this.$route.params.saleprice +
        this.$route.params.deliveryFee;
      // 최종 결제금액
      this.amount =
        this.$route.params.goodprice -
        this.$route.params.saleprice +
        this.$route.params.deliveryFee;
      // 결제아이디 랜덤 생성
      this.orderNum = Math.floor(Math.random() * (1000000 - 1) + 1);
    },
    useAllPoint(amt, pnt) {
      var v_point = 0; //사용할 포인트 (input 입력값)

      //결제금액보다 포인트가 크거나 같을 때(최소금액 100원)
      if (pnt >= amt) {
        v_point = amt - 100;
      } else {
        v_point = amt;
      }

      this.usePoint = v_point;
      this.changePoint(amt, pnt);
    },
    changePoint(amt, pnt) {
      var v_point = this.usePoint;

      //입력값이 사용가능 포인트보다 클때
      if (v_point > pnt) {
        v_point = pnt;
        this.usePoint = v_point; //input 값 재설정
      }

      //결제금액보다 포인트가 크거나 같을 때(최소금액 100원)
      if (v_point >= amt) {
        v_point = amt - 100; //사용할 포인트는 결제금액과 동일하게 설정
        this.usePoint = v_point; //input 값 재설정
      }
      // 최종 결제금액
      this.amount = amt - v_point;
    },
  },
  filters: {
    currency: function (value) {
      var num = new Number(value);
      return num.toFixed(0).replace(/(\d)(?=(\d{3})+(?:\.\d+)?$)/g, "$1,");
    },
  },
  created() {
    // 로그인 세션
    this.secretKey = this.$session.get("secretKey");

    // 회원번호 넣기
    axios
      .get("/member/getUserNum?secretKey=" + this.secretKey)
      .then((response) => {
        this.memberNum = response.data;
      })
      .catch((error) => {
        console.log(error);
      });

    axios
      .get("/order/getOrderSheet?secretKey=" + this.secretKey)
      .then((response) => {
        this.models = response.data;

        this.allPoint = this.models[0].memberVO.point;

        for (var i in this.models) {
          // 객체,배열은 값 변경을 인식해주는 $set 사용해야된다
          this.$set(this.productNum, i, this.models[i].cartVO.product_num);
        }
      })
      .catch((error) => {
        console.log(error);
      });

    this.getCost();
  },
};
</script>

<style scoped>
h1 {
  font-size: 28px;
  margin: 20px 0 0;
  text-align: center;
}

h2 {
  font-size: 24px;
  margin: 10px 0;
}

.bg {
  color: #333;
  margin: auto;
  max-width: 1050px;
  padding: 30px 0;
}

.order-section-1 {
  padding: 40px 0;
}

.order-section-2 {
  padding: 40px 0 0;
  display: flex;
}

.sheet-header {
  border-bottom: 1px solid #333;
}

.summary {
  text-align: center;
  padding: 30px 0;
  font-weight: 700;
  font-size: 16px;
}

.product-cnt {
  color: #5f0080;
}

.info-box {
  display: flex;
  padding: 20px 0;
}

.info-item {
  display: flex;
  flex-direction: column;
  font-size: 14px;
}

.info-title {
  font-weight: 700;
  padding: 10px 0px;
  width: 150px;
}

.info-sub {
  padding: 10px 0px;
  color: #999;
}

.sheet-point {
  display: flex;
  flex-direction: column;
  flex-grow: 1;
}

.input-point {
  height: 40px;
  width: 180px;
  border: 1px solid #999;
  border-radius: 4px;
  padding-left: 10px;
}

.total-point-button {
  margin-left: 10px;
  border: 1px solid #5f0080;
  border-radius: 4px;
  height: 40px;
  width: 80px;
  color: #5f0080;
  font-weight: 700;
}

.receipt {
  position: relative;
  width: 400px;
  padding: 0 40px;
}

.receipt-box {
  display: flex;
  flex-direction: column;
  background-color: #f9f9f9;
  font-size: 14px;
  font-weight: 700;
  padding: 20px;
}

.receipt-item {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  padding-bottom: 10px;
  align-items: center;
}

.receipt-bot {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  padding-top: 10px;
  border-top: 1px solid #e7e7e8;
  align-items: center;
}

.receipt-title {
  display: inline-flex;
  width: 100px;
}

.receipt-detail-title {
  width: 100px;
  display: inline-flex;
  color: #999;
  font-weight: lighter;
  font-size: 12px;
}

.receipt-sub {
  display: inline-flex;
  flex-direction: row;
}

.receipt-detail-sub {
  display: flex;
  flex-direction: row;
  color: #999;
  font-weight: lighter;
}

.price {
  padding: 0 5px;
  font-size: 16px;
}

.total-price {
  padding: 0 5px;
  font-size: 20px;
}

.won {
  padding-top: 3px;
}

.total-won {
  padding-top: 5px;
}

.receipt-detail-won {
  font-size: 12px;
  padding-top: 2px;
}

.payment {
  padding: 20px 0;
}

.pay-button {
  font-weight: 700;
  color: #fff;
  background: #5f0080;
  height: 50px;
  width: 320px;
  border-radius: 4px;
}

.info-point-title {
  font-weight: 700;
  padding: 20px 0px;
  width: 150px;
}

.info-point-sub {
  display: flex;
  flex-direction: column;
}

.point-description {
  display: flex;
  flex-direction: row;
  color: #333;
}

.max-point {
  font-size: 12px;
  padding-top: 5px;
}

.point {
  padding-left: 7px;
  padding-right: 3px;
  font-size: 16px;
  font-weight: 700;
}
</style>

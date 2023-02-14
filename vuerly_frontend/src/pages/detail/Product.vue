<template>
  <div class="detail-bg" ref="scrollContainer">
    <div class="product-atf">
      <div class="img-box">
        <img :src="product.thumbnail" class="img" />
      </div>
      <div class="p-info">
        <div class="p-info-1">
          <div class="special">샛별배송</div>
          <div class="p-name">{{ product.name }}</div>
          <div class="p-flex p-price">
            <span class="sale">{{ product.sale }}%</span>
            <span class="price">{{ product.price | currency }}</span>
            <span class="won">원</span>
          </div>
        </div>
        <div class="p-info-2">
          <div class="p-flex">
            <span class="p-info-title">배송</span>
            <div class="p-info-sub">
              <p>샛별배송</p>
              <p class="d-descript">
                23시 전 주문시 내일 아침 7시 전 도착<br />(대구·부산·울산
                샛별배송 운영시간 별도 확인)
              </p>
            </div>
          </div>
          <div class="p-flex">
            <span class="p-info-title">판매자</span>
            <div class="p-info-sub">
              <p>VUERLY</p>
            </div>
          </div>
          <div class="p-flex">
            <span class="p-info-title">상품 선택</span>
            <div class="p-info-sub">
              <label>
                <select
                  class="p-dropdown"
                  v-model="selectValue"
                  @change="dropChange"
                >
                  <option value="0">상품을 선택해주세요</option>
                  <option value="1">{{ product.name }}</option>
                </select>
              </label>
            </div>
          </div>
        </div>
        <div class="p-info-3">
          <div class="total-price">
            <span>총 상품금액:</span>
            <span class="price">{{ isPrice | currency }}</span>
            <span class="won">원</span>
          </div>
        </div>
      </div>
    </div>
    <div class="product-buy">
      <p class="point-event">로그인 후, 할인 및 적립혜택이 제공됩니다.</p>
      <button type="button" class="cart" @click="addCart">장바구니 담기</button>
    </div>
    <div class="p-detail">
      <div class="detail-header">
        <ul class="tabs">
          <li :class="{ tabChange: tab === 1 }" @click="setTab(1)">상품설명</li>
          <li :class="{ tabChange: tab === 2 }" @click="setTab(2)">상세정보</li>
          <li :class="{ tabChange: tab === 3 }" @click="setTab(3)">후기</li>
        </ul>
      </div>
      <div class="p-detail-top" ref="tab1">
        <div class="description-img">
          <!-- 상품설명 사진-->
          <img :src="product.desc_image" class="img" />
        </div>
      </div>
      <div class="p-detail-mid" ref="tab2">
        <div class="detail-img">
          <!-- 상세정보 사진 -->
          <img :src="product.detail_image" class="img" />
        </div>
      </div>
      <div class="p-detail-mid">
        <h1>상품고시정보</h1>
        <ul class="css-1lb3ltj">
          <li class="css-pxuqs9">용량 또는 중량</li>
          <li class="css-141eau5">상품설명 및 상품이미지참조</li>
          <li class="css-pxuqs9">제조국</li>
          <li class="css-141eau5">상품설명 및 상품이미지참조</li>
          <li class="css-pxuqs9">제품 주요 사양 (피부타입, 색상(호, 번) 등)</li>
          <li class="css-141eau5">상품설명 및 상품이미지참조</li>
          <li class="css-pxuqs9">
            화장품법에 따라 기재·표시하여야 하는 모든 성분
          </li>
          <li class="css-141eau5">상품설명 및 상품이미지참조</li>
          <li class="css-pxuqs9">
            사용기한 또는 개봉 후 사용기간(개봉 후 사용기간을 기재할 경우에는
            제조연월일을 병행표기)
          </li>
          <li class="css-141eau5">제품 별도 라벨 표기 참조</li>
          <li class="css-pxuqs9">
            기능성 화장품의 경우 화장품법에 따른 식품의약품안전처 심사 필 유무
            (미백, 주름개선, 자외선차단,태닝오일, 염색약, 탈모샴푸 등)
          </li>
          <li class="css-141eau5">해당사항 없음</li>
          <li class="css-pxuqs9">사용방법</li>
          <li class="css-141eau5">상품설명 및 상품이미지참조</li>
          <li class="css-pxuqs9">사용할 때 주의사항</li>
          <li class="css-141eau5">상품설명 및 상품이미지참조</li>
          <li class="css-pxuqs9">화장품제조업자 및 화장품책임판매업자</li>
          <li class="css-141eau5">상품설명 및 상품이미지참조</li>
          <li class="css-pxuqs9">품질보증기준</li>
          <li class="css-141eau5">
            본 제품에 이상이 있을 경우 공정거래위원회 고시 소비자 분쟁해결
            기준에 의해 보상해드립니다.
          </li>
          <li class="css-pxuqs9">소비자상담 관련 전화번호</li>
          <li class="css-141eau5">고객센터 (1544-2431)</li>
          <li class="css-pxuqs9"></li>
        </ul>
      </div>
      <div class="p-detail-mid">
        <h1>판매자정보</h1>
        <ul class="css-1lb3ltj">
          <li class="css-pxuqs9">판매자</li>
          <li class="css-141eau5">VUERLY</li>
        </ul>
      </div>
      <div class="p-detail-mid">
        <h1 class="inquiry">고객센터</h1>
        <h2 class="inquiry">
          궁금하신 점이나 서비스 이용에 불편한 점이 있으신가요?
        </h2>
        <div class="inquiry-box">
          <div class="inquiry-cate">
            <strong class="inquiry-item">전화 문의 1544-2431</strong>
            <p>월~금요일 오전 9시 - 오후 7시</p>
          </div>
          <div class="inquiry-cate">
            <strong class="inquiry-item">톡 상담하기</strong>
            <p>월~금요일 오전 9시 - 오후 7시</p>
          </div>
        </div>
      </div>
      <div class="p-detail-bot" ref="tab3">
        <!-- review component -->
        <Review v-bind:propsdata="productNum" />
      </div>
    </div>
  </div>
</template>

<script>
import Review from "../../components/Review.vue";
import axios from "axios";

export default {
  components: {
    Review,
  },
  data() {
    return {
      // tab
      tab: 1,
      section1: 0,
      section2: 0,
      section3: 0,
      // select
      selectValue: 0,
      isPrice: 0,
      // DB
      product: [],
      productNum: 0,
      memberNum: null,
      cartCnt: 0,
    };
  },
  methods: {
    setScroll() {
      var scrollTop = window.scrollY;

      if (scrollTop >= this.section3) {
        this.tab = 3;
      } else if (scrollTop >= this.section2) {
        this.tab = 2;
      } else if (scrollTop >= this.section1) {
        this.tab = 1;
      }
    },
    setTab(tab) {
      this.tab = tab;

      if (this.tab == 1) {
        window.scrollTo({
          top: this.section1,
        });
      } else if (this.tab == 2) {
        window.scrollTo({
          top: this.section2,
        });
      } else if (this.tab == 3) {
        window.scrollTo({
          top: this.section3,
        });
      }
    },

    dropChange() {
      if (this.selectValue == 1) {
        this.isPrice = this.product.price;
      } else {
        this.isPrice = 0;
      }
    },
    getCartCnt() {
      axios
        .get(
          "/cart/getProductCount?product_num=" +
            this.productNum +
            "&secretKey=" +
            this.secretKey
        )
        .then((response) => {
          this.cartCnt = response.data;

          // console.log(this.cartCnt);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 장바구니 담기
    addCart() {
      // 로그인 했을 때
      if (this.secretKey != null) {
        // 상품을 선택 했을 때
        if (this.selectValue == 1) {
          // 상품이 장바구니에 담겨져 있지 않을 때
          if (this.cartCnt == 0 || this.cartCnt == null) {
            axios
              .post(
                "/cart/insertCart",
                {
                  product_num: this.productNum,
                  secretKey: this.secretKey,
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
                // 장바구니에 담겨있는 수량 재조회
                this.getCartCnt();

                alert("장바구니에 담았습니다.");
              })
              .catch((error) => {
                console.log(error);
              });
          } else {
            // 상품이 이미 장바구니에 담겨 있을 때
            alert("선택하신 상품이 장바구니에 있습니다.");
          }
        } else {
          // select로 상품을 선택하지 않았을 때
          alert("상품을 선택해 주세요.");
        }
      } else {
        // 로그인 하지 않았을때
        alert("로그인 후 이용가능 합니다.");
      }
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
    this.productNum = this.$route.params.productNum;

    axios
      .get("/product/getProductDetail?num=" + this.productNum)
      .then((response) => {
        this.product = response.data;

        // console.log(this.product);
      })
      .catch((error) => {
        console.log(error);
      });

    axios
      .patch("/product/addReadcount?num=" + this.productNum)
      .then(() => {
        //alert("조회수 증가")
      })
      .catch((error) => {
        console.log(error);
      });

    if (this.secretKey != null) {
      // 장바구니에 담겨있는 수량 조회
      this.getCartCnt();
    }
  },
  mounted() {
    window.addEventListener("scroll", this.setScroll);
  },
  updated() {
    this.section1 = this.$refs.tab1.offsetTop - 50;
    this.section2 = this.$refs.tab2.offsetTop - 50;
    this.section3 = this.$refs.tab3.offsetTop - 50;

    //  현재 localstorage에 있는 최근본상품 불러오기
    let recentlyViewed =
      JSON.parse(localStorage.getItem("recentlyViewed")) || [];

    let recentlyViewdProduct = {
      num: this.productNum,
      thumbnail: this.product.thumbnail,
    };

    // 중복제거
    let productIndex = recentlyViewed.findIndex(
      (p) => p.num === recentlyViewdProduct.num
    );
    if (productIndex !== -1) {
      recentlyViewed.splice(productIndex, 1);
    }

    // 현재 상품 리스트에 추가
    recentlyViewed.unshift(recentlyViewdProduct);

    // 5개의 상품만 저장 하도록
    recentlyViewed = recentlyViewed.slice(0, 2);

    // 로컬스토리지에 저장
    localStorage.setItem("recentlyViewed", JSON.stringify(recentlyViewed));
  },
};
</script>

<style>
.detail-bg h1 {
  font-size: 28px;
  text-align: center;
  color: #666;
}

.detail-bg span {
  font-size: 12px;
  font-weight: 700;
}

.detail-bg p {
  font-size: 14px;
  margin: 0;
}

.detail-bg {
  color: #333;
  margin: auto;
  max-width: 1050px;
  padding: 30px 0;
}

.product-atf {
  display: flex;
  justify-content: space-between;
}

.special {
  height: 30px;
  font-size: 12px;
  font-weight: bold;
  color: #666;
}

.img-box {
  margin: 0px 40px;
  width: 320px;
  height: 400px;
  overflow: hidden;
}

.img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.p-info {
  display: flex;
  flex-direction: column;
  width: 560px;
  margin: 0px 40px;
}

.p-info-1 div {
  padding: 10px 0;
}

.p-info-2 div {
  padding: 20px 0;
}

.p-info-3 {
  display: flex;
  justify-content: flex-end;
  padding: 20px 0 5px;
}

.p-name {
  font-size: 28px;
  font-weight: 500;
}

.p-flex {
  display: flex;
  border-bottom: 1px solid #d6d6d6;
}

.p-info-sub {
  flex-grow: 1;
  padding: 0 !important;
}

.p-info span.sale {
  font-size: 28px;
  font-weight: 700;
  color: rgb(250, 98, 47);
}

.p-info span.price {
  font-size: 28px;
  font-weight: 700;
  padding-left: 10px;
}

.p-info span.won {
  padding-left: 5px;
  padding-top: 12px;
  font-size: 16px;
  font-weight: 700;
}

.p-info-title {
  font-size: 14px;
  color: #999;
  width: 125px;
}

.d-descript {
  margin-top: 3px !important;
  font-size: 12px !important;
  color: #666;
}

.p-dropdown {
  vertical-align: top;
  font-size: 14px;
  width: 100%;
  height: 40px;
  padding-left: 5px;
  border: 1px solid #d6d6d6;
  min-height: 30px;
  appearance: auto;
}

.product-buy {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  padding: 5px 40px;
}

.point-event {
  color: #666;
  margin: 10px 0;
}

.cart {
  height: 60px;
  width: 450px;
  background-color: #5f0080;
  color: #fff;
  font-weight: 700;
  border-radius: 4px;
}

.detail-header {
  margin: 50px 0 0;
  position: sticky;
  top: 50px;
}

ul.tabs {
  display: flex;
  height: 60px;
  padding: 0;
  list-style-type: none;
  border-width: 1px 1px 0px 0px;
  border-style: solid;
  border-color: #e7e7e8;
}

ul.tabs li {
  display: flex;
  flex: 1 1 auto;
  justify-content: center;
  align-items: center;
  font-weight: 700;
  cursor: pointer;
  background-color: #f9f9f9;
  border-width: 0px 0px 1px 1px;
  border-style: solid;
  border-color: #e7e7e8;
}

ul.tabs li.tabChange {
  background-color: #fff;
  border-width: 0px 0px 0px 1px;
  border-style: solid;
  border-color: #e7e7e8;
  color: #5f0080;
}

ul.tabs li a {
  text-decoration: none;
  color: #333;
  display: flex;
  flex: 1 1 auto;
  align-items: center;
  justify-content: center;
}

.p-detail {
  padding: 0px 50px 20px;
  min-height: 700px;
}

.p-detail-top {
  padding: 60px 0;
  border-bottom: 1px solid #d6d6d6;
}

.p-detail-mid {
  padding: 60px 0;
  border-bottom: 1px solid #d6d6d6;
}

.p-detail-bot {
  padding-top: 60px;
}

.description-img {
  min-height: 200px;
  overflow: hidden;
}

.detail-img {
  min-height: 200px;
  overflow: hidden;
}

/* 상품고시정보/판매자정보 table */
ul.css-1lb3ltj {
  display: flex;
  flex-wrap: wrap;
  width: 100%;
  padding: 30px 0 0;
  font-size: 13px;
  color: rgb(51, 51, 51);
  line-height: 18px;
  letter-spacing: -0.5px;
  list-style-type: none;
}

.css-pxuqs9 {
  width: 180px;
  padding: 18px 18px;
  background-color: rgb(247, 247, 247);
  word-break: break-all;
}

.css-141eau5 {
  display: flex;
  width: 295px;
  padding: 18px 10px;
  color: rgb(102, 102, 102);
}
/* end*/

h1.inquiry {
  text-align: left;
}

h2.inquiry {
  font-size: 18px;
  margin: 30px 0px 20px;
}

.inquiry-box {
  display: flex;
}

.inquiry-item {
  display: block;
  font-weight: 500;
  font-size: 18px;
  color: #5f0080;
  line-height: 33px;
  font-weight: 700;
  flex: 1 1 auto;
  padding-right: 100px;
}

.inquiry-item:before {
  content: "";
  display: inline-block;
  width: 3px;
  height: 20px;
  margin: 5px 10px 0 0;
  background-color: #5f0080;
  vertical-align: top;
}
</style>

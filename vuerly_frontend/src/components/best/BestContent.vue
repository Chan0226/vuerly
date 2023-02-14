<template>
  <div class="best-content">
    <div class="best-content-filter">
      <p>총 {{ models.length }}건</p>
      <ul class="content-filter">
        <li @click="popularsort" class="checksort">인기순</li>
        <li @click="recentsort">신상품순</li>
        <li @click="lowcostsort">낮은가격순</li>
        <li @click="highcostsort">높은가격순</li>
      </ul>
    </div>

    <div class="best-card">
      <div
        class="card-list"
        v-for="(rowData, idx) in paginatedData"
        :key="idx"
        @click="goDetail(rowData.num)"
      >
        <div class="img-wrap">
          <img :src="rowData.thumbnail" class="card-image" />
        </div>
        <div class="product-title">
          <b>{{ rowData.name }}</b>
          <p class="subtitle">{{ rowData.subtitle }}</p>
        </div>
        <div>
          <span class="sale">{{ rowData.sale }}%</span>
          {{ rowData.price | currency }}원
        </div>
        <div class="original-price">
          {{ rowData.cost | currency }}
        </div>
      </div>
    </div>
    <div class="pagination-box">
      <div class="pagination-btn" >
          <button type="button" class="prev-btn" :disabled="pageNum === 0"  @click="prevPage">&lt;</button>
          <button type="button" class="next-btn" :disabled="pageNum >= pageCnt - 1" @click="nextPage">&gt;</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import router from "../../routes";

export default {
  data: () => ({
    models: [], // axios로 받은 데이터 넣을 변수
    sort: "readcount_desc", // 필터링 적용시킬 변수
    category: "",
    pageNum: 0,
  }),
  methods: {
    // 필터링 sort시 axios 호출하는 함수
    callaxios() {
      axios
        .get(
          "/product/getAllProduct?sort=" +
            this.sort +
            "&category=" +
            this.category
        )
        .then((response) => {
          //console.log("models:", response.data);
          this.models = response.data;
          //console.log(this.models);
        })
        .catch((error) => {
          console.log(error);
        });

    },

    // 최신순
    recentsort() {
      this.sort = "num_desc";
      this.callaxios();
      this.changecolor(event);
    },
    // 인기순
    popularsort() {
      this.sort = "readcount_desc";
      this.callaxios();
      this.changecolor(event);
    },
    // 낮은가격순
    lowcostsort() {
      this.sort = "price_asc";
      this.callaxios();
      this.changecolor(event);
    },
    // 높은가격순
    highcostsort() {
      this.sort = "price_desc";
      this.callaxios();
      this.changecolor(event);
    },
    //props이벤트
    passEvent: function () {
      this.$emit("propsevent");
    },
    // 색상 변경 이벤트
    changecolor(event) {
      // 색상 적용시킬 클래스 선택
      const nonClick = document.querySelectorAll(".checksort");
      // 클릭한 요소 제외 클래스 제거
      nonClick.forEach((e) => {
        e.classList.remove("checksort");
      });
      // 클릭한 요소 색상 적용
      event.target.classList.add("checksort");
    },
    // 상세보기 이동
    goDetail(productNum) {
      router.push({ path: "/detail/" + productNum });
    },

    //pagination
    nextPage () {
        this.pageNum += 1;
        window.scrollTo({
          top: 0,
        });
    },
    prevPage () {
        this.pageNum -= 1;
        window.scrollTo({
          top: 0,
        });
    }
  },
  computed: {
      pageCnt() {
          let listLeng = this.models.length,
              page = Math.floor(listLeng/ 15)

              if (listLeng % 15 > 0) {
                  page += 1;
              }
              
          return page;
      },
      paginatedData () {
          const start = this.pageNum * 15,
                  end = start + 15;
          
          return this.models.slice(start, end);
      }
  },
  created() {
    axios
      .get("/product/getAllProduct?sort=" + this.sort)
      .then((response) => {
        this.models = response.data;
      })
      .catch((error) => {
        console.log(error);
      });
  },
  watch: {
    propsdata(newVal) {
      //console.log(newVal, oldVal);
      // 카테고리 조건 추가
      this.category = newVal;
      this.callaxios();
      this.pageNum = 0;
    },
  },
  filters: {
    currency: function (value) {
      var num = new Number(value);
      return num.toFixed(0).replace(/(\d)(?=(\d{3})+(?:\.\d+)?$)/g, "$1,");
    },
  },
  props: ["propsdata"],
};
</script>

<style>
.best-content {
  width: 100%;
  display: flex;
  flex-direction: column;
}
.best-card {
  display: flex;
  margin-left: 30px;
  flex-wrap: wrap;
}
.card-list {
  display: flex;
  flex-direction: column;
  width: 30%;
  margin: 0 13px 50px;
  overflow: hidden;
}
.card-list:hover {
  cursor: pointer;
}
/* 크기 커질때 카드 밖으로 못나가도록 */
.img-wrap {
  overflow: hidden;
}
.card-image {
  position: relative;
  width: 100%;
  transition-duration: 0.3s;
}
/* 마우스 올릴시에 크기 커지도록 */
.card-image:hover {
  transform: scale(1.2, 1.2);
  transition-duration: 0.5s;
}
.best-content-filter {
  margin: 0px 0px 20px 30px;
  display: flex;
  padding-bottom: 5px;
  justify-content: space-between;
  border-bottom: 0.1px solid rgb(224, 224, 224);
}
.content-filter {
  list-style: none;
}
.content-filter > li {
  float: left;
  padding-right: 10px;
}
.content-filter > li:hover {
  cursor: pointer;
}
.subtitle {
  font-size: 12px;
  color: gray;
}
.checksort {
  font-weight: 700;
}
.product-title {
  height: 90px;
}
.sale {
  color: rgb(250, 98, 47);
}
.original-price {
  color: rgb(192, 192, 192);
  text-decoration-line: line-through;
}
.pagination-box {
    display: flex;
    justify-content: center;
    padding: 20px;
}
.pagination-btn {
    display: flex;
    justify-content: space-between;
    width: 100px;
    font-size: 28px;
    font-weight: lighter;
}

.prev-btn {
    width: 45px;
    height: 45px;
    border: 1px solid #adadad;
    border-radius: 4px;
}

.prev-btn:hover {
    font-weight: 400;
    border: 1px solid #5f0080;
    color: #5f0080;
} 

.next-btn {
    width: 45px;
    height: 45px;
    border: 1px solid #adadad;
    border-radius: 4px;
}

.next-btn:hover {
    font-weight: 400;
    border: 1px solid #5f0080;
    color: #5f0080;
} 
</style>

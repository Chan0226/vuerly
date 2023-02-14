<template>
  <div>
    <Header></Header>
    <CategoryMenu></CategoryMenu>
    <router-view></router-view>
    <div v-if="isSideAppear" class="recentlyWatchedProduct">
      <b>최근본상품</b>
      <img
        v-for="(rowData, idx) in recentlyViewed"
        :key="idx"
        :src="rowData.thumbnail"
        class="recently-product-thumbnail"
        style="margin-top: 15px"
        @click="goDetail(rowData.num)"
      />
    </div>
    <div
      v-if="isSideAppear"
      style="cursor: pointer"
      @click="moveToTop"
      class="scroll-top"
    ></div>
    <Footer></Footer>
  </div>
</template>

<script>
import Header from "./Header";
import CategoryMenu from "./CategoryMenu.vue";
import Footer from "./Footer";
import router from "../routes";

export default {
  data() {
    return {
      isSideAppear: false,
      recentlyViewed: [],
      num: 0,
      thumbnail: "",
    };
  },
  components: {
    Header,
    Footer,
    CategoryMenu,
  },
  mounted() {
    window.addEventListener("scroll", this.handleScroll);
    this.recentlyViewed =
      JSON.parse(localStorage.getItem("recentlyViewed")) || [];
      //console.log(this.recentlyViewed);
  },
  methods: {
    handleScroll() {
      this.isSideAppear = window.pageYOffset > 300;
    },
    moveToTop() {
      if (window.pageYOffset > 0) {
        //스무스하게 스크롤 하기 //어디까지 올라갈지 위치를 정한다. behavior 자연스럽게 이동.
        window.scrollTo({ top: 0 });
        // this.scrollToTop();
      }
    },

    goDetail(productNum) {
      router.push({ path: "/detail/" + productNum });
    },
  },
};
</script>

<style>
html {
  scroll-behavior: smooth;
}

div.scroll-top {
  position: fixed;
  right: 55px;
  bottom: 200px;
  background-image: url("../static/top.png");
  background-position: center center;
  background-color: white;
  cursor: pointer;
  background-repeat: no-repeat;
  appearance: none;
  width: 60px;
  height: 60px;
  border-radius: 60px;
  border: 1px solid #c5c5c5;
  /* width: 100%; */
}
div.recentlyWatchedProduct {
  position: fixed;
  display: flex;
  flex-direction: column;
  align-items: center;
  right: 40px;
  bottom: 270px;
  width: 90px;
  height: 250px;
  border: 1px solid #c5c5c5;
  background-color: #fff;
}
div.recentlyWatchedProduct > b {
  padding-top: 15px;
  font-size: 15px;
}
.recently-product-thumbnail {
  width: 70px;
  height: 80px;
}
.recently-product-thumbnail:hover {
  cursor: pointer;
}
</style>

<template>
  <div class="search-page">
    <div class="search-result">
      <span class="search-keyword">'{{ $route.query.keyword }}'</span>에 대한
      검색결과
    </div>
    <div class="search-body">
      <side-menu v-on:pass="deliverCategory"></side-menu>
      <!-- props로 num 전달 -->
      <search-content
        v-bind:propsdata="num"
        v-on:propsevent="propsEvent"
      ></search-content>
    </div>
  </div>
</template>

<script>
import SideMenu from "../../components/SideMenu.vue";
import SearchContent from "../../components/search/SearchContent.vue";
export default {
  data: function () {
    return {
      str: "Headers",
      // props로 내릴 data
      num: "props",
    };
  },
  methods: {
    propsEvent() {
      console.log("propsEvent");
    },
    deliverCategory(value) {
      this.num = value;
    },
  },
  components: {
    "side-menu": SideMenu,
    "search-content": SearchContent,
  },
};
</script>

<style>
.search-page {
  display: flex;
  flex-direction: column;
  margin-top: 50px;
  margin-bottom: 50px;
  width: 1050px;
  margin-left: auto;
  margin-right: auto;
}
div.search-page > h1 {
  text-align: center;
  margin-bottom: 50px;
}
.search-body {
  display: flex;
}
.search-result {
  text-align: center;
  height: 50px;
}
.search-keyword {
  color: #5f0080;
  font-weight: 700;
}
</style>

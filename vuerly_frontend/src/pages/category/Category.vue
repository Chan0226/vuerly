<template>
    <div class="cate-page">
      <h1 v-if="$route.path === '/category/skincare'">스킨케어</h1>
      <h1 v-else-if="$route.path === '/category/makeup'">메이크업</h1>
      <h1 v-else-if="$route.path === '/category/cleansing'">클렌징</h1>
      <h1 v-else-if="$route.path === '/category/haircare'">헤어케어</h1>
      <h1 v-else-if="$route.path === '/category/bodycare'">바디케어</h1>
      <h1 v-else-if="$route.path === '/category/other'">기타</h1>
      <div class="cate-body">
        <cate-side-menu v-on:pass="deliverCategory"></cate-side-menu>
        <cate-content
          v-bind:propsdata="num"
          v-on:propsevent="propsEvent"
        ></cate-content>
      </div>
    </div>
  </template>
  
  <script>
  import CateSideMenu from "../../components/category/CateSideMenu.vue";
  import CategoryContent from "../../components/category/CategoryContent.vue";
  
  export default {
    data: function () {
      return {
        str: "Headers",
        // props로 내릴 data
        num: "props",
        models: []
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
      "cate-side-menu": CateSideMenu,
      "cate-content": CategoryContent,
    },
    created() {
    this.brandNum = this.$route.params.brandNum
    },
  };
  </script>
  
  <style>
  .cate-page {
    display: flex;
    flex-direction: column;
    padding: 50px 0;
    width: 1050px;
    margin: auto;
  }
  div.cate-page > h1 {
    text-align: center;
    margin-bottom: 50px;
  }
  .cate-body {
    display: flex;
  }
  </style>
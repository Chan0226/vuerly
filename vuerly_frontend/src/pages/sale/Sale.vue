<template>
    <div class="best-page">
      <h1>알뜰쇼핑</h1>
      <div class="best-body">
        <side-menu v-on:pass="deliverCategory"></side-menu>
        <!-- props로 num 전달 -->
        <sale-content
          v-bind:propsdata="num"
          v-on:propsevent="propsEvent"
        ></sale-content>
      </div>
    </div>
  </template>
  
  <script>
  import SideMenu from "../../components/SideMenu.vue";
  import SaleContent from "../../components/sale/SaleContent.vue";
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
      "sale-content": SaleContent,
    },
  };
  </script>
  
  <style>
  .best-page {
    display: flex;
    flex-direction: column;
    margin-top: 50px;
    margin-bottom: 50px;
    width: 1050px;
    margin-left: auto;
    margin-right: auto;
  }
  div.best-page > h1 {
    text-align: center;
    margin-bottom: 50px;
  }
  .best-body {
    display: flex;
  }
  </style>
  
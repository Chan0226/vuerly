<template>
    <div class="brand-page">
      <img class="brand-desc" :src="models.brand_desc"/>
      <h1>{{ models.brand }}</h1>
      <div class="brand-body">
        <side-menu v-on:pass="deliverCategory"></side-menu>
        <brand-content
          v-bind:propsdata="num"
          v-on:propsevent="propsEvent"
        ></brand-content>
      </div>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  import SideMenu from "../../components/SideMenu.vue";
  import BrandContent from "../../components/brand/BrandContent.vue";
  
  export default {
    data: function () {
      return {
        str: "Headers",
        // props로 내릴 data
        num: "props",
        brandNum: 0,
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
      "side-menu": SideMenu,
      "brand-content": BrandContent,
    },
    created() {
    this.brandNum = this.$route.params.brandNum
    
    axios
      .get("/brand/getBrand?num="
      + this.brandNum
      )
      .then((response) => {
        this.models = response.data;
      })
      .catch((error) => {
        console.log(error);
      });
  },
  };
  </script>
  
  <style>
  .brand-page {
    display: flex;
    flex-direction: column;
    padding: 30px 0;
    width: 1050px;
    margin: auto;
  }
  div.brand-page > h1 {
    text-align: center;
    margin-bottom: 50px;
  }
  .brand-body {
    display: flex;
  }

  .brand-desc {
    width: 100%;
    padding-bottom: 50px;
  }
  </style>
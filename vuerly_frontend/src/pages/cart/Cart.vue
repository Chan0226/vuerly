<template>
  <div class="cart-page">
    <h1>장바구니</h1>
    <div class="cart-body">
      <cart-content v-on:pass="getGoodCost"></cart-content>
      <cart-side-menu v-bind:propsdata="price"></cart-side-menu>
    </div>
  </div>
</template>

<script>
import CartContent from "../../components/cart/CartContent.vue";
import CartSideMenu from "../../components/cart/CartSideMenu.vue";
export default {
  data: function () {
    return {
      str: "Headers",
      // props로 내릴 data
      price: "props",
    };
  },
  methods: {
    getGoodCost(value) {
      this.price = value;
    },
  },
  components: {
    "cart-side-menu": CartSideMenu,
    "cart-content": CartContent,
  },
};
</script>

<style>
.cart-page {
  display: flex;
  flex-direction: column;
  height: 100%;
  margin-top: 50px;
  margin-bottom: 50px;
  width: 1050px;
  margin-left: auto;
  margin-right: auto;
}
.cart-page > h1 {
  text-align: center;
  margin-bottom: 50px;
}
.cart-body {
  height: 50vh;
  display: flex;
}
</style>

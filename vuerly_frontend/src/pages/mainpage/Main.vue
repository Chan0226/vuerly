<template>
  <div class="entire">
    <main-banner></main-banner>
    <best-goods></best-goods>
    <event-goods></event-goods>
    <brand-goods></brand-goods>
    <new-goods></new-goods>
    <sale-goods></sale-goods>
  </div>
</template>
<!--         reverse-transition="fade-transition" -->
<script>
import MainBanner from "../../components/MainBanner.vue";
import BestGoods from "../../components/MainBest.vue";
import MainBrand from "../../components/MainBrand.vue";
import NewGoods from "../../components/MainNewGood.vue";
import MainEvent from "../../components/MainEvent.vue";
import MainSale from "../../components/MainSale.vue";

export default {
  data: function () {
    return {
      str: "Headers",
    };
  },
  components: {
    "main-banner": MainBanner,
    "best-goods": BestGoods,
    "brand-goods": MainBrand,
    "new-goods": NewGoods,
    "event-goods": MainEvent,
    "sale-goods": MainSale
  },
};
</script>

<style>
.entire {
  padding-bottom: 100px;
}
</style>

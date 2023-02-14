<template>
  <div class="best-good">
    <h2>알뜰쇼핑</h2>
    <v-sheet>
      <v-slide-group multiple show-arrows>
        <v-slide-item v-for="(rowData, idx) in models" :key="idx">
          <v-card
            style="margin: 10px 8px; width: 220px; height: 380px; border-radius: 0; box-shadow: none;"
            @click="goDetail(rowData.num)"
          >
            <v-img
              class="white--text align-end"
              height="150px"
              :src="rowData.thumbnail"
              style="height: 275px;"
            ></v-img>
            <v-card-subtitle style="padding: 10px 7px;">{{ rowData.name }} </v-card-subtitle>
            <v-card-text style="position: absolute; bottom:0; padding: 7px; font-weight: 700; font-size: 16px;">
              <span class="sale">{{ rowData.sale }}%</span>
              {{ rowData.price | currency }}원
              <span class="original-price">
              {{ rowData.cost | currency }}
              </span>
            </v-card-text>
          </v-card>
        </v-slide-item>
      </v-slide-group>
    </v-sheet>
  </div>
</template>

<script>
import axios from "axios";
import router from "../routes";

export default {
  data: () => ({
    models: [],
  }),
  created() {
    axios
      .get("/product/getAllProduct?sort=price_asc")
      .then((response) => {
        // console.log("models:", response.data);
        // this.models = response.data.response.body.items.item;
        this.models = response.data;
        // console.log(this.models);
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    goDetail(productNum) {
      router.push({ path: "/detail/" + productNum });
    },
  },
  filters: {
    currency: function (value) {
      var num = new Number(value);
      return num.toFixed(0).replace(/(\d)(?=(\d{3})+(?:\.\d+)?$)/g, "$1,");
    },
  },
};
</script>
<style scoped>
div.best-good {
  margin-top: 100px;
  width: 1050px;
  margin-left: auto;
  margin-right: auto;
  /* position: absolute; */
  /* left: 50%; */
}
div.best-good > h2 {
  font-size: 28px;
  text-align: center;
  margin-bottom: 30px;
}
div.v-sheet {
  /* position: absolute; */
  /* left: 50%; */
}
div.title {
  font-size: 5px;
}
.sale {
  color: rgb(250, 98, 47);
}
.original-price {
    color: rgb(192, 192, 192);
    text-decoration-line: line-through;
    font-weight: normal;
    margin-left: 5px;
  }
</style>

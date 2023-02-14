<template>
  <div class="main-brand">
    <h2>브랜드관</h2>
    <v-sheet>
      <v-slide-group multiple show-arrows>
        <v-slide-item v-for="(rowData, idx) in models" :key="idx">
          <v-card
            style="
              margin: 0 40px 5px 40px;
              width: 130px;
              height: 130px;
              border-radius: 80px;
            "
            @click="goBrand(rowData.num)"
          >
            <v-img
              class="white--text align-end"
              :src="rowData.brand_logo"
              style=""
            ></v-img>
          </v-card>
        </v-slide-item>
      </v-slide-group>
    </v-sheet>
  </div>
</template>

<script>
import router from "../routes";
import axios from "axios";

export default {
  data: () => ({
    models: [],
  }),
  methods: {
    // 상세보기 이동
    goBrand(brandNum) {
      router.push({ path: "/brand/" + brandNum });
    },
  },
  created() {
    axios
      .get("/brand/brandList")
      .then((response) => {
        //console.log("models:", response.data);
        // this.models = response.data.response.body.items.item;
        this.models = response.data;
        //console.log(this.models);
      })
      .catch((error) => {
        console.log(error);
      });
  },
};
</script>
<style>
.main-brand {
  margin-top: 70px;
  width: 950px;
  margin-left: auto;
  margin-right: auto;
}
div.main-brand > h2 {
  font-size: 28px;
  text-align: center;
  margin-bottom: 40px;
}
</style>

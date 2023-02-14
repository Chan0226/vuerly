<template>
  <div class="best-good">
    <div class="main-event-title">
      <img class="hot-sale" src="../static/sale.png"/>
      <h2>특가/혜택</h2>
    </div>
    <div class="event-wrap">
      <div class="countdown-wrap">
        <div class="title-wrap">
          <img class="sale-clock" src="../static/clock.png"/>
          <h3>이달의 특가</h3>
        </div>
        <countdown :end="endDate"></countdown>
      </div>
      <div>
        <v-slide-group multiple show-arrows>
          <v-slide-item v-for="(rowData, idx) in paginatedData" :key="idx">
            <v-card
              style="
                margin: 10px 8px;
                width: 220px;
                height: 380px;
                border-radius: 0;
                box-shadow: none;
              "
              @click="goDetail(rowData.num)"
            >
              <v-img
                class="white--text align-end"
                height="150px"
                :src="rowData.thumbnail"
                style="height: 275px"
              ></v-img>
              <v-card-subtitle style="padding: 10px 7px">{{
                rowData.name
              }}</v-card-subtitle>
              <v-card-text
                style="
                  position: absolute;
                  bottom: 0;
                  padding: 7px;
                  font-weight: 700;
                  font-size: 16px;
                "
              >
                <span class="sale">{{ rowData.sale }}%</span>
                {{ rowData.price | currency }}원
                <span class="original-price">
                  {{ rowData.cost | currency }}
                </span>
              </v-card-text>
            </v-card>
          </v-slide-item>
        </v-slide-group>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import router from "../routes";
import Countdown from './event/Countdown.vue';

export default {
  data: () => ({
    models: [],
    endDate: '2023-02-28',
  }),
  created() {
    axios
      .get("/product/getEventProduct?sort=sale_desc")
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
  computed: {
    paginatedData() {            
        return this.models.slice(0, 3);
    }
  },
  filters: {
    currency: function (value) {
      var num = new Number(value);
      return num.toFixed(0).replace(/(\d)(?=(\d{3})+(?:\.\d+)?$)/g, "$1,");
    },
  },
  components: {
    Countdown
  }
};
</script>
<style scoped>
div.best-good {
  margin-top: 70px;
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
.sale {
  color: rgb(250, 98, 47);
}
.original-price {
  color: rgb(192, 192, 192);
  text-decoration-line: line-through;
  font-weight: normal;
  margin-left: 5px;
}

.event-good {
  display: flex;
  font-size: 28px;
}
.event-wrap {
  display: flex;
}
.event-wrap h3 {
  color: #999;
  font-size: 20px;
}
.countdown-wrap {
  width: 270px;
  height: 300px;
  margin: 10px;
  font-size: 30px;
  font-weight: bold;
  display: flex;
  flex-direction: column;
  justify-content: center;
  color: #5f0080;
}

.main-event-title {
  display: flex;
  justify-content: center;
}

.main-event-title h2{
  font-size: 28px;
  margin-bottom: 30px;
}

.hot-sale {
  width: 40px;
  height: 40px;
  margin-right: 10px;
}

.title-wrap {
  display: flex;
  align-items: flex-end;
  margin-bottom: 30px;
}
.sale-clock {
  width: 40px;
  margin-left: 50px;
  margin-right: 10px;
}
</style>

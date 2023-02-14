<template>
  <div class="category-menu-wrap">
    <div class="all">
      <div class="category-menu">
        <ul class="header-category">
          <li
            class="category-box"
            @mouseover="upHere = true"
            @mouseleave="upHere = false"
          >
            <span class="category-icon">&nbsp;</span>
            <span>카테고리</span>
          </li>
          <!-- <router-link to="/login" class="category">카테고리</router-link> -->
          <li>
            <router-link to="/new"> 신상품 </router-link>
          </li>
          <li>
            <router-link to="/best">베스트</router-link>
          </li>
          <li>
            <router-link to="/sale">알뜰쇼핑</router-link>
          </li>
          <li>
            <router-link to="/event">특가/혜택</router-link>
          </li>
          <li class="icon delivery-icon" @click="goDelivery">
            <router-link to="/delivery">&nbsp;</router-link>
          </li>
        </ul>
      </div>
      <div
        class="category-list"
        v-show="upHere"
        @mouseover="upHere = true"
        @mouseleave="upHere = false"
      >
        <div class="category" @click="goCate" id="skincare">스킨케어</div>
        <div class="category" @click="goCate" id="makeup">메이크업</div>
        <div class="category" @click="goCate" id="cleansing">클렌징</div>
        <div class="category" @click="goCate" id="haircare">헤어케어</div>
        <div class="category" @click="goCate" id="bodycare">바디케어</div>
        <div class="category" @click="goCate" id="other">기타</div>
      </div>
    </div>
  </div>
</template>

<script>
import router from "../routes";
export default {
  data() {
    return {
      upHere: false,
      category: "",
    };
  },
  methods: {
    goDelivery() {
      router.push({ path: "/delivery" });
    },
    goCate(event) {
      this.category = event.target.id;

      router.push({ path: "/category/" + this.category },
      () => {
          this.$router.go();
      });
    },
  },
  created() {
    // 로그인 회원 번호 불러오기
    this.loginchk = this.$session.get("userNum");
  },
};
</script>

<style scoped>
.category-menu-wrap {
  display: flex;
  justify-content: center;
  position: sticky;
  top: 0;
  z-index: 30;
  background: #fff;
  box-shadow: rgb(0 0 0 / 7%) 0px 3px 4px 0px;
}
.all {
  width: 1050px;
}
div.category-menu {
  position: sticky;
  top: 0;
}
ul.header-category {
  display: flex;
  align-items: center;
  justify-content: space-between;
  list-style-type: none;
  padding-left: 0;
}

ul.header-category > li {
  font-size: 16px;
}
ul.header-category > li:hover {
  color: #5f0080;
  cursor: pointer;
}
ul.header-category > li > a {
  text-decoration: none;
  color: #333;
}

ul.header-category > li > a:hover {
  color: #5f0080;
}
.category-box {
  height: 50px;
  display: flex;
  align-items: center;
}

span.category-icon {
  background: url(https://res.kurly.com/pc/service/common/1908/ico_gnb_all_off_x2.png)
    no-repeat 0 0;
  background-size: contain;
  width: 16px;
  height: 14px;
  margin: 0 10px;
}
li.delivery-icon {
  width: 121px;
  height: 22px;
  background: url(https://res.kurly.com/pc/service/common/2011/delivery_210801.png)
    no-repeat;
  background-size: contain;
}
.icon {
  font-size: 0;
}
.icon:hover {
  cursor: pointer;
}

.category-list {
  position: absolute;
  background-color: #fff;
  width: 200px;
  border: 1px solid #e7e7e8;
}

.category {
  padding: 10px 20px;
}

.category:hover {
  cursor: pointer;
  color: #5f0080;
  background-color: #f9e4ff;
}
</style>

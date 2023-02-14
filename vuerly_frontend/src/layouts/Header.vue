<template>
  <div class="grid">
    <div class="header-wrap">
      <ul class="user-menu" v-if="loginchk == null">
        <router-link to="/join">회원가입</router-link>
        <router-link to="/login">로그인</router-link>
        <router-link to="/cs">고객센터</router-link>
      </ul>
      <ul class="user-menu" v-else>
        <router-link to="/mypage">마이페이지</router-link>
        <router-link to="/" @click.native="deleteSession">로그아웃</router-link>
        <router-link to="/cs">고객센터</router-link>
      </ul>

      <div class="logo-wrap">
        <router-link to="/">
          <div class="logo">뷰얼리 로고</div>
        </router-link>
        <div class="search">
          <input
            type="text"
            placeholder="검색어를 입력해주세요"
            v-model="searchKeyword"
            @keyup.enter="search"
          />
          <img
            src="~@/static/searchicon.png"
            class="icon search-icon"
            @click="search"
          />
        </div>
        <div class="icon-wrap">
          <router-link to="/address">
            <div class="icon location-icon">배송지 아이콘</div>
          </router-link>
          <router-link to="/cart">
            <div class="icon cart-icon">장바구니 아이콘</div>
          </router-link>
        </div>
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
      loginchk: null,
      searchKeyword: null,
      category: "skincare",
    };
  },
  methods: {
    deleteSession() {
      // secretKey 세션 제거
      this.$session.remove("secretKey");
      this.loginchk = null;
    },
    goDelivery() {
      router.push({ path: "/delivery" });
    },
    // 검색 시, 이동
    search() {
      router.push(
        {
          // query -> path, params -> name
          name: "search",
          query: { keyword: this.searchKeyword },
        },
        () => {
          this.$router.go(this.$router.currentRoute);
        }
      );
    },
  },
  created() {
    // 로그인 회원 번호 불러오기
    this.loginchk = this.$session.get("secretKey");
    //console.log(this.loginchk);
  },
};
</script>

<style scoped>
.grid {
  width: 100%;
  background: #fff;
}

div.header-wrap {
  width: 1050px;
  margin: 0 auto;
}

ul.user-menu {
  display: flex;
  justify-content: flex-end;
  list-style: none;
  color: #4c4c4c;
  font-size: 12px;
  padding: 30px 0 20px;
}

ul.user-menu > a {
  position: relative;
  color: #4c4c4c;
  font-size: 12px;
  text-decoration: none;
}
ul.user-menu > a::after {
  content: "";
  float: right;
  width: 1px;
  height: 13px;
  background-color: #d8d8d8;
  margin: 0 10px;
}
ul.user-menu > a:last-child:after {
  content: none;
}
div.search {
  margin: 0 50px;
  width: 450px;
  position: relative;
}
.search > input {
  border: 1px solid #5f0080;
}

input {
  width: 100%;
  border: 1px solid #bbb;
  border-radius: 8px;
  padding: 10px 12px;
  font-size: 15px;
  height: 50px;
}

img {
  position: absolute;
  width: 20px;
  top: 10px;
  right: 12px;
  margin: 0;
  color: #5f0080;
}

.icon {
  font-size: 0;
}
.icon:hover {
  cursor: pointer;
}
div.logo-wrap {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 0 auto;
  padding-bottom: 20px;
}

div.logo {
  margin: 0 20px;
  width: 100px;
  height: 50px;
  max-width: 100%;
  background: url("~@/static/vuerly.png");
  background-position: center;
  background-repeat: no-repeat;
  background-size: contain;
  cursor: pointer;
  font-size: 0;
}

div.icon-wrap {
  display: flex;
}

div.cart-icon {
  width: 36px;
  height: 36px;
  background-image: url(https://res.kurly.com/pc/service/common/2011/ico_cart.svg);
  background-repeat: no-repeat;
  background-position: center;
  background-size: contain;
  margin: 0px 10px;
}

div.cart-icon:hover {
  background-image: url(https://res.kurly.com/pc/service/common/2011/ico_cart_on.svg?v=1);
  cursor: pointer;
}

div.location-icon {
  width: 36px;
  height: 36px;
  background: url(https://res.kurly.com/pc/ico/2008/ico_delivery_setting_done.svg);
  background-repeat: no-repeat;
  background-position: center;
  background-size: contain;
  margin: 0px 10px;
}

input.search {
  border-radius: 50px;
  box-sizing: border-box;
  border: 1px solid #f7f7f7;
  background-color: #f7f7f7;
  background-image: "https://res.kurly.com/pc/service/common/1908/ico_search_x2.png";
  outline: none;
  width: 235px;
  height: 35px;
  padding: 0 60px 0 14px;
  margin-bottom: 2px;
  margin-left: -31px;
  letter-spacing: -1px;
  font-family: "Noto Sans";
  font-weight: 400;
  font-size: 12px;
  color: #666;
  line-height: 16px;
}

img.search-icon {
  position: absolute;
  width: 20px;
  top: 15px;
  right: 12px;
  margin: 0;
}

.category-list {
  position: absolute;
  background-color: #fff;
  width: 200px;
  padding: 10px 20px;
  border: 1px solid #e7e7e8;
}

.category {
  padding: 10px 0;
}

.category:hover {
  cursor: pointer;
  color: #5f0080;
}
</style>

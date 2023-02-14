<template>
  <div class="bg">
    <div class="profile-box">
      <div class="profile-item name">
        <p>{{ models.name }}님</p>
      </div>
      <div class="profile-item point-box">
        <p>적립금</p>
        <p class="point">{{ models.point | currency }}원</p>
      </div>
      <div class="profile-snb">
        <div
          class="profile-snb-item profile-order"
          @click="currentTab = 'OrderView'"
        >
          <img src="../../static/order_list.png" class="profile-icon" />
          <p>주문내역</p>
        </div>
        <div
          class="profile-snb-item profile-review"
          @click="currentTab = 'MyReview'"
        >
          <img src="../../static/review_list.png" class="profile-icon" />
          <p>상품후기</p>
        </div>
      </div>
    </div>
    <h1>마이페이지</h1>
    <div class="mypage">
      <div class="snb">
        <div class="snb-item snb-order" @click="currentTab = 'OrderView'">
          <span>주문내역</span>
          <span class="float-right">&gt;</span>
        </div>
        <div class="snb-item snb-review" @click="currentTab = 'MyReview'">
          <span>상품 후기</span>
          <span class="float-right">&gt;</span>
        </div>
        <!-- <div class="snb-item snb-profile-u" @click="currentTab = 'EditProfile'"> -->
        <div class="snb-item snb-profile-u" @click="currentTab = 'CheckMember'">
          <span>개인정보수정</span>
          <span class="float-right">&gt;</span>
        </div>
        <div
          class="snb-item snb-profile-address"
          @click="currentTab = 'EditAddress'"
        >
          <span>배송지 관리</span>
          <span class="float-right">&gt;</span>
        </div>
      </div>
      <div class="change-page">
        <!-- 탭에 있는 메뉴 클릭시, 컴포넌트 가져다 변경 -->
        <component
          v-bind:is="currentTabComponent"
          v-bind:propsdata="currentTab"
          v-on:pass="validMember"
        ></component>
      </div>
    </div>
  </div>
</template>

<script>
import OrderView from "@/components/mypage/OrderView.vue";
import MyReview from "@/components/mypage/MypageReview.vue";
import EditProfile from "@/components/mypage/EditProfile.vue";
import EditAddress from "@/components/mypage/EditAddress.vue";
import CheckMember from "@/components/mypage/CheckMember.vue";
import axios from "axios";

export default {
  data() {
    return {
      currentTab: "OrderView",
      models: [],
      memberNum: null,
    };
  },
  computed: {
    currentTabComponent() {
      return this.currentTab;
    },
  },
  components: {
    OrderView,
    MyReview,
    EditProfile,
    EditAddress,
    CheckMember,
  },
  filters: {
    currency: function (value) {
      var num = new Number(value);
      return num.toFixed(0).replace(/(\d)(?=(\d{3})+(?:\.\d+)?$)/g, "$1,");
    },
  },
  methods: {
    validMember(value) {
      this.currentTab = value;
    },
  },
  created() {
    // 로그인 세션
    this.secretKey = this.$session.get("secretKey");

    axios
      .get("/member/getMemberInfo?secretKey=" + this.secretKey)
      .then((response) => {
        this.models = response.data;
        //console.log(this.models);
      })
      .catch((error) => {
        console.log(error);
      });
  },
};
</script>

<style scoped>
h1 {
  font-size: 28px;
  margin: 20px;
}

p {
  margin: 0;
}

.bg {
  color: #333;
  margin: auto;
  max-width: 1050px;
  padding: 30px 0;
}

.profile-box {
  display: flex;
  justify-content: center;
  padding: 20px;
  border: 1px solid #d6d6d6;
  font-size: 16px;
}

.profile-box p {
  font-size: 20px;
  font-weight: bold;
}

.profile-item {
  display: flex;
  flex-direction: column;
  flex: 1 1 auto;
  align-items: center;
  justify-content: center;
  padding: 0 20px;
}

.point-box {
  border-left: 1px solid #d6d6d6;
  border-right: 1px solid #d6d6d6;
}

.point {
  margin: 40px auto;
  font-size: 24px;
  color: #5f0080;
}

.profile-snb {
  width: 350px;
}

.profile-snb-item {
  display: flex;
  flex-direction: column;
  flex: 1 1 auto;
  align-items: center;
  justify-content: center;
  padding: 10px;
}

.profile-order {
  border-bottom: 1px solid #d6d6d6;
}

.profile-order:hover {
  cursor: pointer;
}

.profile-review:hover {
  cursor: pointer;
}

.profile-icon {
  position: relative;
  margin: 10px auto;
  width: 40px;
  height: 40px;
}

.mypage {
  display: flex;
  align-items: flex-start;
}

.snb {
  flex-direction: column;
  width: 180px;
  margin-right: 20px;
  border: 1px solid #d6d6d6;
}

.snb span {
  font-size: 14px;
}

.snb-item {
  height: 50px;
  padding: 15px;
}

.snb-item:hover {
  cursor: pointer;
  color: #5f0080;
  background-color: rgb(250, 250, 250);
}
.snb-review {
  border-top: 1px solid #d6d6d6;
  border-bottom: 1px solid #d6d6d6;
}

.snb-profile-u {
  border-bottom: 1px solid #d6d6d6;
}

.change-page {
  display: inline-block;
  vertical-align: top;
  flex-grow: 1;
  border: 1px solid #d6d6d6;
  padding: 20px;
}
</style>

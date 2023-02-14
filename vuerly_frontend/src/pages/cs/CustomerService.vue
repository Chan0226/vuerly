<template>
  <div class="cs">
    <div class="cs-side">
      <h1>고객센터</h1>
      <div class="snb">
        <div class="snb-item snb-order" @click="currentTab = 'Notice'">
          <span>공지사항</span>
          <span class="float-right">&gt;</span>
        </div>
        <div class="snb-item snb-review" @click="currentTab = 'Faq'">
          <span>자주하는 질문</span>
          <span class="float-right">&gt;</span>
        </div>
        <div class="snb-item snb-profile-u" @click="currentTab = 'OneToOne'">
          <span>1:1문의</span>
          <span class="float-right">&gt;</span>
        </div>
      </div>
    </div>
    <div class="change-page">
      <!-- 탭에 있는 컴포넌트 가져다 변경 -->
      <component v-bind:is="currentTabComponent"></component>
    </div>
  </div>
</template>

<script>
import OneToOne from "@/components/cs/OneToOne.vue";
import Notice from "@/components/cs/Notice.vue";
import Faq from "@/components/cs/Faq.vue";

// import axios from "axios";
export default {
  data() {
    return {
      currentTab: "Notice",
      models: [],
      secretKey: "",
    };
  },
  computed: {
    currentTabComponent() {
      let changeTab = this.currentTab;

      if (this.secretKey == undefined) {
        if (changeTab == "OneToOne") {
          alert("로그인 후 이용 가능합니다.");
          changeTab = "Faq";
        }
      }

      return changeTab;
    },
  },
  components: {
    OneToOne,
    Notice,
    Faq,
  },
  created() {
    this.secretKey = this.$session.get("secretKey");
  },
};
</script>

<style scoped>
h1 {
  font-size: 28px;
  margin: 20px;
}
.cs {
  color: #333;
  margin: auto;
  max-width: 1050px;
  display: flex;
  margin-top: 40px;
}
.cs-side {
  display: flex;
  flex-direction: column;
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

.change-page {
  /* display: inline-block;
  vertical-align: top;
  */
  flex-grow: 1;
  /* border: 1px solid #d6d6d6; */
  /* padding: 20px; */
}
</style>

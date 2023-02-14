<template>
  <div class="component-bg">
    <div class="review-header">
      <h1>상품 후기</h1>
    </div>
    <div class="review-sort">
      <div class="review-writable checkReview" @click="changeWritableReview">
        작성 가능 후기
      </div>
      <div class="review-writed" @click="changeWritedReview">작성한 후기</div>
    </div>
    <!-- 변경될 컴포넌트들(작성 가능 후기, 작성한 후기) -->
    <component v-bind:is="currentReviewTabComponent"></component>
  </div>
</template>

<script>
import WritableReview from "@/components/mypage/review/WritableReview.vue";
import WritedReview from "@/components/mypage/review/WritedReview.vue";

export default {
  data() {
    return {
      memberNum: this.$session.get("userNum"),
      models: [],
      currentReviewTab: "WritableReview",
    };
  },
  computed: {
    currentReviewTabComponent() {
      return this.currentReviewTab;
    },
  },
  components: {
    WritableReview,
    WritedReview,
  },

  methods: {
    changeWritableReview(event) {
      this.currentReviewTab = "WritableReview";
      this.changeReviewcolor(event);
    },
    changeWritedReview(event) {
      this.currentReviewTab = "WritedReview";
      this.changeReviewcolor(event);
    },

    // 색상 변경 이벤트
    changeReviewcolor(event) {
      // 색상 적용시킬 클래스 선택
      const nonClick = document.querySelectorAll(".checkReview");
      // 클릭한 요소 제외 클래스 제거
      nonClick.forEach((e) => {
        e.classList.remove("checkReview");
      });
      // 클릭한 요소 색상 적용
      event.target.classList.add("checkReview");
    },
  },
};
</script>

<style scoped>
h1 {
  font-size: 24px;
}

.component-bg {
  color: #333;
  margin: auto;
}
.review-header {
  display: flex;
  padding: 10px;
}
.review-writable {
  width: 50%;
  text-align: center;
  font-weight: 700;
  background-color: rgb(250, 250, 250);
  border-right: 0.1px solid rgb(216, 216, 216);
}
.review-writable:hover {
  cursor: pointer;
}
.review-writed {
  width: 50%;
  text-align: center;
  font-weight: 700;
  background-color: rgb(250, 250, 250);
}
.review-writed:hover {
  cursor: pointer;
}
.review-sort {
  display: flex;
  border: 0.1px solid rgb(216, 216, 216);
  height: 50px;
  justify-content: space-between;
  line-height: 50px;
}
.checkReview {
  color: rgb(100, 2, 100);
  font-weight: 700;
  background-color: white;
}
</style>

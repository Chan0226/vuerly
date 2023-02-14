<template>
  <div class="side-menu">
    <div class="filter-reset">
      <div>
        <p class="filter-p"><b>필터</b></p>
      </div>
      <div>
        <p class="reset" @click="resetCategory">초기화</p>
      </div>
    </div>
    <div class="filter-category">
      <p><b>카테고리</b></p>
      <ul class="category-list">
        <li @click="chagecategory('skincare')" :class="{ active: 'skincare' == isActive }">스킨케어</li>
        <li @click="chagecategory('makeup')" :class="{ active: 'makeup' == isActive }">메이크업</li>
        <li @click="chagecategory('cleansing')" :class="{ active: 'cleansing' == isActive }">클렌징</li>
        <li @click="chagecategory('haircare')" :class="{ active: 'haircare' == isActive }">헤어케어</li>
        <li @click="chagecategory('bodycare')" :class="{ active: 'bodycare' == isActive }">바디케어</li>
        <li @click="chagecategory('other')" :class="{ active: 'other' == isActive }">기타</li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  data: () => ({
    category: "",
    isActive: "",
  }),
  methods: {
    chagecategory(id) {
      this.category = id;
      this.isActive = id;
      // content에 보낼 데이터 $emit으로 부모 컴포넌트에 보내기(클릭한 리스트의 id값)
      this.$emit("pass", this.category);
    },

    // 초기화 이벤트
    resetCategory() {
      this.isActive = "";
      //console.log("초기화");
      this.$emit("pass", "");
    },
  },
};
</script>

<style>
.side-menu {
  display: flex;
  flex-direction: column;
  width: 200px;
}
.filter-reset {
  display: flex;
  justify-content: space-between;
  padding: 5px 5px 1px 1px;
  border-bottom: 1px solid rgba(196, 196, 196, 0.945);
  width: 200px;
}
.filter-reset > p {
  display: inline-block;
  /* width: 100px; */
  font-size: 15px;
}
.reset {
  /* position: absolute; */
  color: rgba(196, 196, 196, 0.945);
}
.reset:hover {
  cursor: pointer;
}
.category-list > li {
  list-style-type: none;
  margin-bottom: 15px;
}
.category-list > li:hover {
  cursor: pointer;
  color: #5f0080;
}
.filter-category {
  margin-top: 15px;
  padding: 5px 5px 1px 1px;
  border-bottom: 1px solid rgba(196, 196, 196, 0.945);
  width: 200px;
}
.filter-brand {
  margin-top: 15px;
  padding: 5px 5px 1px 1px;
  border-bottom: 1px solid rgba(196, 196, 196, 0.945);
  width: 200px;
}
.brand-list > li {
  list-style-type: none;
  margin-bottom: 15px;
}
.brand-list > li:hover {
  cursor: pointer;
  color: #5f0080;
}
.active {
  color: #5f0080;
  font-weight: 700;
}
.filter-p {
  color: #5f0080;
}
</style>

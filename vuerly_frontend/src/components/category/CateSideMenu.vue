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
    <div class="filter-brand">
      <p><b>브랜드</b></p>
      <ul class="brand-list">
        <li 
        v-for="(rowData, i) in models"
        :key="i"
        :class="{ active: rowData.num == isActive }"
        @click="changeBrand(rowData.num)">{{ rowData.brand }}</li>
      </ul>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data: () => ({
    models: [],
    category: "",
    brandNum: 0,
    isActive: 0
  }),
  methods: {
    changeBrand(num) {
      this.brandNum = num;
      this.isActive = num;
      // content에 보낼 데이터 $emit으로 부모 컴포넌트에 보내기
      this.$emit("pass", this.brandNum);
    },

    // 초기화 이벤트
    resetCategory() {
      this.isActive = -1;
      // console.log("초기화");
      this.$emit("pass", 0);
    },
  },
  created() {
    axios
    .get(
      "/brand/brandList"
    )
    .then((response) => {
      this.models = response.data

      //console.log(this.models)
    })
    .catch((error) => {
      console.log(error);
    });
  }
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

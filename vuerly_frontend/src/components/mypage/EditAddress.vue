<template>
  <div class="address-list">
    <div>
      <h1>배송지 관리</h1>
      <p class="address-list-desc">
        배송지에 따라 상품정보 및 배송유형이 달라질 수 있습니다.
      </p>
    </div>
    <div>
      <table class="address-table">
        <th style="width: 130px">주소</th>
        <th style="width: 40px">받으실분</th>
        <th style="width: 20px">연락처</th>
        <th style="width: 20px">수정</th>
        <tr class="table-row">
          <!-- 첫번째 줄 시작 -->
          <td>{{ address }}</td>
          <td>{{ name }}</td>
          <td>{{ phoneNum }}</td>
          <td class="update-button">
            <button @click="showModal = true">수정하기</button>
          </td>
        </tr>
      </table>
      <div class="modal" v-if="showModal">
        <div class="modal-content">
          <div class="update-modal-head">
            <h1 class="update-title">배송지 수정</h1>
            <span @click="showModal = false" class="close-button">X</span>
          </div>
          <div class="addr-input-area">
            <div class="addr-search-area">
              <input
                type="text"
                v-model="roadAddress"
                class="addr-input"
                placeholder="수정할 주소를 검색해주세요"
                readonly
              />
              <button
                type="button"
                id="searchAddrBtn"
                class="address-btn"
                @click="openPostcode"
              >
                주소검색
              </button>
            </div>
            <input
              type="text"
              v-model="detailAddress"
              id="name"
              class="addr-input"
              placeholder="상세주소를 입력해주세요"
              maxlength="20"
            />
          </div>
          <!-- 새로운 주소 입력 버튼 -->
          <div class="button-part">
            <button
              type="submit"
              id="updateBtn"
              class="update-btn"
              @click="updateAddress"
            >
              저장하기
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      num: 0,
      name: "",
      phoneNum: "",
      address: "",
      showModal: false,
      // 주소
      roadAddress: null,
      // 상세주소
      detailAddress: null,
    };
  },
  methods: {
    openPostcode() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          //console.log(data);
          this.zonecode = data.zonecode;
          this.roadAddress = data.roadAddress;
        },
      }).open();
    },
    // 배송지 수정 axios
    updateAddress() {
      let data = {
        secretKey: this.$session.get("secretKey"),
        addr: this.roadAddress + "," + this.detailAddress,
      };

      axios
        .patch("/member/updateAddress", JSON.stringify(data), {
          headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Credentials": "true",
          },
        })
        .then(() => {
          alert("배송지 수정이 완료되었습니다.");
          // 모달 끄기
          // this.showModal = false
          this.$router.go(this.$router.currentRoute);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  created() {
    // 세션에서 회원정보 불러오기
    this.secretKey = this.$session.get("secretKey");
    axios
      .get("/member/updateMemberInfo?secretKey=" + this.secretKey)
      .then((response) => {
        this.name = response.data.name;
        this.address = response.data.addr;
        this.phoneNum = response.data.phoneNum.replace(
          /(\d{3})(\d{4})(\d{4})/g,
          "$1-$2-$3"
        );
      })
      .catch((error) => {
        console.log(error);
      });
  },
};
</script>

<style scoped>
h1 {
  font-size: 24px;
  font-weight: 600;
  height: 20px;
  line-height: 20px;
  /* display: inline-block; */
}
.address-list-desc {
  display: inline-block;
  color: gray;
  /* margin-left: 20px; */
  margin-top: 20px;
  height: 20px;
  line-height: 20px;
}
.address-list {
  width: 100%;
  height: 500px;
  margin: auto;
  display: flex;
  flex-direction: column;
  padding: 10px;
}
.address-table {
  width: 100%;
  text-align: center;
  border-top: 1px solid #444444;
  border-collapse: collapse;
  margin-top: 30px;
}
th {
  border-bottom: 1px solid #444444;
  height: 70px;
  line-height: 70px;
}

td {
  border-bottom: 1px solid #e7e6e6;
  height: 70px;
  line-height: 70px;
  font-size: 15px;
  font-weight: 500;
}
.update-button:hover {
  cursor: pointer;
}

.modal {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  transition: opacity 0.3s ease;
}

.modal-content {
  width: 600px;
  height: 400px;
  margin: auto;
  padding: 20px 30px;
  background-color: #fff;
  border-radius: 2px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
  transition: all 0.3s ease;
}

.close-button {
  float: right;
}
.close-button:hover {
  cursor: pointer;
}
.update-title {
  font-size: 25px;
}
.update-modal-head {
  border-bottom: 1px solid black;
  padding-top: 10px;
  padding-bottom: 10px;
}
.addr-input-area {
  /* display: flex; */
  /* flex-wrap: wrap; */
  margin-top: 10px;
  height: 200px;
}
.addr-input {
  width: 350px;
  height: 50px;
  padding: 10px;
  text-align: left;
  margin: 20px auto;
  border-radius: 10px;
  border: 1px solid #dddddd;
}

.address-btn {
  width: 150px;
  padding: 10px;
  margin-left: 20px;
  border-radius: 10px;
  border: 1px solid #5f0080;
  color: #5f0080;
  font-weight: bold;
}

.button-part {
  display: flex;
  justify-content: center;
  margin-top: 10px;
}
#updateBtn {
  /* width: 350px; */
  width: 100%;
  height: 60px;
  padding: 10px;
  margin: auto;
  border-radius: 10px;
  border: 1px solid #dddddd;
  color: white;
  font-weight: bold;
  font-size: 20px;
  background-color: #5f0080;
}
.update-modal-head {
  display: flex;
  justify-content: space-between;
}
</style>

<template>
  <div class="layFooter">
    <div class="footer-wrapper">
      <div class="footer-content">
        <div>
          <span><b>(주) 엠투엠 글로벌</b></span>
          &emsp;&emsp;
          <span>대표이사 : 김승원 </span>
          &emsp;&emsp;
          <span>
            주소 : 서울특별시 영등포구 당산동1가 영등포로 150 생각공장
            당산 B동 1509호
          </span>
        </div>
        <div>
          <span>사업자등록번호 : 120-81-47525</span>
          &emsp;
          <span> 통신판매업신고번호 : 제2022 - 서울 - 0011호 </span>
          &emsp;
          <span>호스팅 사업자 : (주)엠투엠 글로벌</span>
        </div>
        <div>
          <span>
            구매안전서비스 : 가입사실확인이메일 : cs@moim.com
          </span>
        </div>
        <div>
          <span>
                고객센터 : <b>1544-2431</b> (통화료 발생 / 평일 09:00~19:00),
                톡상담하기 (평일 09:00~19:00)
              </span>
        </div>
      </div>
      <div class="footer-bot">
        <div class="block">
          <span className="footer-link">
                <b>호스트센터</b>
                &nbsp;|&nbsp;
                <a>이용약관</a>
                &nbsp;|&nbsp;
                <a>개인정보처리방침</a>
                &nbsp;|&nbsp;
                <a>지식재산권보호센터</a>
            </span>
            <span> ©m2mglobal Corp. All rights reserved </span>
        </div>
            <img
            alt=""
            src="https://gift-s.kakaocdn.net/dn/gift/images/m640/img_pcfamily.png"
            />
      </div>
    </div>
  </div>
</template>

<style scoped>
b {
  color: #777;
}
.layFooter {
  padding: 20px 40px;
  color: #999;
  font-size: 14px;
  background-color: #f9f9f9;
}

.footer-wrapper {
  display: flex;
  justify-content: space-between;
  padding: 20px;
}

.footer-content {
  display: flex;
  flex-direction: column;
  flex-grow: 1;
}

.footer-content div {
  padding: 3px 0;
}

.block {
  padding: 0 20px;
  display: flex;
  flex-direction: column;
  align-items: flex-end;
}

.footer-bot {
  display: flex;
  justify-content: end;
  align-items: flex-end;
}
</style>

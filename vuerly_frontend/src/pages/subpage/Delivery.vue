<template>
    <div class="bg">
        <div class="delivery">
            <div class="delivery-info-1">샛별배송 안내</div>
            <div class="delivery-info-2">주문부터 배송까지</div>
            <div class="delivery-info-3">기타배송 안내</div>
            <div class="delivery-info-4">
                <h1>우리집이 샛별배송 지역인지</h1>
                <h2>확인해보세요.</h2>
                <div class="search">
                    <input
                    type="text"
                    placeholder="배송 지역 검색하기"
                    class="main-search"
                    />
                    <img src="~@/static/searchicon.png" class="search-icon" />
                </div>
            </div>
            <div class="delivery-info-5">샛별택배 배송</div>
            <div class="delivery-info-6">주 6일</div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {

        }
    },
    methods: {

    }

}
</script>

<style scoped>
    h1 {
        font-size: 28px;
        text-align: center;
    }

    h2 {
        font-size: 28px;
        text-align: center;
        font-weight: normal;
    }

    .bg {
        color: #333;
        margin: auto;
        width: 100%;
    }

    .delivery {
        display: flex;
        flex-direction: column;
    }

    .delivery-info-1 {
        font-size: 0;
        background-position: center;
        background-image: url(../../static/img_delivery1.jpg);
        background-color: #eae6e2;
        height: 800px;
    }
    
    .delivery-info-2 {
        font-size: 0;
        background-position: center;
        background-image: url(../../static/img_delivery2.jpg);
        background-color: #fff;
        height: 1200px;
    }
    
    .delivery-info-3 {
        font-size: 0;
        background-position: center;
        background-image: url(../../static/img_delivery3.jpg);
        background-color: #fff;
        height: 600px;
    }
    
    .delivery-info-4 {
        padding: 100px 0;
        background-color: #d8d1df;
    }
    
    .delivery-info-5 {
        font-size: 0;
        background-position: center;
        background-image: url(../../static/img_delivery5.jpg);
        background-color: #e9e7f1;
        height: 1200px;
    }
    
    .delivery-info-6 {
        font-size: 0;
        background-position: center;
        background-image: url(../../static/img_delivery6.jpg);
        background-color: #e9e7f1;
        height: 1000px;
    }


    div.search {
        position: relative;
        margin: 30px auto 0px;
        width: 450px;
        background-color: #fff;
    }

    input.main-search {
        width: 100%;
        border: 2px solid #5f0080;
        border-radius: 4px;
        padding: 10px 12px;
        font-size: 15px;
        font-weight: 700;
        height: 50px;
    }

    input.main-search::placeholder {
        color: #5f0080;
    }

    img.search-icon {
        position: absolute;
        width: 20px;
        top: 15px;
        right: 12px;
        margin: 0;
    }
    
    img.search-icon:hover {
        cursor: pointer;
    }
</style>
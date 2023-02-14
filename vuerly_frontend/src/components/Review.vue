<template>
    <div class="component-bg">
        <div class="review-top">
            <h1>상품 후기</h1>
            <span>총 {{ models.length }}개</span>
        </div>
        <div class="review-box"  v-if="models.length > 0">
            <!-- v-for -->
            <div class="review-list" v-for="(rowData, i) in paginatedData" :key="i">
                <div class="review-name">
                  {{ rowData.memberVO.name }}님  
                </div>
                <div class="review-detail">
                    <div class="review-title">{{ rowData.productDTO.product_name }}</div>
                    <div class="review-content">{{ rowData.content }}</div>
                    <div class="photo-box" v-if="rowData.photo != null">
                        <img :src="rowData.photo" class="review-photo"/>
                    </div>
                </div>
                <div class="review-date">
                    {{ rowData.created_at }}
                </div>
            </div>
            <!-- v-for end -->
        </div>
        <div class="review-box"  v-else>
        <div class="review-list" >
                <div class="review-empt">
                상품후기가 없습니다. 
                </div>
            </div>
        </div>
        <div class="review-bot">
            <div class="pagination-btn" >
                <button type="button" class="prev-btn" :disabled="pageNum === 0"  @click="prevPage">&lt;</button>
                <button type="button" class="next-btn" :disabled="pageNum >= pageCnt - 1" @click="nextPage">&gt;</button>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    props: [ 'propsdata'],
    data() {
        return {
            models: [],
            productNum: 0,
            pageNum: 0,
        }
    },
    methods: {
        nextPage () {
            this.pageNum += 1;
        },
        prevPage () {
            this.pageNum -= 1;
        }
    },
    computed: {
        pageCnt() {
            let listLeng = this.models.length,
                page = Math.floor(listLeng/ 5)

                if (listLeng % 5 > 0) {
                    page += 1;
                }
                
            return page;
        },
        paginatedData () {
            const start = this.pageNum * 5,
                    end = start + 5;
            
            return this.models.slice(start, end);
        }
    },
    created() {
        // prop으로 부모컴포넌트로 부터 productNum 가져오기
        this.productNum = this.propsdata

        axios
        .get(
            "/review/getReviewList?product_num="
            + this.productNum
        )
        .then((response) => {
            this.models = response.data;
        })
        .catch((error) => {
            console.log(error);
        });

    },

}
</script>

<style scoped>
    .component-bg {
        color: #333;
        margin: auto;
    }

    h1 {
        font-size: 24px;
        margin: 10px 0 20px;
    }
    
    span {
        font-size: 14px;
        font-weight: 700;
    }
    
    .review-top {
        padding: 10px 0;
        border-bottom: 1px solid #333;
    }
    
    .review-box {
        padding: 0px 20px;
        font-size: 14px;
    }

    .review-list {
        display: flex;
        border-bottom: 1px solid #e7e7e8;
        padding: 20px 0;
        justify-content: center;
    }

    .review-name {
        width: 100px;
        text-align: center;
    }
    .review-detail {
        width: 680px;
        display: flex;
        flex-direction: column;
        word-break:break-all;
    }

    .review-title {
        color: #adadad;
    }

    .review-content {
        padding: 10px 0;
        white-space: pre-line;
    }
    
    .review-date {
        width: 130px;
        text-align: center;
        color: #adadad
    }

    .photo-box {
        display: flex;
    }

    .review-photo {
        width: 100px;
        height: 100px;
        margin-right: 5px;
        overflow:hidden;
    }

    .review-bot {
        display: flex;
        justify-content: center;
        padding: 20px;
    }
    .pagination-btn {
        display: flex;
        justify-content: space-between;
        width: 100px;
        font-size: 28px;
        font-weight: lighter;
    }

    .prev-btn {
        width: 45px;
        height: 45px;
        border: 1px solid #adadad;
        border-radius: 4px;
    }
    
    .prev-btn:hover {
        font-weight: 400;
        border: 1px solid #5f0080;
        color: #5f0080;
    } 
    
    .next-btn {
        width: 45px;
        height: 45px;
        border: 1px solid #adadad;
        border-radius: 4px;
    }

    .next-btn:hover {
        font-weight: 400;
        border: 1px solid #5f0080;
        color: #5f0080;
    } 
    </style>
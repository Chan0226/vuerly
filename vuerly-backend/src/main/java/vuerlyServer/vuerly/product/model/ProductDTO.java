package vuerlyServer.vuerly.product.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ProductDTO {
        @ApiModelProperty(value = "정렬순서",example = "인기순")
        private String sort;
        @ApiModelProperty(value = "카테고리",example = "스킨케어")
        private String category;
        @ApiModelProperty(value = "상품의 브랜드 번호")
        private int brand_num;
        @ApiModelProperty(value = "상품 번호")
        private int num;
        @ApiModelProperty(value = "상품명",example = "버베나 핸드 크림")
        private String product_name;
        @ApiModelProperty(value = "상품 부제목",example = "레몬 향을 머금은 시원한 보습감")
        private String subtitle;
        @ApiModelProperty(value = "상품 가격",example = "10000")
        private int price;
        @ApiModelProperty(value = "상품 재고",example = "1")
        private int stock;
        @ApiModelProperty(value = "상품 설명 이미지")
        private String desc_image;
        @ApiModelProperty(value = "상품 상세 이미지")
        private String detail_image;
        @ApiModelProperty(value = "상품 세일 가격",example = "1000")
        private int sale;
        @ApiModelProperty(value = "상품 썸네일 이미지")
        private String thumbnail;
        @ApiModelProperty(value = "상품 조회수",example = "100")
        private int readcount;
        @ApiModelProperty(value = "상품 배달 옵션",example = "1")
        private boolean shipping_option;
        @ApiModelProperty(value = "상품 등록일",example = "2023-01-17 19:55:49")
        private Timestamp createdAt;
        // 검색 키워드
        @ApiModelProperty(value = "검색 키워드",example = "핸드크림")
        private String search;
    }

package vuerlyServer.vuerly.review.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ReviewDTO {
    @ApiModelProperty(value = "리뷰 번호")
    private int num;
    @ApiModelProperty(value = "리뷰 내용")
    private String content;
    @ApiModelProperty(value = "리뷰 사진")
    private String photo;
    @ApiModelProperty(value = "상품 번호")
    private int product_num;
    @ApiModelProperty(value = "회원 번호")
    private int member_num;
    @ApiModelProperty(value = "주문 번호")
    private int order_num;
}

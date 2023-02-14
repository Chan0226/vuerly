package vuerlyServer.vuerly.review.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WritableReviewDTO {
    @ApiModelProperty(value = "상품 번호")
    private int product_num;
    @ApiModelProperty(value = "상품명",example = "버베나 핸드 크림")
    private String product_name;
    @ApiModelProperty(value = "상품 썸네일")
    private String thumbnail;
    @ApiModelProperty(value = "상품 부제목",example = "레몬 향을 머금은 시원한 보습감")
    private String subtitle;
    @ApiModelProperty(value = "주문 번호")
    private int order_num;
}

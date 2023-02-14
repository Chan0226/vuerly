package vuerlyServer.vuerly.cart.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CartDTO {
    @ApiModelProperty(value = "장바구니의 항목의 번호(같은 장바구니라도 번호다름)")
    private int idx;
    @ApiModelProperty(value = "장바구니 상품 개수")
    private int count;
    @ApiModelProperty(value = "회원의 secretKey",example = "6795760f-e37b-4e84-ae05-faab58907c21")
    private String secretKey;
    @ApiModelProperty(value = "장바구니 번호")
    private int num;
    @ApiModelProperty(value = "장바구니의 상품 번호")
    private int product_num;
    @ApiModelProperty(value = "회원 번호")
    private int member_num;
}

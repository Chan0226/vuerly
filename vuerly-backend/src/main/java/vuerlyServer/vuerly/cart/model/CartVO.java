package vuerlyServer.vuerly.cart.model;

import lombok.Data;
import vuerlyServer.vuerly.product.model.ProductVO;

@Data
public class CartVO {
    private int idx;
    // 장바구니 num
    private int num;
    // 상품 개수
    private int count;
    // 상품번호
    private int product_num;
    // 회원번호
    private int member_num;
    //  join할 product 테이블
    private ProductVO productVO;
    // join할 member 테이블

}

package vuerlyServer.vuerly.order.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import vuerlyServer.vuerly.cart.model.CartVO;
import vuerlyServer.vuerly.member.model.MemberVO;
import vuerlyServer.vuerly.product.model.ProductDTO;
import vuerlyServer.vuerly.product.model.ProductVO;

import java.sql.Timestamp;
@Data
public class OrderVO {

    // 주문 num
    private int num;
    // 주문상태
    private int stauts;
    // 사용포인트
    private int point;
    // 배송지 주소
    private String addr;
    // 주문날짜

    private Timestamp created_at;
    // 회원번호
    private int member_num;
    // join할 cart 테이블;
    private CartVO cartVO;
    // join할 product 테이블
    private ProductDTO productDTO;
    // join할 member 테이블
    private MemberVO memberVO;
}

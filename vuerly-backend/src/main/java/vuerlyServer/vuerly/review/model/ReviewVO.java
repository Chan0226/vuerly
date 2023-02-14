package vuerlyServer.vuerly.review.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import vuerlyServer.vuerly.member.model.MemberVO;
import vuerlyServer.vuerly.product.model.ProductDTO;
import vuerlyServer.vuerly.product.model.ProductVO;

import java.sql.Timestamp;

@Data
public class ReviewVO {
    private int num;
    private String content;
    private String photo;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm")
    private Timestamp created_at;
    private int product_num;
    private int member_num;
    // 조인할 product 테이블
    private ProductDTO productDTO;
    // 조인할 member 테이블
    private MemberVO memberVO;
}

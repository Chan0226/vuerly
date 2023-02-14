package vuerlyServer.vuerly.order.model;

import lombok.Data;
import vuerlyServer.vuerly.member.model.MemberVO;
import vuerlyServer.vuerly.product.model.ProductDTO;

import java.sql.Timestamp;

@Data
public class PaymentVO {
    private int num;
    private int total_price;
    private int discount_fee;
    private int delivery_fee;
    private int final_price;
    private int used_point;
    private Timestamp created_at;
    private int order_num;
}

package vuerlyServer.vuerly.order.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import vuerlyServer.vuerly.cart.model.CartVO;
import vuerlyServer.vuerly.product.model.ProductDTO;

import java.sql.Timestamp;
import java.util.List;

@Data
public class OrderDTO {
    private int num;
    private int status;
    private int point;
    private String addr;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Timestamp created_at;
    private int member_num;
    private int order_num;

    private String product_name;
    private int final_price;

    private String thumbnail;
    private String secretKey;
    private int term;

}

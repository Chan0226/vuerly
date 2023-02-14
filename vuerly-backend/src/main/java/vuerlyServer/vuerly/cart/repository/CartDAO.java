package vuerlyServer.vuerly.cart.repository;

import org.apache.ibatis.annotations.Mapper;
import vuerlyServer.vuerly.cart.model.CartDTO;
import vuerlyServer.vuerly.cart.model.CartVO;

import java.util.List;

@Mapper
public interface CartDAO {
    public List<CartVO> getCartInfo(int member_num);
    public void updateCountCart(CartDTO cartDTO);

    public void deleteCart(int idx);
    public String getMemberAddress(int num);

    public void insertCart(CartDTO cartDTO);

    public Integer getProductCount(CartDTO cartDTO);

    public void deleteAll(int member_num);
}

package vuerlyServer.vuerly.cart.service;

import vuerlyServer.vuerly.cart.model.CartDTO;
import vuerlyServer.vuerly.cart.model.CartVO;

import java.util.List;

public interface CartService {
    public List<CartVO> getCartInfo(int member_num);

    public void updateCountCart(CartDTO cartDTO);

    public void deleteCart(int idx);

    public String getMemberAddress(int num);

    public void insertCart(CartDTO cartDTO);

    public Integer getProductCount(CartDTO cartDTO);

    public void deleteAll(int member_num);
}

package vuerlyServer.vuerly.cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vuerlyServer.vuerly.cart.model.CartDTO;
import vuerlyServer.vuerly.cart.model.CartVO;
import vuerlyServer.vuerly.cart.repository.CartDAO;

import java.util.List;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private CartDAO cartDAO;
    @Override
    public List<CartVO> getCartInfo(int member_num) {
        return cartDAO.getCartInfo(member_num);
    }

    @Override
    public void updateCountCart(CartDTO cartDTO) {
        cartDAO.updateCountCart(cartDTO);
    }

    @Override
    public void deleteCart(int idx) {
        cartDAO.deleteCart(idx);
    }

    @Override
    public String getMemberAddress(int num) {
        return cartDAO.getMemberAddress(num);
    }

    @Override
    public void insertCart(CartDTO cartDTO) {
        cartDAO.insertCart(cartDTO);
    }

    @Override
    public Integer getProductCount(CartDTO cartDTO) {
        return cartDAO.getProductCount(cartDTO);
    }

    @Override
    public void deleteAll(int member_num) {
        cartDAO.deleteAll(member_num);
    }
}

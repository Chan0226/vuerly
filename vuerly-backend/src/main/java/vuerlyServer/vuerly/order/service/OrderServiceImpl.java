package vuerlyServer.vuerly.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vuerlyServer.vuerly.order.model.OrderDTO;
import vuerlyServer.vuerly.order.model.OrderProductVO;
import vuerlyServer.vuerly.order.model.OrderVO;
import vuerlyServer.vuerly.order.model.PaymentVO;
import vuerlyServer.vuerly.order.repository.OrderDAO;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderDAO orderDAO;

    @Override
    public List<OrderVO> getOrderSheet(int num) {
        return orderDAO.getOrderSheet(num);
    }

    @Override
    public void insertOrder(OrderDTO orderDTO) {
//        for (int i = 0;  i < orderDTO.getProduct_num().size(); i++){
//            orderDTO.getProduct_num().get(i).setOrderNum(orderDTO.getOrder_num());
//        }
        orderDAO.insertOrder(orderDTO);
    }

    @Override
    public void updatePoint(OrderDTO orderDTO) {
        orderDAO.updatePoint(orderDTO);
    }

    @Override
    public void insertOrderProduct(OrderProductVO orderProductVO) {
        orderDAO.insertOrderProduct(orderProductVO);
    }
    @Override
    public void insertPayment(PaymentVO paymentVO) {
        orderDAO.insertPayment(paymentVO);
    }

    @Override
    public List<OrderVO> getOrderList(OrderDTO orderDTO) {
        return orderDAO.getOrderList(orderDTO);
    }
}

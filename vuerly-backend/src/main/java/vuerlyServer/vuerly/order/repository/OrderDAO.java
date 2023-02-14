package vuerlyServer.vuerly.order.repository;

import org.apache.ibatis.annotations.Mapper;
import vuerlyServer.vuerly.order.model.OrderDTO;
import vuerlyServer.vuerly.order.model.OrderProductVO;
import vuerlyServer.vuerly.order.model.OrderVO;
import vuerlyServer.vuerly.order.model.PaymentVO;

import java.util.List;
@Mapper
public interface OrderDAO {
    public List<OrderVO> getOrderSheet(int num);
    public void insertOrder(OrderDTO orderDTO);
    public void updatePoint(OrderDTO orderDTO);
    public void insertOrderProduct(OrderProductVO orderProductVO);
    public void insertPayment(PaymentVO paymentVO);

    public List<OrderVO> getOrderList(OrderDTO orderDTO);
}

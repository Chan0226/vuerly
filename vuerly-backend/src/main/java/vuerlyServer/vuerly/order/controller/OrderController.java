package vuerlyServer.vuerly.order.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vuerlyServer.vuerly.cart.model.CartVO;
import vuerlyServer.vuerly.member.service.MemberService;
import vuerlyServer.vuerly.order.model.OrderDTO;
import vuerlyServer.vuerly.order.model.OrderProductVO;
import vuerlyServer.vuerly.order.model.OrderVO;
import vuerlyServer.vuerly.order.model.PaymentVO;
import vuerlyServer.vuerly.order.service.OrderService;

import java.util.List;

@Slf4j
@RestController
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    MemberService memberService;

    @ApiOperation(value="주문서 호출", notes="회원의 주문서를 호출한다.")
    @GetMapping("/order/getOrderSheet")
    public List<OrderVO> getOrderSheet(
            @ApiParam(value = "호출할 주문서의 회원 secretKey", example = "6795760f-e37b-4e84-ae05-faab58907c21")
            @RequestParam String secretKey) {
        int num = memberService.getUserNum(secretKey);
        return orderService.getOrderSheet(num);
    }

    @ApiOperation(value = "주문 등록", notes = "주문을 등록한다.")
    @PostMapping("/order/insertOrder")
    public void  insertOrder(OrderDTO orderDTO) {
        log.info("insert:{}", orderDTO);
        orderService.insertOrder(orderDTO);
    }

    @ApiOperation(value = "포인트 사용", notes = "사용한 포인트를 보유 적립금에서 뺀다.")
    @PatchMapping("/order/updatePoint")
    public void updatePoint(OrderDTO orderDTO) {
        log.info("update:{}", orderDTO);
        orderService.updatePoint(orderDTO);
    }

    @ApiOperation(value = "주문 상품 등록", notes = "후기 작성을 위해 주문한 상품을 등록한다.")
    @PostMapping("/order/insertOrderProduct")
    public void insertOrderProduct(OrderProductVO orderProductVO) {
        log.info("insert:{}", orderProductVO);
        orderService.insertOrderProduct(orderProductVO);
    }

    @ApiOperation(value = "결제 내역 등록", notes = "결제 내역을 등록한다.")
    @PostMapping("/order/insertPayment")
    public void insertPayment(PaymentVO paymentVO) {
        log.info("insert:{}", paymentVO);
        orderService.insertPayment(paymentVO);
    }

    @ApiOperation(value="주문내역 호출", notes="회원의 주문내역을 호출한다.")
    @GetMapping("/order/getOrderList")
    public List<OrderVO> getOrderList(OrderDTO orderDTO) {
        int member_num = memberService.getUserNum(orderDTO.getSecretKey());
        orderDTO.setMember_num(member_num);
        return orderService.getOrderList(orderDTO);
    }

}

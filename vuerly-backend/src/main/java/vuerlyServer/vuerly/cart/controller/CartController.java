package vuerlyServer.vuerly.cart.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vuerlyServer.vuerly.cart.model.CartDTO;
import vuerlyServer.vuerly.cart.model.CartVO;
import vuerlyServer.vuerly.cart.service.CartService;
import vuerlyServer.vuerly.member.service.MemberService;

import java.util.List;

@Slf4j
@RestController
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    MemberService memberService;
    @ApiOperation(value="장바구니 정보 호출", notes="회원의 장바구니 정보를 호출한다.")
    @GetMapping("/cart/getCartInfo")
    public List<CartVO> getCartInfo(
            @ApiParam(value = "장바구니 정보를 찾고자 하는 회원의 secretKey", example = "6795760f-e37b-4e84-ae05-faab58907c21")
            @RequestParam String secretKey) {
        int member_num = memberService.getUserNum(secretKey);
        return cartService.getCartInfo(member_num);
    }
    @ApiOperation(value="장바구니 상품 개수 수정", notes="장바구니에서 상품별 개수를 수정한다")
    @PatchMapping("/cart/updateCountCart")
    public void updateCountCart(CartDTO cartDTO) {
        log.info("update:{}", cartDTO);
        cartService.updateCountCart(cartDTO);
    }
    @ApiOperation(value="장바구니에서 product 삭제", notes="해당 상품을 장바구니에서 삭제한다.")
    @DeleteMapping("/cart/deleteCart")
    public void deleteCart(
            @ApiParam(value = "삭제하려는 장바구니의 idx", example = "1")
            @RequestParam int idx) {
        log.info("delete:{}", idx);
        cartService.deleteCart(idx);
    }
    @ApiOperation(value="장바구니 회원 주소 정보 호출", notes="장바구니 페이지에서 해당 고객의 주소를 반환한다.")
    @GetMapping("/cart/getMemberAddress")
    public String getMemberAddress(
            @ApiParam(value = "주소를 찾고자 하는 회원의 secretKey", example = "6795760f-e37b-4e84-ae05-faab58907c21")
            @RequestParam String secretKey) {
        int num = memberService.getUserNum(secretKey);
        return cartService.getMemberAddress(num);
    }

    @ApiOperation(value="장바구니 담기", notes = "장바구니에 product를 담는다")
    @PostMapping("/cart/insertCart")
    public void insertCart(CartDTO cartDTO) {
        int num = memberService.getUserNum(cartDTO.getSecretKey());
        cartDTO.setMember_num(num);
        cartDTO.setNum(num);
        log.info("insert:{}", cartDTO);
        cartService.insertCart(cartDTO);
    }

    @ApiOperation(value="장바구니에 담겨있는 상품의 수량", notes="장바구니 담기 할 때 이미 담겨져 있는 상품에 대한 처리")
    @GetMapping("/cart/getProductCount")
    public Integer getProductCount(CartDTO cartDTO) {
        // 회원번호 받기
        int member_num = memberService.getUserNum(cartDTO.getSecretKey());
        cartDTO.setMember_num(member_num);
        return cartService.getProductCount(cartDTO);
    }

    @ApiOperation(value="장바구니에서 product 전체 삭제", notes="주문완료 후 전체 상품을 장바구니에서 삭제한다.")
    @DeleteMapping("/cart/deleteAllCart")
    public void deleteAll(
            @ApiParam(value = "장바구니 내역을 삭제할 회원의 secretKey", example = "6795760f-e37b-4e84-ae05-faab58907c21")
            @RequestParam String secretKey) {
        int member_num = memberService.getUserNum(secretKey);
        log.info("delete:{}", member_num);
        cartService.deleteAll(member_num);
    }
}

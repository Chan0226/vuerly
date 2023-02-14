package vuerlyServer.vuerly.review.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vuerlyServer.vuerly.member.service.MemberService;
import vuerlyServer.vuerly.product.model.ProductDTO;
import vuerlyServer.vuerly.review.model.ReviewDTO;
import vuerlyServer.vuerly.review.model.ReviewVO;
import vuerlyServer.vuerly.review.model.WritableReviewDTO;
import vuerlyServer.vuerly.review.service.ReviewService;

import java.util.List;

@Slf4j
@RestController
public class ReviewController {
    @Autowired
    ReviewService reviewService;
    @Autowired
    MemberService memberService;

    @ApiOperation(value = "리뷰 등록", notes = "리뷰를 등록하는 api")
    @PostMapping("/review/registerReview")
    public void inertReview(ReviewDTO reviewDTO) {
        log.info("리뷰 내용 :{}",reviewDTO);
        reviewService.inertReview(reviewDTO);
        reviewService.updateReviewStatus(reviewDTO.getOrder_num());
    }
    @ApiOperation(value = "리뷰 데이터 호출", notes = "회원의 리뷰를 조회하는 api")
    @GetMapping("/review/getReview")
    public List<ReviewVO> inertReview(
            @ApiParam(value = "리뷰 데이터를 찾고자 하는 회원의 secretKey", example = "6795760f-e37b-4e84-ae05-faab58907c21")
            @RequestParam String secretKey) {
        int member_num = memberService.getUserNum(secretKey);
        log.info("정보 호출할 회원 num :{}",member_num);
        return reviewService.getReviewInfo(member_num);
    }
    @ApiOperation(value = "리뷰 수정", notes = "회원이 작성한 리뷰를 수정하는 api")
    @PatchMapping("/review/updateReview")
    public void updateReview(ReviewDTO reviewDTO) {
        log.info("수정할 리뷰 정보 num :{}",reviewDTO);
        reviewService.updateReview(reviewDTO);
    }
    @ApiOperation(value = "특정 리뷰 데이터 호출", notes = "수정할 특정 리뷰 데이터를 호출하는 api")
    @GetMapping("/review/getSelectReview")
    public ReviewVO getSelectReview(
            @ApiParam(value = "호출할 특정 리뷰 번호", example = "1")
            @RequestParam int num) {
        log.info("호출할 리뷰 정보 num :{}",num);
        return reviewService.getSelectReview(num);
    }

    @ApiOperation(value = "상품 리뷰 목록 데이터 호출", notes = "상품 리뷰 목록 데이터를 호출하는 api")
    @GetMapping("/review/getReviewList")
    public List<ReviewVO> getReviewList(
            @ApiParam(value = "상품 번호", example = "1")
            @RequestParam int product_num) {
        log.info("호출할 poduct num, page :{}",product_num);
        return reviewService.getReviewList(product_num);
    }
    @ApiOperation(value = "회원의 리뷰 작성 가능한 상품 호출", notes = "마이페이지에서 리뷰 가능한 상품을 호출하는 api")
    @GetMapping("/review/getWritableReview")
    public List<WritableReviewDTO> getWritableReview(
            @ApiParam(value = "작성 가능 리뷰를 확인할 회원의 secretKey", example = "6795760f-e37b-4e84-ae05-faab58907c21")
            @RequestParam String secretKey) {
        int member_num = memberService.getUserNum(secretKey);
        log.info("호출할 member num:{}",member_num);
        return reviewService.getWritableReview(member_num);
    }
}

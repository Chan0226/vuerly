package vuerlyServer.vuerly.review.service;

import vuerlyServer.vuerly.product.model.ProductDTO;
import vuerlyServer.vuerly.review.model.ReviewDTO;
import vuerlyServer.vuerly.review.model.ReviewVO;
import vuerlyServer.vuerly.review.model.WritableReviewDTO;

import java.util.List;

public interface ReviewService {
    public List<ReviewVO> getReviewInfo(int member_num);
    public void inertReview(ReviewDTO reviewDTO);
    public void updateReview(ReviewDTO reviewDTO);
    public ReviewVO getSelectReview(int num);
    public List<ReviewVO> getReviewList(int product_num);
    List<WritableReviewDTO> getWritableReview(int member_num);
    public void updateReviewStatus(int order_num);
}

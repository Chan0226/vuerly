package vuerlyServer.vuerly.review.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vuerlyServer.vuerly.product.model.ProductDTO;
import vuerlyServer.vuerly.review.model.ReviewDTO;
import vuerlyServer.vuerly.review.model.ReviewVO;
import vuerlyServer.vuerly.review.model.WritableReviewDTO;
import vuerlyServer.vuerly.review.repository.ReviewDAO;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{
    @Autowired
    private ReviewDAO reviewDAO;

    @Override
    public List<ReviewVO> getReviewInfo(int member_num) {
        return reviewDAO.getReviewInfo(member_num);
    }

    @Override
    public void inertReview(ReviewDTO reviewDTO) {
        reviewDAO.insertReview(reviewDTO);
    }

    @Override
    public void updateReview(ReviewDTO reviewDTO) {
        reviewDAO.updateReview(reviewDTO);
    }

    @Override
    public ReviewVO getSelectReview(int num) {
        return reviewDAO.getSelectReview(num);
    }

    @Override
    public List<ReviewVO> getReviewList(int product_num) {
        return reviewDAO.getReviewList(product_num);
    }

    @Override
    public List<WritableReviewDTO> getWritableReview(int member_num) {
        return reviewDAO.getWritableReview(member_num);
    }

    @Override
    public void updateReviewStatus(int order_num) {
        reviewDAO.updateReviewStatus(order_num);
    }
}

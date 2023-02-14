package vuerlyServer.vuerly.review.repository;

import org.apache.ibatis.annotations.Mapper;
import vuerlyServer.vuerly.product.model.ProductDTO;
import vuerlyServer.vuerly.review.model.ReviewDTO;
import vuerlyServer.vuerly.review.model.ReviewVO;
import vuerlyServer.vuerly.review.model.WritableReviewDTO;

import java.util.List;

@Mapper
public interface ReviewDAO {
    public List<ReviewVO> getReviewInfo(int member_num);
    public void insertReview(ReviewDTO reviewDTO);

    public void updateReview(ReviewDTO reviewDTO);

    public ReviewVO getSelectReview(int num);

    List<ReviewVO> getReviewList(int product_num);
    List<WritableReviewDTO> getWritableReview(int member_num);
    public void updateReviewStatus(int order_num);
}

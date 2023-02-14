package vuerlyServer.vuerly.product.repository;

import org.apache.ibatis.annotations.Mapper;
import vuerlyServer.vuerly.product.model.ProductDTO;
import vuerlyServer.vuerly.product.model.ProductVO;

import java.util.List;
@Mapper
public interface ProductDAO {
//    public List<ProductDTO> getAllProduct(String category,String sort);
//    public List<ProductDTO> getAllProduct(HashMap<String,Object> map);
    public List<ProductDTO> getAllProduct(ProductDTO productDTO);

    // readcount 업데이트
    public void updateReadCount(int num);

    public ProductVO getProductDetail(int num);

    public List<ProductDTO> unifiedSearch(ProductDTO productDTO);

    public List<ProductDTO> getCateProduct(ProductDTO productDTO);

    public void addReadcount(int num);

    public List<ProductDTO> getEventProduct(ProductDTO productDTO);
}

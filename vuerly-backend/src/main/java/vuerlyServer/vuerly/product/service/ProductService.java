package vuerlyServer.vuerly.product.service;

import vuerlyServer.vuerly.product.model.ProductDTO;
import vuerlyServer.vuerly.product.model.ProductVO;

import java.util.List;

public interface ProductService {
    public List<ProductDTO> getAllProduct(ProductDTO productDTO);
    public ProductVO getProductDetail(int num);
    public List<ProductDTO> unifiedSearch(ProductDTO productDTO);

    public List<ProductDTO> getCateProduct(ProductDTO productDTO);

    public void addReadcount(int num);

    public List<ProductDTO> getEventProduct(ProductDTO productDTO);
}

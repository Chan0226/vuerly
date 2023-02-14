package vuerlyServer.vuerly.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vuerlyServer.vuerly.product.model.ProductDTO;
import vuerlyServer.vuerly.product.model.ProductVO;
import vuerlyServer.vuerly.product.repository.ProductDAO;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductDAO productDAO;
//    @Override
//    public List<ProductDTO> getAllProduct(String category,String sort) {
//        return productDAO.getAllProduct(category,sort);
//    }
    @Override
    public List<ProductDTO> getAllProduct(ProductDTO productDTO) {
        return productDAO.getAllProduct(productDTO);
    }

    @Override
    public ProductVO getProductDetail(int num) {
        return productDAO.getProductDetail(num);
    }

    @Override
    public List<ProductDTO> unifiedSearch(ProductDTO productDTO) {
        return productDAO.unifiedSearch(productDTO);
    }

    @Override
    public List<ProductDTO> getCateProduct(ProductDTO productDTO) {
        return productDAO.getCateProduct(productDTO);
    }

    @Override
    public void addReadcount(int num) {
        productDAO.addReadcount(num);
    }

    @Override
    public List<ProductDTO> getEventProduct(ProductDTO productDTO) {
        return productDAO.getEventProduct(productDTO);
    }
}

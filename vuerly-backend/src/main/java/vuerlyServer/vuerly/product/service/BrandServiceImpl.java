package vuerlyServer.vuerly.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vuerlyServer.vuerly.product.model.BrandDTO;
import vuerlyServer.vuerly.product.model.ProductDTO;
import vuerlyServer.vuerly.product.repository.BrandDAO;

import java.util.List;
@Service
public class BrandServiceImpl implements BrandService{

    @Autowired
    private BrandDAO brandDAO;
    @Override
    public List<BrandDTO> selectBrandInfo() {
        return brandDAO.selectBrandInfo();
    }

    @Override
    public List<BrandDTO> getBrandProduct(BrandDTO brandDTO) {
        return brandDAO.getBrandProduct(brandDTO);
    }

    @Override
    public BrandDTO getBrand(BrandDTO brandDTO) {
        return brandDAO.getBrand(brandDTO);
    }
}

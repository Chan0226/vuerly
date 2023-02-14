package vuerlyServer.vuerly.product.service;

import vuerlyServer.vuerly.product.model.BrandDTO;
import vuerlyServer.vuerly.product.model.ProductDTO;

import java.util.List;

public interface BrandService {
    public List<BrandDTO> selectBrandInfo();

    public List<BrandDTO> getBrandProduct(BrandDTO brandDTO);

    public BrandDTO getBrand(BrandDTO brandDTO);
}

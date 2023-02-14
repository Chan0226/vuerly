package vuerlyServer.vuerly.product.repository;

import org.apache.ibatis.annotations.Mapper;
import vuerlyServer.vuerly.product.model.BrandDTO;
import vuerlyServer.vuerly.product.model.ProductDTO;

import java.util.List;
@Mapper
public interface BrandDAO {
    public List<BrandDTO> selectBrandInfo();

    public List<BrandDTO> getBrandProduct(BrandDTO brandDTO);

    public BrandDTO getBrand(BrandDTO brandDTO);
}

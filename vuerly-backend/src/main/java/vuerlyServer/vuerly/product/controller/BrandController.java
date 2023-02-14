package vuerlyServer.vuerly.product.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vuerlyServer.vuerly.product.model.BrandDTO;
import vuerlyServer.vuerly.product.model.ProductDTO;
import vuerlyServer.vuerly.product.service.BrandService;

import java.util.List;

@RestController
public class BrandController {
    @Autowired
    BrandService brandService;
    @ApiOperation(value="브랜드 목록 호출", notes="브랜드의 목록을 호출한다.")
    @GetMapping("/brand/brandList")
    public List<BrandDTO> brandList() {
        return brandService.selectBrandInfo();
    }

    @ApiOperation(value="브랜드 호출", notes="해당 브랜드를 호출한다.")
    @GetMapping("/brand/getBrand")
    public BrandDTO getBrand(BrandDTO brandDTO) {
        return brandService.getBrand(brandDTO);
    }

    @ApiOperation(value="브랜드 별 상품 호출", notes="해당하는 브랜드의 상품을 호출한다.")
    @GetMapping("/brand/getBrandProduct")
    public List<BrandDTO> getBrandProduct(BrandDTO brandDTO) {
        return brandService.getBrandProduct(brandDTO);
    }
}

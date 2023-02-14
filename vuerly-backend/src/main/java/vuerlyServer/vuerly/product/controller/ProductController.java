package vuerlyServer.vuerly.product.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vuerlyServer.vuerly.product.model.ProductDTO;
import vuerlyServer.vuerly.product.model.ProductVO;
import vuerlyServer.vuerly.product.service.ProductService;

import java.util.List;

@Slf4j
@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @ApiOperation(value="조건에 따른 product 정보 호출", notes="카테고리(스킨케어,메이크업 등)와 정렬순서(인기순,가격순 등)를 입력해 해당하는 product 정보를 호출한다.")
    @GetMapping("/product/getAllProduct")
//    public List<ProductDTO> getAllProduct(@RequestParam(required = false) String category, @RequestParam String sort){
    public List<ProductDTO> getAllProduct(ProductDTO productDTO){
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("category", category);
//        map.put("sort", sort);
//
//        log.info("category :{}",category);
        log.info("sort :{}",productDTO);
        return productService.getAllProduct(productDTO);
    }

    @ApiOperation(value="카테고리에 따른 product 정보 호출", notes="각 브랜드와 정렬순서(인기순,가격순 등)를 입력해 해당하는 product 정보를 호출한다.")
    @GetMapping("/product/getCateProduct")
    public List<ProductDTO> getCateProduct(ProductDTO productDTO) {
        log.info("sort :{}",productDTO);
        return productService.getCateProduct(productDTO);
    }

    @ApiOperation(value="특가/혜택 상품 product 정보 호출", notes="세일이 가장 큰 순으로 product 정보를 호출한다.")
    @GetMapping("/product/getEventProduct")
    public List<ProductDTO> getEventProduct(ProductDTO productDTO) {
        log.info("sort :{}",productDTO);
        return productService.getEventProduct(productDTO);
    }


    @ApiOperation(value="product 상세정보 호출", notes="product 상세 페이지에서 정보를 호출한다.")
    @GetMapping("/product/getProductDetail")
    public ProductVO getProductDetail(
            @ApiParam(value = "상세 정보를 보고자 하는 product의 num")
            @RequestParam int num) {
        return productService.getProductDetail(num);
    }

    @ApiOperation(value="readcount 증가", notes = "상세페이지에 들어갈 시, product의 readcount 증가")
    @PatchMapping("/product/addReadcount")
    public void addReadcount(
            @ApiParam(value = "조회수를 증가시킬 product의 num")
            @RequestParam int num) { productService.addReadcount(num); }

    @ApiOperation(value="통합검색", notes="키워드를 받아 상품명,카테고리,브랜드에서 해당하는 상품 반환한다")
    @GetMapping("/product/unifiedSearch")
    public List<ProductDTO> unifiedSearch(ProductDTO productDTO){
        return productService.unifiedSearch(productDTO);
    }

}

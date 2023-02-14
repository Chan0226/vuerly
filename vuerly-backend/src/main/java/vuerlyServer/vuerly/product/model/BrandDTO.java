package vuerlyServer.vuerly.product.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BrandDTO {

    @ApiModelProperty(value = "정렬순서",example = "인기순")
    private String sort;
    @ApiModelProperty(value = "카테고리",example = "스킨케어")
    private String category;
    @ApiModelProperty(value = "브랜드번호")
    private int num;
    @ApiModelProperty(value = "브랜드 명",example = "토니모리")
    private String brand;
    @ApiModelProperty(value = "브랜드 로고 사진")
    private String brand_logo;
    @ApiModelProperty(value = "브랜드 설명")
    private String brand_desc;
}

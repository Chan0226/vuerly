package vuerlyServer.vuerly.qna.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FaqDTO {
    @ApiModelProperty(value = "자주묻는 질문 num")
    private int num;
    @ApiModelProperty(value = "자주묻는 질문 제목")
    private String title;
    @ApiModelProperty(value = "자주묻는 질문 답변")
    private String answer;
    @ApiModelProperty(value = "자주묻는 질문 카테고리",example = "주문/결제")
    private String category;

}

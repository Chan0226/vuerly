package vuerlyServer.vuerly.qna.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class QnADTO {
    @ApiModelProperty(value = "1:1문의 num")
    private int num;
    @ApiModelProperty(value = "1:1문의 제목")
    private String title;
    @ApiModelProperty(value = "1:1문의 내용")
    private String content;
    @ApiModelProperty(value = "1:1문의 작성일")
    private Timestamp created_at;
    @ApiModelProperty(value = "1:1문의 답변상태")
    private int status;
    @ApiModelProperty(value = "1:1문의 유형",example = "상품문의")
    private String qnaType;
}

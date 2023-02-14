package vuerlyServer.vuerly.qna.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;
@Data
public class AnnouncementDTO {
    @ApiModelProperty(value = "공지사항 num")
    private int num;
    @ApiModelProperty(value = "공지사항 제목")
    private String title;
    @ApiModelProperty(value = "공지사항 작성자",example = "뷰얼리")
    private String writer;
    @ApiModelProperty(value = "공지사항 내용")
    private String content;
    @ApiModelProperty(value = "공지사항 작성일",example = "2023-01-17 19:55:49")
    private Timestamp created_at;
}

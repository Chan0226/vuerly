package vuerlyServer.vuerly.qna.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class QnAVO {
    private int num;
    private String title;
    private String content;
    private Timestamp created_at;
    private int status;
    private int member_num;
    private int qnatype_num;
    private String secretKey; // 추후 DTO로 수정
}

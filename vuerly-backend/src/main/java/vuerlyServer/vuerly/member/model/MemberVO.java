package vuerlyServer.vuerly.member.model;

import lombok.Data;
import vuerlyServer.vuerly.product.model.ProductVO;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class MemberVO {
    private int num;
    private String id;
    private String password;
    private String name;
    private String email;
    private Date birth;
    private int sex;
    private String addr;
    private int point;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}

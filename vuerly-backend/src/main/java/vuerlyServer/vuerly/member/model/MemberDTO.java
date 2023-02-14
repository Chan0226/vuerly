package vuerlyServer.vuerly.member.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Data
public class MemberDTO {
    @ApiModelProperty(value = "회원번호")
    private int num;
    @ApiModelProperty(value = "회원 아이디",example = "k123")
    private String id;
    @ApiModelProperty(value = "회원의 보유포인트",example = "1000")
    private int point;
    @ApiModelProperty(value = "회원 비밀번호",example = "$2a$10$K3LfFV.Ve9yBl2hdafDr1OOFbNjNbDHhqAC8gSx8uXWOflMVXr.Ku")
    private String password;
    @ApiModelProperty(value = "회원 이름",example = "홍길동")
    private String name;
    @ApiModelProperty(value = "회원의 email",example = "1234@naver.com")
    private String email;
    @ApiModelProperty(value = "회원 생일",example = "1999-01-01")
    private Date birth;
    @ApiModelProperty(value = "회원 성별",example = "0")
    private int sex;
    @ApiModelProperty(value = "회원 주소",example = "서울시 영등포구 당산동")
    private String addr;
    @ApiModelProperty(value = "회원 핸드폰 번호",example = "01011112222")
    private String phoneNum;
    @ApiModelProperty(value = "회원의 secretKey",example = "6795760f-e37b-4e84-ae05-faab58907c21")
    private String secretKey;
    @ApiModelProperty(value = "로그인시 변경할 새로운 secretKey",example = "6795760f-e37b-4e84-ae05-faab58907c21")
    private String newSecretKey;
}

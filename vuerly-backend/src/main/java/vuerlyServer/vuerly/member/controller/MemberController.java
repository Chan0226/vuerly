package vuerlyServer.vuerly.member.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import vuerlyServer.vuerly.cart.model.CartDTO;
import vuerlyServer.vuerly.member.model.MemberDTO;
import vuerlyServer.vuerly.member.model.MemberVO;
import vuerlyServer.vuerly.member.service.EmailService;
import vuerlyServer.vuerly.member.service.MemberService;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Slf4j
@RestController
public class MemberController {
    @Autowired
    MemberService memberService;
    @Autowired
    private PasswordEncoder pwEncoder;
    @Autowired
    private EmailService emailService;
    @ApiOperation(value="회원가입", notes="회원가입을 처리하는 api")
    @PostMapping("/member/memberJoin")
    public void memberJoin(@RequestBody MemberDTO memberDTO) {
        log.info("create:{}", memberDTO);
        // 시크릿 키 생성
        UUID secretKey = UUID.randomUUID();
        String rawPw = "";
        String encodePw = "";

        rawPw = memberDTO.getPassword();
        encodePw = pwEncoder.encode(rawPw);
        memberDTO.setPassword(encodePw);
        // secretKey string형으로 변환 후 대입
        memberDTO.setSecretKey(secretKey.toString());
        memberService.memberJoin(memberDTO);
    }

    @ApiOperation(value="아이디 중복체크", notes="회원가입에서 중복아이디인지 체크하는 api")
    @GetMapping("/member/idCheck")
    public int idCheck(
            @ApiParam(value = "중복체크하고자 하는 id", example = "k123")
            @RequestParam String id) {
        log.info("id:{}", id);
        return memberService.idCheck(id);
    }

    @ApiOperation(value="이메일 중복체크", notes="회원가입에서 중복이메일인지 체크하는 api")
    @GetMapping("/member/checkEmail")
    public int checkEmail(
            @ApiParam(value = "중복체크하고자 하는 email", example = "12345@naver.com")
            @RequestParam String email) {
        log.info("email:{}", email);
        return memberService.checkEmail(email);
    }
    @ApiOperation(value="로그인", notes="로그인 성공시 회원정보를 호출하는 api")
    @PostMapping("/member/login")
    public String getMember(@RequestBody MemberDTO memberDTO) {
        log.info("check:{}", memberDTO);
        UUID newSecretKey = UUID.randomUUID();
        String rawPw = "";
        String encodePw = "";
        // 업데이트할 새로운 secretKey

        MemberDTO ldto = memberService.getMember(memberDTO);  // 제출한 아이디와 일치하는 아이디 여부 확인

        if(ldto != null){                       //일치하는 아이디 존재시
            rawPw = memberDTO.getPassword();    // 사용자가 제출한 비밀번호
            encodePw = ldto.getPassword();      // 데이터베이스에 인코딩된 비밀번호

            if(pwEncoder.matches(rawPw,encodePw)==true){    // 비밀번호 일치
                ldto.setPassword("");                       // 인코딩된 비밀번호 정보 지움
                memberDTO.setNewSecretKey(newSecretKey.toString());
                memberService.updateSecretKey(memberDTO); //새로운 secretkey 업데이트
                return newSecretKey.toString();
            }else{                                          // 비밀번호 일치하지 않음
                return "-1";
            }
        }
        else{                                               // 아이디 일치하지 않음
            return "0";
        }
    }
    @ApiOperation(value="수정할 회원정보 호출", notes="회원 수정 이전에 해당 회원의 정보를 불러오는 api")
    @GetMapping("/member/updateMemberInfo")
    public MemberDTO getUpdateMemberInfo(
            @ApiParam(value = "수정할 회원의 secretKey", example = "6795760f-e37b-4e84-ae05-faab58907c21")
            @RequestParam String secretKey) {
        int num = memberService.getUserNum(secretKey);
        log.info("수정할 회원 num:{}", num);
        return memberService.getUpdateMemberInfo(num);
    }

    @ApiOperation(value="회원정보 수정", notes="회원정보를 수정하는 api")
    @PatchMapping("/member/updateMember")
    public void updateMember(@RequestBody MemberDTO memberDTO) {
        log.info("member:{}", memberDTO);
        String rawPw = "";
        String encodePw = "";

        rawPw = memberDTO.getPassword();
        encodePw = pwEncoder.encode(rawPw);
        memberDTO.setPassword(encodePw);
        memberService.updateMember(memberDTO);
    }
    @ApiOperation(value="회원 삭제", notes="회원정보를 삭제하는 api")
    @DeleteMapping("/member/deleteMember")
    public void deleteMember(
            @ApiParam(value = "삭제할 회원의 secretKey", example = "6795760f-e37b-4e84-ae05-faab58907c21")
            @RequestParam String secretKey) {
        int num = memberService.getUserNum(secretKey);
        log.info("id:{}", num);
        memberService.deleteMember(num);
    }
    @ApiOperation(value="회원 이름 확인", notes="아이디 찾기 시에 회원이 맞는지 이름을 확인하는 api")
    @PostMapping("/member/checkMemberPassword")
    public int checkMemberPassword(@RequestBody MemberDTO memberDTO) {
        log.info("check:{}", memberDTO);

        String rawPw = "";
        String encodePw = "";

        MemberDTO ldto = memberService.getMember(memberDTO);  // 제출한 아이디와 일치하는 객체 불러오기

        rawPw = memberDTO.getPassword();    // 사용자가 제출한 비밀번호
        encodePw = ldto.getPassword();      // 데이터베이스에 인코딩된 비밀번호

        if(pwEncoder.matches(rawPw,encodePw)==true){    // 비밀번호 일치
            return 0;
        }else{                                          // 비밀번호 일치하지 않음
            return -1;
        }
    }

    @ApiOperation(value="회원 이름 확인", notes="아이디 찾기 시에 회원이 맞는지 이름을 확인하는 api")
    @GetMapping("/member/checkName")
    public int checkPw(
            @ApiParam(value = "확인할 회원의 이름", example = "홍길동")
            @RequestParam String name) {
        log.info("name:{}", name);
        return memberService.checkName(name);
    }
    @ApiOperation(value="아이디 찾기", notes="인증 완료 이후 아이디를 찾는 api")
    @GetMapping("/member/getId")
    @ResponseBody
    public String getId(MemberDTO memberDTO) {
        log.info("아이디 찾기:{}", memberDTO);
        return memberService.getId(memberDTO);
    }

    @ApiOperation(value="이메일 인증", notes="아이디, 비밃번호를 찾을 때, 인증메일을 보내는 api")
    @PostMapping("member/mailConfirm")
    public String mailConfirm(
            @ApiParam(value = "인증메일을 보낼 email", example = "1234@naver.com")
            @RequestParam String email) throws Exception {
        String code = emailService.sendSimpleMessage(email);
        log.info("인증코드 : " + code);
        return code;
    }

    @ApiOperation(value="회원 아이디,이메일 확인", notes="비밀번호 초기화 이전에 아이디,이메일 여부 확인하는 api")
    @GetMapping("/member/checkIdEmail")
    @ResponseBody
    public int checkIdEmail(MemberDTO memberDTO) {
        log.info("memberDTO:{}", memberDTO);
        return memberService.checkIdEmail(memberDTO);
    }
    @ApiOperation(value="회원 아이디,전화번호 확인", notes="비밀번호 초기화 이전에 아이디,전화번호 여부 확인하는 api")
    @GetMapping("/member/checkIdPhone")
    @ResponseBody
    public int checkIdPhone(MemberDTO memberDTO) {
        log.info("memberDTO:{}", memberDTO);
        return memberService.checkIdPhone(memberDTO);
    }

    @ApiOperation(value="비밀번호 초기화", notes="비밀번호 변경하는 api")
    @PatchMapping("/member/updatePwd")
    public void updatePwd(@RequestBody MemberDTO memberDTO) {
        log.info("member:{}", memberDTO);
        String rawPw = "";
        String encodePw = "";

        rawPw = memberDTO.getPassword();
        encodePw = pwEncoder.encode(rawPw);
        memberDTO.setPassword(encodePw);
        memberService.updatePwd(memberDTO);
    }

    @ApiOperation(value="마이페이지 회원 정보 조회", notes="마이페이지에서 회원 정보를 조회하기 위한 api")
    @GetMapping("/member/getMemberInfo")
    public MemberDTO getMemberInfo(
            @ApiParam(value = "조회할 회원의 secretKey", example = "6795760f-e37b-4e84-ae05-faab58907c21")
            @RequestParam String secretKey) {
        int num = memberService.getUserNum(secretKey);
        return memberService.getMemberInfo(num);
    }

    @ApiOperation(value="회원 배송지 수정", notes="회원의 배송지를 수정하는 api")
    @PatchMapping("/member/updateAddress")
    public void updateAddress(@RequestBody MemberDTO memberDTO) {
        log.info("member:{}", memberDTO);
        memberService.updateAddress(memberDTO);
    }

    @ApiOperation(value="회원 번호 호출", notes="secretKey로 회원정보를 불러오는 api")
    @GetMapping("/member/getUserNum")
    public int getUserNum(
            @ApiParam(value = "회원 번호를 받아올 회원의 secretKey", example = "6795760f-e37b-4e84-ae05-faab58907c21")
            @RequestParam String secretKey) {
        log.info("secretKey:{}", secretKey);
        return memberService.getUserNum(secretKey);
    }
}

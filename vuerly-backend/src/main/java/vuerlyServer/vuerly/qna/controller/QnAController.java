package vuerlyServer.vuerly.qna.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vuerlyServer.vuerly.member.service.MemberService;
import vuerlyServer.vuerly.product.model.ProductDTO;
import vuerlyServer.vuerly.qna.model.AnnouncementDTO;
import vuerlyServer.vuerly.qna.model.FaqDTO;
import vuerlyServer.vuerly.qna.model.QnADTO;
import vuerlyServer.vuerly.qna.model.QnAVO;
import vuerlyServer.vuerly.qna.service.QnAService;

import java.util.List;

@Slf4j
@RestController
public class QnAController {
    @Autowired
    QnAService qnAService;
    @Autowired
    MemberService memberService;
    @ApiOperation(value="모든 공지사항 호출", notes="모든 공지사항을 num 순서대로 호출")
    @GetMapping("/qna/getAllProduct")
    public List<AnnouncementDTO> getAllProduct(){
        return qnAService.getAllAnnouncement();
    }
    @ApiOperation(value="해당번호 공지사항 호출", notes="번호와 일치하는 공지사항 정보 호출")
    @GetMapping("/qna/getAnnouncementByNum")
    public AnnouncementDTO getAnnouncementByNum(
            @ApiParam(value = "상세 내용을 확인할 공지사항의 번호")
            @RequestParam int num){
        return qnAService.getAnnouncementByNum(num);
    }
    @ApiOperation(value="자주묻는 질문 전부 호출", notes="자주묻는 질문 num 순서대로 호출")
    @GetMapping("/qna/getAllFaq")
    public List<FaqDTO> getAllFaq(){
        return qnAService.getAllFaq();
    }
    @ApiOperation(value="1:1문의 호출", notes="회원의 1:1문의 내역을 호출")
    @GetMapping("/qna/getMemberInquiry")
    public List<QnADTO> getMemberInquiry(
            @ApiParam(value = "1:1문의 정보를 찾고자 하는 회원의 secretKey", example = "6795760f-e37b-4e84-ae05-faab58907c21")
            @RequestParam String secretKey){
        int member_num = memberService.getUserNum(secretKey);
        return qnAService.getMemberInquiry(member_num);
    }
    @ApiOperation(value="1:1문의 작성", notes="회원의 1:1문의 내역을 호출")
    @PostMapping("/qna/insertInquiry")
    public void insertInquiry(@RequestBody QnAVO qnAVO){
        int member_num = memberService.getUserNum(qnAVO.getSecretKey());
        qnAVO.setMember_num(member_num);
        log.info("check:{}", qnAVO);
        qnAService.insertInquiry(qnAVO);
    }
    @ApiOperation(value="1:1문의 수정", notes="회원의 1:1문의 수정")
    @PatchMapping("/qna/updateInquiry")
    public void updateInquiry(@RequestBody QnAVO qnAVO){
        log.info("check:{}", qnAVO);
        qnAService.updateInquiry(qnAVO);
    }
    @ApiOperation(value="1:1문의 삭제", notes="회원의 1:1문의 삭제")
    @DeleteMapping("/qna/deleteInquiry")
    public void deleteInquiry(
            @ApiParam(value = "삭제할 1:1문의 번호")
            @RequestParam int num){
        log.info("삭제할 qna번호:{}", num);
        qnAService.deleteInquiry(num);
    }
    @ApiOperation(value="수정할 1:1문의 호출", notes="수정할 회원의 1:1문의 내역을 호출")
    @GetMapping("/qna/getUpdateInquiryInfo")
    public List<QnAVO> getUpdateInquiryInfo(
            @ApiParam(value = "수정할 1:1문의 내용 호출")
            @RequestParam int num){
        return qnAService.getUpdateInquiryInfo(num);
    }
}

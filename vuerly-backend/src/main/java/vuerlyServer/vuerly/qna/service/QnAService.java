package vuerlyServer.vuerly.qna.service;

import vuerlyServer.vuerly.qna.model.AnnouncementDTO;
import vuerlyServer.vuerly.qna.model.FaqDTO;
import vuerlyServer.vuerly.qna.model.QnADTO;
import vuerlyServer.vuerly.qna.model.QnAVO;

import java.util.List;

public interface QnAService {
    public List<AnnouncementDTO> getAllAnnouncement();
    public AnnouncementDTO getAnnouncementByNum(int num);
    public List<FaqDTO> getAllFaq();
    public List<QnADTO> getMemberInquiry(int member_num);
    public void insertInquiry(QnAVO qnAVO);
    public void updateInquiry(QnAVO qnAVO);
    public void deleteInquiry(int num);
    public List<QnAVO> getUpdateInquiryInfo(int num);
}

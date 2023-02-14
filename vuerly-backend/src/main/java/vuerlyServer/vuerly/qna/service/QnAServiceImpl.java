package vuerlyServer.vuerly.qna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vuerlyServer.vuerly.qna.model.AnnouncementDTO;
import vuerlyServer.vuerly.qna.model.FaqDTO;
import vuerlyServer.vuerly.qna.model.QnADTO;
import vuerlyServer.vuerly.qna.model.QnAVO;
import vuerlyServer.vuerly.qna.repository.QnADAO;

import java.util.List;

@Service
public class QnAServiceImpl implements QnAService{
    @Autowired
    QnADAO qnADAO;
    @Override
    public List<AnnouncementDTO> getAllAnnouncement() {
        return qnADAO.getAllAnnouncement();
    }
    @Override
    public AnnouncementDTO getAnnouncementByNum(int num) {
        return qnADAO.getAnnouncementByNum(num);
    }
    @Override
    public List<FaqDTO> getAllFaq() {
        return qnADAO.getAllFaq();
    }

    @Override
    public List<QnADTO> getMemberInquiry(int member_num) {
        return qnADAO.getMemberInquiry(member_num);
    }

    @Override
    public void insertInquiry(QnAVO qnAVO) {
        qnADAO.insertInquiry(qnAVO);
    }

    @Override
    public void updateInquiry(QnAVO qnAVO) {
        qnADAO.updateInquiry(qnAVO);
    }

    @Override
    public void deleteInquiry(int num) {
        qnADAO.deleteInquiry(num);
    }

    @Override
    public List<QnAVO> getUpdateInquiryInfo(int num) {
        return qnADAO.getUpdateInquiryInfo(num);
    }
}

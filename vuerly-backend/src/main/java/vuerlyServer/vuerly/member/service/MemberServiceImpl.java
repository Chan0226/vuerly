package vuerlyServer.vuerly.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vuerlyServer.vuerly.member.model.MemberDTO;
import vuerlyServer.vuerly.member.model.MemberVO;
import vuerlyServer.vuerly.member.repository.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService{
    @Autowired
    private MemberDAO memberDAO;
    @Override
    public void memberJoin(MemberDTO memberDTO) {
        memberDAO.memberJoin(memberDTO);
    }

    @Override
    public int idCheck(String id) {
        return memberDAO.idCheck(id);
    }

    @Override
    public int checkEmail(String email) {
        return memberDAO.checkEmail(email);
    }

    @Override
    public MemberDTO getMember(MemberDTO memberDTO) {
        return memberDAO.getMember(memberDTO);
    }

    @Override
    public MemberDTO getUpdateMemberInfo(int num) {
        return memberDAO.getUpdateMemberInfo(num);
    }

    @Override
    public void updateMember(MemberDTO memberDTO) {
        memberDAO.updateMember(memberDTO);
    }

    @Override
    public void deleteMember(int num) {
        memberDAO.deleteMember(num);
    }

    @Override
    public int checkName(String name) {
        return memberDAO.checkName(name);
    }

    @Override
    public String getId(MemberDTO memberDTO) {
        return memberDAO.getId(memberDTO);
    }

    @Override
    public int checkIdEmail(MemberDTO memberDTO) {
        return memberDAO.checkIdEmail(memberDTO);
    }

    @Override
    public int checkIdPhone(MemberDTO memberDTO) {
        return memberDAO.checkIdPhone(memberDTO);
    }

    @Override
    public void updatePwd(MemberDTO memberDTO) {
        memberDAO.updatePwd(memberDTO);
    }

    @Override
    public MemberDTO getMemberInfo(int num) {
        return memberDAO.getMemberInfo(num);
    }

    @Override
    public void updateAddress(MemberDTO memberDTO) {
        memberDAO.updateAddress(memberDTO);
    }

    @Override
    public int getUserNum(String secretKey) {
        return memberDAO.getUserNum(secretKey);
    }

    @Override
    public void updateSecretKey(MemberDTO memberDTO) {
        memberDAO.updateSecretKey(memberDTO);
    }
}

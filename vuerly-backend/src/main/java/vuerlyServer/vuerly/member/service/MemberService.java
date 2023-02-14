package vuerlyServer.vuerly.member.service;

import vuerlyServer.vuerly.member.model.MemberDTO;
import vuerlyServer.vuerly.member.model.MemberVO;

public interface MemberService {
    public void memberJoin(MemberDTO memberDTO);
    public int idCheck(String id);

    public int checkEmail(String email);
    public MemberDTO getMember(MemberDTO memberDTO);
    public MemberDTO getUpdateMemberInfo(int num);
    public void updateMember(MemberDTO memberDTO);
    public void deleteMember(int num);
    public int checkName(String name);
    public String getId(MemberDTO memberDTO);
    public int checkIdEmail(MemberDTO memberDTO);
    public int checkIdPhone(MemberDTO memberDTO);

    public void updatePwd(MemberDTO memberDTO);
    public MemberDTO getMemberInfo(int num);
    public void updateAddress(MemberDTO memberDTO);
    public int getUserNum(String secretKey);
    public void updateSecretKey(MemberDTO memberDTO);
}

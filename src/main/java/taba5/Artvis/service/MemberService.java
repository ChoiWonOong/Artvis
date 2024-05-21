package taba5.Artvis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import taba5.Artvis.domain.Member;
import taba5.Artvis.dto.member.MyPageDto;
import taba5.Artvis.repository.MemberRepository;
import taba5.Artvis.util.SecurityUtil;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MyPageDto getMyPage(){
        Member member = getMe();
        return member.MemberToMyPageDto();
    }
    private Member getMe(){
        return memberRepository.findById(SecurityUtil.getCurrentMemberId()).get();
    }
}

package taba5.Artvis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import taba5.Artvis.Exception.ErrorCode;
import taba5.Artvis.Exception.RestApiException;
import taba5.Artvis.domain.Member;
import taba5.Artvis.dto.HistoryDto;
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
    public Member getMe(){
        return getMember(SecurityUtil.getCurrentMemberId());
    }
    public HistoryDto getHistory(Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        return member.getHistoryDto();
    }
    public Member getMember(Long memberId){
        return memberRepository.findById(memberId).orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
    }
}

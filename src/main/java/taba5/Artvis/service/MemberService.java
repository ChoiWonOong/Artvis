package taba5.Artvis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import taba5.Artvis.Exception.ErrorCode;
import taba5.Artvis.Exception.RestApiException;
import taba5.Artvis.domain.Exhibition.Exhibition;
import taba5.Artvis.domain.History.History;
import taba5.Artvis.domain.Member;
import taba5.Artvis.dto.Exhibition.ExhibitionResponseDto;
import taba5.Artvis.dto.member.MyPageDto;
import taba5.Artvis.repository.HistoryRepository;
import taba5.Artvis.repository.MemberRepository;
import taba5.Artvis.util.SecurityUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final HistoryRepository historyRepository;
    public MyPageDto getMyPage(){
        Member member = getMe();
        return member.MemberToMyPageDto();
    }
    public Member getMe(){
        return getMember(SecurityUtil.getCurrentMemberId());
    }
    public List<ExhibitionResponseDto> getHistory(Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        List<History> history = historyRepository.findByMember(member);
        return history.stream()
                .map(History::getExhibition)
                .map(Exhibition::toResponseDto).toList();
    }
    public Member getMember(Long memberId){
        return memberRepository.findById(memberId).orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
    }
}

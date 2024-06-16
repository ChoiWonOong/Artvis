package taba5.Artvis.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import taba5.Artvis.Exception.ErrorCode;
import taba5.Artvis.Exception.RestApiException;
import taba5.Artvis.domain.Exhibition.Exhibition;
import taba5.Artvis.domain.History.History;
import taba5.Artvis.domain.Like.ExhibitionLike;
import taba5.Artvis.domain.Member;
import taba5.Artvis.domain.Recommend.InitRecommendDto;
import taba5.Artvis.domain.Review.Review;
import taba5.Artvis.dto.Exhibition.ExhibitionResponseDto;
import taba5.Artvis.dto.member.MyPageDto;
import taba5.Artvis.repository.HistoryRepository;
import taba5.Artvis.repository.LikeRepository.ExhibitionLikeRepository;
import taba5.Artvis.repository.MemberRepository;
import taba5.Artvis.repository.ReviewRepository;
import taba5.Artvis.util.SecurityUtil;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final HistoryRepository historyRepository;
    private final ExhibitionLikeRepository exhibitionLikeRepository;
    private final ReviewRepository reviewRepository;

    public MyPageDto getMyPage(){
        Member member = getMe();
        return member.MemberToMyPageDto();
    }
    public Member getMe(){
        return getMember(SecurityUtil.getCurrentMemberId());
    }
    public List<Exhibition> getHistory(Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        List<History> history = historyRepository.findByMember(member);
        return history.stream()
                .map(History::getExhibition).toList();
    }
    public Member getMember(Long memberId){
        return memberRepository.findById(memberId).orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
    }
    public List<Exhibition> getHistoryAndLikes(Long memberId){
        List<Exhibition> request = new ArrayList<>();
        List<Exhibition> history = getHistory(memberId);
        log.info("history: {}", history.stream().map(Exhibition::getTitle).toList());

        List<Exhibition> likes = exhibitionLikeRepository.findByMember_Id(memberId)
                .stream().map(ExhibitionLike::getExhibition).toList();
        log.info("likes: {}", likes.stream().map(Exhibition::getTitle).toList());
        request.addAll(history);
        request.addAll(likes);
        log.info("history+likes: {}", request.stream().map(Exhibition::getTitle).toList());
        return request.stream().distinct().toList();
    }

    public InitRecommendDto initRecommend(Long currentMemberId, InitRecommendDto dto) {
        Member member = getMember(currentMemberId);
        List<Long> exhibitionIds = dto.getExhibitionIdList();
        exhibitionIds.stream().map(exhibitionId->{// 리뷰 생성
            Review review = new Review("null" , (byte) 5, member, exhibitionId);
            review.setDummy();
            return reviewRepository.save(review);
        });
        dto.setMemberId(currentMemberId);
        return dto;
    }
}

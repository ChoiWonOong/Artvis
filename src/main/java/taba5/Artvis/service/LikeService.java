package taba5.Artvis.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import taba5.Artvis.Exception.ErrorCode;
import taba5.Artvis.Exception.RestApiException;
import taba5.Artvis.domain.Exhibition.Exhibition;
import taba5.Artvis.domain.Like.ExhibitionLike;
import taba5.Artvis.domain.Member;
import taba5.Artvis.dto.Exhibition.ExhibitionResponseDto;
import taba5.Artvis.dto.Like.ExhibitionLikeDto;
import taba5.Artvis.dto.Like.InitializeLikeDto;
import taba5.Artvis.repository.ExhibitionRepository;
import taba5.Artvis.repository.LikeRepository.ExhibitionLikeRepository;
import taba5.Artvis.repository.MemberRepository;
import taba5.Artvis.util.SecurityUtil;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class LikeService {
    private final ExhibitionLikeRepository exhibitionLikeRepository;
    private final ExhibitionRepository exhibitionRepository;
    private final ExhibitionService exhibitionService;
    private final MemberRepository memberRepository;

    public ExhibitionLikeDto saveExhibitionLike(Long memberId, Long exhibitionId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        Exhibition exhibition = exhibitionService.getExhibition(exhibitionId);
        ExhibitionLike exhibitionLike = new ExhibitionLike(member, exhibition);
        exhibitionLike = exhibitionLikeRepository.save(exhibitionLike);
        log.info("exhibitionLike: {} {}", exhibitionLike.getExhibition().getTitle(), exhibitionLike.getMember().getNickname());
        return exhibitionLike.toDto();
    }
    public ExhibitionLikeDto deleteExhibitionLike(Long memberId, Long exhibitionId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        Exhibition exhibition = exhibitionService.getExhibition(exhibitionId);
        ExhibitionLike exhibitionLike = exhibitionLikeRepository.findByMember_IdAndExhibition_Id(member.getId(), exhibition.getId())
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        exhibitionLikeRepository.delete(exhibitionLike);
        return exhibitionLike.toDto();
    }
    public List<ExhibitionLike> getExhibitionLikes(Long memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        return exhibitionLikeRepository.findByMember(member);
    }

    public List<ExhibitionResponseDto> initializeLike(InitializeLikeDto dto, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        List<ExhibitionLike> exhibitionLikes = dto.getExhibitionIdList().stream()
                .map(r->{
                    Exhibition exhibition = exhibitionRepository.findById(r)
                            .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
                    ExhibitionLike exhibitionLike = new ExhibitionLike(member, exhibition);
                    exhibitionLike.setDummy();
                    exhibitionLikeRepository.save(exhibitionLike);
                    return exhibitionLike;
                }).toList();
        return exhibitionLikes.stream()
                .map((r)->exhibitionService.getExhibitionResponseDto(SecurityUtil.getCurrentMemberId(), r.getExhibition()))
                .toList();
    }
}

package taba5.Artvis.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import taba5.Artvis.Exception.ErrorCode;
import taba5.Artvis.Exception.RestApiException;
import taba5.Artvis.domain.Detail;
import taba5.Artvis.domain.Exhibition.Exhibition;
import taba5.Artvis.domain.Exhibition.ExhibitionTag;
import taba5.Artvis.domain.Exhibition.Tag;
import taba5.Artvis.domain.Like.ExhibitionLike;
import taba5.Artvis.domain.Member;
import taba5.Artvis.domain.Review;
import taba5.Artvis.dto.Exhibition.ExhibitionHistoryDto;
import taba5.Artvis.dto.Exhibition.ExhibitionRequestDto;
import taba5.Artvis.dto.Exhibition.ExhibitionResponseDto;
import taba5.Artvis.repository.*;
import taba5.Artvis.repository.LikeRepository.ExhibitionLikeRepository;
import taba5.Artvis.util.SecurityUtil;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExhibitionService {
    private final ExhibitionRepository exhibitionRepository;
    private final ExhibitionTagRepository exhibitionTagRepository;
    private final TagRepository tagRepository;
    private final DetailRepository detailRepository;
    private final ExhibitionLikeRepository exhibitionLikeRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    public ExhibitionResponseDto createExhibition(ExhibitionRequestDto dto){
        dto.printDto();
        List<Detail> detailList = Detail.toEntityList(dto.getDetailList());
        detailRepository.saveAll(detailList);
        Exhibition exhibition = new Exhibition(
                dto.getTitle(),
                dto.getLocation(),
                dto.getStartDate(),
                dto.getEndDate(),
                detailList);
        exhibitionRepository.save(exhibition);
        for(String tagName : dto.getExhibitionTagList()){
            Tag tag = tagRepository.findByTagName(tagName)
                    .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
            createExhibitionTag(exhibition, tag);
        }
        return getExhibitionResponseDto(SecurityUtil.getCurrentMemberId(), exhibition);
    }
    public ExhibitionResponseDto getExhibition(Long memberId, Long exhibitionId){
        Exhibition exhibition = exhibitionRepository.findById(exhibitionId)
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        return getExhibitionResponseDto(memberId, exhibition);
    }

    private ExhibitionResponseDto getExhibitionResponseDto(Long memberId, Exhibition exhibition) {
        List<ExhibitionTag> exhibitionTag = exhibitionTagRepository.findByExhibition(exhibition);

        ExhibitionResponseDto dto = ExhibitionResponseDto.builder()
                .id(exhibition.getId())
                .title(exhibition.getTitle())
                .location(exhibition.getLocation())
                .startDate(exhibition.getStartDate())
                .endDate(exhibition.getEndDate())
                .detailList(exhibition.getDetailList().stream().map(Detail::toDto).toList())
                .tagList(exhibitionTag.stream().map(ExhibitionTag::getTagName).toList())
                .build();
        dto.setIsLiked(exhibitionLikeRepository.existsByMember_IdAndExhibition_Id(memberId, exhibition.getId()));
        reviewRepository.findAllByExhibition(exhibition).stream()
                .map(Review::toDto)
                .forEach(dto.getReviewList()::add);
        return dto;
    }
    public List<ExhibitionResponseDto> getLikedExhibition(Long memberId){
        List<ExhibitionLike> exhibitionLikes = exhibitionLikeRepository.findByMember_Id(memberId);
        return exhibitionLikes.stream().map((r)->getExhibitionResponseDto(SecurityUtil.getCurrentMemberId(), r.getExhibition())).toList();
    }
    public ExhibitionTag createExhibitionTag(Exhibition exhibition, Tag tag){
        ExhibitionTag exhibitionTag = ExhibitionTag.builder()
                .exhibition(exhibition)
                .tag(tag)
                .build();
        exhibitionTagRepository.save(exhibitionTag);
        return exhibitionTag;
    }
    public ExhibitionTag getExhibitionTag(Long id){
        return exhibitionTagRepository.findById(id)
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
    }
    public List<ExhibitionTag> getExhibitionTagList(Exhibition exhibition){
        return exhibitionTagRepository.findByExhibition(exhibition);
    }

    public ExhibitionHistoryDto addHistory(Long id) {
        Exhibition exhibition = exhibitionRepository.findById(id)
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        member.addHistory(exhibition);
        return ExhibitionHistoryDto.builder()
                .exhibitionId(exhibition.getId())
                .title(exhibition.getTitle())
                .startDate(exhibition.getStartDate())
                .endDate(exhibition.getEndDate())
                .galleryName(exhibition.getLocation())
                .imageUrl(exhibition.getImage().getUrl())
                .build();
    }
}
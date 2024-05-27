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
import taba5.Artvis.dto.DetailDto;
import taba5.Artvis.dto.Exhibition.ExhibitionRequestDto;
import taba5.Artvis.dto.Exhibition.ExhibitionResponseDto;
import taba5.Artvis.repository.*;

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
        return getExhibitionResponseDto(exhibition);
    }
    public ExhibitionResponseDto getExhibition(Long id){
        Exhibition exhibition = exhibitionRepository.findById(id)
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        return getExhibitionResponseDto(exhibition);
    }

    private ExhibitionResponseDto getExhibitionResponseDto(Exhibition exhibition) {
        List<ExhibitionTag> exhibitionTag = exhibitionTagRepository.findByExhibition(exhibition);
        return ExhibitionResponseDto.builder()
                .id(exhibition.getId())
                .title(exhibition.getTitle())
                .location(exhibition.getLocation())
                .startDate(exhibition.getStartDate())
                .endDate(exhibition.getEndDate())
                .detailList(exhibition.getDetailList().stream().map(Detail::toDto).toList())
                .tagList(exhibitionTag.stream().map(ExhibitionTag::getTagName).toList())
                .build();
    }
    public List<ExhibitionResponseDto> getLikedExhibition(Long memberId){
        List<ExhibitionLike> exhibitionLikes = exhibitionLikeRepository.findByMember(memberId);
        return exhibitionLikes.stream().map((r)->getExhibitionResponseDto(r.getExhibition())).toList();
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
    public List<DetailDto> getDetailDtoList(Exhibition exhibition){
        return detailRepository.findByExhibition(exhibition).stream().map(Detail::toDto).toList();
    }
}
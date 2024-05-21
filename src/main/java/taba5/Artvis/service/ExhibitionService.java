package taba5.Artvis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import taba5.Artvis.Exception.ErrorCode;
import taba5.Artvis.Exception.RestApiException;
import taba5.Artvis.domain.Detail;
import taba5.Artvis.domain.Exhibition;
import taba5.Artvis.domain.ExhibitionTag;
import taba5.Artvis.domain.Tag;
import taba5.Artvis.dto.Exhibition.ExhibitionRequestDto;
import taba5.Artvis.dto.Exhibition.ExhibitionResponseDto;
import taba5.Artvis.repository.ExhibitionRepository;
import taba5.Artvis.repository.ExhibitionTagRepository;
import taba5.Artvis.repository.TagRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExhibitionService {
    private final ExhibitionRepository exhibitionRepository;
    private final ExhibitionTagRepository exhibitionTagRepository;
    private final TagRepository tagRepository;

    public ExhibitionResponseDto createExhibition(ExhibitionRequestDto exhibitionCreateDto){
        Exhibition exhibition = exhibitionCreateDto.toEntity();
        exhibition.setDetailList(exhibitionCreateDto.getDetailList());
        List<ExhibitionTag> exhibitionTagList = new ArrayList<>();
        for(String tagName : exhibitionCreateDto.getExhibitionTagList()){
            Tag tag = tagRepository.findByTagName(tagName)
                    .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
            exhibitionTagList.add(createExhibitionTag(exhibition, tag));
        }
        exhibition.setExhibitionTagList(exhibitionTagList);
        exhibitionRepository.save(exhibition);
        return ExhibitionResponseDto.builder()
                .id(exhibition.getId())
                .title(exhibition.getTitle())
                .location(exhibition.getLocation())
                .startDate(exhibition.getStartDate())
                .endDate(exhibition.getEndDate())
                .tagList(exhibition.getExhibitionTagList().stream().map(h->h.getTag().getTagName()).toList())
                .detailList(exhibition.getDetailList().stream().map(Detail::toDto).toList())
                .build();
    }
    public ExhibitionResponseDto getExhibition(Long id){
        Exhibition exhibition = exhibitionRepository.findById(id)
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        return exhibition.toResponseDto();
    }
    public ExhibitionTag createExhibitionTag(Exhibition exhibition, Tag tag){
        ExhibitionTag exhibitionTag = ExhibitionTag.builder()
                .exhibition(exhibition)
                .tag(tag)
                .build();
        exhibitionTagRepository.save(exhibitionTag);
        return exhibitionTag;
    }
}
package taba5.Artvis.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import taba5.Artvis.Exception.ErrorCode;
import taba5.Artvis.Exception.RestApiException;
import taba5.Artvis.domain.Art.Artwork;
import taba5.Artvis.domain.Exhibition.Exhibition;
import taba5.Artvis.domain.Exhibition.ExhibitionTag;
import taba5.Artvis.domain.Exhibition.Tag;
import taba5.Artvis.domain.History.History;
import taba5.Artvis.domain.Like.ExhibitionLike;
import taba5.Artvis.domain.Member;
import taba5.Artvis.domain.Review.Review;
import taba5.Artvis.dto.Exhibition.ExhibitionArtworkAddDto;
import taba5.Artvis.dto.Exhibition.ExhibitionHistoryDto;
import taba5.Artvis.dto.Exhibition.ExhibitionRequestDto;
import taba5.Artvis.dto.Exhibition.ExhibitionResponseDto;
import taba5.Artvis.dto.Review.ReviewResponseDto;
import taba5.Artvis.dto.flask.CollaborativeHomeRequestDto;
import taba5.Artvis.dto.flask.ContentsHomeRequestDto;
import taba5.Artvis.dto.flask.FlaskResponseDto;
import taba5.Artvis.repository.*;
import taba5.Artvis.repository.LikeRepository.ExhibitionLikeRepository;
import taba5.Artvis.util.SecurityUtil;

import java.util.ArrayList;
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
    private final ArtworkRepository artworkRepository;
    private final HistoryRepository historyRepository;
    private final FlaskService flaskService;
    private final MemberService memberService;
    private final ReviewService reviewService;

    public ExhibitionResponseDto createExhibitionDto(ExhibitionRequestDto dto){
        Exhibition exhibition = createExhibition(dto);
        exhibitionRepository.save(exhibition);
        for(String tagName : dto.getExhibitionTagList()){
            Tag tag = tagRepository.findByTagName(tagName)
                    .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
            createExhibitionTag(exhibition, tag);
        }
        return getExhibitionResponseDto(SecurityUtil.getCurrentMemberId(), exhibition);
    }
    public Exhibition createExhibition(ExhibitionRequestDto dto){
        return new Exhibition(
                dto.getTitle(),
                dto.getLocation(),
                dto.getStartDate(),
                dto.getEndDate(),
                dto.getDetail(),
                dto.getImageUrl());
    }
    public Exhibition getExhibition(Long exhibitionId){
        return exhibitionRepository.findById(exhibitionId)
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
    }
    public void addArtworkToExhibition(ExhibitionArtworkAddDto dto){
        Exhibition exhibition = exhibitionRepository.findById(dto.getExhibitionId())
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        List<Artwork> artworks = dto.getArtworkIds().stream().map(r->artworkRepository.findById(r)
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR))).toList();
        exhibition.addArtworks(artworks);
        exhibitionRepository.save(exhibition);
    }
    public ExhibitionResponseDto getExhibitionResponseDto(Long memberId, Exhibition exhibition) {
        List<ExhibitionTag> exhibitionTag = exhibitionTagRepository.findByExhibition(exhibition);

        ExhibitionResponseDto dto = ExhibitionResponseDto.builder()
                .id(exhibition.getId())
                .title(exhibition.getTitle())
                .location(exhibition.getLocation())
                .startDate(exhibition.getStartDate())
                .endDate(exhibition.getEndDate())
                .detail(exhibition.getDetail())
                .imageUrl(exhibition.getImageUrl())
                .artworkDtos(exhibition.getArtworkList().stream().map(Artwork::toDto).toList())
                .tagList(exhibitionTag.stream().map(ExhibitionTag::getTagName).toList())
                .build();
        dto.setIsLiked(exhibitionLikeRepository.existsByMember_IdAndExhibition_Id(memberId, exhibition.getId()));
        List<Review> reviewList = reviewRepository.findAllByExhibitionId(exhibition.getId());
        dto.getReviewList().addAll(reviewList.stream().map(reviewService::reviewToDto).toList());
        return dto;
    }

    public List<ExhibitionResponseDto> getLikedExhibition(Long memberId){
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).get();
        List<ExhibitionLike> exhibitionLikes = exhibitionLikeRepository.findByMember(member);
        return exhibitionLikes.stream().map((r)->getExhibitionResponseDto(SecurityUtil.getCurrentMemberId(), r.getExhibition())).toList();
    }
    public void createExhibitionTag(Exhibition exhibition, Tag tag){
        ExhibitionTag exhibitionTag = ExhibitionTag.builder()
                .exhibition(exhibition)
                .tag(tag)
                .build();
        exhibitionTagRepository.save(exhibitionTag);
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
        History history = new History(member, exhibition);
        historyRepository.save(history);
        return ExhibitionHistoryDto.builder()
                .exhibitionId(exhibition.getId())
                .title(exhibition.getTitle())
                .startDate(exhibition.getStartDate())
                .endDate(exhibition.getEndDate())
                .galleryName(exhibition.getLocation())
                //.imageUrl(exhibition.getImage().getUrl())
                .imageUrl(exhibition.getImageUrl())
                .isLiked(exhibitionLikeRepository.existsByMember_IdAndExhibition_Id(SecurityUtil.getCurrentMemberId(), exhibition.getId()))
                .build();
    }
    public List<Exhibition> getExhibitionList(){
        return exhibitionRepository.findAll();
    }
    public List<ExhibitionResponseDto> getExhibitionDtoList(List<Exhibition> exhibitionList) {
        return exhibitionList.stream()
                .map((r)->getExhibitionResponseDto(SecurityUtil.getCurrentMemberId(), r))
                .toList();
    }
    public Page<ExhibitionResponseDto> getExhibitionDtoPage(int pageNo, String criteria) {
        int PAGE_SIZE = 50;
        Pageable pageable = PageRequest.of(pageNo, PAGE_SIZE, Sort.by(Sort.Direction.DESC, criteria));

        return exhibitionRepository.findByOrderById(pageable).map(Exhibition::toResponseDto);
    }

    public List<ExhibitionResponseDto> getExhibitionListByTagName(String tagName) {
        Tag tag = tagRepository.findByTagName(tagName)
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        List<ExhibitionTag> exhibitionTagList = exhibitionTagRepository.findByTag(tag);
        return exhibitionTagList.stream()
                .map(ExhibitionTag::getExhibition)
                .map((r)->getExhibitionResponseDto(SecurityUtil.getCurrentMemberId(), r))
                .toList();
    }

    public List<ExhibitionResponseDto> searchExhibition(String keyword) {
        List<Exhibition> list =  exhibitionRepository.findByTitleContaining(keyword);
        List<ExhibitionResponseDto> result = list.stream().map((r)->getExhibitionResponseDto(SecurityUtil.getCurrentMemberId(), r)).toList();
        return result;
    }

    public List<ExhibitionResponseDto> getRecommendExhibitionList(Long currentMemberId) {
        Member member = memberRepository.findById(currentMemberId)
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        List<Exhibition> recommendList = exhibitionRepository.findAll();
        return recommendList.stream()
                .map((r)->getExhibitionResponseDto(SecurityUtil.getCurrentMemberId(), r))
                .toList();
    }

    public List<ExhibitionResponseDto> setRecommend(Long id, List<Long> exhibitionIdList) {
        Exhibition exhibition = exhibitionRepository.findById(id)
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        List<ExhibitionResponseDto> result = new ArrayList<>();
        for(Long exhibitionId : exhibitionIdList){
            Exhibition recommendExhibition = exhibitionRepository.findById(exhibitionId)
                    .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
            exhibition.addRecommend(recommendExhibition);
            result.add(getExhibitionResponseDto(SecurityUtil.getCurrentMemberId(), recommendExhibition));
        }
        return result;
    }

    public ExhibitionResponseDto findExhibition(String title) {
        log.info("enter");
        Exhibition exhibition = exhibitionRepository.findByTitle(title).orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        log.info("enter");
        return getExhibitionResponseDto(SecurityUtil.getCurrentMemberId(), exhibition);
    }

    public ExhibitionResponseDto createDummyExhibition(ExhibitionRequestDto exhibitionRequestDto) {
        Exhibition dummy = createExhibition(exhibitionRequestDto);
        dummy.setDummy();
        exhibitionRepository.save(dummy);
        return getExhibitionResponseDto(SecurityUtil.getCurrentMemberId(), dummy);
    }
    public List<Exhibition> getContentsHome() throws JsonProcessingException {
        List<Long> historyAndLikes = memberService.getHistoryAndLikes(SecurityUtil.getCurrentMemberId())
                .stream().map(Exhibition::getId).toList();
        CollaborativeHomeRequestDto flaskDto = new CollaborativeHomeRequestDto(SecurityUtil.getCurrentMemberId());
        flaskDto.addExhibitionId(historyAndLikes);
        String url = "api/home1";
        FlaskResponseDto flaskResult = flaskService.getCollaborativeHome(flaskDto, url);
        return flaskResult.getResult().stream()
                .map(r->exhibitionRepository.findById(r).orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR)))
                .toList();
    }
    public List<Exhibition> getCollaborativeHome() throws JsonProcessingException {
        List<Long> historyAndLikes = memberService.getHistoryAndLikes(SecurityUtil.getCurrentMemberId())
                .stream().map(Exhibition::getId).toList();
        CollaborativeHomeRequestDto flaskDto = new CollaborativeHomeRequestDto(SecurityUtil.getCurrentMemberId());
        flaskDto.addExhibitionId(historyAndLikes);
        String url = "api/home2";
        FlaskResponseDto flaskResult = flaskService.getCollaborativeHome(flaskDto, url);
        return flaskResult.getResult().stream()
                .map(r->exhibitionRepository.findById(r).orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR)))
                .toList();
    }
    public List<Exhibition> getDummyExhibition() {
        return exhibitionRepository.findByDummyIsTrue();
    }
}
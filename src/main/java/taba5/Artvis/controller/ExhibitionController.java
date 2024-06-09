package taba5.Artvis.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taba5.Artvis.dto.Exhibition.ExhibitionArtworkAddDto;
import taba5.Artvis.dto.Exhibition.ExhibitionHistoryDto;
import taba5.Artvis.dto.Exhibition.ExhibitionRequestDto;
import taba5.Artvis.dto.Exhibition.ExhibitionResponseDto;
import taba5.Artvis.dto.InitializeRecommendDto;
import taba5.Artvis.service.ExhibitionService;
import taba5.Artvis.util.SecurityUtil;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exhibition")
public class ExhibitionController {
    private final ExhibitionService exhibitionService;
    // 전시회 생성
    @PostMapping("/create")
    public ResponseEntity<ExhibitionResponseDto> createExhibition(@RequestBody ExhibitionRequestDto exhibitionRequestDto){
        return ResponseEntity.ok(exhibitionService.createExhibition(exhibitionRequestDto));
    }
    // 작품 삽입
    @PostMapping("/add/artworks")
    public ResponseEntity<ExhibitionArtworkAddDto> addArtworksToExhibition(@RequestBody ExhibitionArtworkAddDto dto){
        exhibitionService.addArtworkToExhibition(dto);
        return ResponseEntity.ok(dto);
    }
    // id로 전시회 불러오기
    @GetMapping("/{id}")
    public ResponseEntity<ExhibitionResponseDto> getExhibition(@PathVariable Long id){
        return ResponseEntity.ok(exhibitionService.getExhibition(SecurityUtil.getCurrentMemberId(), id));
    }
    // 이름으로 전시회 찾기
    @PostMapping("/find")
    public ResponseEntity<ExhibitionResponseDto> findExhibition(@RequestBody ExhibitionRequestDto exhibitionRequestDto){
        return ResponseEntity.ok(exhibitionService.findExhibition(exhibitionRequestDto.getTitle()));
    }
    // 좋아요한 전시회 불러오기
    @GetMapping("/liked")
    public ResponseEntity<List<ExhibitionResponseDto>> likeExhibition(){
        return ResponseEntity.ok(exhibitionService.getLikedExhibition(SecurityUtil.getCurrentMemberId()));
    }
    // 전시회 좋아요
    @PostMapping("/add/history/{id}")
    public ResponseEntity<ExhibitionHistoryDto> addHistory(@PathVariable Long id){
        return ResponseEntity.ok(exhibitionService.addHistory(id));
    }
    // 모든 전시회 불러오기
    @GetMapping("/list")
    public ResponseEntity<List<ExhibitionResponseDto>> getExhibitionList(){
        return ResponseEntity.ok(exhibitionService.getExhibitionDtoList());
    }
    // 추천 전시회 불러오기
    @GetMapping("/list/recommend")
    public ResponseEntity<List<ExhibitionResponseDto>> getRecommendExhibitionList(){
        return ResponseEntity.ok(exhibitionService.getRecommendExhibitionList(SecurityUtil.getCurrentMemberId()));
    }
    // 태그로 전시회 불러오기
    @GetMapping("/list/{tagName}")
    public ResponseEntity<List<ExhibitionResponseDto>> getExhibitionListByTagName(@PathVariable String tagName){
        return ResponseEntity.ok(exhibitionService.getExhibitionListByTagName(tagName));
    }
    // 전시회 검색
    @GetMapping("search/{keyword}")
    public ResponseEntity<List<ExhibitionResponseDto>> searchExhibition(@PathVariable String keyword){
        return ResponseEntity.ok(exhibitionService.searchExhibition(keyword));
    }
    // 전시회 추천 설정
    @PostMapping("/set/recommend")
    public ResponseEntity<List<ExhibitionResponseDto>> setRecommend(Long id, List<Long> exhibitionIdList){
        return ResponseEntity.ok(exhibitionService.setRecommend(id, exhibitionIdList));
    }
    // 전시회 초기 추천
    @PostMapping("/initialize")
    public ResponseEntity<List<ExhibitionResponseDto>> initializeRecommend(@RequestBody InitializeRecommendDto dto){
        return ResponseEntity.ok(exhibitionService.initializeRecommend(dto, SecurityUtil.getCurrentMemberId()));
    }
}

package taba5.Artvis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taba5.Artvis.dto.Exhibition.ExhibitionHistoryDto;
import taba5.Artvis.dto.Exhibition.ExhibitionRequestDto;
import taba5.Artvis.dto.Exhibition.ExhibitionResponseDto;
import taba5.Artvis.service.ExhibitionService;
import taba5.Artvis.util.SecurityUtil;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exhibition")
public class ExhibitionController {
    private final ExhibitionService exhibitionService;
    @PostMapping("/create")
    public ResponseEntity<ExhibitionResponseDto> createExhibition(@RequestBody ExhibitionRequestDto exhibitionRequestDto){
        return ResponseEntity.ok(exhibitionService.createExhibition(exhibitionRequestDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ExhibitionResponseDto> getExhibition(@PathVariable Long id){
        return ResponseEntity.ok(exhibitionService.getExhibition(SecurityUtil.getCurrentMemberId(), id));
    }
    @GetMapping("/liked")
    public ResponseEntity<List<ExhibitionResponseDto>> likeExhibition(){
        return ResponseEntity.ok(exhibitionService.getLikedExhibition(SecurityUtil.getCurrentMemberId()));
    }
    @PostMapping("/add/history/{id}")
    public ResponseEntity<ExhibitionHistoryDto> addHistory(@PathVariable Long id){
        return ResponseEntity.ok(exhibitionService.addHistory(id));
    }
    @GetMapping("/list")
    public ResponseEntity<List<ExhibitionResponseDto>> getExhibitionList(){
        return ResponseEntity.ok(exhibitionService.getExhibitionDtoList());
    }
    @GetMapping("/list/recommend")
    public ResponseEntity<List<ExhibitionResponseDto>> getRecommendExhibitionList(){
        return ResponseEntity.ok(exhibitionService.getRecommendExhibitionList(SecurityUtil.getCurrentMemberId()));
    }
    @GetMapping("/list/{tagName}")
    public ResponseEntity<List<ExhibitionResponseDto>> getExhibitionListByTagName(@PathVariable String tagName){
        return ResponseEntity.ok(exhibitionService.getExhibitionListByTagName(tagName));
    }
    @GetMapping("search/{keyword}")
    public ResponseEntity<List<ExhibitionResponseDto>> searchExhibition(@PathVariable String keyword){
        return ResponseEntity.ok(exhibitionService.searchExhibition(keyword));
    }
    @PostMapping("/set/recommend")
    public ResponseEntity<List<ExhibitionResponseDto>> setRecommend(Long id, List<Long> exhibitionIdList){
        return ResponseEntity.ok(exhibitionService.setRecommend(id, exhibitionIdList));
    }
}

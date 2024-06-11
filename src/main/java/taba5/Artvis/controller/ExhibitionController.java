package taba5.Artvis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taba5.Artvis.Exception.ErrorResponse;
import taba5.Artvis.Exception.RestApiException;
import taba5.Artvis.domain.Exhibition.Exhibition;
import taba5.Artvis.dto.Exhibition.ExhibitionArtworkAddDto;
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
    // 전시회 생성
    @PostMapping("/create")
    public ResponseEntity<ExhibitionResponseDto> createExhibition(@RequestBody ExhibitionRequestDto exhibitionRequestDto){
        return ResponseEntity.ok(exhibitionService.createExhibitionDto(exhibitionRequestDto));
    }
    // 작품 삽입
    @PostMapping("/add/artworks")
    public ResponseEntity<?> addArtworksToExhibition(@RequestBody ExhibitionArtworkAddDto dto){
        try{
            exhibitionService.addArtworkToExhibition(dto);
            return ResponseEntity.ok(dto);
        }catch (RestApiException e) {
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }catch (RuntimeException e){
            return ErrorResponse.toResponseEntity(e, "BAD_REQUEST");
        }
    }
    // 더미 작품 삽입
    @PostMapping("/create/dummy")
    public ResponseEntity<?> createDummyExhibition(@RequestBody ExhibitionRequestDto exhibitionRequestDto){
        try{
            return ResponseEntity.ok(exhibitionService.createDummyExhibition(exhibitionRequestDto));
        }catch (RestApiException e){
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }catch (RuntimeException e){
            return ErrorResponse.toResponseEntity(e, "BAD_REQUEST");
        }
    }
    // id로 전시회 불러오기
    @GetMapping("/{id}")
    public ResponseEntity<?> getExhibition(@PathVariable Long id){
        try{
            return ResponseEntity.ok(exhibitionService.getExhibition(SecurityUtil.getCurrentMemberId(), id));
        }catch (RestApiException e) {
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }catch (RuntimeException e){
            return ErrorResponse.toResponseEntity(e, "BAD_REQUEST");
        }
    }
    // 이름으로 전시회 찾기
    @PostMapping("/find")
    public ResponseEntity<?> findExhibition(@RequestBody ExhibitionRequestDto exhibitionRequestDto){
        try{
            return ResponseEntity.ok(exhibitionService.findExhibition(exhibitionRequestDto.getTitle()));
        }catch (RestApiException e) {
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }catch (RuntimeException e){
            return ErrorResponse.toResponseEntity(e, "BAD_REQUEST");
        }
    }
    // 좋아요한 전시회 불러오기
    @GetMapping("/liked")
    public ResponseEntity<?> likeExhibition(){
        try{
            return ResponseEntity.ok(exhibitionService.getLikedExhibition(SecurityUtil.getCurrentMemberId()));
        }catch (RestApiException e) {
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }catch (RuntimeException e){
            return ErrorResponse.toResponseEntity(e, "BAD_REQUEST");
        }
    }
    // 전시회 좋아요
    @PostMapping("/add/history/{id}")
    public ResponseEntity<?> addHistory(@PathVariable Long id){
        try{
            return ResponseEntity.ok(exhibitionService.addHistory(id));
        }catch (RestApiException e) {
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }catch (RuntimeException e){
            return ErrorResponse.toResponseEntity(e, "BAD_REQUEST");
        }
    }
    // 모든 전시회 불러오기
    @GetMapping("/list")
    public ResponseEntity<?> getExhibitionList(){
        try{
            return ResponseEntity.ok(exhibitionService.getExhibitionDtoList());
        }catch (RestApiException e) {
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }catch (RuntimeException e){
            return ErrorResponse.toResponseEntity(e, "BAD_REQUEST");
        }
    }
    // 추천 전시회 불러오기
    @GetMapping("/list/recommend")
    public ResponseEntity<?> getRecommendExhibitionList(){
        try{
            return ResponseEntity.ok(exhibitionService.getRecommendExhibitionList(SecurityUtil.getCurrentMemberId()));
        }catch (RestApiException e) {
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }catch (RuntimeException e){
            return ErrorResponse.toResponseEntity(e, "BAD_REQUEST");
        }
    }
    // 태그로 전시회 불러오기
    @GetMapping("/list/{tagName}")
    public ResponseEntity<?> getExhibitionListByTagName(@PathVariable String tagName){
        try{
            return ResponseEntity.ok(exhibitionService.getExhibitionListByTagName(tagName));
        }catch (RestApiException e) {
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }catch (RuntimeException e){
            return ErrorResponse.toResponseEntity(e, "BAD_REQUEST");
        }
    }
    // 전시회 추천 설정
    @PostMapping("/set/recommend")
    public ResponseEntity<?> setRecommend(Long id, List<Long> exhibitionIdList){
        try{
            return ResponseEntity.ok(exhibitionService.setRecommend(id, exhibitionIdList));
        }catch (RestApiException e) {
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }catch (RuntimeException e){
            return ErrorResponse.toResponseEntity(e, "BAD_REQUEST");
        }
    }
    @GetMapping("/dummy")
    public ResponseEntity<?> getDummyExhibition(){
        try{
            return ResponseEntity.ok(exhibitionService.getDummyExhibition()
                    .stream().map(Exhibition::toResponseDto).toList());
        }catch (RestApiException e) {
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }catch (RuntimeException e){
            return ErrorResponse.toResponseEntity(e, "BAD_REQUEST");
        }
    }
}

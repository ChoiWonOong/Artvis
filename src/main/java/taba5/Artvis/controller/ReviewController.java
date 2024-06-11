package taba5.Artvis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taba5.Artvis.Exception.ErrorResponse;
import taba5.Artvis.Exception.RestApiException;
import taba5.Artvis.dto.ReviewDto;
import taba5.Artvis.service.ReviewService;
import taba5.Artvis.util.SecurityUtil;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;
    // 리뷰 작성
    @PostMapping("/write")
    public ResponseEntity<?> writeReview(@RequestBody ReviewDto reviewDto){
        try{
            return ResponseEntity.ok(reviewService.saveReview(reviewDto));
        }catch (RuntimeException e){
            return ErrorResponse.toResponseEntity(e, "BAD_REQUEST");
        }
    }
    // 전시회 리뷰 가져오기
    @GetMapping("/{exhibitionId}")
    public ResponseEntity<?> getReview(@PathVariable Long exhibitionId){
        try{
            return ResponseEntity.ok(reviewService.getExhibitionReview(exhibitionId));
        }catch (RestApiException e){
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }catch (RuntimeException e){
            return ErrorResponse.toResponseEntity(e, "BAD_REQUEST");
        }
    }
    // 내 리뷰 가져오기
    @GetMapping("/my")
    public ResponseEntity<?> getMyReview(){
        try{
            return ResponseEntity.ok(reviewService.getMemberReviewDto(SecurityUtil.getCurrentMemberId()));
        }catch (RestApiException e) {
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }catch (RuntimeException e){
            return ErrorResponse.toResponseEntity(e, "BAD_REQUEST");
        }
    }
    // 리뷰 리스트 가져오기
    @GetMapping("/list")
    public ResponseEntity<?> getReviewList(){
        try{
            return ResponseEntity.ok(reviewService.getReviewDtoList());
        }catch (RestApiException e){
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }catch (RuntimeException e){
            return ErrorResponse.toResponseEntity(e, "BAD_REQUEST");
        }
    }
}

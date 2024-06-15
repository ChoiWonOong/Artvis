package taba5.Artvis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taba5.Artvis.Exception.ErrorResponse;
import taba5.Artvis.Exception.RestApiException;
import taba5.Artvis.domain.Review.Review;
import taba5.Artvis.dto.Review.ReviewRequestDto;
import taba5.Artvis.service.ReviewService;
import taba5.Artvis.util.SecurityUtil;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;
    // 리뷰 작성
    @PostMapping("/write")
    public ResponseEntity<?> writeReview(@RequestBody ReviewRequestDto reviewDto){
        try{
            return ResponseEntity.ok(reviewService.saveReview(reviewDto));
        }catch (RuntimeException e){
            return ErrorResponse.toResponseEntity(e, "BAD_REQUEST");
        }
    }
    // 전시회 리뷰 가져오기
    @GetMapping("/exhibition/{exhibitionId}")
    public ResponseEntity<?> getReview(@PathVariable(name = "exhibitionId") Long exhibitionId){
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
    @GetMapping("/list/id/asc")
    public ResponseEntity<?> getReviewListAsc(){
        try{
            return ResponseEntity.ok(reviewService.getReviewDtoListAsc());
        }catch (RestApiException e){
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }catch (RuntimeException e){
            return ErrorResponse.toResponseEntity(e, "BAD_REQUEST");
        }
    }
/*    @GetMapping("/list/id/desc")
    public ResponseEntity<?> getReviewListDesc(){
        try{
            return ResponseEntity.ok(reviewService.getReviewDtoListDesc());
        }catch (RestApiException e){
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }catch (RuntimeException e){
            return ErrorResponse.toResponseEntity(e, "BAD_REQUEST");
        }
    }*/
    @GetMapping("/list/rating/desc")
    public ResponseEntity<?> getReviewListByRatingDesc(){
        try{
            return ResponseEntity.ok(reviewService.findReviewsOrderByRatingDesc()
                    .stream().map(reviewService::reviewToDto).toList());
        }catch (RuntimeException e){
            return ErrorResponse.toResponseEntity(e, "BAD_REQUEST");
        }
    }
    @GetMapping("/list/rating/asc")
    public ResponseEntity<?> getReviewListByRatingAsc(){
        try{
            return ResponseEntity.ok(reviewService.findReviewsOrderByRatingAsc()
                    .stream().map(reviewService::reviewToDto).toList());
        }catch (RuntimeException e){
            return ErrorResponse.toResponseEntity(e, "BAD_REQUEST");
        }
    }
}

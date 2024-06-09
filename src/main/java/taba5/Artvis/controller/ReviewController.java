package taba5.Artvis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<ReviewDto> writeReview(@RequestBody ReviewDto reviewDto){
        return ResponseEntity.ok(reviewService.saveReview(reviewDto));
    }
    // 전시회 리뷰 가져오기
    @GetMapping("/{exhibitionId}")
    public ResponseEntity<List<ReviewDto>> getReview(@PathVariable Long exhibitionId){
        return ResponseEntity.ok(reviewService.getExhibitionReview(exhibitionId));
    }
    // 내 리뷰 가져오기
    @GetMapping("/my")
    public ResponseEntity<List<ReviewDto>> getMyReview(){
        return ResponseEntity.ok(reviewService.getMemberReviewDto(SecurityUtil.getCurrentMemberId()));
    }
    // 리뷰 리스트 가져오기
    @GetMapping("/list")
    public ResponseEntity<List<ReviewDto>> getReviewList(){
        return ResponseEntity.ok(reviewService.getReviewDtoList());
    }
}

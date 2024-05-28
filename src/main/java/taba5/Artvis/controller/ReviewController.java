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

    @PostMapping("/write")
    public ResponseEntity<ReviewDto> writeReview(@RequestBody ReviewDto reviewDto){
        return ResponseEntity.ok(reviewService.saveReview(reviewDto));
    }
    @GetMapping("/{exhibitionId}")
    public ResponseEntity<List<ReviewDto>> getReview(@PathVariable Long exhibitionId){
        return ResponseEntity.ok(reviewService.getExhibitionReview(exhibitionId));
    }
    @GetMapping("/my")
    public ResponseEntity<List<ReviewDto>> getMyReview(){
        return ResponseEntity.ok(reviewService.getMemberReview(SecurityUtil.getCurrentMemberId()));
    }
}

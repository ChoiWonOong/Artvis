package taba5.Artvis.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import taba5.Artvis.Exception.ErrorCode;
import taba5.Artvis.Exception.RestApiException;
import taba5.Artvis.domain.Exhibition.Exhibition;
import taba5.Artvis.domain.Member;
import taba5.Artvis.domain.Review.Review;
import taba5.Artvis.dto.Review.ReviewRequestDto;
import taba5.Artvis.dto.Review.ReviewResponseDto;
import taba5.Artvis.repository.ExhibitionRepository;
import taba5.Artvis.repository.MemberRepository;
import taba5.Artvis.repository.ReviewRepository;

import java.util.Collections;
import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final ExhibitionRepository exhibitionRepository;
    private final MemberService memberService;
    @Transactional
    public ReviewResponseDto saveReview(Review review) {
        review = reviewRepository.save(review);
        return reviewToDto(review);
    }
    @Transactional
    public ReviewResponseDto saveReview(ReviewRequestDto reviewDto) {
        Member member = memberService.getMe();
        Review review = new Review(reviewDto.getContents(), reviewDto.getRating(), member, reviewDto.getExhibitionId());
        review = reviewRepository.save(review);
        return reviewToDto(review);
    }
    public ReviewResponseDto reviewToDto(Review review){
        return ReviewResponseDto.builder()
                .id(review.getId())
                .contents(review.getContents())
                .rating(review.getRating())
                .memberId(review.getMember().getId())
                .exhibitionId(review.getExhibitionId())
                .title(exhibitionRepository.findById(review.getExhibitionId()).get().getTitle())
                .build();
    }
    public List<ReviewResponseDto> getExhibitionReview(Long exhibitionId) {
        List<Review> reviewList = reviewRepository.findAllByExhibitionId(exhibitionId);
        return reviewList.stream().map(this::reviewToDto).toList();
    }
    public int getExhibitionReviewCount(Exhibition exhibition) {
        return reviewRepository.countByExhibition(exhibition);
    }
    public int getExhibitionReviewAvg(Exhibition exhibition) {
        return reviewRepository.avgRatingByExhibition(exhibition);
    }
    public List<Review> getReviewList() {
        return reviewRepository.findAll();
    }
    public List<ReviewResponseDto> getMemberReviewDto(Long memberId) {
        List<Review> reviewList = getMemberReview(memberId);
        return reviewList.stream().map(this::reviewToDto).toList();
    }
    public List<Review> getMemberReview(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        return reviewRepository.findAllByMember(member);
    }

    public List<ReviewResponseDto> getReviewDtoListAsc() {
        List<Review> reviewList = getReviewList();
        return reviewList.stream().map(this::reviewToDto).toList();
    }

    public List<Review> searchReview(String keyword) {
        return reviewRepository.findByContentsContaining(keyword);
    }
    public List<Review> findReviewsOrderByRatingDesc() {
        return reviewRepository.findAllOrderByRatingDesc();
    }
    public List<Review> findReviewsOrderByRatingAsc() {
        return reviewRepository.findAllOrderByRatingAsc();
    }

    public List<Review> getReviewDtoListDesc() {
        List<Review> list = reviewRepository.findAll();
        Collections.reverse(list);
        return list;
    }

    public int countByMember(Long memberId) {
        Member member = memberService.getMember(memberId);
        return reviewRepository.countByMember(member);
    }
}
package taba5.Artvis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import taba5.Artvis.Exception.ErrorCode;
import taba5.Artvis.Exception.RestApiException;
import taba5.Artvis.domain.Exhibition.Exhibition;
import taba5.Artvis.domain.Member;
import taba5.Artvis.domain.Review;
import taba5.Artvis.dto.ReviewDto;
import taba5.Artvis.repository.ExhibitionRepository;
import taba5.Artvis.repository.MemberRepository;
import taba5.Artvis.repository.ReviewRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final ExhibitionRepository exhibitionRepository;

    public ReviewDto saveReview(ReviewDto reviewDto) {
        Member member = memberRepository.findById(reviewDto.getMemberId())
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        Exhibition exhibition = exhibitionRepository.findById(reviewDto.getExhibitionId())
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        Review review = new Review(reviewDto.getTitle(), reviewDto.getContents(), reviewDto.getRating(), member, exhibition);
        reviewRepository.save(review);
        return review.toDto();
    }

    public List<ReviewDto> getExhibitionReview(Long exhibitionId) {
        Exhibition exhibition = exhibitionRepository.findById(exhibitionId)
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        List<Review> reviewList = reviewRepository.findAllByExhibition(exhibition);
        return reviewList.stream().map(Review::toDto).toList();
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
    public List<ReviewDto> getMemberReviewDto(Long memberId) {
        List<Review> reviewList = getMemberReview(memberId);
        return reviewList.stream().map(Review::toDto).toList();
    }
    public List<Review> getMemberReview(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        return reviewRepository.findAllByMember(member);
    }
}
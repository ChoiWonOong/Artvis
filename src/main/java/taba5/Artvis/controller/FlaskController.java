package taba5.Artvis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taba5.Artvis.domain.Exhibition.Exhibition;
import taba5.Artvis.domain.Member;
import taba5.Artvis.domain.Review;
import taba5.Artvis.dto.flask.ExhibitionContentsRequestDto;
import taba5.Artvis.dto.flask.FlaskResponseDto;
import taba5.Artvis.dto.flask.RatingRequestDto;
import taba5.Artvis.service.ExhibitionService;
import taba5.Artvis.service.FlaskService;
import taba5.Artvis.service.MemberService;
import taba5.Artvis.service.ReviewService;
import taba5.Artvis.util.SecurityUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/flask")
@Slf4j
public class FlaskController {
    private final FlaskService flaskService;
    private final ExhibitionService exhibitionService;
    private final ReviewService reviewService;
    private final MemberService memberService;

    @PostMapping("/get/collaborative")
    public ResponseEntity<FlaskResponseDto> getCollaborative() throws JsonProcessingException {
        RatingRequestDto flaskDto = new RatingRequestDto(SecurityUtil.getCurrentMemberId());
        return ResponseEntity.ok(flaskService.getCollaborative(flaskDto));
    }
    @PostMapping("/get/contentsbased")
    public ResponseEntity<FlaskResponseDto> getContentsBased() throws JsonProcessingException {
        Member member = memberService.getMe();

        List<Review> reviewList = reviewService.getMemberReview(member.getId());
        List<Exhibition> reviewExhibitionList = reviewList.stream().map(Review::getExhibition).toList();
        List<Long> reviewExhibitionIdList = reviewExhibitionList
                .stream().map(Exhibition::getId).toList();
        List<Long> exhibitionIdList = member.getHistory()
                .stream().map(Exhibition::getId).toList();

        List<Long> recommendExhibitionIdList = new ArrayList<>();
        recommendExhibitionIdList.addAll(exhibitionIdList);
        recommendExhibitionIdList.addAll(reviewExhibitionIdList);
        List<Long> result = recommendExhibitionIdList.stream().distinct().toList();

        ExhibitionContentsRequestDto flaskDto = new ExhibitionContentsRequestDto(result);
        return ResponseEntity.ok(flaskService.getContentsBased(flaskDto));
    }
}

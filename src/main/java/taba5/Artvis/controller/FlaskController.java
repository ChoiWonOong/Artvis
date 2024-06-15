package taba5.Artvis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taba5.Artvis.domain.Exhibition.Exhibition;
import taba5.Artvis.domain.Like.ExhibitionLike;
import taba5.Artvis.domain.Member;
import taba5.Artvis.domain.Review.Review;
import taba5.Artvis.dto.flask.ContentsDetailRequestDto;
import taba5.Artvis.dto.flask.ContentsHomeRequestDto;
import taba5.Artvis.dto.flask.FlaskResponseDto;
import taba5.Artvis.dto.flask.CollaborativeHomeRequestDto;
import taba5.Artvis.repository.HistoryRepository;
import taba5.Artvis.service.FlaskService;
import taba5.Artvis.service.LikeService;
import taba5.Artvis.service.MemberService;
import taba5.Artvis.service.ReviewService;
import taba5.Artvis.util.SecurityUtil;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recommend")
@Slf4j
public class FlaskController {
    private final FlaskService flaskService;
    private final ReviewService reviewService;
    private final MemberService memberService;
    private final LikeService likeService;
    private final HistoryRepository historyRepository;
    @PostMapping("/get/home/contents")
    public ResponseEntity<?> getContentsHome() throws JsonProcessingException{
        List<Long> historyAndLikes = memberService.getHistoryAndLikes(SecurityUtil.getCurrentMemberId())
                .stream().map(Exhibition::getId).toList();
        CollaborativeHomeRequestDto flaskDto = new CollaborativeHomeRequestDto(SecurityUtil.getCurrentMemberId());
        flaskDto.addExhibitionId(historyAndLikes);
        String url = "api/home1";
        return ResponseEntity.ok(flaskService.getCollaborativeHome(flaskDto,url));
    }
    @PostMapping("/get/home/collaborative")
    public ResponseEntity<FlaskResponseDto> getCollaborativeHome() throws JsonProcessingException {
        List<Long> historyAndLikes = memberService.getHistoryAndLikes(SecurityUtil.getCurrentMemberId())
                .stream().map(Exhibition::getId).toList();
        CollaborativeHomeRequestDto flaskDto = new CollaborativeHomeRequestDto(SecurityUtil.getCurrentMemberId());
        flaskDto.addExhibitionId(historyAndLikes);
        String url = "api/home2";
        return ResponseEntity.ok(flaskService.getCollaborativeHome(flaskDto, url));
    }
    @PostMapping("/get/contents/{id}")
    public ResponseEntity<FlaskResponseDto> getContentsBased(@PathVariable(name = "id") Long id) throws JsonProcessingException {
        ContentsDetailRequestDto flaskDto = new ContentsDetailRequestDto(SecurityUtil.getCurrentMemberId(), id);
        String url = "api/exhibition";
        return ResponseEntity.ok(flaskService.getContentsBased(flaskDto,url));
    }
}

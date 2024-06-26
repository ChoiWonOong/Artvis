package taba5.Artvis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import taba5.Artvis.Exception.ErrorResponse;
import taba5.Artvis.domain.Exhibition.Exhibition;
import taba5.Artvis.domain.Recommend.InitRecommendDto;
import taba5.Artvis.dto.Exhibition.ExhibitionResponseDto;
import taba5.Artvis.dto.HistoryDto;
import taba5.Artvis.dto.member.MyPageDto;
import taba5.Artvis.service.MemberService;
import taba5.Artvis.util.SecurityUtil;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    // 마이페이지 출력
    @GetMapping("/mypage")
    public ResponseEntity<MyPageDto> getMyPage(){
        return ResponseEntity.ok(memberService.getMyPage());
    }
    // 멤버 히스토리 출력
    @GetMapping("/history")
    public ResponseEntity<List<ExhibitionResponseDto>> getHistory(){
        return ResponseEntity.ok(memberService.getHistory(SecurityUtil.getCurrentMemberId())
                .stream().map(Exhibition::toResponseDto).toList());
    }
    @PostMapping("/recommend/init")
    public ResponseEntity<?> initRecommend(@RequestBody InitRecommendDto dto){
        try{
            return ResponseEntity.ok(memberService.initRecommend(SecurityUtil.getCurrentMemberId(), dto));
        }catch (RuntimeException e){
            return ErrorResponse.toResponseEntity(e, "BAD_REQUEST");
        }
    }
}

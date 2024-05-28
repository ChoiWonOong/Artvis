package taba5.Artvis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import taba5.Artvis.dto.HistoryDto;
import taba5.Artvis.dto.member.MyPageDto;
import taba5.Artvis.service.MemberService;
import taba5.Artvis.util.SecurityUtil;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/mypage")
    public ResponseEntity<MyPageDto> getMyPage(){
        return ResponseEntity.ok(memberService.getMyPage());
    }
    @GetMapping("/history")
    public ResponseEntity<HistoryDto> getHistory(){
        return ResponseEntity.ok(memberService.getHistory(SecurityUtil.getCurrentMemberId()));
    }
}

package taba5.Artvis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import taba5.Artvis.dto.member.MyPageDto;
import taba5.Artvis.service.MemberService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/mypage")
    public ResponseEntity<MyPageDto> getMyPage(){
        return ResponseEntity.ok(memberService.getMyPage());
    }
}

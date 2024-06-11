package taba5.Artvis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import taba5.Artvis.Exception.ErrorCode;
import taba5.Artvis.Exception.ErrorResponse;
import taba5.Artvis.Exception.RestApiException;
import taba5.Artvis.dto.member.MemberCreateRequestDto;
import taba5.Artvis.dto.member.MemberCreateResponseDto;
import taba5.Artvis.dto.member.MemberPasswordReassignDto;
import taba5.Artvis.dto.token.TokenDto;
import taba5.Artvis.dto.token.TokenRequestDto;
import taba5.Artvis.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<MemberCreateResponseDto> memberSignup(@RequestBody MemberCreateRequestDto memberCreateRequestDto) {
        return ResponseEntity.ok(authService.memberSignup(memberCreateRequestDto));
    }
    @PostMapping("/login")
    public ResponseEntity<?> memberLogin(@RequestBody MemberCreateRequestDto memberCreateRequestDto){
        try{
            return ResponseEntity.ok(authService.memberLogin(memberCreateRequestDto));
        }catch (RuntimeException e){
            return ErrorResponse.toResponseEntity(e, "BAD_CREDENTIALS_EXCEPTION");
        }
    }
    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }
    @PatchMapping("/logout")
    public ResponseEntity<String> logout(@AuthenticationPrincipal User user, @RequestBody TokenDto tokenDto) {
        return ResponseEntity.ok(authService.logout(tokenDto.getAccessToken(), user));
    }
    @PatchMapping("/reassign/password")
    public ResponseEntity<MemberPasswordReassignDto> passwordReassign(@RequestBody MemberPasswordReassignDto memberPasswordReassignDto){
        return ResponseEntity.ok(authService.passwordReassign(memberPasswordReassignDto));
    }
}

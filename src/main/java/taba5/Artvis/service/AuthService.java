package taba5.Artvis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taba5.Artvis.Exception.ErrorCode;
import taba5.Artvis.Exception.RestApiException;
import taba5.Artvis.domain.Member;
import taba5.Artvis.dto.member.MemberCreateRequestDto;
import taba5.Artvis.dto.member.MemberCreateResponseDto;
import taba5.Artvis.dto.member.MemberPasswordReassignDto;
import taba5.Artvis.dto.token.TokenDto;
import taba5.Artvis.dto.token.TokenRequestDto;
import taba5.Artvis.jwt.RefreshToken;
import taba5.Artvis.jwt.TokenProvider;
import taba5.Artvis.repository.MemberRepository;
import taba5.Artvis.repository.RefreshTokenRepository;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final BCryptPasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    public MemberCreateResponseDto memberSignup(MemberCreateRequestDto memberCreateRequestDto) {
        if (memberRepository.existsByNickname(memberCreateRequestDto.getNickname())) {
            throw new RestApiException(ErrorCode.ALREADY_EXIST_ERROR);
        }
        Member member = memberCreateRequestDto.toMember(passwordEncoder);
        return Member.memberToResponseDto(memberRepository.save(member));
    }
    @Transactional
    public TokenDto memberLogin(MemberCreateRequestDto memberCreateRequestDto) {
        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = memberCreateRequestDto.toAuthentication();
        System.out.println(authenticationToken.toString());
        TokenDto tokenDto = getToken(authenticationToken);
        System.out.println(tokenDto.getAccessToken());
        return tokenDto;
    }
    @Transactional
    public String logout(String accessToken, User user){
        String username = user.getUsername();
        refreshTokenRepository.deleteRefreshTokenByKey(username);

        return "logged out";
    }
    @Transactional
    public TokenDto reissue(TokenRequestDto tokenRequestDto) {
        // 1. Refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RestApiException(ErrorCode.EXPIRED_TOKEN_ERROR);
        }

        // 2. Access Token 에서 Member ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // 3. 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new RestApiException(ErrorCode.EXPIRED_TOKEN_ERROR));

        // 4. Refresh Token 일치하는지 검사
        if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new RestApiException(ErrorCode.NOT_EXIST_ERROR);
        }

        // 5. 새로운 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 6. 저장소 정보 업데이트
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        // 토큰 발급
        return tokenDto;
    }
    public TokenDto getToken(UsernamePasswordAuthenticationToken authenticationToken){
        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);
        System.out.println(tokenDto);
        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        // 5. 토큰 발급
        return tokenDto;
    }

    public MemberPasswordReassignDto passwordReassign(MemberPasswordReassignDto memberPasswordReassignDto){
        Optional<Member> optionalMember = memberRepository.findByUsername(memberPasswordReassignDto.getUsername());
        if(optionalMember.isPresent()){
            Member member = optionalMember.get();
            member.passwordReassign(passwordEncoder.encode(memberPasswordReassignDto.getPassword()));
            memberRepository.save(member);
            return new MemberPasswordReassignDto(memberPasswordReassignDto.getUsername(), true);
        }
        return new MemberPasswordReassignDto(memberPasswordReassignDto.getUsername(), false);
    }
}

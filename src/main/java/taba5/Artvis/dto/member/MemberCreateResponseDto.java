package taba5.Artvis.dto.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
public class MemberCreateResponseDto {
    private String username;
    private String nickname;

    public MemberCreateResponseDto(String username, String name) {
        this.username = username;
        this.nickname = name;
    }
}
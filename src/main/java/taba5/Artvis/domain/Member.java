package taba5.Artvis.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import taba5.Artvis.domain.Exhibition.Exhibition;
import taba5.Artvis.dto.HistoryDto;
import taba5.Artvis.dto.member.MemberCreateResponseDto;
import taba5.Artvis.dto.member.MyPageDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "member")
@NoArgsConstructor
public class    Member extends BaseEntity{
    @Getter
    @Id
    @Column(name ="member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Getter
    @Column(nullable = false)
    private String password;

    @Getter
    @Column(nullable = false)
    private String nickname;
    @Getter
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public Member(String username, String password, String nickname, Authority authority){
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.authority = authority;
    }
    public static MemberCreateResponseDto memberToResponseDto(Member member) {
        return new MemberCreateResponseDto(member.username, member.nickname);
    }
    public static User memberToUser(Member member){
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.authority.toString());
        return new User(
                String.valueOf(member.id),
                member.password,
                Collections.singleton(grantedAuthority)
        );
    }
    public void passwordReassign(String password){
        this.password = password;
    }
    public MyPageDto MemberToMyPageDto(){
        return new MyPageDto(this.username, this.nickname);
    }
}

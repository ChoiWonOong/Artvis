package taba5.Artvis.domain.Like;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import taba5.Artvis.domain.Member;

@Entity
public abstract class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    public Like(Member member) {
        this.member = member;
    }
}

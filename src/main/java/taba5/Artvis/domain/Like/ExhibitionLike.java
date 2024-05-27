package taba5.Artvis.domain.Like;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import taba5.Artvis.domain.Exhibition.Exhibition;
import taba5.Artvis.domain.Member;
import taba5.Artvis.dto.Like.ExhibitionLikeDto;

@Entity
@NoArgsConstructor
public class ExhibitionLike{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;
    @ManyToOne
    @JoinColumn(name = "exhibition_id")
    private Exhibition exhibition;

    public ExhibitionLike(Member member, Exhibition exhibition) {
        this.member = member;
        this.exhibition = exhibition;
    }
    public ExhibitionLikeDto toDto() {
        return new ExhibitionLikeDto(getMember().getId(), this.getExhibition().getId());
    }
    public Exhibition getExhibition() {
        return exhibition;
    }
    public Member getMember() {
        return member;
    }
}

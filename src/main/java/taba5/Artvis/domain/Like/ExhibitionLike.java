package taba5.Artvis.domain.Like;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import taba5.Artvis.domain.Exhibition.Exhibition;
import taba5.Artvis.domain.Member;
import taba5.Artvis.dto.Like.ExhibitionLikeDto;

@Entity
@NoArgsConstructor
@Getter
@IdClass(LikeId.class)
public class ExhibitionLike{
    @ManyToOne
    @Id
    private Member member;

    @ManyToOne
    @JoinColumn(name = "exhibition_id")
    @Id
    private Exhibition exhibition;

    public ExhibitionLike(Member member, Exhibition exhibition) {
        this.member = member;
        this.exhibition = exhibition;
    }
    public ExhibitionLikeDto toDto() {
        return new ExhibitionLikeDto(getMember().getId(), this.getExhibition().getId());
    }
}

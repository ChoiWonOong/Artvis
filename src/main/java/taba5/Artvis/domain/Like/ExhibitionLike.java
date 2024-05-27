package taba5.Artvis.domain.Like;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;
import taba5.Artvis.domain.Exhibition.Exhibition;
import taba5.Artvis.domain.Member;
import taba5.Artvis.dto.Like.ExhibitionLikeDto;

@Entity
@NoArgsConstructor
public class ExhibitionLike extends Like{
    @ManyToOne
    @JoinColumn(name = "exhibition_id")
    private Exhibition exhibition;

    public ExhibitionLike(Member member, Exhibition exhibition) {
        super(member);
        this.exhibition = exhibition;
    }
    public ExhibitionLikeDto toDto() {
        return new ExhibitionLikeDto(super.getMember().getId(), this.getExhibition().getId());
    }
    public Exhibition getExhibition() {
        return exhibition;
    }
}

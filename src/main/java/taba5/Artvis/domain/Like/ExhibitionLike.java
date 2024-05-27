package taba5.Artvis.domain.Like;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import taba5.Artvis.domain.Exhibition.Exhibition;
import taba5.Artvis.domain.Member;
import taba5.Artvis.dto.LikeDto;

public class ExhibitionLike extends Like{
    @ManyToOne
    @Column(name = "exhibition_id")
    private Exhibition exhibition;

    public ExhibitionLike(Member member, Exhibition exhibition) {
        super(member);
        this.exhibition = exhibition;
    }
    public LikeDto toDto() {
        return new LikeDto(this.getMember().getId(), this.getExhibition().getId());
    }
}

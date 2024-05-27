package taba5.Artvis.domain.Like;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;
import taba5.Artvis.domain.Art.Artwork;
import taba5.Artvis.domain.Member;
import taba5.Artvis.dto.Like.ArtworkLikeDto;
@Entity
@NoArgsConstructor
public class ArtworkLike extends Like{
    @ManyToOne
    @JoinColumn(name = "artwork_id")
    private Artwork artwork;

    public ArtworkLike(Member member, Artwork artwork) {
        super(member);
        this.artwork = artwork;
    }
    public ArtworkLikeDto toDto() {
        return new ArtworkLikeDto(super.getMember().getId(), this.getArtwork().getId());
    }
    public Artwork getArtwork() {
        return artwork;
    }
}

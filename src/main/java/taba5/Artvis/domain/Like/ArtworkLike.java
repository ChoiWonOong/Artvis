package taba5.Artvis.domain.Like;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import taba5.Artvis.domain.Art.Artwork;
import taba5.Artvis.domain.Member;
import taba5.Artvis.dto.Like.ArtworkLikeDto;
@Entity
@NoArgsConstructor
public class ArtworkLike{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;
    @ManyToOne
    @JoinColumn(name = "artwork_id")
    private Artwork artwork;

    public ArtworkLike(Member member, Artwork artwork) {
        this.member = member;
        this.artwork = artwork;
    }
    public ArtworkLikeDto toDto() {
        return new ArtworkLikeDto(getMember().getId(), this.getArtwork().getId());
    }
    public Artwork getArtwork() {
        return artwork;
    }
    public Member getMember() {
        return member;
    }
}

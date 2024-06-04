package taba5.Artvis.domain.Art;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import taba5.Artvis.domain.Detail;
import taba5.Artvis.domain.Image;
import taba5.Artvis.dto.Artwork.ArtworkDto;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Artwork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "artwork_id")
    private List<Detail> detailList = new ArrayList<>();

    @Setter
    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @Builder
    public Artwork(String title, Artist artist, List<Detail> detailList){
        this.title = title;
        this.artist = artist;
        this.detailList = detailList;
    }

    public ArtworkDto toDto() {
        return ArtworkDto.builder()
                .id(id)
                .title(title)
                .artistName(artist.getName())
                .detail(detailList.stream().map(Detail::toDto).toList())
                .build();
    }
}

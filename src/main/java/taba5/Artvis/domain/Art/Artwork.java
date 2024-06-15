package taba5.Artvis.domain.Art;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import taba5.Artvis.domain.Detail;
import taba5.Artvis.domain.Exhibition.Exhibition;
import taba5.Artvis.domain.Image;
import taba5.Artvis.dto.Artwork.ArtworkDto;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Artwork {
    @Column(name = "artwork_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    private String imageUrl;
    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;*/
    private String artist;
/*
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "artwork_id")
    private List<Detail> detailList = new ArrayList<>();
*/
    @Column(length = 1000)
    private String detail;

    @Column(name = "exhibition")
    private Long exhibitionId;

    @Setter
    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @Builder
    public Artwork(String title, /*Artist artist*/ String artist, /*List<Detail> detailList*/ String detail, String imageUrl){
        this.title = title;
        this.artist = artist;
        //this.detailList = detailList;
        this.imageUrl = imageUrl;
        this.detail = detail;

    }

    public ArtworkDto toDto() {
        return ArtworkDto.builder()
                .id(id)
                .title(title)
                .artistName(artist)
                //.detail(detailList.stream().map(Detail::toDto).toList())
                .detail(detail)
                .imageUrl(imageUrl)
                .build();
    }
}

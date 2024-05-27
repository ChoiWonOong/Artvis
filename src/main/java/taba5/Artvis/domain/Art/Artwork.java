package taba5.Artvis.domain.Art;

import com.fasterxml.jackson.core.JsonToken;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import taba5.Artvis.domain.Detail;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Artwork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "details")
    private List<Detail> detailList;

    @Builder
    public Artwork(String title, Artist artist, List<Detail> detailList){
        this.title = title;
        this.artist = artist;
        this.detailList = detailList;
    }
}

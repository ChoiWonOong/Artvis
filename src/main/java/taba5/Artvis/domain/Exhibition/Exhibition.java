package taba5.Artvis.domain.Exhibition;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import taba5.Artvis.domain.Detail;
import taba5.Artvis.domain.Gallery;
import taba5.Artvis.domain.Image;
import taba5.Artvis.dto.Exhibition.ExhibitionHistoryDto;
import taba5.Artvis.dto.Exhibition.ExhibitionResponseDto;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Exhibition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String location;
    private String startDate;
    private String endDate;
    @ManyToOne
    @JoinColumn(name = "gallery_id")
    private Gallery gallery;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "details")
    private List<Detail> detailList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @OneToOne
    @JoinColumn(name = "thumbnail_id")
    private Image thumbnail;

    public Exhibition(String title, String location, String startDate, String endDate, List<Detail> detailList) {
        this.title = title;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.detailList = detailList;
    }

    public ExhibitionResponseDto toResponseDto(){
        return ExhibitionResponseDto.builder()
                .title(title)
                .location(location)
                .startDate(startDate)
                .endDate(endDate)
                .detailList(detailList.stream().map(Detail::toDto).toList())
                .build();
    }
    public ExhibitionHistoryDto toHistoryDto(){
        return ExhibitionHistoryDto.builder()
                .title(title)
                .galleryName(gallery.getName())
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}
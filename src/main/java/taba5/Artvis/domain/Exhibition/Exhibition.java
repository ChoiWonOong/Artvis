package taba5.Artvis.domain.Exhibition;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import taba5.Artvis.domain.*;
import taba5.Artvis.dto.Exhibition.ExhibitionHistoryDto;
import taba5.Artvis.dto.Exhibition.ExhibitionResponseDto;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Exhibition extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String location;
    private String startDate;
    private String endDate;

    @Setter
    @ManyToOne
    @JoinColumn(name = "gallery_id")
    private Gallery gallery;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "details")
    private List<Detail> detailList = new ArrayList<>();

    @Setter
    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "history")
    private List<Exhibition> historyList = new ArrayList<>();

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
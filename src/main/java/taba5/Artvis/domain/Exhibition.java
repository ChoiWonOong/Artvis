package taba5.Artvis.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import taba5.Artvis.dto.DetailDto;
import taba5.Artvis.dto.Exhibition.ExhibitionResponseDto;

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
    @Setter
    @OneToMany(mappedBy = "exhibition")
    private List<ExhibitionTag> exhibitionTagList;
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "details")
    private List<Detail> detailList;
    @Builder
    public Exhibition(String title, String location, String startDate, String endDate, List<ExhibitionTag> exhibitionTagList, List<DetailDto> detailList){
        this.title = title;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.exhibitionTagList = exhibitionTagList;
        this.detailList = detailList.stream().map(DetailDto::toEntity).toList();
    }
    public void setDetailList(List<DetailDto> detailList){
        this.detailList = detailList.stream().map(DetailDto::toEntity).toList();
    }
    public ExhibitionResponseDto toResponseDto(){
        return ExhibitionResponseDto.builder()
                .title(title)
                .location(location)
                .startDate(startDate)
                .endDate(endDate)
                .tagList(exhibitionTagList.stream().map(ExhibitionTag::getTag).map(Tag::getTagName).toList())
                .detailList(detailList.stream().map(Detail::toDto).toList())
                .build();
    }
}
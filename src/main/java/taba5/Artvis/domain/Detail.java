package taba5.Artvis.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;
import taba5.Artvis.domain.Exhibition.Exhibition;
import taba5.Artvis.dto.DetailDto;

import java.util.List;

@Entity
@NoArgsConstructor
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String attribute;
    private String contents;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exhibition_id")
    private Exhibition exhibition;
    @Builder
    public Detail(String attribute, String contents){
        this.attribute = attribute;
        this.contents = contents;
    }
    public DetailDto toDto(){
        return DetailDto.builder()
                .attribute(attribute)
                .contents(contents)
                .build();
    }
    public static List<Detail> toEntityList(List<DetailDto> detailDtoList){
        return detailDtoList.stream().map(DetailDto::toEntity).toList();
    }
}
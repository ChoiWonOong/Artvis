package taba5.Artvis.dto.Exhibition;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import taba5.Artvis.dto.DetailDto;

import java.util.List;

@Getter
@Setter
public class ExhibitionResponseDto {
    private Long id;
    private String title;
    private String location;
    private String startDate;
    private String endDate;
    private List<String> tagList;
    private List<DetailDto> detailList;
    @Builder
    public ExhibitionResponseDto(Long id, String title, String location, String startDate, String endDate, List<String> tagList, List<DetailDto> detailList){
        this.id = id;
        this.title = title;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tagList = tagList;
        this.detailList = detailList;
    }
}

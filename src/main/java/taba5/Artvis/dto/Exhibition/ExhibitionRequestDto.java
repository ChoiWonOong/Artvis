package taba5.Artvis.dto.Exhibition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import taba5.Artvis.domain.Exhibition;
import taba5.Artvis.dto.DetailDto;

import java.util.List;

@AllArgsConstructor
@Getter
public class ExhibitionRequestDto {
    private String title;
    private String location;
    private String startDate;
    private String endDate;
    private List<String> exhibitionTagList;
    private List<DetailDto> detailList;
    public Exhibition toEntity(){
        return Exhibition.builder()
                .title(title)
                .location(location)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}

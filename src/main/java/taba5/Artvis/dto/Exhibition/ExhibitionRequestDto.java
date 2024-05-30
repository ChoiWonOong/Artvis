package taba5.Artvis.dto.Exhibition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import taba5.Artvis.dto.DetailDto;

import java.util.List;
@Slf4j
@AllArgsConstructor
@Getter
public class ExhibitionRequestDto {
    private String title;
    private String location;
    private String startDate;
    private String endDate;
    private List<String> exhibitionTagList;
    private List<DetailDto> detailList;
    private String imageUrl;

    public void printDto(){
        log.info("title: {}", title);
        log.info("location: {}", location);
        log.info("startDate: {}", startDate);
        log.info("endDate: {}", endDate);
        log.info("exhibitionTagList: {}", exhibitionTagList);
        log.info("detailList: {}", detailList);
    }
}

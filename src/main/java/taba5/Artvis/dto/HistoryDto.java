package taba5.Artvis.dto;

import lombok.Getter;
import lombok.Setter;
import taba5.Artvis.dto.Exhibition.ExhibitionHistoryDto;

import java.util.List;

@Getter
@Setter
public class HistoryDto {
    private List<ExhibitionHistoryDto> history;
}

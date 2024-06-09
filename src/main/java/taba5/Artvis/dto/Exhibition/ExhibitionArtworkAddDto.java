package taba5.Artvis.dto.Exhibition;

import lombok.Getter;

import java.util.List;

@Getter
public class ExhibitionArtworkAddDto {
    private Long exhibitionId;
    private List<Long> artworkIds;
}

package taba5.Artvis.dto.Artwork;

import lombok.Builder;
import lombok.Getter;
import taba5.Artvis.dto.DetailDto;

import java.util.List;

@Getter
public class ArtworkDto {
    private Long artwork_id;
    private String title;
    private String artist;
    private List<DetailDto> detailList;
    @Builder
    public ArtworkDto(Long artwork_id, String title, String artist, List<DetailDto> detailList){
        this.artwork_id = artwork_id;
        this.title = title;
        this.artist = artist;
        this.detailList = detailList;
    }
}

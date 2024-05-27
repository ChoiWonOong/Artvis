package taba5.Artvis.dto.Artwork;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ArtworkDto {
    private Long artwork_id;
    private String title;
    private String artist;
    private List<String> detailList;
    @Builder
    public ArtworkDto(Long artwork_id, String title, String artist, List<String> detailList){
        this.artwork_id = artwork_id;
        this.title = title;
        this.artist = artist;
        this.detailList = detailList;
    }
}

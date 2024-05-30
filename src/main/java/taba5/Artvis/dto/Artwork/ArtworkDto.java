package taba5.Artvis.dto.Artwork;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import taba5.Artvis.dto.DetailDto;
import taba5.Artvis.dto.Image.ImageResponseDto;

import java.util.List;

@Getter
@NoArgsConstructor
public class ArtworkDto extends ImageResponseDto {
    private Long artwork_id;
    private String title;
    private String artist;
    private List<DetailDto> detailList;
    @Setter
    private Boolean isLiked;
    @Builder
    public ArtworkDto(Long artwork_id, String title, String artist, List<DetailDto> detailList){
        this.artwork_id = artwork_id;
        this.title = title;
        this.artist = artist;
        this.detailList = detailList;
    }
}

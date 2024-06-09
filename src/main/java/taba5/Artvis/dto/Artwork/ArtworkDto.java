package taba5.Artvis.dto.Artwork;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import taba5.Artvis.dto.DetailDto;
import taba5.Artvis.dto.Image.ImageResponseDto;
import taba5.Artvis.domain.Art.Artwork;
import java.util.List;

@Getter
@NoArgsConstructor
public class ArtworkDto extends ImageResponseDto {
    private Long id;
    private String title;
    private String artistName;
    private String imageUrl;
    /*private List<DetailDto> detail;
    */
    private String detail;
    @Setter
    private Boolean isLiked;
    @Builder
    public ArtworkDto(Long id, String title, String artistName, /*List<DetailDto> detail*/ String detail, String imageUrl){
        this.id = id;
        this.title = title;
        this.artistName = artistName;
        this.detail = detail;
        this.imageUrl = imageUrl;
    }
    public Artwork toEntity(){
        return Artwork.builder()
                .title(title)
                .detail(detail)
                .artist(artistName)
                .imageUrl(imageUrl)
                .build();
    }
}

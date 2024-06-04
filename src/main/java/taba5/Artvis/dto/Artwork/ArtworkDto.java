package taba5.Artvis.dto.Artwork;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import taba5.Artvis.domain.Art.Artwork;
import taba5.Artvis.dto.DetailDto;
import taba5.Artvis.dto.Image.ImageResponseDto;

import java.util.List;

@Getter
@NoArgsConstructor
public class ArtworkDto extends ImageResponseDto {
    private Long id;
    private String title;
    private String artistName;
    @ManyToOne(fetch = FetchType.EAGER)
    private List<DetailDto> detail;
    @Setter
    private Boolean isLiked;
    @Builder
    public ArtworkDto(Long id, String title, String artistName, List<DetailDto> detail){
        this.id = id;
        this.title = title;
        this.artistName = artistName;
        this.detail = detail;
    }
    public Artwork toEntity(){
        return Artwork.builder()
                .title(title)
                .build();
    }
}

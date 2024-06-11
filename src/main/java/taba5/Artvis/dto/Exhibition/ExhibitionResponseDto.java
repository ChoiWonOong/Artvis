package taba5.Artvis.dto.Exhibition;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import taba5.Artvis.dto.Artwork.ArtworkDto;
import taba5.Artvis.dto.DetailDto;
import taba5.Artvis.dto.Image.ImageResponseDto;
import taba5.Artvis.dto.ReviewDto;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ExhibitionResponseDto {
    private Long id;
    private String title;
    private List<ArtworkDto> artworkDtos;
    private String location;
    private String startDate;
    private String endDate;
    private List<String> tagList;
    private String detail;
    private String imageUrl;
    private Boolean isLiked;
    private List<ReviewDto> reviewList = new ArrayList<>();
    @Builder
    public ExhibitionResponseDto(Long id, String title,List<ArtworkDto> artworkDtos, String location, String startDate, String endDate, List<String> tagList, String detail, String imageUrl){
        this.id = id;
        this.title = title;
        this.artworkDtos = artworkDtos;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tagList = tagList;
        this.detail = detail;
        this.imageUrl = imageUrl;
    }
}

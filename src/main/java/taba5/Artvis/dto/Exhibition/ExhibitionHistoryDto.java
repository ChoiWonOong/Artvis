package taba5.Artvis.dto.Exhibition;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.ErrorResponse;

@Getter
@NoArgsConstructor
public class ExhibitionHistoryDto {
    private Long exhibitionId;
    private String title;
    private String startDate;
    private String endDate;
    private String galleryName;
    private String imageUrl;
    private Boolean isLiked;

    @Builder
    public ExhibitionHistoryDto(Long exhibitionId, String title, String startDate, String endDate, String galleryName, String imageUrl, Boolean isLiked){
        this.exhibitionId = exhibitionId;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.galleryName = galleryName;
        this.imageUrl = imageUrl;
        this.isLiked = isLiked;
    }
}

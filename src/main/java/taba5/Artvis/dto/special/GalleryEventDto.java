package taba5.Artvis.dto.special;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import taba5.Artvis.domain.Special.GalleryEvent;
@Getter
@Builder
@AllArgsConstructor
public class GalleryEventDto {
    private Long id;
    private String title;
    private String description;
    private String location;
    private String startDate;
    private String endDate;
    private String organizer;
    private String imageUrl;
    private Long galleryId;

    public GalleryEvent toEntity(){
        return GalleryEvent.builder()
                .title(title)
                .description(description)
                .location(location)
                .startDate(startDate)
                .endDate(endDate)
                .organizer(organizer)
                .imageUrl(imageUrl)
                .build();
    }
}

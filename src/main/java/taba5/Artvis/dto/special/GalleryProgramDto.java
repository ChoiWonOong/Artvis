package taba5.Artvis.dto.special;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import taba5.Artvis.domain.Special.GalleryProgram;

@Getter
@Builder
@AllArgsConstructor
public class GalleryProgramDto {
    private Long id;
    private String title;
    private String description;
    private String location;
    private String startDate;
    private String endDate;
    private String organizer;
    private Long galleryId;
    public GalleryProgram toEntity(){
        return GalleryProgram.builder()
                .title(title)
                .description(description)
                .location(location)
                .startDate(startDate)
                .endDate(endDate)
                .organizer(organizer)
                .build();
    }
}

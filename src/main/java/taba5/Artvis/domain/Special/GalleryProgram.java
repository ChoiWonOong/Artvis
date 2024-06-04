package taba5.Artvis.domain.Special;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.NoArgsConstructor;
import taba5.Artvis.dto.special.GalleryProgramDto;

@Entity
@NoArgsConstructor
public class GalleryProgram  extends GallerySpecial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder
    public GalleryProgram(String title, String description, String location, String startDate, String endDate, String organizer) {
        super(title, description, location, startDate, endDate, organizer);
    }
    public GalleryProgramDto toDto(){
        return GalleryProgramDto.builder()
                .id(id)
                .title(title)
                .description(description)
                .location(location)
                .startDate(startDate)
                .endDate(endDate)
                .organizer(organizer)
                .build();
    }
}

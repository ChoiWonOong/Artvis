package taba5.Artvis.domain.Special;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.NoArgsConstructor;
import taba5.Artvis.dto.special.GalleryEventDto;

@Entity
@NoArgsConstructor
public class GalleryEvent extends GallerySpecial{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder
    public GalleryEvent(String title, String description, String location, String startDate, String endDate, String organizer) {
        super(title, description, location, startDate, endDate, organizer);
    }
    public GalleryEventDto toDto(){
        return GalleryEventDto.builder()
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

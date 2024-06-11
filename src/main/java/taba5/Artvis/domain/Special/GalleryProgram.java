package taba5.Artvis.domain.Special;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import taba5.Artvis.domain.Gallery;
import taba5.Artvis.dto.special.GalleryProgramDto;

@Entity
@NoArgsConstructor
public class GalleryProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    protected String title;
    protected String description;
    protected String location;
    protected String startDate;
    protected String endDate;
    protected String organizer;
    protected String imageUrl;

    @Setter
    @ManyToOne
    @JoinColumn(name = "gallery_id")
    private Gallery gallery;

    @Builder
    public GalleryProgram(String title, String description, String location, String startDate, String endDate, String organizer, String imageUrl, Gallery gallery) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.organizer = organizer;
        this.imageUrl = imageUrl;
        this.gallery = gallery;
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
                .imageUrl(imageUrl)
                .build();
    }
}

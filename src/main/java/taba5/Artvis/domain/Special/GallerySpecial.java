package taba5.Artvis.domain.Special;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.NoArgsConstructor;
import lombok.Setter;
import taba5.Artvis.domain.Gallery;
import taba5.Artvis.domain.Image;

@NoArgsConstructor
public abstract class GallerySpecial {
    protected String title;
    protected String description;
    protected String location;
    protected String startDate;
    protected String endDate;
    protected String organizer;

    @Setter
    @ManyToOne
    @Column(name = "gallery_id")
    private Gallery gallery;

    @Setter
    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    public GallerySpecial(String title, String description, String location, String startDate, String endDate, String organizer) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.organizer = organizer;
    }
}

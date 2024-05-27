package taba5.Artvis.domain.Event;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import taba5.Artvis.domain.Gallery;

public abstract class GallerySpecial {
    private String title;
    private String description;
    private String location;
    private String startDate;
    private String endDate;
    private String organizer;
    @ManyToOne
    @Column(name = "gallery_id")
    private Gallery gallery;
}

package taba5.Artvis.domain.Event;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class GalleryProgram  extends GallerySpecial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}

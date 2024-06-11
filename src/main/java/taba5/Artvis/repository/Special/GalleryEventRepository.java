package taba5.Artvis.repository.Special;

import org.springframework.data.jpa.repository.JpaRepository;
import taba5.Artvis.domain.Special.GalleryEvent;

import java.util.List;

public interface GalleryEventRepository extends JpaRepository<GalleryEvent, Long> {
    List<GalleryEvent> findByTitleContaining(String keyword);
}

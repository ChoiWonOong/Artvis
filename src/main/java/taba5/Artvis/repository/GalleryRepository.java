package taba5.Artvis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taba5.Artvis.domain.Gallery;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {

}

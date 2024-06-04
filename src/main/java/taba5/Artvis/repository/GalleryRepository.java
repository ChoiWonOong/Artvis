package taba5.Artvis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taba5.Artvis.domain.Gallery;

import java.util.List;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {

    List<Gallery> findByNameContaining(String keyword);
}

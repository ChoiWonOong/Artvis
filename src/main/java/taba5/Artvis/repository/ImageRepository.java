package taba5.Artvis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taba5.Artvis.domain.Image;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByUrl(String url);
}

package taba5.Artvis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taba5.Artvis.domain.Art.Artwork;

public interface ArtworkRepository extends JpaRepository<Artwork, Long> {
}

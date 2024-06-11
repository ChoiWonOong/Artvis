package taba5.Artvis.repository.Special;

import org.springframework.data.jpa.repository.JpaRepository;
import taba5.Artvis.domain.Special.GalleryProgram;

import java.util.List;

public interface GalleryProgramRepository extends JpaRepository<GalleryProgram, Long> {
    List<GalleryProgram> findByTitleContaining(String keyword);
}

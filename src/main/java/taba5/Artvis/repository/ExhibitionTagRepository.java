package taba5.Artvis.repository;

import taba5.Artvis.domain.ExhibitionTag;
import org.springframework.data.jpa.repository.JpaRepository;
import taba5.Artvis.domain.Tag;

import java.util.Optional;

public interface ExhibitionTagRepository extends JpaRepository<ExhibitionTag, Long>{
    Optional<ExhibitionTag> findByTag(Tag tag);
}

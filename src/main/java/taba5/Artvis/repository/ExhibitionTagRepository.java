package taba5.Artvis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taba5.Artvis.domain.Exhibition.Exhibition;
import taba5.Artvis.domain.Exhibition.ExhibitionTag;
import taba5.Artvis.domain.Exhibition.Tag;

import java.util.List;

public interface ExhibitionTagRepository extends JpaRepository<ExhibitionTag, Long>{
    List<ExhibitionTag> findByTag(Tag tag);
    List<ExhibitionTag> findByExhibition(Exhibition exhibition);
}
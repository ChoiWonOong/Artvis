package taba5.Artvis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taba5.Artvis.domain.Exhibition.Exhibition;

public interface ExhibitionRepository extends JpaRepository<Exhibition, Long> {
}

package taba5.Artvis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;
import taba5.Artvis.domain.Exhibition.Exhibition;

import java.util.List;
import java.util.Optional;

public interface ExhibitionRepository extends JpaRepository<Exhibition, Long> {
    List<Exhibition> findByTitleContaining(@RequestParam("keyword") String keyword);
    @Query("select e from Exhibition e where e.isDummy = true")
    List<Exhibition> findByDummyIsTrue();
    Optional<Exhibition> findByTitle(String title);
}

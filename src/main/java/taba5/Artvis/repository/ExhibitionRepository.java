package taba5.Artvis.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;
import taba5.Artvis.domain.Exhibition.Exhibition;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface ExhibitionRepository extends JpaRepository<Exhibition, Long> {
    Page<Exhibition> findByOrderById(Pageable pageable);
    List<Exhibition> findByTitleContainingAndIsDummy(@RequestParam("keyword") String keyword, boolean isDummy);
    @Query("select e from Exhibition e where e.isDummy = true")
    List<Exhibition> findByDummyIsTrue();
    Optional<Exhibition> findByTitle(String title);
}

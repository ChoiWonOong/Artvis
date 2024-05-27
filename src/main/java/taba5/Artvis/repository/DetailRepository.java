package taba5.Artvis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taba5.Artvis.domain.Detail;
import taba5.Artvis.domain.Exhibition.Exhibition;

import java.util.Optional;

public interface DetailRepository extends JpaRepository<Detail, Long> {
}

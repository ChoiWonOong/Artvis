package taba5.Artvis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taba5.Artvis.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

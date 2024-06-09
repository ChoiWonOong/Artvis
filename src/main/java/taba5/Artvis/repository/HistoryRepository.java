package taba5.Artvis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taba5.Artvis.domain.History.History;
import taba5.Artvis.domain.History.HistoryId;
import taba5.Artvis.domain.Member;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, HistoryId>{
    List<History> findByMember(Member member);
}

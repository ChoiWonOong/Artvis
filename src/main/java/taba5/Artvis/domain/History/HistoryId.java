package taba5.Artvis.domain.History;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import taba5.Artvis.domain.Exhibition.Exhibition;
import taba5.Artvis.domain.Member;

@Entity
public class HistoryId {
    @Id
    @ManyToOne
    private Member member;
    @Id
    @ManyToOne
    private Exhibition exhibition;
}

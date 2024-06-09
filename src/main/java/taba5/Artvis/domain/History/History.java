package taba5.Artvis.domain.History;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import taba5.Artvis.domain.Exhibition.Exhibition;
import taba5.Artvis.domain.Member;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(HistoryId.class)
public class History {
    @Id
    @ManyToOne
    @Column(name = "member")
    private Member member;
    @Getter
    @Id
    @ManyToOne
    @Column(name = "exhibition")
    private Exhibition exhibition;

}

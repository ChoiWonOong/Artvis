package taba5.Artvis.domain.Recommend;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import taba5.Artvis.domain.Exhibition.Exhibition;
import taba5.Artvis.domain.Member;

@Entity
@Getter
@IdClass(RecommendId.class)
public class Recommend {
    @Id
    @ManyToOne
    private Member memberId;

    @Id
    @ManyToOne
    private Exhibition exhibitionId;
}

package taba5.Artvis.domain.Recommend;

import lombok.Data;
import taba5.Artvis.domain.Exhibition.Exhibition;
import taba5.Artvis.domain.Member;

@Data
public class RecommendId {
    private Member memberId;
    private Exhibition exhibitionId;
}

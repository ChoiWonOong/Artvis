package taba5.Artvis.domain.Recommend;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class InitRecommendDto {
    @Setter
    Long memberId;
    List<Long> exhibitionIdList;
}

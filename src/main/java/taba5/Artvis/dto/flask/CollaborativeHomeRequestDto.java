package taba5.Artvis.dto.flask;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CollaborativeHomeRequestDto extends FlaskRequestDto{
    private Long userId;
    private List<Long> exhibitionId_list = new ArrayList<>();

    public CollaborativeHomeRequestDto(Long userId){
        this.userId = userId;
    }
    public void addExhibitionId(List<Long> exhibitionIdList){
        exhibitionId_list.addAll(exhibitionIdList);
    }
}
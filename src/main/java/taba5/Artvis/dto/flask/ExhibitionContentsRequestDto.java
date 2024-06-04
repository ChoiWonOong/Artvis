package taba5.Artvis.dto.flask;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ExhibitionContentsRequestDto extends FlaskRequestDto{
    List<Long> exhibitionIdList = new ArrayList<>();
    public void addExhibitionId(Long exhibitionId){
        exhibitionIdList.add(exhibitionId);
    }
}
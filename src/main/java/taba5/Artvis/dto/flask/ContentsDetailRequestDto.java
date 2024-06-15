package taba5.Artvis.dto.flask;

public class ContentsDetailRequestDto extends FlaskRequestDto{
    public ContentsDetailRequestDto(Long userId, Long exhibitionId){
        this.userId = userId;
        this.exhibitionId = exhibitionId;
    }
    Long userId;
    Long exhibitionId;
}

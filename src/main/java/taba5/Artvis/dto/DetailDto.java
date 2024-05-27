package taba5.Artvis.dto;

import lombok.Builder;
import lombok.Getter;
import taba5.Artvis.domain.Detail;

@Getter
public class DetailDto {
    private String attribute;
    private String contents;
    public Detail toEntity(){
        return new Detail(attribute, contents);
    }
    @Builder
    public DetailDto(String attribute, String contents){
        this.attribute = attribute;
        this.contents = contents;
    }
}

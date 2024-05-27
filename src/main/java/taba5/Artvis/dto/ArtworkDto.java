package taba5.Artvis.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ArtworkDto {
    private Long artwork_id;
    private String title;
    private String artist;
    private List<DetailDto> detailList;
}

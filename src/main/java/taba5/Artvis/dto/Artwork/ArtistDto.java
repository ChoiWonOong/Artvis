package taba5.Artvis.dto.Artwork;

import lombok.Builder;
import lombok.Getter;
import taba5.Artvis.domain.Art.Artist;

@Getter
public class ArtistDto {
    private Long id;
    private String name;
    private String description;
    private String birthDate;
    private String deathDate;
    private String nationality;

    public Artist toEntity(){
        return new Artist(name, description, birthDate, deathDate, nationality);
    }
    @Builder
    public ArtistDto(Long id, String name, String description, String birthDate, String deathDate, String nationality){
        this.id = id;
        this.name = name;
        this.description = description;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.nationality = nationality;
    }
}

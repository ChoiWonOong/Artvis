package taba5.Artvis.domain.Art;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import taba5.Artvis.dto.Artwork.ArtistDto;

@Entity
@Getter
@NoArgsConstructor
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String birthDate;
    private String deathDate;
    private String nationality;

    @Builder
    public Artist(String name, String description, String birthDate, String deathDate, String nationality) {
        this.name = name;
        this.description = description;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.nationality = nationality;
    }
    public ArtistDto toDto() {
        return ArtistDto.builder()
                .id(id)
                .name(name)
                .description(description)
                .birthDate(birthDate)
                .deathDate(deathDate)
                .nationality(nationality)
                .build();
    }
}

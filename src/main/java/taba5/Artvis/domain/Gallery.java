package taba5.Artvis.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import taba5.Artvis.dto.GalleryDto;

@Entity
@NoArgsConstructor
@Getter
public class Gallery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String location;
    private String phoneNumber;
    private String email;
    private String homepage;

    @Builder
    public Gallery(String name, String description, String location, String phoneNumber, String email, String homepage) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.homepage = homepage;
    }
    public GalleryDto toDto(){
        return GalleryDto.builder()
                .id(id)
                .name(name)
                .description(description)
                .location(location)
                .phoneNumber(phoneNumber)
                .email(email)
                .homepage(homepage)
                .build();
    }
}

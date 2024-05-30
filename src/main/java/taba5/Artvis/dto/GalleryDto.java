package taba5.Artvis.dto;

import lombok.Builder;
import lombok.Getter;
import taba5.Artvis.domain.Gallery;

@Getter
public class GalleryDto {
    private Long id;
    private String name;
    private String description;
    private String location;
    private String phoneNumber;
    private String email;
    private String homepage;

    public Gallery toEntity(){
        return Gallery.builder()
                .name(name)
                .description(description)
                .location(location)
                .phoneNumber(phoneNumber)
                .email(email)
                .homepage(homepage)
                .build();
    }
    @Builder
    public GalleryDto(Long id, String name, String description, String location, String phoneNumber, String email, String homepage) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.homepage = homepage;
    }
}

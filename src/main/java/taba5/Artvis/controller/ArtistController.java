package taba5.Artvis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taba5.Artvis.dto.Artwork.ArtistDto;
import taba5.Artvis.service.ArtistService;

@RequestMapping("/artist")
@RestController
@RequiredArgsConstructor
public class ArtistController {
    private final ArtistService artistService;

    @PostMapping("/save")
    public void saveArtist(@RequestBody ArtistDto artistDto){
        artistService.save(artistDto);
    }
    @GetMapping("/{id}")
    public ArtistDto getArtist(@PathVariable Long id){
        return artistService.getArtist(id);
    }
}

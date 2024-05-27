package taba5.Artvis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taba5.Artvis.dto.ArtworkDto;
import taba5.Artvis.service.ArtworkService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/artwork")
public class ArtworkController {
    private final ArtworkService artworkService;

    @PostMapping("/save")
    public void saveArtwork(@RequestBody ArtworkDto artworkDto){
        artworkService.saveArtwork(artworkDto);
    }
}

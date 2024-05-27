package taba5.Artvis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taba5.Artvis.dto.Artwork.ArtworkDto;
import taba5.Artvis.service.ArtworkService;
import taba5.Artvis.util.SecurityUtil;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/artwork")
public class ArtworkController {
    private final ArtworkService artworkService;

    @PostMapping("/save")
    public void saveArtwork(@RequestBody ArtworkDto artworkDto){
        artworkService.saveArtwork(artworkDto);
    }
    @GetMapping("/{id}")
    public ArtworkDto getArtwork(@PathVariable Long id){
        return artworkService.getArtwork(id);
    }
    @GetMapping("/liked")
    public List<ArtworkDto> likedArtwork(){
        return artworkService.getLikedArtwork(SecurityUtil.getCurrentMemberId());
    }
}

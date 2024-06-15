package taba5.Artvis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taba5.Artvis.Exception.ErrorResponse;
import taba5.Artvis.Exception.RestApiException;
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
    public ResponseEntity<?> getArtist(@PathVariable(name = "id") Long id){
        try{
            return ResponseEntity.ok(artistService.getArtist(id));
        }catch (RestApiException e){
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }
    }
}

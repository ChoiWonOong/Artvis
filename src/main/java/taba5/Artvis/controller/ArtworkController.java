package taba5.Artvis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taba5.Artvis.Exception.ErrorResponse;
import taba5.Artvis.Exception.RestApiException;
import taba5.Artvis.dto.Artwork.ArtworkDto;
import taba5.Artvis.service.ArtworkService;
import taba5.Artvis.util.SecurityUtil;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/artwork")
public class ArtworkController {
    private final ArtworkService artworkService;
    // 작품 저장
    @PostMapping("/save")
    public void saveArtwork(@RequestBody ArtworkDto artworkDto){
        artworkService.saveArtwork(artworkDto);
    }
    // id로 작품 불러오기
    @GetMapping("/{id}")
    public ResponseEntity<?> getArtwork(@PathVariable(name = "id") Long id){
        try{
            return ResponseEntity.ok(artworkService.getArtwork(id));
        }catch (RestApiException e){
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }catch (RuntimeException e){
            return ErrorResponse.toResponseEntity(e, "BAD_REQUEST");
        }
    }
    // 좋아요한 작품 불러오기
    @GetMapping("/liked")
    public ResponseEntity<?> likedArtwork(){
        try{
            return ResponseEntity.ok(artworkService.getLikedArtwork(SecurityUtil.getCurrentMemberId()));
        }catch (RestApiException e){
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }catch (RuntimeException e){
            return ErrorResponse.toResponseEntity(e, "BAD_REQUEST");
        }
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateArtwork(@PathVariable Long id, @RequestBody ArtworkDto artworkDto){
        try{
            return ResponseEntity.ok(artworkService.updateArtwork(id, artworkDto));
        }catch (RestApiException e){
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }catch (RuntimeException e){
            return ErrorResponse.toResponseEntity(e, "BAD_REQUEST");
        }
    }
}

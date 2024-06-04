package taba5.Artvis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import taba5.Artvis.dto.Exhibition.ExhibitionRequestDto;
import taba5.Artvis.dto.Image.ImageUploadDto;
import taba5.Artvis.service.ImageService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/save")
    public ResponseEntity<Long> saveImage(@RequestPart(value = "image")MultipartFile image, @RequestPart(value = "request") ExhibitionRequestDto exhibitionRequestDto) {
        return ResponseEntity.ok(imageService.saveImage(image, exhibitionRequestDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Resource> loadImage(@PathVariable Long id) throws IOException {
        return imageService.loadImage(id);
    }
}

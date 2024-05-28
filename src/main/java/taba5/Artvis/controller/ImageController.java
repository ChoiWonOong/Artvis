package taba5.Artvis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taba5.Artvis.dto.Image.ImageUploadDto;
import taba5.Artvis.service.ImageService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/save")
    public ResponseEntity<Long> saveImage(@ModelAttribute ImageUploadDto imageUploadDto) {
        return ResponseEntity.ok(imageService.saveImage(imageUploadDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Resource> loadImage(@PathVariable Long id) throws IOException {
        return imageService.loadImage(id);
    }
}

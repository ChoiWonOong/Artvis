package taba5.Artvis.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import taba5.Artvis.Exception.ErrorCode;
import taba5.Artvis.Exception.RestApiException;
import taba5.Artvis.domain.Image;
import taba5.Artvis.dto.Image.ImageUploadDto;
import taba5.Artvis.repository.ImageRepository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    private final String uploadFolder = "C:\\Artvis\\Image\\";
    @Transactional
    public Long saveImage(ImageUploadDto imageUploadDTO) {

        Image image = null;
        if (imageUploadDTO.getFile() != null && !imageUploadDTO.getFile().isEmpty()) {
            MultipartFile file = imageUploadDTO.getFile();
            UUID uuid = UUID.randomUUID();
            String imageFileName = uuid + "_" + file.getOriginalFilename();

            File destinationFile = new File(uploadFolder + imageFileName);

            try {
                file.transferTo(destinationFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            image = Image.builder()
                    .url(imageFileName)
                    .build();

            image = imageRepository.save(image);
        }
        assert image != null;
        Image result = imageRepository.findByUrl(image.getUrl()).orElseThrow(RuntimeException::new);
        return image.getId();
    }
    public ResponseEntity<Resource> loadImage(Long id) throws IOException {
        Image image = imageRepository.findById(id)
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        Path path = Paths.get(uploadFolder + "\\" + image.getUrl());
        String contentType = Files.probeContentType(path);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(
                ContentDisposition.builder("attachment")
                        .filename(image.getUrl(), StandardCharsets.UTF_8)
                        .build());
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);

        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}

package taba5.Artvis.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import taba5.Artvis.Exception.ErrorCode;
import taba5.Artvis.Exception.RestApiException;
import taba5.Artvis.domain.Image;
import taba5.Artvis.dto.Image.ImageUploadDto;
import taba5.Artvis.repository.ImageRepository;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    @Transactional
    public Long saveImage(ImageUploadDto imageUploadDTO) {
        Image image = null;
        if (imageUploadDTO.getFile() != null && !imageUploadDTO.getFile().isEmpty()) {
            MultipartFile file = imageUploadDTO.getFile();
            UUID uuid = UUID.randomUUID();
            String imageFileName = uuid + "_" + file.getOriginalFilename();

            String uploadFolder = "C:\\Artvis\\Image\\";
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
    public Resource loadImage(Long id) throws MalformedURLException {
        Image image = imageRepository.findById(id).orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        return new UrlResource("file:C:\\Artvis\\Image\\" + image.getUrl());
    }
}

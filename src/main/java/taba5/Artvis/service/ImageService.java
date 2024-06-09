package taba5.Artvis.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import taba5.Artvis.domain.Detail;
import taba5.Artvis.domain.Exhibition.Exhibition;
import taba5.Artvis.domain.Exhibition.ExhibitionTag;
import taba5.Artvis.domain.Exhibition.Tag;
import taba5.Artvis.domain.Image;
import taba5.Artvis.dto.DetailDto;
import taba5.Artvis.dto.Exhibition.ExhibitionRequestDto;
import taba5.Artvis.dto.Image.ImageUploadDto;
import taba5.Artvis.repository.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    private final DetailRepository detailRepository;
    private final ExhibitionRepository exhibitionRepository;
    private final TagRepository tagRepository;
    private final ExhibitionTagRepository exhibitionTagRepository;


    private final String uploadFolder = "C:\\Artvis\\Image\\";
    @Transactional
    public Long saveImage(MultipartFile file, ExhibitionRequestDto exhibitionRequestDto) {
        // Create Exhibition
        log.info("detailList : " + exhibitionRequestDto.getDetail());
        Exhibition exhibition = new Exhibition(
                exhibitionRequestDto.getTitle(),
                exhibitionRequestDto.getLocation(),
                exhibitionRequestDto.getStartDate(),
                exhibitionRequestDto.getEndDate(),
                exhibitionRequestDto.getDetail(),
                exhibitionRequestDto.getImageUrl()
                );
        exhibitionRepository.save(exhibition);
        for(String tagName : exhibitionRequestDto.getExhibitionTagList()){
            Tag tag = tagRepository.findByTagName(tagName)
                    .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
            createExhibitionTag(exhibition, tag);
        }
        Image image = null;
        if (file != null && !file.isEmpty()) {
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
    public void createExhibitionTag(Exhibition exhibition, Tag tag){
        ExhibitionTag exhibitionTag = ExhibitionTag.builder()
                .exhibition(exhibition)
                .tag(tag)
                .build();
        exhibitionTagRepository.save(exhibitionTag);
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

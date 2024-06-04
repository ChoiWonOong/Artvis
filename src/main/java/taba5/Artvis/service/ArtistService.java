package taba5.Artvis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import taba5.Artvis.Exception.ErrorCode;
import taba5.Artvis.Exception.RestApiException;
import taba5.Artvis.domain.Art.Artist;
import taba5.Artvis.dto.Artwork.ArtistDto;
import taba5.Artvis.repository.ArtistRepository;

@Service
@RequiredArgsConstructor
public class ArtistService {
    private final ArtistRepository artistRepository;

    public void save(ArtistDto dto){
        artistRepository.save(dto.toEntity());
    }
    public ArtistDto getArtist(Long id) {
        return artistRepository.findById(id).map(Artist::toDto)
                .orElseThrow(() -> new RestApiException(ErrorCode.NOT_EXIST_ERROR));
    }
}

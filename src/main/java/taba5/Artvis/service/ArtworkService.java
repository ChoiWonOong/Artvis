package taba5.Artvis.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import taba5.Artvis.Exception.ErrorCode;
import taba5.Artvis.Exception.RestApiException;
import taba5.Artvis.domain.Art.Artist;
import taba5.Artvis.domain.Art.Artwork;
import taba5.Artvis.domain.Detail;
import taba5.Artvis.domain.Exhibition.Exhibition;
import taba5.Artvis.domain.Like.ArtworkLike;
import taba5.Artvis.dto.Artwork.ArtworkDto;
import taba5.Artvis.repository.ArtistRepository;
import taba5.Artvis.repository.ExhibitionRepository;
import taba5.Artvis.repository.LikeRepository.ArtworkLikeRepository;
import taba5.Artvis.repository.ArtworkRepository;
import taba5.Artvis.repository.DetailRepository;
import taba5.Artvis.util.SecurityUtil;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class ArtworkService {
    private final ArtworkRepository artworkRepository;
    private final DetailRepository detailRepository;
    private final ArtistRepository artistRepository;
    private final ArtworkLikeRepository artworkLikeRepository;
    private final ExhibitionRepository exhibitionRepository;

    @Transactional
    public void saveArtwork(ArtworkDto artworkDto){
        /*
        List<Detail> details = detailRepository.saveAll(Detail.toEntityList(artworkDto.getDetail()));
        Artist artist = artistRepository.findByName(artworkDto.getArtistName())
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        */
        Artwork artwork = new Artwork(
                artworkDto.getTitle(),
                artworkDto.getArtistName(),
                artworkDto.getDetail(),
                artworkDto.getImageUrl()
        );
        log.info("artworkName: {}", artwork.getArtist());
        artworkRepository.save(artwork);
    }
    public ArtworkDto getArtwork(Long artworkId){
        Artwork artwork = artworkRepository.findById(artworkId)
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        return getArtworkResponseDto(artwork);
    }
    public Artwork findArtwork(Long artworkId){
        return artworkRepository.findById(artworkId)
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
    }
    public ArtworkDto getArtworkResponseDto(Artwork artwork){
        return artwork.toDto();
    }
    public List<ArtworkDto> getLikedArtwork(Long memberId){
        List<ArtworkLike> artworkLikeList = artworkLikeRepository.findByMember_Id(memberId);
        return artworkLikeList.stream()
                .map(artworkLike -> getArtworkResponseDto(artworkLike.getArtwork())).toList();
    }

    public ArtworkDto updateArtwork(Long id, ArtworkDto artworkDto) {
        Artwork artwork = artworkRepository.findById(id)
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        artwork = new Artwork(
                artworkDto.getTitle(),
                artworkDto.getArtistName(),
                artworkDto.getDetail(),
                artworkDto.getImageUrl()
        );
        artwork = artworkRepository.save(artwork);
        return artwork.toDto();
    }
}

package taba5.Artvis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import taba5.Artvis.Exception.ErrorCode;
import taba5.Artvis.Exception.RestApiException;
import taba5.Artvis.domain.Art.Artist;
import taba5.Artvis.domain.Art.Artwork;
import taba5.Artvis.domain.Detail;
import taba5.Artvis.domain.Like.ArtworkLike;
import taba5.Artvis.dto.Artwork.ArtworkDto;
import taba5.Artvis.repository.ArtistRepository;
import taba5.Artvis.repository.LikeRepository.ArtworkLikeRepository;
import taba5.Artvis.repository.ArtworkRepository;
import taba5.Artvis.repository.DetailRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtworkService {
    private final ArtworkRepository artworkRepository;
    private final DetailRepository detailRepository;
    private final ArtistRepository artistRepository;
    private final ArtworkLikeRepository artworkLikeRepository;

    public void saveArtwork(ArtworkDto artworkDto){
        List<Detail> detailList = Detail.toEntityList(artworkDto.getDetailList());
        detailRepository.saveAll(detailList);
        Artist artist = artistRepository.findByName(artworkDto.getArtist())
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        Artwork artwork = new Artwork(
                artworkDto.getTitle(),
                artist,
                detailList
        );
        artworkRepository.save(artwork);
    }
    public ArtworkDto getArtwork(Long id){
        Artwork artwork = artworkRepository.findById(id)
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        return getArtworkResponseDto(artwork);
    }
    public ArtworkDto getArtworkResponseDto(Artwork artwork){
        return ArtworkDto.builder()
                .artwork_id(artwork.getId())
                .title(artwork.getTitle())
                .artist(artwork.getArtist().getName())
                .detailList(artwork.getDetailList().stream()
                        .map(Detail::toDto).toList())
                .build();
    }
    public List<ArtworkDto> getLikedArtwork(Long memberId){
        List<ArtworkLike> artworkLikeList = artworkLikeRepository.findByMember_Id(memberId);
        return artworkLikeList.stream()
                .map(artworkLike -> getArtworkResponseDto(artworkLike.getArtwork())).toList();
    }
}

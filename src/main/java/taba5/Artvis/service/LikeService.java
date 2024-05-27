package taba5.Artvis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import taba5.Artvis.Exception.ErrorCode;
import taba5.Artvis.Exception.RestApiException;
import taba5.Artvis.domain.Art.Artwork;
import taba5.Artvis.domain.Exhibition.Exhibition;
import taba5.Artvis.domain.Like.ArtworkLike;
import taba5.Artvis.domain.Like.ExhibitionLike;
import taba5.Artvis.domain.Member;
import taba5.Artvis.dto.Like.ArtworkLikeDto;
import taba5.Artvis.dto.Like.ExhibitionLikeDto;
import taba5.Artvis.repository.ArtworkLikeRepository;
import taba5.Artvis.repository.ArtworkRepository;
import taba5.Artvis.repository.ExhibitionRepository;
import taba5.Artvis.repository.LikeRepository.ExhibitionLikeRepository;
import taba5.Artvis.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final ExhibitionLikeRepository exhibitionLikeRepository;
    private final MemberRepository memberRepository;
    private final ExhibitionRepository exhibitionRepository;
    private final ArtworkRepository artworkRepository;
    private final ArtworkLikeRepository artworkLikeRepository;
    public ArtworkLikeDto saveArtworkLike(ArtworkLikeDto artworkLikeDto){
        Member member = findMember(artworkLikeDto.getMemberId());
        Artwork artwork = findArtwork(artworkLikeDto.getArtworkId());
        ArtworkLike artworkLike = new ArtworkLike(member, artwork);
        artworkLikeRepository.save(artworkLike);
        return artworkLike.toDto();
    }
    public ExhibitionLikeDto saveExhibitionLike(ExhibitionLikeDto exhibitionLikeDto) {
        Member member = findMember(exhibitionLikeDto.getMemberId());
        Exhibition exhibition = findExhibition(exhibitionLikeDto.getExhibitionId());
        ExhibitionLike exhibitionLike = new ExhibitionLike(member, exhibition);
        exhibitionLikeRepository.save(exhibitionLike);
        return exhibitionLike.toDto();
    }
    public List<ExhibitionLikeDto> getExhibitionLikes(Long memberId){
        Member member = findMember(memberId);
        List<ExhibitionLike> exhibitionLikes = exhibitionLikeRepository.findByMember(member);
        return exhibitionLikes.stream().map(ExhibitionLike::toDto).toList();
    }
    public Member findMember(Long memberId){
        return memberRepository.findById(memberId)
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
    }
    public Exhibition findExhibition(Long exhibitionId){
        return exhibitionRepository.findById(exhibitionId)
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
    }
    public Artwork findArtwork(Long artworkId){
        return artworkRepository.findById(artworkId)
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
    }

}

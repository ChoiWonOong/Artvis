package taba5.Artvis.domain.Exhibition;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import taba5.Artvis.domain.*;
import taba5.Artvis.domain.Art.Artwork;
import taba5.Artvis.domain.Review.Review;
import taba5.Artvis.dto.Exhibition.ExhibitionHistoryDto;
import taba5.Artvis.dto.Exhibition.ExhibitionResponseDto;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Exhibition extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String location;
    private String startDate;
    private String endDate;

    @Setter
    @ManyToOne
    @JoinColumn(name = "gallery_id")
    private Gallery gallery;

    /*@OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "exhibition_id")
    private List<Detail> detailList = new ArrayList<>();*/
    private String detail;

    /*@Setter
    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;*/

    private String imageUrl;

    private boolean isDummy = false;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "history")
    private List<Exhibition> historyList = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "recommend")
    private List<Exhibition> recommendList;

    @OneToMany
    @JoinColumn(name = "review_id")
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "artwork_id")
    private List<Artwork> artworkList = new ArrayList<>();

    public Exhibition(String title, String location, String startDate, String endDate, /*List<Detail> detailList*/ String detail, String imageUrl){
        this.title = title;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageUrl = imageUrl;
        /*this.detailList = detailList;*/
        this.detail = detail;
    }

    public ExhibitionResponseDto toResponseDto(){
        return ExhibitionResponseDto.builder()
                .title(title)
                .artworkDtos(artworkList.stream().map(Artwork::toDto).toList())
                .location(location)
                .startDate(startDate)
                .endDate(endDate)
                .detail(detail)
                .imageUrl(imageUrl)
                .build();
    }
    public ExhibitionHistoryDto toHistoryDto(){
        return ExhibitionHistoryDto.builder()
                .title(title)
                .galleryName(gallery.getName())
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
    public void addRecommend(Exhibition exhibition){
        recommendList.add(exhibition);
    }
    public void initializeRecommend(){
        recommendList = new ArrayList<>();
    }
    public void addReview(Review review){
        reviewList.add(review);
    }
    public void addArtwork(Artwork artwork){
        artworkList.add(artwork);
    }
    public void addHistory(Exhibition exhibition){
        historyList.add(exhibition);
    }
    public void setDummy(){
        isDummy = true;
    }
}
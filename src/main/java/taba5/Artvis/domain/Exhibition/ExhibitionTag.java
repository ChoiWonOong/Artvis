package taba5.Artvis.domain.Exhibition;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class ExhibitionTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exhibition_id")
    private Exhibition exhibition;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    @Getter
    private Tag tag;

    @Builder
    public ExhibitionTag(Exhibition exhibition, Tag tag){
        this.exhibition = exhibition;
        this.tag = tag;
    }
    public String getTagName(){
        return this.getTag().getTagName();
    }
}

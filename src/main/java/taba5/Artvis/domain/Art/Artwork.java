package taba5.Artvis.domain.Art;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import taba5.Artvis.domain.Detail;

import java.util.List;

@Entity
@NoArgsConstructor
public class Artwork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String artist;
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "details")
    private List<Detail> detailList;
}

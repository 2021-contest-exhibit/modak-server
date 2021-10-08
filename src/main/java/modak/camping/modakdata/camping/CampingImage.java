package modak.camping.modakdata.camping;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CampingImage {
    @Id @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    @JsonIgnore
    private Camping camping;

    private String serialnum;
    private String modifiedtime;
    private String imageUrl;


    public void assignCamping(Camping camping) {
        this.camping = camping;
    }

    public CampingImage(String serialnum, String modifiedtime, String imageUrl) {
        this.serialnum = serialnum;
        this.modifiedtime = modifiedtime;
        this.imageUrl = imageUrl;
    }
}

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
    @Id
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    @JsonIgnore
    private Camping camping;

    private String modifiedtime;

    public void assignCamping(Camping camping) {
        this.camping = camping;
    }

}

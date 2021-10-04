package modak.camping.modakdata.environment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import modak.camping.modakdata.camping.Camping;

import javax.persistence.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Environment {
    @Id @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    @JsonIgnore
    private Camping camping;

    public Environment(String name,Camping camping) {
        this.name = name;

        if(camping != null) {
            changeCamping(camping);
        }
    }

    private void changeCamping(Camping camping) {
        this.camping = camping;
        camping.getEnvironments().add(this);
    }
}

package modak.camping.modakdata.facility;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import modak.camping.modakdata.camping.Camping;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Facility {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "content_id")
  @JsonIgnore
  private Camping camping;

  public Facility(String name, Camping camping) {
    this.name = name;

    if(camping != null) {
      changeCamping(camping);
    }
  }

  private void changeCamping(Camping camping) {
    this.camping = camping;
    camping.getFacilities().add(this);
  }
}

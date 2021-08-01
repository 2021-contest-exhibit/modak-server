package modak.camping.opendata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    private Long contentId;
    private String serialnum;
    private String imageUrl;
    private String createdtime;
    private String modifiedtime;
}

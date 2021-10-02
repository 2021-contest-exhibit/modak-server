package modak.camping.opendata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseIntroDto {
    private Long contentId;
    private String intro;

}

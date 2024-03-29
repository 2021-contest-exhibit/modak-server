package modak.camping.modakdata.dto.condition;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CampingSearchCondition {
    private String environmentName;
    private String operationType;
    private String regionContains;
    private Long contentId;
    private String nameContains;
}

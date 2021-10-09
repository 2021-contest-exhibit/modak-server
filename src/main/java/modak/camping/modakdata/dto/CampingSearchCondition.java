package modak.camping.modakdata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CampingSearchCondition {
    private String environmentName;
    private String operationType;
    private String regionContains;
}

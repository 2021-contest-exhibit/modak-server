package modak.camping.modakdata.dto;

import lombok.Data;

@Data
public class CampingSearchCondition {
    private String regionContains;
    private String operationType;
    private String environmentName;
}

package modak.camping.modakdata.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FindCampingsRequestDto {
    List<String> operationTypeEqual;
    List<String> regionContains;
    List<String> environmentEqual;
}

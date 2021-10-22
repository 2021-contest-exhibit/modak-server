package modak.camping.modakdata.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FindCampingsRequestDto {
    List<String> operationTypeEqual;
    List<String> regionContains;
    List<String> environmentEqual;
    String nameContains;
}

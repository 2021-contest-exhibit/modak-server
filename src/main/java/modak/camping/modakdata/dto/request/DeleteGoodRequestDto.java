package modak.camping.modakdata.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class DeleteGoodRequestDto {
    private Long contentId;
    private String email;
}

package modak.camping.modakdata.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateGoodRequestDto {
    private Long contentId;
    private String email;
}

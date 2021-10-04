package modak.camping.modakdata.camping;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
public class Camping {
    @Id
    private String contentId; // contentId
    private String name; // facltNm
    private Long viewCount; // X
    private String addr; // addr1 + \t + addr2
    private String phoneNumber; // tel
    private String environment; // lct_cl (캠핑장 환경)
    private String type; // induty (캠핑장 타입)
    private String operationSeasons; // operPdCl (운영기간)
    private String operationDays; // operDeCl (운영일)
    private String reservationWay; // resveCl (예약방법)
    private String nearbyFacilitiesAvailable; // posblFcltyCl (주변이용가능시설)
    private String facilities; // sbrs_cl (캠핑장 시설정보)
    private String longitude; // mapx (경도)
    private String latitude; // mapy (위도)
    private String operationType; // faclt_div_nm (운영형태, 사업주체 구분)
}

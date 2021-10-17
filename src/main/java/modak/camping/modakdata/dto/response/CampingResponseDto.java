package modak.camping.modakdata.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import com.querydsl.core.types.dsl.ListPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import modak.camping.modakdata.camping.CampingImage;
import modak.camping.modakdata.camping.QCampingImage;
import modak.camping.modakdata.environment.Environment;
import modak.camping.modakdata.environment.QEnvironment;

import javax.persistence.Lob;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CampingResponseDto {
    private Long contentId; // contentId
    private String name; // facltNm
    private Long viewCount; // X
    private String addr; // addr1 + \t + addr2
    private String phoneNumber; // tel
    private String type; // induty (캠핑장 타입)
    private String operationSeasons; // operPdCl (운영기간)
    private String operationDays; // operDeCl (운영일)
    private String reservationWay; // resveCl (예약방법)
    private String nearbyFacilitiesAvailable; // posblFcltyCl (주변이용가능시설)
    private String facilities; // sbrs_cl (캠핑장 시설정보)
    private String longitude; // mapx (경도)
    private String latitude; // mapy (위도)
    private String operationType; // faclt_div_nm (운영형태, 사업주체 구분)
    private String shortDescription; // lineintro (한줄소개)
    private String longDescription; // intro (긴소개)
    private Long goodCount;
    private List<Environment> environments = new ArrayList<>(); // lct_cl (캠핑장 환경)
    private List<CampingImage> campingImages = new ArrayList<>(); // 이미지
}

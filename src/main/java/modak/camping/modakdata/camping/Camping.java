package modak.camping.modakdata.camping;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import modak.camping.modakdata.environment.Environment;
import modak.camping.modakdata.facility.Facility;
import modak.camping.modakdata.good.Good;

import javax.persistence.*;
import java.util.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
public class Camping {
    @Id
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
    private String longitude; // mapx (경도)
    private String latitude; // mapy (위도)
    private String operationType; // faclt_div_nm (운영형태, 사업주체 구분)
    @Lob
    private String shortDescription; // lineintro (한줄소개)
    @Lob
    private String longDescription; // intro (긴소개)
    private String thumbnailImageUrl; // first_image_url(대표이미지)

    @OneToMany(mappedBy = "camping")
    @JsonIgnore
    private Set<Good> goods = new HashSet<>();

    @OneToMany(mappedBy = "camping")
    private List<Environment> environments = new ArrayList<>(); // lct_cl (캠핑장 환경)

    @OneToMany(mappedBy = "camping", cascade = CascadeType.ALL)
    private List<CampingImage> campingImages = new ArrayList<>(); // 이미지

    @OneToMany(mappedBy = "camping")
    private List<Facility> facilities = new ArrayList<>(); // sbrs_cl (캠핑장 시설정보)

    public Camping(Long contentId, String name, Long viewCount, String addr, String phoneNumber, String type, String operationSeasons, String operationDays, String reservationWay, String nearbyFacilitiesAvailable, String longitude, String latitude, String operationType, String shortDescription, String longDescription, String thumbnailImageUrl, List<CampingImage> campingImages) {
        this.contentId = contentId;
        this.name = name;
        this.viewCount = viewCount;
        this.addr = addr;
        this.phoneNumber = phoneNumber;
        this.type = type;
        this.operationSeasons = operationSeasons;
        this.operationDays = operationDays;
        this.reservationWay = reservationWay;
        this.nearbyFacilitiesAvailable = nearbyFacilitiesAvailable;
        this.longitude = longitude;
        this.latitude = latitude;
        this.operationType = operationType;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.thumbnailImageUrl = thumbnailImageUrl;
        campingImages.forEach(this::addCampingImage);
    }

    //==연관관계 메서드==//
    public void addCampingImage(CampingImage campingImage) {
        this.campingImages.add(campingImage);
        campingImage.assignCamping(this);
    }
}

package modak.camping.opendata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Base {
    @Id
    private int contentId;
    private String doNm;
    private String mangeDivNm;
    private String facltNm;
    private String caravAcmpnyAt;
    private String insrncAt;
    private String induty;
    private int siteMg3Vrticl;
    private int siteBottomCl5;
    private int siteMg3Width;
    private int swrmCo;
    private int glampSiteCo;
    private int siteBottomCl3;
    private int siteBottomCl4;
    private int siteBottomCl1;
    private int siteBottomCl2;
    @Builder.Default
    private String tel = "";
    private int allar;
    private String manageSttus;
    private int sitedStnc;
    private int indvdlCaravSiteCo;
    private String operDeCl;
    private int siteMg3Co;
    private String brazierCl;
    private int manageNmpr;
    private int siteMg1Co;
    private int gnrlSiteCo;
    private int zipcode;
    private String exprnProgrmAt;
    private int extshrCo;
    private int caravSiteCo;
    private int autoSiteCo;
    private int frprvtSandCo;
    private int siteMg2Width;
    private int siteMg1Width;
    private int fireSensorCo;
    private String operPdCl;
    private Double mapY;
    private Double mapX;
    private int wtrplCo;
    private String modifiedtime;
    private String sigunguNm;
    private int siteMg1Vrticl;
    private int frprvtWrppCo;
    private String prmisnDe;
    private String trlerAcmpnyAt;
    private String addr1;
    private String clturEventAt;
    private String createdtime;
    private int siteMg2Co;
    private int toiletCo;
    private String animalCmgCl;
    private int siteMg2Vrticl;
    @Builder.Default
    private String bizrno = "";
}

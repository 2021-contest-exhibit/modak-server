package modak.camping.opendata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Base implements Opendata{
    @Id
    private Long contentId;
    private String doNm;
    @Builder.Default
    private String mangeDivNm = "";
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
    @Builder.Default
    private String operDeCl = "";
    private int siteMg3Co;
    @Builder.Default
    private String brazierCl = "";
    private int manageNmpr;
    private int siteMg1Co;
    private int gnrlSiteCo;
    @Builder.Default
    @Lob
    private String zipcode = "";
    private String exprnProgrmAt;
    private int extshrCo;
    private int caravSiteCo;
    private int autoSiteCo;
    private int frprvtSandCo;
    private int siteMg2Width;
    private int siteMg1Width;
    private int fireSensorCo;
    @Builder.Default
    private String operPdCl = "";
    @Builder.Default
    private String mapY = "";
    @Builder.Default
    private String mapX = "";
    private int wtrplCo;
    private String modifiedtime;
    @Builder.Default
    private String sigunguNm = "";
    private int siteMg1Vrticl;
    private int frprvtWrppCo;
    @Builder.Default
    private String prmisnDe = "";
    private String trlerAcmpnyAt;
    private String addr1;
    private String clturEventAt;
    private String createdtime;
    private int siteMg2Co;
    private int toiletCo;
    @Builder.Default
    private String animalCmgCl = "";
    private int siteMg2Vrticl;
    @Builder.Default
    private String bizrno = "";
    @Builder.Default
    private String posblFcltyCl = "";
    @Builder.Default
    private String tourEraCl = "";
    @Builder.Default
    private String sbrsEtc = "";
    @Builder.Default
    private String posblFcltyEtc = "";
    @Builder.Default
    private String sbrsCl = "";
    @Builder.Default
    private String eqpmnLendCl = "";
    @Builder.Default
    private String exprnProgrm = "";
    @Builder.Default
    private String clturEvent = "";
    @Builder.Default
    private String themaEnvrnCl = "";
    @Builder.Default
    private String firstImageUrl = "";
    @Builder.Default
    private String lctCl = "";
    @Builder.Default
    @Lob
    private String resveUrl = "";
    @Builder.Default
    private String facltDivNm = "";
    @Builder.Default
    private String trsagntNo = "";
    @Builder.Default
    private String lineIntro = "";
    @Builder.Default
    private String resveCl = "";
    @Builder.Default
    private String homepage = "";
    @Builder.Default
    @Lob
    private String intro = "";
    @Builder.Default
    private String mgcDiv = "";
    @Builder.Default
    @Lob
    private String featureNm = "";
    @Builder.Default
    @Lob
    private String direction = "";
    @Builder.Default
    private String addr2 = "";
    @Builder.Default
    private String hvofEnddle = "";
    @Builder.Default
    private String hvofBgnde = "";
    @Builder.Default
    @Lob
    private String tooltip = "";
    @Builder.Default
    private String glampInnerFclty = "";
    @Builder.Default
    private String caravInnerFclty = "";

    @Lob
    @Builder.Default
    private String embedding = "";

    public void updateEmbedding(String embedding) {
        this.embedding = embedding;
    }
}

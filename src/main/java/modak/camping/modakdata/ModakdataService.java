package modak.camping.modakdata;

import modak.camping.opendata.Base;

import java.util.List;

public interface ModakdataService {
    String saveCamping(Camping camping) throws Exception;
    String saveCampings(List<Base> baseList);
}

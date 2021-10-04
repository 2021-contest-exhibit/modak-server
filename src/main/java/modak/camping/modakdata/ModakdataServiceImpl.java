package modak.camping.modakdata;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import lombok.RequiredArgsConstructor;
import modak.camping.opendata.Base;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModakdataServiceImpl implements ModakdataService {
    private final CampingRepository campingRepository;
    private final CampingFirestoreRepository campingFirestoreRepository;

    @Override
    public String saveCamping(Camping camping) throws Exception {
        campingFirestoreRepository.save(camping);
        campingRepository.save(camping);

        return "ok";
    }

    @Override
    public String saveCampings(List<Base> baseList) {
        List<Camping> campingList = baseList.stream()
                .map(base -> new Camping(
                        Long.toString(base.getContentId()),
                        base.getFacltNm(),
                        0L,
                        base.getAddr1() + "\t" + base.getAddr2(),
                        base.getTel(),
                        base.getLctCl(),
                        base.getInduty(),
                        base.getOperPdCl(),
                        base.getOperDeCl(),
                        base.getResveCl(),
                        base.getPosblFcltyCl(),
                        base.getSbrsCl(),
                        base.getMapX(),
                        base.getMapY()
                ))
                .collect(Collectors.toList());

        campingList.stream().forEach(camping -> {
            try {
                saveCamping(camping);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return "ok";
    }
}

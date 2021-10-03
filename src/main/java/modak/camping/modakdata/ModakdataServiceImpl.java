package modak.camping.modakdata;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import modak.camping.opendata.Base;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModakdataServiceImpl implements ModakdataService {
    private static final String campingName = "camping";

    @Override
    public String saveCamping(Camping camping) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> apiFuture = firestore
                .collection(campingName)
                .document(camping.getContentId())
                .set(camping);
        return apiFuture.get().getUpdateTime().toString();
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

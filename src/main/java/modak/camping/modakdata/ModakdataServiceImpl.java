package modak.camping.modakdata;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

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
}

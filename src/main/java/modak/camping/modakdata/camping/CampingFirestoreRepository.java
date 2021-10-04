package modak.camping.modakdata.camping;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CampingFirestoreRepository {
    private static final String campingName = "camping";

    public String save(Camping camping) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> apiFuture = firestore
                .collection(campingName)
                .document(Long.toString(camping.getContentId()))
                .set(camping);


        return apiFuture.get().getUpdateTime().toString();

    }
}

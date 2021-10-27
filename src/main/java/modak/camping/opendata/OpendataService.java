package modak.camping.opendata;

import java.util.List;
import modak.camping.opendata.dto.request.PatchBaseRequestDto;

public interface OpendataService {
    void getOpenDataJson(String tableName, int rangePageNo, int numOfRows, Long contentId) throws Exception;
    List findAll(String tableName);
    void save(String tableName, Opendata opendata);
    String updateBaseEmbedding(PatchBaseRequestDto patchBaseRequestDto);
}

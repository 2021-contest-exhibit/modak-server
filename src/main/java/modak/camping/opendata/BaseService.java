package modak.camping.opendata;

import java.util.List;

public interface BaseService {
    void getOpenDataJson(int rangePageNo, int numOfRows) throws Exception;
    List findAll(String tableName);
}

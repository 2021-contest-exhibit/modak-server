package modak.camping.opendata;

import java.util.List;

public interface OpendataService {
    void getOpenDataJson(int rangePageNo, int numOfRows) throws Exception;
    List findAll(String tableName);
}

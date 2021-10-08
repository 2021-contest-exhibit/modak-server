package modak.camping.opendata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import modak.camping.util.HashMapConverter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.List;
import java.util.Map;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image implements Opendata{
    @Id
    private Long contentId;
    @Lob
    @Convert(converter = HashMapConverter.class)
    private Map<String, Object> jsonData;

    public List<Map<String,Object>> getJsonDataToListMap() {
        return (List<Map<String, Object>>) jsonData.get("images");
    }
}

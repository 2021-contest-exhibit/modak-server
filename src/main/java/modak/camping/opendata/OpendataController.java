package modak.camping.opendata;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/opendata")
@Data
public class OpendataController {
    private final OpendataService opendataService;

    @PostMapping(value = "/base")
    public ResponseEntity PostBase(@RequestParam(required = false, defaultValue = "3") int rangePageNo,
                                   @RequestParam(required = false, defaultValue = "1000") int numOfRows) throws Exception {
        opendataService.getOpenDataJson(rangePageNo, numOfRows);
        return  ResponseEntity.ok("ok");
    }

    @GetMapping(value = "/base")
    public List getBase() {
        return opendataService.findAll("base");
    }

}

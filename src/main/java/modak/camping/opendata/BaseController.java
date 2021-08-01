package modak.camping.opendata;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/base")
@Data
public class BaseController {
    private final BaseService baseService;

    @PostMapping(value = "")
    public ResponseEntity PostBase(@RequestParam(required = false, defaultValue = "3") int rangePageNo,
                                   @RequestParam(required = false, defaultValue = "1000") int numOfRows) throws Exception {
        baseService.getOpenDataJson(rangePageNo, numOfRows);
        return  ResponseEntity.ok("ok");
    }

    @GetMapping(value = "/base")
    public List getBase() {
        return baseService.findAll("base");
    }

}

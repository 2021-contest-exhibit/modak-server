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
    public ResponseEntity postBase(@RequestParam(required = false, defaultValue = "3") int rangePageNo,
                                   @RequestParam(required = false, defaultValue = "1000") int numOfRows) throws Exception {
        opendataService.getOpenDataJson("base",rangePageNo, numOfRows, -1);
        return  ResponseEntity.ok("ok");
    }

    @GetMapping(value = "/base")
    public List getBase() {
        return opendataService.findAll("base");
    }

    @PostMapping(value = "/image")
    public ResponseEntity postImage(@RequestParam(required = false, defaultValue = "-1") Integer contentId) throws Exception {
        opendataService.getOpenDataJson("image",-1,-1,contentId);
        return ResponseEntity.ok("ok");
    }

}

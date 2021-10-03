package modak.camping.modakdata;

import lombok.Data;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/modak")
@Data
public class ModakdataController {

    private final ModakdataService modakdataService;

    @PostMapping("/camping")
    public String saveCamping(@RequestBody Camping camping) throws Exception {
        return modakdataService.saveCamping(camping);
    }

}

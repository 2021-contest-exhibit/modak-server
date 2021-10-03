package modak.camping.modakdata;

import lombok.Data;
import modak.camping.opendata.Base;
import modak.camping.opendata.OpendataService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/modak")
@Data
public class ModakdataController {

    private final OpendataService opendataService;
    private final ModakdataService modakdataService;

    @PostMapping("/camping")
    public String saveCamping(@RequestBody Camping camping) throws Exception {
        return modakdataService.saveCamping(camping);
    }

    @PostMapping("/campings")
    public String saveCampings() throws Exception {
        List<Base> baseList = opendataService.findAll("base");


        return modakdataService.saveCampings(baseList);
    }

}

package modak.camping.modakdata;

import lombok.Data;
import modak.camping.modakdata.camping.Camping;
import modak.camping.modakdata.dto.CampingSearchCondition;
import modak.camping.opendata.Base;
import modak.camping.opendata.OpendataService;
import modak.camping.response.ResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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

    @GetMapping("/campings/regions")
    public ResponseDto findCampingRegion() {
        Set<String> campingAddrSet = modakdataService.findCampingAddr();

        return new ResponseDto(campingAddrSet);
    }

    @GetMapping("/campings/operation-types")
    public ResponseDto findCampingOperationType() {
        Set<String> campingOperationTypeSet = modakdataService.findCampingOperationType();
        return new ResponseDto(campingOperationTypeSet);
    }

    @GetMapping("/campings/environments")
    public ResponseDto findCampingEnvironment() {
        Set<String> campingEnvironmentSet = modakdataService.findEnvironmentName();
        return new ResponseDto(campingEnvironmentSet);
    }

    @GetMapping("/campings")
    public Page findCampings(@RequestBody CampingSearchCondition condition, Pageable pageable) {
        return modakdataService.findAllCampingPage(condition, pageable);
    }


}

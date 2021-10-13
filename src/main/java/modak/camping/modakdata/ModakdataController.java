package modak.camping.modakdata;

import lombok.Data;
import modak.camping.modakdata.camping.Camping;
import modak.camping.modakdata.dto.CampingSearchCondition;
import modak.camping.modakdata.good.GoodService;
import modak.camping.modakdata.request.CreateGoodRequestDto;
import modak.camping.modakdata.request.CreateUserRequestDto;
import modak.camping.modakdata.user.UserService;
import modak.camping.opendata.Base;
import modak.camping.opendata.Image;
import modak.camping.opendata.OpendataService;
import modak.camping.response.ResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/modak")
@Data
public class ModakdataController {

    private final OpendataService opendataService;
    private final ModakdataService modakdataService;
    private final UserService userService;
    private final GoodService goodService;

    @PostMapping("/camping")
    public String saveCamping(@RequestBody Camping camping) throws Exception {
        return modakdataService.saveCamping(camping);
    }

    @PostMapping("/campings")
    public String saveCampings() throws Exception {
        List<Base> baseList = opendataService.findAll("base");
        Map<Long, List<Image>> imageMap = ( (List<Image>) opendataService.findAll("image") ).stream()
                .collect(Collectors.groupingBy(Image::getContentId));
        return modakdataService.saveCampings(baseList, imageMap);
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
    public Page findCampings(String environmentName,
                             String operationType,
                             String regionContains,
                             @RequestParam(required = false, defaultValue = "-1") Long contentId,
                             Pageable pageable) {
        CampingSearchCondition campingSearchCondition = new CampingSearchCondition(environmentName, operationType, regionContains, contentId);
        return modakdataService.findAllCampingPage(campingSearchCondition, pageable);
    }

    @PostMapping("/user")
    public String saveUser(@RequestBody CreateUserRequestDto createUserRequestDto) {
        return userService.save(createUserRequestDto);
    }

    @PostMapping("/good")
    public String saveGood(@RequestBody CreateGoodRequestDto createGoodRequestDto) {
        return goodService.save(createGoodRequestDto);
    }


}

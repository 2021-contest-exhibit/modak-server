package modak.camping.modakdata;

import io.swagger.annotations.ApiOperation;
import lombok.Data;
import modak.camping.modakdata.camping.Camping;
import modak.camping.modakdata.dto.condition.CampingSearchCondition;
import modak.camping.modakdata.dto.mapper.CampingDtoMapper;
import modak.camping.modakdata.dto.request.DeleteGoodRequestDto;
import modak.camping.modakdata.dto.request.FindCampingsRequestDto;
import modak.camping.modakdata.good.GoodService;
import modak.camping.modakdata.dto.request.CreateGoodRequestDto;
import modak.camping.modakdata.dto.request.CreateUserRequestDto;
import modak.camping.modakdata.user.UserService;
import modak.camping.opendata.Base;
import modak.camping.opendata.Image;
import modak.camping.opendata.OpendataService;
import modak.camping.response.ResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;


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
    private final CampingDtoMapper campingDtoMapper;

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
                                                      String nameContains,
                                                      String email,
                                                      Pageable pageable) {
        CampingSearchCondition campingSearchCondition = new CampingSearchCondition(environmentName, operationType, regionContains, contentId, nameContains);

        return campingDtoMapper.CampingToResponse(modakdataService.findAllCampingPage(campingSearchCondition, pageable), email);
    }

    @PostMapping("find-campings")
    public Page findCampingsByList(String email, Pageable pageable, @RequestBody FindCampingsRequestDto findCampingsRequestDto) {

        return campingDtoMapper.CampingToResponse(modakdataService.findAllCampingPage(findCampingsRequestDto, pageable), email);
    }

    @ApiOperation(value = "recommend campings by good", notes = "전체 유저 좋아요 기반 캠핑 추천")
    @GetMapping("/campings/recommendation/good")
    public Page findCampingsRecommendationToday(String email, Pageable pageable) {

        return campingDtoMapper.CampingToResponse(modakdataService.findAllCampingTodayPage(pageable), email);
    }

    @ApiOperation(value = "recommend campings by ai", notes = "하나의 유저 좋아요 ai 기반 캠핑 추천")
    @GetMapping("/campings/recommendation/ai")
    public Page findCampingsRecommendationAi(String email, Pageable pageable) {
        return campingDtoMapper.CampingToResponse(modakdataService.findAllCampingTodayPage(pageable), email);
    }


    @GetMapping("/user")
    public Page findUser(String email) {
        return userService.findPageByEmail(email);
    }

    @PostMapping("/user")
    public String saveUser(@RequestBody CreateUserRequestDto createUserRequestDto) {
        return userService.save(createUserRequestDto);
    }

    @PostMapping("/good")
    public String saveGood(@RequestBody CreateGoodRequestDto createGoodRequestDto) {
        return goodService.save(createGoodRequestDto);
    }

    @DeleteMapping("/good")
    public String deleteGood(@RequestBody DeleteGoodRequestDto deleteGoodRequestDto){
        return goodService.delete(deleteGoodRequestDto);
    }
}

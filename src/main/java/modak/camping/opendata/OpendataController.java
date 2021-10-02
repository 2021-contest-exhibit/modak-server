package modak.camping.opendata;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/opendata")
@Data
public class OpendataController {
    private final OpendataService opendataService;

    @PostMapping(value = "/base")
    public ResponseEntity postBase(@RequestParam(required = false, defaultValue = "3") int rangePageNo,
                                   @RequestParam(required = false, defaultValue = "1000") int numOfRows) throws Exception {
        opendataService.getOpenDataJson("base",rangePageNo, numOfRows, -1L);
        return  ResponseEntity.ok("ok");
    }

    @GetMapping(value = "/base")
    public List getBase() {
        return opendataService.findAll("base");
    }

    @GetMapping(value = "/base/intro")
    public List getBaseIntro() {
        List<Base> baseList = opendataService.findAll("base");

        List<BaseIntroDto> baseIntroDtoList = baseList.stream()
                .filter(base -> !base.getIntro().equals("") && base.getIntro().length() >= 10 )
                .map(base -> new BaseIntroDto(base.getContentId(), base.getIntro()))
                .collect(Collectors.toList());

        return baseIntroDtoList;
    }

    @PostMapping(value = "/image")
    public ResponseEntity postImage(@RequestParam(required = false, defaultValue = "-1") Long contentId) throws Exception {
        opendataService.getOpenDataJson("image",-1,-1,contentId);
        return ResponseEntity.ok("ok");
    }

}

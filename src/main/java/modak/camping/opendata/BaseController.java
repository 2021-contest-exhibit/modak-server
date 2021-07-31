package modak.camping.opendata;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/base")
@Data
public class BaseController {
    private final BaseRepository baseRepository;

    @PostMapping(value = "")
    public ResponseEntity PostBase() {

        

        return (ResponseEntity) ResponseEntity.ok();
    }

}
